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
    public abstract void insertEdge(int node1, int node2, int weight);
    public abstract boolean checkEdge(int node1, int node2);
    public abstract int inDegree(int node1);
    public abstract int outDegree(int node1);
    public abstract LinkedList<Integer> getAdjacentNodes(int node);
    public abstract LinkedList<Integer> getParentNodes(int node);
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
    
    public void insertEdge(int node1, int node2, int weight) {
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
    
    public LinkedList<Integer> getParentNodes(int node) {
        LinkedList<Integer> nodes = new LinkedList<Integer>();
        for (int i = 0; i < vertexCount; i++)
            if (data.get(i).contains(node))
                nodes.add(i);
        return nodes;
    }

    public Node[] getVertexData() {
        return vertexData;
    }
}

class adjacencyMatrix implements GraphInterface {
    int maxSize;
    int[][] matrix;
    boolean isDirected;
    Node[] vertexData;
    
    adjacencyMatrix(int size, boolean directed) {
        vertexData = new Node[size];
        isDirected = directed;
        maxSize = size;
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            vertexData[i] = new Node(i);
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        
    }
    
    public void insertEdge(int node1, int node2, int weight) {
        matrix[node1][node2] = weight;
        if (!isDirected)
            matrix[node2][node1] = weight;
    }
    
    public boolean checkEdge(int node1, int node2) {
        return matrix[node1][node2] != Integer.MAX_VALUE ? true : false;
    }
    
    public int getEdgeWeight(int node1, int node2) {
        return matrix[node1][node2];
    }
    
    public int inDegree(int node1) {
        int count = 0;
        for (int i = 0; i < maxSize; i++)
            count = count + (matrix[i][node1] != Integer.MAX_VALUE ? 1 : 0);
        return count;
    }
    
    public int outDegree(int node1) {
        int count = 0;
        for (int i = 0; i < maxSize; i++)
            count = count + (matrix[node1][i] != Integer.MAX_VALUE ? 1 : 0);
        return count;
    }
    
    public LinkedList<Integer> getAdjacentNodes(int node) {
        LinkedList<Integer> nodes = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++)
            if (matrix[node][i] != Integer.MAX_VALUE)
                nodes.add(i);
        return nodes;
    }
    
    public LinkedList<Integer> getParentNodes(int node) {
        LinkedList<Integer> nodes = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++) { 
            if (matrix[i][node] != Integer.MAX_VALUE)
                nodes.add(i);
        }
        return nodes;
    }

    public Node[] getVertexData() {
        return vertexData;
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
    
    public void insertEdge(int node1, int node2, int weight) {
        if (type == "Matrix")
            am.insertEdge(node1, node2, weight);
        else
            al.insertEdge(node1, node2, weight);
    }
    
    public boolean checkEdge(int node1, int node2) {
        return type == "Matrix"? am.checkEdge(node1, node2) : al.checkEdge(node1, node2);
    }
    
    public int getEdgeWeight(int node1, int node2) {
        return type == "Matrix"? am.getEdgeWeight(node1, node2) : 0; 
        // al.getEdgeWeight(node1, node2);
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
    
    public LinkedList<Integer> getParentNodes(int node) {
        return type == "Matrix"? am.getParentNodes(node) : al.getParentNodes(node);
    }
    
    public Node[] getVertexData() {
        return type == "Matrix"? am.getVertexData() : al.getVertexData();
    }
    
}

class GraphDemo {
    
    public void primMST (Graph gr) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
        Node[] vertexData = gr.getVertexData();
        vertexData[0].color = "GRAY";
        vertexData[0].key = 0;
        pQueue.add(0);
        while (pQueue.peek() != null) {
            int u = pQueue.poll();
            LinkedList<Integer> adjNodes = gr.getAdjacentNodes(u);
            while (!adjNodes.isEmpty()) {
                int current = adjNodes.poll();
                if (vertexData[current].color == "WHITE") {
                    vertexData[current].color = "GRAY";
                    pQueue.add(current);
                    vertexData[current].key = gr.getEdgeWeight(u, current);
                    vertexData[current].parent = u;
                } else if (vertexData[current].color == "GRAY") {
                    if (vertexData[current].key > gr.getEdgeWeight(u, current)) {
                        vertexData[current].key = gr.getEdgeWeight(u, current);
                        vertexData[current].parent = u;
                    }
                }
            }
        }
        // vertexData has MST
    }
    
	public static void main (String[] args) {
	    Graph gr = new Graph("Matrix", 6, false);
		gr.insertEdge(5,0,4);
		gr.insertEdge(5,2,8);
		gr.insertEdge(2,3,3);
		gr.insertEdge(3,1,15);
		gr.insertEdge(4,1,2);
		gr.insertEdge(4,0,11);
	    
	    GraphDemo gd = new GraphDemo();
	    gd.primMST(gr);
	}
}
