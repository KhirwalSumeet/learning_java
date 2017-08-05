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
    int vertexCount;
    
    adjacencyList(int size) {
        data = new ArrayList<LinkedList<Integer>>();
        vertexCount = size;
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
    
    public void bfs(int start)
	  {
      int[] visited = new int[vertexCount];
      Queue<Integer> queue = new LinkedList();
      queue.add(start);
      visited[start] = 1;
      int[] parent = new int[vertexCount];
      int[] distance = new int[vertexCount];
      while(!queue.isEmpty()) {
          int parentIndex = queue.peek();
          LinkedList<Integer> current = data.get(parentIndex);
          int child = current.peek() == null?-1:current.poll();
          while (child != -1) {
              if(visited[child] != 1) {
                  parent[child] = parentIndex;
                  distance[child] = distance[parent[child]] + 1;
                  queue.add(child);
                  visited[child] = 1; 
              }
              child = current.peek() == null?-1:current.poll();
          }
          queue.poll();
      }
	}
	
	public void dfs(int start)
	{
	    int[] visited = new int[vertexCount];
		Stack stack = new Stack();
		stack.push(start);
		visited[start] = 1;
		int[] parent = new int[vertexCount];
		int[] distance = new int[vertexCount];
		while(!stack.empty()) {
		    int parentIndex = (Integer) stack.pop();
		    LinkedList<Integer> current = data.get(parentIndex);
		    int child = current.peek() == null?-1:current.poll();
		    while (child != -1) {
		        if(visited[child] != 1) {
		            System.out.println(child);
		            parent[child] = parentIndex;
		            distance[child] = distance[parent[child]] + 1;
    		        stack.push(child);
		            visited[child] = 1; 
		        }
		        child = current.peek() == null?-1:current.poll();
		    }
		}
	}
	
}

class graphDemo {
    
    
    
	public static void main (String[] args) {
    adjacencyList gr = new adjacencyList(5);		
		gr.insertEdge(0,1);
		gr.insertEdge(1,4);
		gr.insertEdge(2,4);
		gr.insertEdge(3,0);
  	gr.bfs(0);
		gr.dfs(0);
	}
}
