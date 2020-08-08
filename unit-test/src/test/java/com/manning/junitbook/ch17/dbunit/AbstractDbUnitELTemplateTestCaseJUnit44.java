/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.manning.junitbook.ch17.dbunit;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;

import static org.dbunit.Assertion.assertEquals;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings("deprecation")
@RunWith(AbstractDbUnitELTemplateTestCaseJUnit44.DataSetsTemplateRunner.class)
public abstract class AbstractDbUnitELTemplateTestCaseJUnit44 extends AbstractDbUnitTestCase {

    protected static long id;
    private static ELContextImpl context;

    public static IDataSet getReplacedDataSet(String name) throws Exception {
        InputStream inputStream = AbstractDbUnitTestCase.class.getResourceAsStream(name);
        assertNotNull("file " + name + " not found in classpath", inputStream);
        Reader reader = new InputStreamReader(inputStream);
        final FlatXmlDataSet dataSet = new ELAwareFlatXmlDataSet(reader);
        final ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
        replacementDataSet.addReplacementObject("[NULL]", null);
        return replacementDataSet;
    }

    protected static ELContextImpl getContext() {
        return context;
    }

    public static class DataSetsTemplateRunner extends JUnit4ClassRunner {

        public DataSetsTemplateRunner(Class<?> klass) throws InitializationError {
            super(klass);
        }

        @Override
        protected void invokeTestMethod(Method method, RunNotifier notifier) {
            context = new ELContextImpl();
            context.bind("id", id);
            setupDataSet(method);
            super.invokeTestMethod(method, notifier);
            context.bind("id", id); //
            assertDataSet(method);
        }

        private void setupDataSet(Method method) {
            DataSets dataSetAnnotation = method.getAnnotation(DataSets.class);
            if (dataSetAnnotation == null) {
                return;
            }
            String dataSetName = dataSetAnnotation.setUpDataSet();
            if (!dataSetName.equals("")) {
                try {
                    IDataSet dataSet = getReplacedDataSet(dataSetName);
                    DatabaseOperation.CLEAN_INSERT.execute(dbunitConnection, dataSet);
                } catch (Exception e) {
                    throw new RuntimeException("exception inserting dataset " + dataSetName, e);
                }
            }
        }

        private void assertDataSet(Method method) {
            DataSets dataSetAnnotation = method.getAnnotation(DataSets.class);
            if (dataSetAnnotation == null) {
                return;
            }
            String dataSetName = dataSetAnnotation.assertDataSet();
            if (!dataSetName.equals("")) {
                try {
                    IDataSet expectedDataSet = getReplacedDataSet(dataSetName);
                    IDataSet actualDataSet = dbunitConnection.createDataSet();
                    assertEquals(expectedDataSet, actualDataSet);
                } catch (Exception e) {
                    throw new RuntimeException("exception asserting dataset " + dataSetName, e);
                }
            }
        }
    }

    private static class ELAwareFlatXmlDataSet extends FlatXmlDataSet {

        public ELAwareFlatXmlDataSet(Reader reader) throws DataSetException, IOException {
            super(reader);
        }

        @Override
        public void row(Object[] values) throws DataSetException {
            final ELContextImpl context = AbstractDbUnitELTemplateTestCaseJUnit44.getContext();
            if (context != null) {
                ExpressionFactory factory = context.getFactory();
                int i = 0;
                for (Object value : values) {
                    String stringValue = "" + value;
                    Object newValue;
                    if (stringValue.startsWith("${") && stringValue.endsWith("}")) {
                        ValueExpression converted = factory.createValueExpression(context, stringValue, Object.class);
                        newValue = converted.getValue(context);
                    } else {
                        newValue = value;
                    }
                    values[i++] = newValue;
                }
            } else {
                throw new IllegalStateException("No context on thread");
            }
            super.row(values);
        }
    }

}
