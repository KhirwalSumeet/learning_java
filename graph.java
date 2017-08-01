import java.io.*;
import java.util.*;

class Node {
    int label;
    int vertex;
    Node next;
    
    Node(int value) {
        vertex = value;
        next = null;
    }
}

class adjacencyList {
    int verticeCount;
    int maxSize = 100;
    Node[] data = new Node[maxSize];
    
    adjacencyList() {
        verticeCount = 0;
    }
    
    public void insertVertex(int value) {
        Node newVertex = new Node(value);
        data[verticeCount++] = newVertex;
    }
    
    public void insertEdge(int node1, int node2) {
        Node temp = data[node1];
        while (temp.next != null)
            temp = temp.next;
        temp.next = new Node(node2);
        temp = data[node2];
        while (temp.next != null)
            temp = temp.next;
        temp.next = new Node(node1);
    }
    
    public int checkEdge(int node1, int node2) {
        int edge = 0;
        Node temp = data[node1];
        temp = temp.next;
        while (temp != null) {
            if (temp.label == node2) {
                edge = 1;
                break;
            }
            temp = temp.next;
        }
        return edge;
    }
    
    public int findDegree(int node1) {
        int degree = 0;
        Node temp = data[node1];
        temp = temp.next;
        while (temp != null) {
            degree++;
            temp = temp.next;
        }
        return degree;
    }
}

class adjacencyMatrix {
    int verticeCount;
    int maxSize = 100;
    int[][] matrix = new int[maxSize][maxSize];

    public void insertEdge(int node1, int node2) {
        matrix[node1 - 1][node2 - 1] = 1;
    }
    
    public int checkEdge(int node1, int node2) {
        return matrix[node1 - 1][node2 - 1];
    }
    
    
    
}

class graphDemo {
    
	public static void main (String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    adjacencyList gr = new adjacencyList();
		int nodeCount = Integer.parseInt(scanner.nextLine());
		int lineCount = 1;
		while (lineCount <= nodeCount) {
		    gr.insertVertex(Integer.parseInt(scanner.nextLine()));
		    lineCount++;
		}
		gr.insertEdge(0,1);
		gr.insertEdge(1,4);
		gr.insertEdge(2,4);
		gr.insertEdge(3,0);
		
	    adjacencyMatrix am = new adjacencyMatrix();
	    am.insertEdge(10,5);
	    System.out.println(am.checkEdge(10,5));
	}
}
