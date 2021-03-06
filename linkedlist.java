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
	
	public static void swapNode(int first, int second) {
	    Node llist = head;
        Node curF = null;
        Node prevF = null;
        Node curS = null;
        Node prevS = null;
        Node temp = new Node(1);
        int count = 0;
        if(first >= second) {
            System.out.println("No swapping done as element order passed is wrong");
        } else {
            while (llist != null) {
                count++;
                if(count == first) {
                    curF = llist;
                }
                if (count == first - 1) {
                    prevF = llist;
                }
                if (count == second - 1 && second - first > 1) {
                    prevS = llist;
                }
                if (count == second) {
                    curS = llist;
                }
                llist = llist.next;
            }
            if (curS == null || curF == null) {
                System.out.println("Range out of bounds of elements");
            } else if (prevF != null && prevS != null) {
                prevS.next = curF;
                prevF.next = curS;
                temp.next = curS.next;
                curS.next = curF.next;
                curF.next = temp.next;
            } else {
                if (second - first == 1) {
                    if(prevF != null){
                        prevF.next = curS;
                        curF.next = curS.next;
                        curS.next = curF;
                    } else {
                        curF.next = curS.next;
                        curS.next = curF;
                        head = curS;
                    }
                } else {
                    System.out.println(prevS);
                        prevS.next = curF;
                        temp.next = curF.next;
                        curF.next = curS.next;
                        curS.next = temp.next;
                        head = curS;
                }
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
	
	public static void makeLoop () {
	    Node n = head;
	    Node temp = null;
	    while(n != null) {
	        if (n.data == 5) {
	            temp = n;
	        } else if(n.data == 9) {
	            n.next = temp.next;
	            break;
	        }
	        n = n.next;
	    }
	    
	}
	
	public static int getLoopPosition (int loopLength) {
	    Node llist_slow = head;
	    Node llist = head;
	    int i = 0;
	    while (i < loopLength) {
	        i++;
	        llist_slow = llist_slow.next;
	    }
	    i = 0;
	    int pos = 1;
	    while (i == 0) {
	        if (llist.next == llist_slow.next) {
	            break;
	        } else {
	            llist_slow = llist_slow.next;
	            llist = llist.next;
	            pos++;
	        }
	    }
	    return pos;
	}
	
	public static void removeLoop (int pos) {
	    Node llist = head;
	    int count = 0;
	    while (llist != null) {
	        count++;
	        if (count == pos) {
	            llist.next = null;
	            break;
	        }
	        llist = llist.next;
	    }
	}
	
	public static void loopDetection() {
	    Node llist = head;
	    Node llist_2x = head;
        llist = llist.next;
        llist_2x = llist_2x.next.next;
        int count = 1;
        int state = 0;
        while( llist != null && llist_2x != null && llist_2x.next != null) {
            if(llist == llist_2x && state == 0){
                state = 1;
                llist = llist.next;
            } else if (state == 1) {
                if (llist == llist_2x) {
                    break;
                } else {
                    llist = llist.next;
                    count++;
                }
            }
            else{
                llist = llist.next;
                llist_2x = llist_2x.next.next;
            }
        }
        if (llist == llist_2x) {
            System.out.println("Loop detected");
            int position = getLoopPosition(count);
            removeLoop (count + position);
        } else {
            System.out.println("No lopp detected");
        }
	}
	
	public static void main(String[] args) {
	    LinkedList llist = new LinkedList();
	    llist.head = new Node(1);
	    last = head;
	    int i = 1;
	    while (i < 10) {
	        i++;
	        pushData(i);
	    }
	    insertAfter(10, 50);
	    delete(10);
	    swapNode(3,5);
	    reverseList();
	    makeLoop();
	    loopDetection();
	    printList();
	}
}
