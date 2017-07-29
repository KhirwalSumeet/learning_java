import java.util.*;
import java.lang.*;
import java.io.*;

class Stack {
    int size = 100;
    char data[] = new char[size];
    int top;
    Stack () {
        top = -1;
    }
    
    public void push (char value) {
        top++;
        if (top < size) {
           data[top] = value; 
        } else {
            top--;
        }
    }
    
    public char pop () {
        if (top == -1) {
            return Character.MIN_VALUE;
        } else {
            return data[top--];
        }
    }
    
    public char getLastElement () {
        if (top == -1) {
            return Character.MIN_VALUE;
        } else {
            return data[top];
        }
    }
}

class convert {
    
    public String convertToPostfix (String exp, Hashtable<String,Integer> order) {
        int len = exp.length();
        int operator[] = new int[len];
        Stack st = new Stack();
        char last, curChar;
        String postfix = "";
        for (int i = 0; i<len; i++) {
            curChar = exp.charAt(i);
            if (order.get(Character.toString(curChar)) != null) {
                last = st.getLastElement();
                if (curChar == '(' || last == Character.MIN_VALUE || order.get( Character.toString(last) ) > order.get( Character.toString(curChar) )) {
                    st.push(curChar);
                } else if (curChar == ')') {
                    last = st.pop();
                    while (last != '(') {
                        postfix = postfix + last;
                        last = st.pop();
                    }
                    postfix = postfix + last;
                    postfix = postfix + curChar;
                } else if (order.get( Character.toString(last) ) <= order.get( Character.toString(curChar))) {
                    last = st.pop();
                    while (order.get( Character.toString(last) ) <= order.get( Character.toString(curChar))) {
                        postfix = postfix + last;
                        last = st.pop();
                        if (last == Character.MIN_VALUE) {
                            break;
                        } 
                    }
                    st.push(curChar);
                    if (last != Character.MIN_VALUE) {
                        postfix = postfix + last;
                    }
                }
            } else {
                postfix = postfix + curChar;
            }
        }
        last = st.pop();
        while (last != Character.MIN_VALUE) {
            postfix = postfix + last;
            last = st.pop();
        }
        return postfix;
    }
    
    public Hashtable<String,Integer> order () {
        Hashtable<String,Integer> ht = new Hashtable<String,Integer>();
		ht.put(new String("^"),new Integer(1));
		ht.put(new String("/"),new Integer(2));
		ht.put(new String("*"),new Integer(3));
		ht.put(new String("+"),new Integer(4));
		ht.put(new String("-"),new Integer(5));
		ht.put(new String("("),new Integer(6));
		return ht;
    }
    
	public static void main (String[] args) {
	    convert cvt = new convert();
		Hashtable<String,Integer> precedencyOrder = cvt.order();
		Scanner scanner = new Scanner(System.in);
        int expCount;
        expCount = Integer.parseInt(scanner.nextLine());
	    String expressions[] = new String[expCount];
	    int scanCount = expCount;
	    while (scanCount > 0) {
	        expressions[--scanCount] = scanner.nextLine();
	    }
	    for (int i = 0; i < expCount; i++) {
	        System.out.println(cvt.convertToPostfix(expressions[i], precedencyOrder));
	    }
	}
}
