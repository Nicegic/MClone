package base;

import base.blox.*;
import java.util.ArrayList;
import generation.ChunkGenerator;
import java.io.Serializable;

public class Chunk implements Serializable {

    private static final long serialVersionUID = -5001308696799129959L;
    short activecounter;					//wie viele Blöcke sind aktiv?
    boolean active;							//ist demzufolge etwas aktives in dem Chunk?
    public Block[][][] blocks;
    Position2D pos;
    public int idx, idy;

    public Chunk() {					//Testkonstruktor
        activecounter = 0;
        active = false;
        blocks = new Block[16][16][256];
    }

    public Chunk(Position2D pos) {		//Generierungskonstruktor
        this();
        this.pos = pos;
        idx = pos.getX();
        idy = pos.getY();
    }

    public ArrayList<Block> getBlocks() {
        ArrayList<Block> blocksl = new ArrayList<Block>();
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 256; z++) {
                    if (blocks[x][y][z] != null) {
                        blocksl.add(blocks[x][y][z]);
                    }
                }
            }
        }
        return blocksl;
    }

    public void generateChunk(String s) {			//wirft den Generierungsvorgang für den speziellen Chunk an
        ChunkGenerator.generate(this, s);
    }

    public void countUp() {							//jeder aktive Block setzt den Counter einen hoch
        activecounter++;
        if (!active) {
            active = true;
        }
    }

    public Block blockAt(int x, int y, int z) {		//für Interaktion mit dem Spieler später wichtig
        return blocks[x][y][z];
    }

    public void countDown() {						//Gegenstück zu CountUp
        activecounter--;
        if (activecounter < 0) {
            System.out.println("Something went inactive too often....");
            activecounter = 0;
        }
        if (activecounter == 0) {
            active = false;
        }
    }

    public boolean active() {						//sind Blöcke aktiv?
        return active;
    }

    public Block delete(Position p) {					//wird ein Block zerstört, wird er aus dem Chunk entfernt und zurückgegeben für weitere Verwendung als Item
        Block b = blocks[(int) p.getX()][(int) p.getY()][(int) p.getZ()];
        blocks[(int) p.getX()][(int) p.getY()][(int) p.getZ()] = null;
        return b;
    }

    public Position2D getPosition() {				//ChunkPosition
        return pos;
    }

    @Override
    public String toString() {						//zum laden und abspeichern
        return "" + this.idx + "." + this.idy;
    }
}
