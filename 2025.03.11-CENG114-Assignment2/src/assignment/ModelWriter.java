package assignment;

import java.io.*;

public class ModelWriter {
	private BufferedWriter writer;
	private int vertexIndex = 0;
	
	public ModelWriter(String fileName)
	{
		try {
			writer = new BufferedWriter(new FileWriter(fileName));		
		}
		catch(IOException e) {
			System.out.println(e);
			
			writer = null;
		}
	}
	
	public void write(Point[] points, Polygon[] polygons) throws IOException
	{
		for (Point point : points) {
			writer.write("v " + point + "\n");
		}
		
		for (Polygon polygon : polygons) {
			writer.write("f " + polygon.getIndices(vertexIndex + 1) + "\n");
		}
		
		vertexIndex += points.length;
	}

	public void close() throws IOException
	{
		if (writer != null) {
			writer.close();
			
			writer = null;
		}
	}
	
}
