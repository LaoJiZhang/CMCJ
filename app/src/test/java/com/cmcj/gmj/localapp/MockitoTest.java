package com.cmcj.gmj.localapp;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by guomaojian on 16/11/17.
 */

public class MockitoTest {

    @Test
    public void testMockito1() {
        //mock creation
        List mockedList = mock(List.class);
        System.out.println(mockedList.toString());

        //using mock object
        mockedList.add("one");
        mockedList.add("two");

        when(mockedList.get(0)).thenReturn("one 111");
        when(mockedList.get(1)).thenReturn("two 222");

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(9999));

        //verification
        verify(mockedList).add("one");
    }
}
