/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
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
	
	public static void swapNode(int first, int sec) {
	    Node llist = head;
        Node curF = null;
        Node prevF = null;
        Node curS = null;
        Node prevS = null;
        Node temp = null;
        int count = 0;
        if(first >= sec) {
            System.out.println("No swapping done as element order passed is wrong");
        } else {
            while (llist.next != null) {
                count++;
                if(count == first) {
                    if(prevF == null)
                        prevF = llist;
                    curF = llist;
                } else if (count == first - 1) {
                    prevF = llist;
                } else if (count == sec - 1) {
                    prevS = llist;
                } else if (count == sec) {
                    curS = llist;
                }
                llist = llist.next;
            }
            if(count + 1 == sec) {
                curS = llist;
            }
            if(prevS == null || curS == null || prevF == null || curF == null) {
                System.out.println("Range out of bounds of elements");
            } else if (prevF == curF) {
                System.out.println("First element is swapped");
                temp.next = prevF.next;
                prevF.next = curS.next;
                curS.next = temp.next;
                prevS.next = prevF;
                head = curS.next;
            } else {
                System.out.println("First element is not swapped");
               
                prevS.next = curF;
                prevF.next = curS;
                temp = curS;
                curS.next = curF.next;
                curF.next = temp.next;
                
            }
        }
	}
	
	public static void reverseList() {
	    Node cur = null;
	    Node prev = null;
	    Node llist = head;
	    prev = llist;
	    llist = llist.next;
	    prev.next = null;
	    while(llist.next != null) {
	        cur = llist;
            llist = llist.next;
            cur.next = prev;
            prev = cur;
	    }
	    llist.next = prev;
	    head = llist;
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
	   // swapNode(1,3);
	    reverseList();
	    printList();
	}
}
