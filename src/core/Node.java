
package core;


public class Node {
    public Node next;
    public Node prev;
    public String data;

    public Node(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
}
