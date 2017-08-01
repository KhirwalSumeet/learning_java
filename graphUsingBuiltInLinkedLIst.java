import java.io.*;
import java.util.*;
import java.util.LinkedList;

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
    List<LinkedList<Integer>> data;
    
    adjacencyList(int size) {
        data = new ArrayList<LinkedList<Integer>>();
        for(int i = 0; i < size; i++) {
            data.add(new LinkedList<Integer>());
        }
    }
    
    public void insertEdge(int node1, int node2) {
        data.get(node1).addLast(node2);
        data.get(node2).addLast(node1);
    }
    
    public boolean checkEdge(int node1, int node2) {
        return data.get(node1).indexOf(node2) != -1;
    }
    
    public int findDegree(int node1) {
        return data.get(node1).size();
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
	    adjacencyList gr = new adjacencyList(5);
		
		gr.insertEdge(0,1);
		gr.insertEdge(1,4);
		gr.insertEdge(2,4);
		gr.insertEdge(3,0);
	    System.out.println(gr.findDegree(4));
	    System.out.println(gr.findDegree(2));
	    System.out.println(gr.checkEdge(0,4));
	    System.out.println(gr.checkEdge(1,4));
	    System.out.println(gr.checkEdge(3,0));
		
	    adjacencyMatrix am = new adjacencyMatrix();
	    am.insertEdge(10,5);
	    System.out.println(am.checkEdge(10,5));
	}
}
