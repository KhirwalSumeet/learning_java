class LinkedList
{
	static Node head; // head of list
	static Node last; // head of list

	/* Linked list Node*/
	static class Node
	{
		int data;
		Node next;
		
		// Constructor to create a new node
		// Next is by default initialized
		// as null
		Node(int d) {
		    data = d;
		    next = null;
		};
	};
	
	public static void printList () {
	    Node n = head;
	    while(n != null) {
	        System.out.println(n.data);
	        n = n.next;
	    }
	}
	
	public static void pushData (int data) {
	    Node newdata = new Node(data);
	    last.next = newdata;
	    last = newdata;
	}
	
	public static void insertAfter (int oldData, int data) {
	    Node llist = head;
	    while(llist != null) {
	        if(llist.data == oldData) {
	            Node newdata = new Node(data);
        	    newdata.next = llist.next;
        	    llist.next = newdata;
	        }
	        llist = llist.next;
	        
	    }
	}
	
	public static void delete(int oldData) {
	    Node llist = head;
	    while(llist.next != null) {
	        if(llist.next.data == oldData) {
        	    llist.next = llist.next.next;
	        }
	        llist = llist.next;
	        
	    }	    
	}
	
	public static void main(String[] args) {
	    LinkedList llist = new LinkedList();
	    llist.head = new Node(1);
	    last = head;
	    pushData(10);
	    pushData(100);
	    pushData(1000);
	    insertAfter(10, 50);
	    delete(10);
	    printList();
	}
}
