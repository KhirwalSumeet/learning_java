import java.io.*;
import java.util.*;
import java.util.LinkedList;

class Node {
    String color;
    Integer parent;
    Integer key;
    int vertex;
    
    Node(int value) {
        vertex = value;
        color = "WHITE";
        key = null;
        parent = null;
    }
}

interface GraphInterface {
    public abstract void insertEdge(int node1, int node2);
    public abstract boolean checkEdge(int node1, int node2);
    public abstract int inDegree(int node1);
    public abstract int outDegree(int node1);
}

class adjacencyList implements GraphInterface {
    List<LinkedList<Integer>> data;
    boolean isDirected;
    Node[] vertexData;
    int vertexCount;
    
    adjacencyList(int size, boolean directed) {
        isDirected = directed;
        vertexData = new Node[size];
        data = new ArrayList<LinkedList<Integer>>();
        vertexCount = size;
        for(int i = 0; i < size; i++) {
            vertexData[i] = new Node(i);
            data.add(new LinkedList<Integer>());
        }
    }
    
    public void insertEdge(int node1, int node2) {
        data.get(node1).addLast(node2);
        if (!isDirected)
            data.get(node2).addLast(node1);
    }
    
    public boolean checkEdge(int node1, int node2) {
        return data.get(node1).indexOf(node2) != -1;
    }
    
    public int outDegree(int node1) {
        return data.get(node1).size();
    }
    
    public int inDegree(int node1) {
        int count = 0;
        for(int i = 0; i < vertexCount; i++) {
            if (data.get(i).contains(node1))
                count++;
        }
        return count;
    }
    
    public LinkedList<Integer> getAdjacentNodes(int node) {
        return data.get(node);
    }
    
}

class adjacencyMatrix implements GraphInterface {
    int maxSize;
    int[][] matrix;
    boolean isDirected;
    Node[] vertexData;
    
    adjacencyMatrix(int size, boolean directed) {
        vertexData = new Node[size];
        for (int i = 0; i < size; i++)
            vertexData[i] = new Node(i);
        isDirected = directed;
        maxSize = size;
        matrix = new int[size][size];
    }
    
    public void insertEdge(int node1, int node2) {
        matrix[node1][node2] = 1;
        if (!isDirected)
            matrix[node2][node1] = 1;
    }
    
    public boolean checkEdge(int node1, int node2) {
        return matrix[node1][node2] == 1 ? true : false;
    }
    
    public int inDegree(int node1) {
        int count = 0;
        for (int i = 0; i < maxSize; i++)
            count = count + (matrix[i][node1] == 1 ? 1 : 0);
        return count;
    }
    
    public int outDegree(int node1) {
        int count = 0;
        for (int i = 0; i < maxSize; i++)
            count = count + (matrix[node1][i] == 1 ? 1 : 0);
        return count;
    }
    
    public LinkedList<Integer> getAdjacentNodes(int node) {
        LinkedList<Integer> nodes = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++)
            if (matrix[node][i] == 1)
                nodes.add(i);
        return nodes;
    }
}

class Graph implements GraphInterface {
    String type;
    adjacencyMatrix am;
    adjacencyList al;
    int vertexCount;
    int data;
    
    Graph(String gType, int size, boolean isDirected) {
        vertexCount = size;
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
    
    public int inDegree(int node1) {
        return type == "Matrix"? am.inDegree(node1) : al.inDegree(node1);
    }
    
    public int outDegree(int node1) {
        return type == "Matrix"? am.outDegree(node1) : al.outDegree(node1);
    }
    
    public LinkedList<Integer> getAdjacentNodes(int node) {
        return type == "Matrix"? am.getAdjacentNodes(node) : al.getAdjacentNodes(node);
    }
    
}

class GraphDemo {
    
    public void topologyTraversal(Stack<Integer> temp, Stack<Integer> stack, int[] visited, Graph gr) {
        int parentIndex = (Integer) stack.pop();
        temp.push(parentIndex);
        visited[parentIndex] = 1; 
	    LinkedList<Integer> current = gr.getAdjacentNodes(parentIndex);
	    int child = current.peek() == null?-1:current.poll();
	    while (child != -1) {
	        if(visited[child] != 1) {
		        stack.push(child);
	        }
	        child = current.peek() == null?-1:current.poll();
	    }
	    if (!stack.empty())
	        topologyTraversal(temp, stack, visited, gr);
    }
    
    public void topology(Graph gr) {
        int[] visited = new int[gr.vertexCount];
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		for (int i = 0; i < gr.vertexCount; i++)
		    if (visited[i] == 0) {
		        stack.push(i);
		        topologyTraversal(temp, stack, visited, gr);
		        while(!temp.empty()) {
        		    System.out.println(temp.pop());
        		}
		    }
    }
    
	public static void main (String[] args) {
	    Graph gr = new Graph("Matrix", 6, true);
		gr.insertEdge(5,0);
		gr.insertEdge(5,2);
		gr.insertEdge(2,3);
		gr.insertEdge(3,1);
		gr.insertEdge(4,1);
		gr.insertEdge(4,0);
	    
	    GraphDemo gd = new GraphDemo();
	    gd.topology(gr);
	    
	}
}
