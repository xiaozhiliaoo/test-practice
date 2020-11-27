package org.lili.objenesis;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

/**
 * @author lili
 * @date 2020/11/27 3:52
 * @see
 * @since
 */
class Person {
    private int age;
    private String name;
}

public class Main {
    public static void main(String[] args) {
        Objenesis objenesis = new ObjenesisStd();
        ObjectInstantiator thingyInstantiator = objenesis.getInstantiatorOf(Person.class);
        Person o =(Person)thingyInstantiator.newInstance();
        System.out.println(o);
    }
}
