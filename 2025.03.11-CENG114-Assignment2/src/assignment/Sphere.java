package assignment;

import java.io.IOException;

public class Sphere extends Geometry {

    final Point center;
    final double radius;
    final int stepCount;

    public Sphere( double radius, int stepCount ,Point center) {
        this.center = center;
        this.radius = radius;
        this.stepCount = stepCount;
    }

    @Override
    public void write(ModelWriter writer) throws IOException {
        Point[] points = new Point[stepCount * stepCount + 2];
        Polygon[] polygons = new Polygon[stepCount + (stepCount - 1) * stepCount + stepCount];

        // Set the poles
        points[0] = new Point(center.x, center.y - radius, center.z); 
        points[points.length - 1] = new Point(center.x, center.y + radius, center.z);

        // Generate surface Points
        int index = 1;
        for (int y = 0; y < stepCount; y++) {

            double alpha = Math.toRadians(-90 + y * (180.0 / stepCount));
            double yCoordinate = center.y + (radius * Math.sin(alpha));

            for (int x = 0; x < stepCount; x++) {

                double beta = Math.toRadians(x * 360.0 / stepCount);
                double xCoordinate = center.x + (radius * Math.cos(alpha) * Math.cos(beta));
                double zCoordinate = center.z + (radius * Math.cos(alpha) * Math.sin(beta));

                points[index++] = new Point(xCoordinate, yCoordinate, zCoordinate);
            }
        }

        int polygonIndex = 0;
        // Bottom Triangles
        for (int i = 0; i < stepCount; i++) {

            int poleB = 0; 
            int v1 = 1 + i; 
            int v2 = 1 + ((i + 1) % stepCount); 

            polygons[polygonIndex++] = new Triangle(poleB, v1, v2);
        }

        // Quadrilaterals
        for (int y = 1; y < stepCount; y++) {
            for (int x = 0; x < stepCount; x++) {

                int vertex1 = (y - 1) * stepCount + x + 1;
                int vertex2 = (y - 1) * stepCount + ((x + 1) % stepCount) + 1;
                int vertex3 = y * stepCount + ((x + 1) % stepCount) + 1; 
                int vertex4 = y * stepCount + x + 1; 

                polygons[polygonIndex++] = new Quadrilateral(vertex1, vertex4, vertex3, vertex2);
            }
        }

        // Top Triangles
        int lastRingStart = (stepCount - 1) * stepCount + 1; 
        for (int i = 0; i < stepCount; i++) {
            int poleTop = points.length - 1; 
            int v1 = lastRingStart + i; 
            int v2 = lastRingStart + ((i + 1) % stepCount); 

            polygons[polygonIndex++] = new Triangle(v1, poleTop, v2);
        }
        writer.write(points, polygons);

        System.out.printf("The Sphere with radius %.2f on (%.3f,%.3f,%.3f) is Added \n", radius, center.x, center.y, center.z);
    }
}
