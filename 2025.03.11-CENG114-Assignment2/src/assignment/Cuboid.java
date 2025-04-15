package assignment;
import java.io.IOException;

public class Cuboid extends Geometry {

    final Point center;
    final double xLength;
    final double yLength;
    final double zLength;

    public Cuboid(double length , Point center){
        this.xLength = length;
        this.yLength = length;
        this.zLength = length;
        this.center = center;
    }
    public Cuboid (double xLength , double yLength , double zLength , Point center){
        this.xLength = xLength;
        this.yLength = yLength;
        this.zLength = zLength;
        this.center = center;
    }
    
    @Override
    public void write(ModelWriter modelWriter) throws IOException {
        Point[] points = new Point[8];
Polygon[] polygons = new Polygon[6];

double start_x = center.x - (xLength / 2.0);
double start_z = center.z - (zLength / 2.0);
double y = center.y;

int index = 0;
for (int i = 0; i < 2; i++) {
    double x = start_x;
    double z = start_z;
    for (int j = 1; j < 4 + 1; j++) {
        switch (j % 4) {
            case 1:
                points[index++] = new Point(x, y, z);
                break;
            case 2:
                x += xLength;
                points[index++] = new Point(x, y, z);
                break;
            case 3:
                z += zLength;
                points[index++] = new Point(x, y, z);
                break;
            default:
                x -= xLength;
                points[index++] = new Point(x, y, z);
                break;
        }
    }
    y += yLength;
}
        
        polygons[0] = new Quadrilateral(0,1,2,3); // Bottom Face
        polygons[1] = new Quadrilateral(7,6,5,4); // Top Face
        polygons[2] = new Quadrilateral(4,5,1,0); // Front Face
        polygons[3] = new Quadrilateral(6,7,3,2); // Back Face
        polygons[4] = new Quadrilateral(0,3,7,4); // Left Face
        polygons[5] = new Quadrilateral(5,6,2,1); // Right Face


        
        modelWriter.write(points, polygons);

        System.out.printf("The cube on (%.3f,%.3f,%.3f) is Added \n", center.x , center.y , center.z);

    }

}
 