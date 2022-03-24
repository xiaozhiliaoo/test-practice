package org.lili.mockito;

import java.util.AbstractList;

/**
 * @author lili
 * @date 2022/3/22 16:34
 */
public class MyList extends AbstractList<String> {

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void add(int index, String element) {
        // no-op
    }
}
