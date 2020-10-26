import java.util.ArrayList;
import java.util.List;

public class Graph {
		public List<List<Integer>> adjList = null; // An array of Lists to represent adjacency list
		public int numVertices; // number of vertices
		
		public Graph(List<Edge> edges, int N) // Constructor
		{
			this.numVertices = N;
			adjList = new ArrayList<>(N+1); //skip cell in index 0 

			for (int i = 0; i < N+1; i++) { // initialize cells
				adjList.add(i, new ArrayList<>() );
			}

			for (int i = 0; i < edges.size(); i++){ // add edges to the undirected graph
				int src = edges.get(i).source;
				int dest = edges.get(i).dest;
				adjList.get(src).add(dest);
				adjList.get(dest).add(src);
			}
		}
}
