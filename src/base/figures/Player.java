package base.figures;

import base.items.Item;
import base.Position;
import generation.WorldGenerator;
import base.Position2D;
import base.Chunk;

public class Player {				//Der Spieler

    Position p, lookingAt;			//Position und Block, auf den der Spieler guckt
    String name;
    Thread gen;						//autonomer Generierungschecker
    Position2D lastPos, actPos;
    boolean running = true;
    WorldGenerator wgen;
    Chunk actualChunk;

    public Player(WorldGenerator wgen) {
        this.wgen = wgen;
    }

    public void deployPlayer(Position p) {				//Erstgenerierung und Setzen des aktuellen Chunks
        wgen.addToList(this);
        this.p = p;
        actPos = new Position2D((int) p.getX() / 16, (int) p.getY() / 16);
        lastPos = new Position2D(Integer.MAX_VALUE, Integer.MIN_VALUE);
        gen = new Thread() {
            public void run() {
                while (running) {
                    checkRegen();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException inte) {
                    }
                }
            }
        };
        gen.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException inte) {
        }
        actualChunk = wgen.getChunkAt(actPos.getX(), actPos.getY());
        if (actualChunk == null) {
            System.out.println("Spieler ist irgendwie falsch positioniert! (nämlich bei " + actPos + ")");
        }
    }

    public void stopPlayerRegen() {
        running = false;
    }

    private void checkRegen() {					//Überprüfung, ob ne Chunkgrenze überschritten wurde
        lastPos.set(actPos);
        actPos.set((int) p.getX() / 16, (int) p.getY() / 16);
        if (actPos.getX() != lastPos.getX() || actPos.getY() != lastPos.getY()) {
            System.out.println("Position has changed to Chunk " + actPos);
            actualChunk = wgen.getChunkAt(actPos.getX(), actPos.getY());
            if (actualChunk == null) {
                System.out.println("Something is not working in the repositioning");
            }
            regen();
        }
    }

    private void regen() {
        wgen.addToList(this);
    }

    public Item getSelectedItem() {
        return null;
    }

    public Position getPosition() {
        return p;
    }

    public void setPosition(Position p) {
        this.p.set(p);
    }
}
