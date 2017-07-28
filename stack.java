class Stack {
    int size = 100;
    int data[] = new int[size];
    int top;
    Stack () {
        top = -1;
    }
    
    public void push (int value) {
        top++;
        if (top < size) {
           data[top] = value; 
        } else {
            top--;
            System.out.println("Stack is full");
        }
    }
    
    public void printData () {
        if (top == -1) {
            System.out.println("Stack is empty");
        } else {
            int index = top;
            while (index > -1) {
                System.out.println(data[index--]);
            }
        }
    }
    
    public void pop () {
        if (top == -1) {
            System.out.println("Stack is empty");
        } else {
            System.out.println(data[top--] + " deleted");
        }
    }
    
    public static void main (String args[]) {
        Stack stack = new Stack();
        int i=0;
        while (i < 4) {
            stack.push(++i);
        }
        stack.printData();
        stack.pop();
        stack.printData();
    }
}
