package assignment;

import java.io.IOException;

public class Diamond extends Geometry {
    final double radius;
    final Point center;
    final int stepCount;
    public Diamond(double radius , int stepCount, Point center){
        this.center = center;
        this.radius = radius;
        this.stepCount =stepCount;
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
            points[i] = new Point(x + centerX , radius + centerY , z + centerZ);
            points[i+1] = new Point(x + centerX , radius*2 * 0.33 / Math.cos(Math.toRadians(45)) + centerY , z + centerZ);

            currentAngle += sliceRadian;
        }

        // head and botton point
        points[stepCount*2] =center;
        points[stepCount*2 +1] =  new Point(0 + centerX, radius*2 + centerY , 0 + centerZ);

        //Making surface quadrilaterals
        int index = 0;
        for (int i = 0 ; i < (stepCount-1) * 2 ; i+=2){
            polygons[index ++] = new Quadrilateral(i+2 , i+3 , i+1 , i);
        }
        polygons[stepCount-1] = new Quadrilateral( (stepCount*2) -1, (stepCount-1) *2, 0  , 1);
        
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

        // System.out.printf("The Pen with radius %.2f and height %.2f on (%.3f,%.3f,%.3f) is Added \n", radius,height, center.x, center.y, center.z);


    }

}
