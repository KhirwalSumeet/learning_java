/* Node of a doubly linked list */
class Node
{
    int data;
    Node next; // Pointer to next node in DLL
    Node prev; // Pointer to previous node in DLL
    Node(int d) {
        data = d;
        next = null;
        prev = null;
    }
};
class DoublyLinkedList
{
    Node head = null;
    Node tail = null;
    
    public void pushData (int data) {
	    Node newdata = new Node(data);
	    if (head == null) {
	        head = newdata;
	        tail = newdata;
	    } else {
    	    tail.next = newdata;
    	    if (tail != null)
    	        newdata.prev = tail;
    	    tail = newdata;
	    }
	}
	
	public void printList () {
	    Node n = head;
	    while(n != null) {
	        System.out.println(n.data);
	        n = n.next;
	    }
	}
	
	public void reverseList () {
	    Node n = head;
	    Node temp = new Node(0);
	    while(n != null) {
	        temp.next = n.next;
	        temp.prev = n.prev;
	        n.prev = temp.next;
	        n.next = temp.prev;
	        if(n.prev == null) {
	            temp = head;
	            head = n;
	            tail = temp;
	        }
	        n = n.prev;
	    }
	}
	
    public static void main(String args[]){
        DoublyLinkedList dl = new DoublyLinkedList();
        int i = 0;
	    while (i < 10) {
	        i++;
	        dl.pushData(i);
	    }
        dl.reverseList();
        dl.printList();
    }
}
