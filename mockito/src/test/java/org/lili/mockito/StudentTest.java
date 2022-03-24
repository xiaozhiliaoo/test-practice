package org.lili.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;


class PersonPrinterAnswer implements Answer<Object> {

    private Person person;

    @Override
    public Person answer(InvocationOnMock invocationOnMock) {
        Object[] args = invocationOnMock.getArguments();
        person = (Person) args[0];
        return person;
    }

    public Person getPerson() {
        return person;
    }
}

/**
 * 测试中间变量
 */
class StudentTest {

    @Test
    void realStudent() {
        //mock一下PersonPrinter
        PersonPrinter personPrinter = spy(PersonPrinter.class);
        PersonPrinterAnswer personPrinterAnswer = new PersonPrinterAnswer();
        //使用我们定义的personPrinterAnswer
        doAnswer(personPrinterAnswer).when(personPrinter).printPerson(anyObject());
        Student student = new Student();
        //把我们mock的personPrinter注入student
        student.setPersonPrinter(personPrinter);
        //要测试的方法
        student.realStudent();
        //取出我们存在answer中的中间变量
        HighSchoolStudent highSchoolStudent = (HighSchoolStudent) personPrinterAnswer.getPerson();
        assertThat(highSchoolStudent.getName()).isEqualTo("highSchoolStudent");
    }
}