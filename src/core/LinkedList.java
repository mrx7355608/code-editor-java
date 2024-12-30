
package core;


public class LinkedList {
    private Node head = null;
    
    public void insertAtEnd(String data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            return;
        }
        
        Node lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        
        lastNode.next = newNode;
        newNode.prev = lastNode;
    }
    
    public void insertAtStart(String data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            return;
        }
        
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
    }
    
    public Node deleteAtEnd() {
        if (head == null) {
            return null;
        }
        
        Node lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        
        lastNode.prev = null;
        return lastNode;
    }
    
    public Node deleteAtStart() {
        if (head == null) {
            return null;
        }
        
        Node secondNode = head.next;
        head = secondNode;
        
        return secondNode;
    }
    
    public Node getHead() {
        return this.head;
    }
}
