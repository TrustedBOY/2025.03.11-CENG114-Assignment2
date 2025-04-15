package assignment;

import java.io.IOException;

public class Cube extends Geometry {

    final Point center;
    final double radius;
    final double length;

    public Cube(double length, Point center) {
        this.radius = length * Math.cos(Math.toRadians(45));
        this.center = center;
        this.length = length;
    }

    @Override
    public void write(ModelWriter modelWriter) throws IOException {
        int stepCount = 4;
        Point[] points = new Point[10];
        Polygon[] polygons = new Polygon[12];
        double sliceDegree = 90;
        double sliceRadian = Math.toRadians(sliceDegree);
        double r = radius;
        double centerX = center.x;
        double centerY = center.y;
        double centerZ = center.z;

        double currentAngle = 0;
        double rotationAngle = Math.toRadians(45);
        double cosTheta = Math.cos(rotationAngle);
        double sinTheta = Math.sin(rotationAngle);

        // Setting surface Points
        for (int i = 0; i < stepCount * 2; i += 2) {
            double x = r * Math.cos(currentAngle);
            double z = r * Math.sin(currentAngle);

            double rotatedX = x * cosTheta - z * sinTheta;
            double rotatedZ = x * sinTheta + z * cosTheta;

            points[i] = new Point(rotatedX + centerX, 0 + centerY, rotatedZ + centerZ);
            points[i + 1] = new Point(rotatedX + centerX, length + centerY, rotatedZ + centerZ);

            currentAngle += sliceRadian;
        }

        // head and bottom point
        points[stepCount * 2] = new Point(0 + centerX, length + centerY, 0 + centerZ);
        points[stepCount * 2 + 1] = center;

        //Making surface quadrilaterals
        int index = 0;
        for (int i = 0; i < (stepCount - 1) * 2; i += 2) {
            polygons[index++] = new Quadrilateral(i, i + 1, i + 3, i + 2);
        }
        polygons[index++] = new Quadrilateral((stepCount - 1) * 2, (stepCount * 2) - 1, 1, 0);

        //Making head triangles
        index = stepCount;
        for (int i = 1; i < stepCount * 2 - 1; i += 2) {
            polygons[index++] = new Triangle(i + 2, i, stepCount * 2);
        }
        polygons[index++] = new Triangle(stepCount * 2 - 1, stepCount * 2, 1);

        //Making bottom triangles
        for (int i = 0; i < (stepCount - 1) * 2; i += 2) {
            polygons[index++] = new Triangle(i, i + 2, stepCount * 2 + 1);
        }

        polygons[index++] = new Triangle(0, stepCount * 2 + 1, (stepCount - 1) * 2);

        modelWriter.write(points, polygons);
        System.out.printf("The Cube with length %.2f on (%.3f,%.3f,%.3f) is Added \n", length, center.x, center.y, center.z);

    }
}
