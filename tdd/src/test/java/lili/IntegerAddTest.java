package lili;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author lili
 * @date 2020/1/4 1:01
 * @description
 */
public class IntegerAddTest {
    @Test
    public void testAdd() {
        Integer a = new Integer(1);
        Integer b = a + new Integer(5);
        assertEquals(1, a.intValue());
        assertEquals(6, b.intValue());
        assertTrue(new Integer(100).equals(new Integer(100)));
    }

}