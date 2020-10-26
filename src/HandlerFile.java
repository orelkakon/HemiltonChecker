import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;

public class HandlerFile {
    /***
     * In this class I was help from stack overflow in create/write/read from files.
     */
	public static Graph Extract(String path) throws Exception {
		Graph result;
		int numOfVertices = 0;
		List<Edge> edges = new ArrayList<Edge>();
		List<String> allLines = readFile(path); // extract the lines from file
		if(allLines == null) {
			throw new Exception();
		}
		for(int i = 0; i < allLines.size(); i++) { // run over all the lines
			if(allLines.get(i) == " ") { // not need to be 
				continue;
			}
			else if(allLines.get(i).charAt(0) == 'c') // the first char is c 
				continue; // ignore (its comment)
			else if(allLines.get(i).charAt(0) == 'p') { // the first char is p
				String[] arr = allLines.get(i).split(" "); // split to 4 cells
				numOfVertices = Integer.parseInt(arr[2]); // number of vertices
			}
			else { // the first char is e 
				String[] arr = allLines.get(i).split(" "); // split to 3 cells
				int x = Integer.parseInt(arr[1]); // ver1
				int y = Integer.parseInt(arr[2]); // ver2
				edges.add(new Edge(x,y)); // add edge
			}

		}
		result = new Graph(edges,numOfVertices); //build graph
		return result;
	}
	public static void craeteAndWriteFile(String filename,String toWrite) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename+".hamsol", "UTF-8");
			writer.println(toWrite);
			System.out.println("The file craeted , it's found in directory of project!");
		} catch (Exception e) {
			System.out.println("An error occurred.");
		}
		writer.close();
	}

	private static List<String> readFile(String path)
	{
		List<String> allLines = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = reader.readLine()) != null)
			{
				allLines.add(line);
			}
			reader.close();
			return allLines;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
