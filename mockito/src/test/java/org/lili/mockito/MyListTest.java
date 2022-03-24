package org.lili.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class MyListTest {

    /**
     * 对比whenAddCalledVerified
     * If an argument matcher is used for at least one argument, all
     * arguments must be provided by matchers.
     */
    @Test
    void whenAddCalledVerified3() {
        MyList myList = mock(MyList.class);
        doNothing().when(myList).add(anyInt(), eq("111"));
        myList.add(1, "");

        verify(myList, times(1)).add(1, "");
        verify(myList, times(0)).add(0, "");
    }

    /**
     * doNothing() is Mockito's default behavior for void methods.
     */
    @Test
    void whenAddCalledVerified() {
        MyList myList = mock(MyList.class);
        doNothing().when(myList).add(isA(Integer.class), isA(String.class));
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
    }

    @Test
    void testDothing() {
        List list = new LinkedList();
        List spy = spy(list);

        //let's make clear() do nothing
        doNothing().when(spy).clear();

        spy.add("one");

        //clear() does nothing, so the list still contains "one"
        spy.clear();

        verify(spy, times(1)).add("one");

        assertTrue(spy.contains("one"));
    }

    @Test
    void whenAddCalledVerified2() {
        MyList myList = mock(MyList.class);
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
    }


    @Test
    void givenNull_AddThrows() {
        Assertions.assertThrows(Exception.class, () -> {
            MyList myList = mock(MyList.class);
            doThrow().when(myList).add(isA(Integer.class), isNull());
            myList.add(0, null);
        });

    }

    @Test
    void whenAddCalledValueCaptured() {
        MyList myList = mock(MyList.class);
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(myList).add(any(Integer.class), valueCapture.capture());
        myList.add(0, "captured");

        assertEquals("captured", valueCapture.getValue());
    }

    /**
     * answer类似于参数捕获的效果
     */
    @Test
    void whenAddCalledAnswered2() {
        MyList myList = mock(MyList.class);
        Map<Integer, Object> maps = new HashMap<>();
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            maps.put(0, arg0);
            maps.put(1, arg1);
            return null;
        }).when(myList).add(any(Integer.class), any(String.class));

        myList.add(3, "answer me");

        assertEquals((Integer) maps.get(0), 3);
        assertEquals((String) maps.get(1), "answer me");
    }

    @Test
    void whenAddCalledAnswered() {
        MyList myList = mock(MyList.class);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            assertEquals(3, arg0);
            assertEquals("answer me", arg1);
            return null;
        }).when(myList).add(any(Integer.class), any(String.class));

        myList.add(3, "answer me");
    }

}