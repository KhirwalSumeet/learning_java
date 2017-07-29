/* Node of a doubly linked list */
import java.util.Scanner;

// 1-based index

class Heap {
    int max_size = 1001;
    int data[] = new int[max_size];
    int size = 1;
    
    public void insert(int value) {
        data[size] = value;
        size++;
    }
    
    public void print(int index) {
        System.out.println(data[index--]);
        if (index > 0)
            print(index);
    }
    
    public void adjustUp(int index) {
        if ((index - 1)/2 > 0) {
            if (data[index] < data[(index - 1)/2]) {
                data[index] = data[(index - 1)/2] + data[index];
                data[(index - 1)/2] = data[index] - data[(index - 1)/2];
                data[index] = data[index] - data[(index - 1)/2];
                adjustUp( (index-1)/2 );
            }
        } else if((index/2) > 0) {
            if (data[index] < data[(index)/2]) {
                data[index] = data[(index)/2] + data[index];
                data[(index)/2] = data[index] - data[(index)/2];
                data[index] = data[index] - data[(index)/2];
                adjustUp( (index)/2 );
            }  
        }
    }
    
    public void adjustDown(int index) {
        if (2*index + 1 < size && 2*index < size) {
            if (data[2*index] < data[2*index+1] && data[index] > data[2*index]) {
                data[index] = data[2*index] + data[index];
                data[2*index] = data[index] - data[2*index];
                data[index] = data[index] - data[2*index];
                adjustUp(2*index);
            } else if (data[2*index] > data[2*index+1] && data[index] > data[2*index + 1]) {
                data[index] = data[2*index+1] + data[index];
                data[2*index + 1] = data[index] - data[2*index + 1];
                data[index] = data[index] - data[2*index + 1];
                adjustUp(2*index + 1);
            }
        } else if (2*index < size) {
            if (data[2*index] < data[index]) {
                data[index] = data[2*index] + data[index];
                data[2*index] = data[index] - data[2*index];
                data[index] = data[index] - data[2*index];
                adjustUp(2*index);
            }
        } else if (2*index +1 < size) {
            if (data[2*index + 1] < data[index]) {
                data[index] = data[2*index + 1] + data[index];
                data[2*index] = data[index] - data[2*index + 1];
                data[index] = data[index] - data[2*index + 1];
                adjustUp(2*index + 1);
            }
        }
    }
    
    public void delete(int index) {
        data[index] = data[size - 1] + data[index];
        data[size - 1] = data[index] - data[size - 1];
        data[index] = data[index] - data[size - 1];
        size--;
        adjustDown(index);
    }
    
}

class heapDemo {
    public static void main (String args[]) {
        Heap heap = new Heap();
		Scanner scanner = new Scanner(System.in);
        int numCount = Integer.parseInt(scanner.nextLine());
	    int scanCount = numCount;
	    while (scanCount > 0) {
	        heap.insert(Integer.parseInt(scanner.nextLine()));
	        heap.adjustUp(heap.size -1);
	        scanCount--;
	    }
	    
	    heap.print(heap.size - 1);
	    heap.adjustUp(heap.size - 1);
	    System.out.println("Adjust Up result");
	    heap.print(heap.size - 1);
	    heap.delete(3);
	    System.out.println("After Delete result");
	    heap.print(heap.size - 1);
    }
}
