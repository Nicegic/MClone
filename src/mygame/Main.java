package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1); // create cube shape
        
        
        // Definiere 3 Blockmaterialien
        Material red = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        red.setColor("Color", ColorRGBA.Red);
        
        Material blue = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        blue.setColor("Color", ColorRGBA.Green);
        
        Material yellow = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        blue.setColor("Color", ColorRGBA.Yellow);
        
        // Zur Besseren Testansicht: Startpunkt unter 0
        int start = -50;
        
        Node pivot = new Node("pivot");
        
        // Unschön gemacht, nur zum Testen wie man die Objekte handhabt so gemacht
        Geometry geom[][][] = new Geometry[16][16][16];
        
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                
                int rand = ((int)(Math.random() * 14)) + 3;
                
                for (int k = 0; k < rand; k++){
                    geom[i][j][k] = new Geometry ("Box", b);
                    geom[i][j][k].setLocalTranslation(new Vector3f(i*2,(start + 2 * k), (-100 + 2 * j)));
                    
                    if (k < 3){
                        geom[i][j][k].setMaterial(blue);        // Irgendwie verhaut er bei 3 Farben eine Farbe
                    }
                    else if (k == rand-1){
                        geom[i][j][k].setMaterial(yellow);
                    }
                    else {
                        geom[i][j][k].setMaterial(red);
                    }
  
                    pivot.attachChild(geom[i][j][k]); 
                }                    
            }
          }
        
       
        rootNode.attachChild(pivot);              // make the cube appear in the scene

    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

