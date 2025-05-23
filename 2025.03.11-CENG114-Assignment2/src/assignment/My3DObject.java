package assignment;

import java.io.IOException;

public class My3DObject extends Geometry {
    final private Point center;
    final private double radius;
    final private double height;
    final private int  stepCount;

    public My3DObject(double radius, double height, int stepCount , Point center) {
        this.radius = radius;
        this.height = height;
        this.stepCount = Math.max(stepCount , 3);
        this.center = center;
    }

    @Override
    public void write(ModelWriter modelWriter) throws IOException{
        Point[] points = new Point[(stepCount * 2 )  +1 +1];
        Polygon[] polygons = new Polygon[(stepCount) + (stepCount *2)];
        double sliceDegree = 360.0 / stepCount;
        double sliceRadian = Math.toRadians(sliceDegree);
        double r = radius;
        double centerX = center.x;
        double centerY = center.y;
        double centerZ = center.z;

        double currentAngle = 0;

        // Setting surface Points
        for (int i = 0; i < stepCount*2 ; i+= 2){

            double x = r * Math.cos(currentAngle);
            double z = r * Math.sin(currentAngle);
            points[i] = new Point(x + centerX , 0 + centerY , z + centerZ);
            points[i+1] = new Point(x + centerX , height + centerY , z + centerZ);

            currentAngle += sliceRadian;
        }

        // head and botton point
        points[stepCount*2] = new Point(0 + centerX, height + (r / Math.cos(Math.toRadians(50))) + centerY , 0 + centerZ);
        points[stepCount*2 +1] = center;

        //Making surface quadrilaterals
        int index = 0;
        for (int i = 0 ; i < (stepCount-1) * 2 ; i+=2){
            polygons[index ++] = new Quadrilateral(i, i+1 , i+3 , i+2);
        }
        polygons[stepCount-1] = new Quadrilateral( (stepCount-1) *2,(stepCount*2) -1  , 1  , 0);
        
        //Making head triangles
        index = stepCount;
        for (int i = 1; i < stepCount*2 -1; i+= 2) {
            polygons[index++] = new Triangle( i+2, i , stepCount*2);
        }
        polygons[stepCount*2 -1] = new Triangle(stepCount*2-1, stepCount*2 ,1) ;

        //Making bottom triangles
        index = stepCount*2;
        for (int i = 0; i < (stepCount-1) * 2 ; i+=2) {
            polygons[index++] = new Triangle(i, i+2 , stepCount*2 +1);
        }

        polygons[stepCount*3 -1] = new Triangle(0 , stepCount*2 +1, (stepCount-1)*2) ;

        modelWriter.write(points, polygons);

        System.out.printf("The Pen with radius %.2f and height %.2f on (%.3f,%.3f,%.3f) is Added \n", radius,height, center.x, center.y, center.z);


    }
    


}
