package assignment;


public class Triangle extends Polygon {
	public final int vertex1;
	public final int vertex2;
	public final int vertex3;
	
	public Triangle(int vertex1, int vertex2, int vertex3)
	{
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.vertex3 = vertex3;
	}
	
	
	public String getIndices(int startIndex)
	{
		return (vertex1 + startIndex) + " " + (vertex2 + startIndex) + " " + (vertex3 + startIndex);
	}
		
}
