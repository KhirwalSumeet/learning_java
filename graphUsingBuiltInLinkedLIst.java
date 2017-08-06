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

interface GraphInterface {
    public abstract void insertEdge(int node1, int node2);
    public abstract boolean checkEdge(int node1, int node2);
    public abstract int findDegree(int node1);
}

class adjacencyList implements GraphInterface {
    List<LinkedList<Integer>> data;
    int isDirected;
    Node[] vertexData;
    
    adjacencyList(int size, int directed) {
        isDirected = directed;
        data = new ArrayList<LinkedList<Integer>>();
        vertexData = new Node[size];
        for(int i = 0; i < size; i++) {
            vertexData[i] = new Node(i);
            data.add(new LinkedList<Integer>());
        }
    }
    
    public void insertEdge(int node1, int node2) {
        data.get(node1).addLast(node2);
        if (isDirected == 0)
            data.get(node2).addLast(node1);
    }
    
    public boolean checkEdge(int node1, int node2) {
        return data.get(node1).indexOf(node2) != -1;
    }
    
    public int findDegree(int node1) {
        return data.get(node1).size();
    }
}

class adjacencyMatrix implements GraphInterface {
    int maxSize;
    int[][] matrix;
    int isDirected;
    Node[] vertexData;
    
    adjacencyMatrix(int size, int directed) {
        vertexData = new Node[size];
        for (int i = 0; i < size; i++)
            vertexData[i] = new Node(i);
        isDirected = directed;
        maxSize = size;
        matrix = new int[size][size];
    }
    
    public void insertEdge(int node1, int node2) {
        matrix[node1][node2] = 1;
        if (isDirected == 0)
            matrix[node2][node1] = 1;
    }
    
    public boolean checkEdge(int node1, int node2) {
        return matrix[node1][node2] == 1 ? true : false;
    }
    
    public int findDegree(int node1) {
        int count = 0;
        for (int i = 0; i < maxSize; i++)
            count = count + (matrix[i][0] == 1 ? 1 : 0);
        return count;
    }
    
}

class Graph implements GraphInterface {
    String type;
    adjacencyMatrix am;
    adjacencyList al;
    
    Graph(String gType, int size, int isDirected) {
        if (gType == "Matrix") {
            type = "Matrix";
            am = new adjacencyMatrix(size, isDirected);
        } else {
            type = "List";
            al = new adjacencyList(size, isDirected);
        }
    }
    
    public void insertEdge(int node1, int node2) {
        if (type == "Matrix")
            am.insertEdge(node1, node2);
        else
            al.insertEdge(node1, node2);
    }
    
    public boolean checkEdge(int node1, int node2) {
        return type == "Matrix"? am.checkEdge(node1, node2) : al.checkEdge(node1, node2);
    }
    
    public int findDegree(int node1) {
        return type == "Matrix"? am.findDegree(node1) : al.findDegree(node1);
    }
}

class GraphDemo {
    
	public static void main (String[] args) {
	    Graph gr = new Graph("Matrix",50,0);
		gr.insertEdge(0,1);
		gr.insertEdge(1,4);
		gr.insertEdge(2,4);
		gr.insertEdge(3,0);
	    System.out.println(gr.findDegree(4));
	    System.out.println(gr.findDegree(2));
	    System.out.println(gr.checkEdge(0,4));
	    System.out.println(gr.checkEdge(1,4));
	    System.out.println(gr.checkEdge(3,0));
	}
}
