/* Node of a doubly linked list */
import java.util.Scanner;

// 1-based index

class Node {
    int key;
    int value;
}

class priorityQueue {
    int max_size = 1001;
    Node[] data = new Node[max_size];
    int size = 1;
    
    public void swap(int index1, int index2, Node arr[]) {
        Node temp = new Node();
        temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    
    public void insert(int key, int value) {
        Node temp = new Node();
        temp.value = value;
        temp.key = key;
        data[size] = temp;
        size++;
    }
    
    public void print(int index) {
        System.out.println(data[index].key + " : " + data[index--].value);
        if (index > 0)
            print(index);
    }
    
    public void adjustUp(int index) {
        if ((index - 1)/2 > 0) {
            if (data[index].key < data[(index - 1)/2].key) {
                swap(index, (index-1)/2, data);
                adjustUp( (index-1)/2 );
            }
        } else if((index/2) > 0) {
            if (data[index].key < data[(index)/2].key) {
                swap(index, (index)/2, data);
                adjustUp( (index)/2 );
            }  
        }
    }
    
    public void adjustDown(int index) {
        if (2*index + 1 < size && 2*index < size) {
            if (data[2*index].key < data[2*index+1].key && data[index].key > data[2*index].key) {
                swap(index, 2*index, data);
                adjustUp(2*index);
            } else if (data[2*index].key > data[2*index+1].key && data[index].key > data[2*index + 1].key) {
                swap(index, 2*index + 1, data);
                adjustUp(2*index + 1);
            }
        } else if (2*index < size) {
            if (data[2*index].key < data[index].key) {
                swap(index, 2*index, data);
                adjustUp(2*index);
            }
        } else if (2*index +1 < size) {
            if (data[2*index + 1].key < data[index].key) {
                swap(index, 2*index + 1, data);
                adjustUp(2*index + 1);
            }
        }
    }
    
    public void delete(int index) {
        swap(index, size - 1, data);
        size--;
        adjustDown(index);
    }
    
    public void priorityQueueify(int index) {
        adjustDown(index/2);
    }
    
    public void pop() {
        System.out.println(data[1].key + ":" + data[1].value + " popped!");
        delete(1);
    }
    
    public void updateKey(int oldKey, int newKey) {
        int index = 1;
        int delete = 0;
        int value = 0;
        while (index < size) {
            if (data[index].key == oldKey) {
                value = data[index].value;
                delete(index);
                delete = 1;
            }       
            index++;
        }
        if (delete == 1) {
            insert(newKey, value);
        } else {
            System.out.println("No key found");
        }
    }
}


class pqDemo {
    public static void main (String args[]) {
        priorityQueue pq = new priorityQueue();
		Scanner scanner = new Scanner(System.in);
        int numCount = Integer.parseInt(scanner.nextLine());
	    int scanCount = numCount;
	    while (scanCount > 0) {
	        pq.insert(Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));
	        pq.adjustUp(pq.size -1);
	        scanCount--;
	    }
	    
	    pq.print(pq.size - 1);
	    pq.pop();
	    System.out.println("After Delete result");
	    pq.updateKey(5, 15);
	    pq.print(pq.size - 1);
    }
}
