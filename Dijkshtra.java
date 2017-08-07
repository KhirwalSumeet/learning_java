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

class Edge {
    int weight;
    int dest;
    int src;
    
    Edge (int w, int d, int s) {
        weight = w;
        dest = d;
        src = s;
    }

}

class EdgeComparator implements Comparator<Object>
{

    public int compare(Object o1, Object o2)
    {

        Edge a1 = (Edge)o1;
        Edge a2 = (Edge)o2;

        return Integer.compare(a1.weight, a2.weight);
    }
}

interface GraphInterface {
    public abstract void insertEdge(int node1, int node2, int weight);
    public abstract boolean checkEdge(int node1, int node2);
    public abstract int inDegree(int node1);
    public abstract int outDegree(int node1);
    public abstract LinkedList<Integer> getAdjacentNodes(int node);
}

class adjacencyList implements GraphInterface {
    List<LinkedList<Edge>> data;
    boolean isDirected;
    Node[] vertexData;
    int vertexCount;
    
    adjacencyList(int size, boolean directed) {
        isDirected = directed;
        vertexData = new Node[size];
        data = new ArrayList<LinkedList<Edge>>();
        vertexCount = size;
        for(int i = 0; i < size; i++) {
            vertexData[i] = new Node(i);
            data.add(new LinkedList<Edge>());
        }
    }
    
    public void insertEdge(int node1, int node2, int weight) {
        data.get(node1).addLast(new Edge(weight, node2, node1));
        if (!isDirected)
            data.get(node2).addLast(new Edge(weight, node1, node2));
    }
    
    public boolean checkEdge(int node1, int node2) {
        LinkedList<Edge> edges = new LinkedList<Edge>();
        edges = (LinkedList<Edge>) data.get(node1).clone();
        int present = 0;
        while (edges.peek() != null) {
            Edge e = edges.poll();
            if (e.dest == node2) {
                present = 1;
                break;
            }
        }
        return present != 0;
    }
    
    public int getEdgeWeight(int node1, int node2) {
        LinkedList<Edge> edges = new LinkedList<Edge>();
        edges = (LinkedList<Edge>) data.get(node1).clone();
        int weight = Integer.MAX_VALUE;
        while (edges.peek() != null) {
            Edge e = edges.poll();
            if (e.dest == node2) {
                weight = e.weight;
                break;
            }
        }
        return weight;
    }
    
    public int outDegree(int node1) {
        return data.get(node1).size();
    }
    
    public int inDegree(int node1) {
        int count = 0;
        for(int i = 0; i < vertexCount; i++) {
            LinkedList<Edge> edges = new LinkedList<Edge>();
            edges = (LinkedList<Edge>) data.get(i).clone();
            int present = 0;
            while (edges.peek() != null) {
                Edge e = edges.poll();
                if (e.dest == node1) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    
    public LinkedList<Integer> getAdjacentNodes(int node) {
        LinkedList<Edge> adjNodes = new LinkedList<Edge>();
        adjNodes = (LinkedList<Edge>) data.get(node).clone();
        LinkedList<Integer> nodes = new LinkedList<Integer>();
        while (adjNodes.peek() != null) {
            Edge e = adjNodes.poll();
            nodes.add(e.dest);
        }
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
        return type == "Matrix"? am.getEdgeWeight(node1, node2) : al.getEdgeWeight(node1, node2);
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
    
    public Node[] getVertexData() {
        return type == "Matrix"? am.getVertexData() : al.getVertexData();
    }
    
}

class GraphDemo {
    
    public void dijkshtra(Graph gr) {
        int vertexCount = gr.vertexCount;
        boolean[] sptSet = new boolean[vertexCount];
        int[] distance = new int[vertexCount];
        int[] parent = new int[vertexCount];
        int weight, adjVertex, minDist, current;
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        //Intialising Start node
        distance[0] = 0;
        sptSet[0] = true;
        current = 0;
        
        //Algorithm
        
        for (int i =0; i < vertexCount; i++) {
            minDist = Integer.MAX_VALUE;
            LinkedList<Integer> adjNodes = gr.getAdjacentNodes(current);
            while (adjNodes.peek() != null) {
                adjVertex = adjNodes.poll();
                weight = gr.getEdgeWeight(current, adjVertex);
                if (distance[adjVertex] > (distance[current] + weight) ) {
                    distance[adjVertex] = distance[current] + weight;
                    parent[adjVertex] = current;
                }
            }
            for (int j = 0; j < vertexCount; j++) {
                if (!sptSet[j] && minDist > distance[j]) {
                    current = j;
                    minDist = distance[j];
                }
            }
            sptSet[current] = true;
        }
        
        //Distances from Vertex
        for (int i = 1; i < vertexCount; i++) {
            System.out.println("Distance of node " + i + " from 0th node is " + distance[i]);
        }        
    }
    
	public static void main (String[] args) {
	    Graph gr = new Graph("List", 6, false);
		gr.insertEdge(5,0,4);
		gr.insertEdge(5,2,8);
		gr.insertEdge(2,3,3);
		gr.insertEdge(3,1,15);
		gr.insertEdge(4,1,2);
		gr.insertEdge(4,0,11);
		
	    GraphDemo gd = new GraphDemo();
	    gd.dijkshtra(gr);
	}
}
