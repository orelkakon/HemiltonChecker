import java.util.List;

public class HemiltonCheck {

	public static List<Integer> retHamiltonianPath(Graph g, int newVertex,
			boolean[] visited, List<Integer> path, int N){

		if (path.size() == N) { // if all the vertices are visited, then hamiltonian path exists
			return path;
		}
		// Check if every edge starting from vertex newVertex leads to a solution or not
		for (int maybeVertex : g.adjList.get(newVertex)){
			if (!visited[maybeVertex]) {
				visited[maybeVertex] = true; // mark start node as visited
				path.add(maybeVertex); //add new vertex to path
				List<Integer> goodPath = retHamiltonianPath(g, maybeVertex, visited, path, N); // check if adding vertex maybeVertex to the path leads to solution or not
				if(goodPath != null) {
					return path;
				}
				else {
					visited[maybeVertex] = false; // Backtrack
					path.remove(path.size()-1); // Backtrack
				}
			}
		}
		return null; // not has hamiltonian path
	}
}