
package com.mycompany.core;


public class Stack {
    private final SinglyLinkedList linkedList = new SinglyLinkedList();
    
    public void push(String data) {
        this.linkedList.insertAtStart(data);
    }
    
    public Node pop() {
        Node elem = this.linkedList.deleteAtStart();
        return elem;
    }
    
    public Node peek() {
        return this.linkedList.getHead();
    }
    
}
