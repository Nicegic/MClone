/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generation;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.Random;

/**
 *
 * @author niko
 */
public class HeightMapper {

    private AssetManager assetManager;
    private Node rootNode;
    OpenSimplexNoise osn;

    public HeightMapper(AssetManager assetManager, Node rootNode) {
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        Random seeder = new Random(System.nanoTime());
        this.osn = new OpenSimplexNoise(seeder.nextLong());
    }

    public void generateChunk(int xCoord, int yCoord, int cW, int cL, int cH, Geometry[][][] geom) {
        Box b = new Box(1, 1, 1); // create cube shape

        // Definiere 3 Blockmaterialien
        Material red = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        red.setColor("Color", ColorRGBA.Red);
        red.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));

        Material blue = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        blue.setColor("Color", ColorRGBA.Blue);
        blue.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));

        Material yellow = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        yellow.setColor("Color", ColorRGBA.Yellow);
        yellow.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));

        // Zur Besseren Testansicht: Startpunkt unter 0
        int start = -50;

        Node pivot = new Node("pivot");

        // Unschön gemacht, nur zum Testen wie man die Objekte handhabt so gemacht
        int xOffSet = cW * xCoord;
        int yOffSet = cL * yCoord;

        for (int x = 0; x < cW; x++) {
            for (int y = 0; y < cL; y++) {
                int xPos = x + xOffSet;
                int yPos = y + yOffSet;

                int rand = (int) (osn.eval(xPos / 10.0, yPos / 10.0) * 5 + 10); //max Höhe einer Säule mit SimplexNoise bestimmen

                for (int k = 0; k < rand; k++) {
                    geom[xPos][yPos][k] = new Geometry("Box", b);
                    geom[xPos][yPos][k].setLocalTranslation(new Vector3f(xPos * 2, (start + 2 * k), (-100 + 2 * yPos)));

                    if (k < 2) {
                        geom[xPos][yPos][k].setMaterial(yellow);        // Irgendwie verhaut er bei 3 Farben eine Farbe
                    } else if (k == rand - 1) {
                        geom[xPos][yPos][k].setMaterial(blue);
                    } else {
                        geom[xPos][yPos][k].setMaterial(red);
                    }

                    pivot.attachChild(geom[xPos][yPos][k]);
                }
            }
        }

        rootNode.attachChild(pivot);              // make the cube appear in the scene
    }
}
