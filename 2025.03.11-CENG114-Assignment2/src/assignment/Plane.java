package assignment;

import java.io.IOException;

public class Plane extends Geometry {
	final double A;
	final double B;
	final double D;
	
	final double x1;
	final double y1;
	final double x2;
	final double y2;
	final double step; 

	public Plane(double A, double B, double D, double x1, double y1, double x2, double y2, double step)
	{
		// plane paramaters
		this.A = A;
		this.B = B;
		this.D = D;
		
		// drawing range
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.step = step;
	}
	
	public double evaluate(double x, double y)
	{
		// Ax + By + Cz + D = 0  	(plane equation) 
		// Cz = -(Ax + By + D)
		// z = Ax + By + D     		(for C=-1 as constant)
		
		return A * x + B * y + D;
	}
	
	@Override
	public void write(ModelWriter modelWriter) throws IOException 
	{
		final int xCount = (int)((x2 - x1) / step) + 1;
		final int yCount = (int)((y2 - y1) / step) + 1;
		
		final int pointCount = xCount * yCount ;
		Point[] points = new Point[pointCount];
		
		final int polygonCount = (xCount - 1) * (yCount - 1);
		Polygon[] polygons = new Polygon[polygonCount];		
		
		for (int y=0; y<yCount; y++) {
			double yp = y1 + y * step;
			
			for (int x=0; x<xCount; x++) {
				double xp = x1 + x * step;
				double zp = evaluate(xp, yp);
				
				final int pointIndex = y * xCount + x;
				points[pointIndex] = new Point(xp, yp, zp);
				
				if (x < xCount - 1 && y < yCount - 1) {
					final int vertex1 = pointIndex;
					final int vertex2 = pointIndex + 1;
					final int vertex3 = pointIndex + xCount + 1;
					final int vertex4 = pointIndex + xCount;
					
					final int polygonIndex = y * (xCount - 1) + x;
					polygons[polygonIndex] = new Quadrilateral(vertex1, vertex2, vertex3, vertex4);
				}
			}
		}
		
		modelWriter.write(points, polygons);
	}

}
