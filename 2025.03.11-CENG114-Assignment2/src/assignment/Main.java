package assignment;

import java.io.IOException;

public class Main {

    // https://www.meshlab.net/
    public static void main(String[] args) throws IOException {
        {
            Point centerShape1 = new Point(0, 0, 0);

            Geometry[] geometries = {
                // new Plane(1.0, 2.0, 5.0, -5.0, -5.0, 5.0, 5.0, 0.5),	
                // new Plane(1.0, 0.5, -1.0, 10.0, 10.0, 15.0, 15.0, 1.0),
                // new Torus( ... ), 

                // // table
                // new Cuboid(100 ,1 , 100 , new Point(0,-1,0)),
                // new Cuboid(2,30,2 , new Point(48, -31 ,48)),
                // new Cuboid(2,30,2 , new Point(-48, -31 , -48)),
                // new Cuboid(2,30,2 , new Point(-48, -31 , 48)),
                // new Cuboid(2,30,2 , new Point(48,  -31 , -48)),

                // //shapes
                // new Sphere(10, 100  , new Point(10 * Math.sqrt(2), 10, 10 * Math.sqrt(2))),
                // new Cube(20 , new Point(10 * Math.sqrt(2), 0, -10 * Math.sqrt(2))),
                // new Cylinder(10 , 20, 20 , new Point(-10 * Math.sqrt(2), 0, 10 * Math.sqrt(2))),
                // new My3DObject(10 , 40, 20, new Point(-10 * Math.sqrt(2), 0, -10 * Math.sqrt(2)))
                // new My3DObject(6, 45, 50, new Point(4, 0, 0)),
                // new Sphere( 10, 50,new Point(0, 0, 6)),
                // new Sphere( 10, 50,new Point(0, 0, -6))

                new Diamond(5, 5, new Point (0,0,0))
			};

            String fileName = "scene.obj";
            ModelWriter modelWriter = new ModelWriter(fileName);

            for (Geometry geometry : geometries) {
                geometry.write(modelWriter);
            }

            modelWriter.close();
        }
    }

}
