
package com.mycompany.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SinglyLinkedListTest {
    
    private final String data1 = "this is a data";
    private final String data2 = "this is another data";

    /**
     * Test of insertAtStart method, of class SinglyLinkedList.
     */
    @Test
    public void testInsertAtStart() {
        System.out.println("insertAtStart");
        
        SinglyLinkedList instance = new SinglyLinkedList();
        
        instance.insertAtStart(data1);
        instance.insertAtStart(data2);
        
        String result = instance.getHead().data;
        assertEquals(data2, result);
    }

    /**
     * Test of deleteAtStart method, of class SinglyLinkedList.
     */
    @Test
    public void testDeleteAtStart() {
        System.out.println("deleteAtStart");
        
        SinglyLinkedList instance = new SinglyLinkedList();
        
        instance.insertAtStart(data1);
        instance.insertAtStart(data2);
        
        Node result1 = instance.deleteAtStart();
        assertEquals(data2, result1.data);
        
        Node result2 = instance.deleteAtStart();
        assertEquals(data1, result2.data);
        
        Node result3 = instance.deleteAtStart();
        assertEquals(null, result3);
        
    }

    /**
     * Test of getHead method, of class SinglyLinkedList.
     */
    @Test
    public void testGetHead() {
        System.out.println("getHead");
        
        SinglyLinkedList instance = new SinglyLinkedList();
        
        instance.insertAtStart(data1);
        instance.insertAtStart(data2);
        
        instance.deleteAtStart();
        
        Node result = instance.getHead();
        assertEquals(data1, result.data);
        
        instance.deleteAtStart();
        
        Node result2 = instance.getHead();
        assertEquals(null, result2);
    }
    
}
