
package com.mycompany.core;


public class SinglyLinkedList {
    private Node head = null;
    
    public void insertAtStart(String data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            return;
        }
        
        newNode.next = head;
        head = newNode;
    }
    
    public Node deleteAtStart() {
        if (head == null) {
            return null;
        }
        
        Node firstNode = head;
        head = head.next;
        
        return firstNode;
    }
    
    public Node getHead() {
        return this.head;
    }
}
