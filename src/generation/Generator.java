/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generation;

/**
 *
 * @author Nicolas
 */
import base.IDGenerator;
import base.Position;
import base.figures.Player;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import base.Chunk;
import base.blox.Block;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import interaction.BlockTicker;
import java.util.ArrayList;

public class Generator extends Thread {

    private AssetManager assetManager;
    private Node rootNode;
    private Material red, blue, yellow;
    private WorldGenerator wgen;
    private Player p;                       //ist für Testzwecke drin, muss noch ausgegliedert werden!
    private Box box;
    private Node pivot;
    private Geometry geo;

    public Generator(AssetManager assetManager, Node rootNode) {
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        red = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        red.setColor("Color", ColorRGBA.Red);
        red.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));

        blue = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        blue.setColor("Color", ColorRGBA.Blue);
        blue.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));

        yellow = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        yellow.setColor("Color", ColorRGBA.Yellow);
        yellow.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        pivot = new Node("pivot");
        box = new Box(1,1,1);
        geo = new Geometry("Box",box);
    }

    public void run() {

        IDGenerator.generateIDs();
        wgen = new WorldGenerator((byte) 1, new BlockTicker());
        p = new Player(wgen);
        wgen.start();
        p.deployPlayer(new Position(0.0, 0.0, 60.0));
        /*p.getPosition().add(80, 'z');
        for (int i = 0; i < 1; i++) {
            p.setPosition(new Position(i * 4, i * 4, 60));
            try {
                Thread.sleep(200);
            } catch (InterruptedException inte) {
            }
        }*/
    }
    public void renderIt(){
        for(Chunk c:wgen.loadedChunks){
            ArrayList<Block> blocks=c.getBlocks();
            for(Block b:blocks){
                geo = new Geometry("Box",box);
                if(b.getPosition().getZ()<3){
                    geo.setMaterial(blue);
                }else if(5<b.getPosition().getZ()&&b.getPosition().getZ()<60){
                    geo.setMaterial(yellow);
                }else{
                    geo.setMaterial(red);
                }
                geo.setLocalTranslation((float)(b.getPosition().getX()),(float)(b.getPosition().getY()),(float)(b.getPosition().getZ()-120));
                pivot.attachChild(geo);
                rootNode.attachChild(pivot); 
            }
        }
    wgen.end();
    p.stopPlayerRegen();
    }
}
