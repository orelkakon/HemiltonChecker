import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainProgram {
	public static void main(String[] args) {
		System.out.println("Please Insert the full path of file (must end with .ham)");
		Scanner input = new Scanner(System.in);
		
		String filenamePath = input.nextLine();
		String [] splited = filenamePath.split("\\\\"); //split to take the filename.ham
		String filename = splited[splited.length-1]; //filename.ham
		String [] splited2 = filename.split("\\."); // split to take just filename
		filename = splited2[0]; //filename
		
		input.close();
		Graph GI = null;
		try {
			GI = HandlerFile.Extract(filenamePath); //build graph
		} catch (Exception e) { // file not found
			e.printStackTrace();
			System.out.println("the file is not exist");
			System.exit(0);
		}
		boolean wasFound = false; //found a path
		int i_start = 1; //start vertex 
		while(!wasFound && i_start<GI.numVertices+1) { // run over all option of start vertex & yet found a good path
			List<Integer> path = new ArrayList<>();
			int start = i_start; 
			path.add(start); //add first vertex
			boolean[] visited = new boolean[GI.numVertices+1]; // initialize visited array 
			visited[start] = true; // mark start node as visited
			List<Integer> Result = HemiltonCheck.retHamiltonianPath(GI, start, visited, path, GI.numVertices); //calculate result
			String R = "";
			if(Result == null) { //this option of start vertex not success
				i_start++;
				continue;
			}
			else {
				wasFound = true;
				for (int i : Result) //convert List<Integer> to string 
					R = R + i + " ";
				R = R +"0"; // add 0 in the end
				HandlerFile.craeteAndWriteFile(filename, R);
				break;
			}
		}
		if(!wasFound)
			HandlerFile.craeteAndWriteFile(filename, "0");
	}
}
