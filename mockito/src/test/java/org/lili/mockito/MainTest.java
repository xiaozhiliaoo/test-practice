package org.lili.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


/**
 * @author lili
 * @date 2020/11/25 23:38
 * @notes
 */
class MainTest {

    @Test
    public void testFinalClass() {
        String mock = mock(String.class);
        System.out.println(mock);
    }

    @Test
    public void testInterface() {
        List mock = mock(List.class);
        mock.add("lulul");
//        verify(mock).add("llili");
        System.out.println(mock.get(0));
//        System.out.println(mock);
    }

    @Test
    public void testArrayList() {
        ArrayList list = mock(ArrayList.class);
        given(list.get(1)).willReturn("11111");
        assertThat("11111").isEqualTo(list.get(1));
    }

    @Test
    public void testList() {
        List mock = mock(List.class);
        mock.add("one");
        mock.clear();
        verify(mock).add("one");
        verify(mock).add("two");
        verify(mock).clear();

    }

    @Test
    public void testLinkedList() {
        //stubbing
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(999));
    }

    @Test
    public void testVerify() {

        LinkedList mockedList = mock(LinkedList.class);
//        when(mockedList.get(anyInt())).thenReturn("element");
//        when(mockedList.contains(argThat(isValid()))).thenReturn(true);
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");
        verify(mockedList, never()).add("never happened");
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");


        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();

    }

    @Test
    public void testVerifyInOrder() {
        List singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");
        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder2 = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");
    }

    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }


}