package assignment;

public abstract class Polygon {
	
	public abstract String getIndices(int startIndex);
	
	@Override
	public String toString() 
	{
		return "(" + getIndices(0) + ")";
	}	
}
