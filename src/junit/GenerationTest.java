package junit;

import interaction.BlockTicker;
//import base.blox.Block;
import base.figures.Player;
import base.*;
import generation.*;

public class GenerationTest {

    public static void main(String[] args) {
        IDGenerator.generateIDs();
        WorldGenerator wgen = new WorldGenerator((byte) 12, new BlockTicker());
        Player p = new Player(wgen);
        wgen.start();
        //Block b = wgen.getChunkAt(0, 0).getBlocks().get(0);
        p.deployPlayer(new Position(0.0, 0.0, 60.0));
        p.getPosition().add(80, 'z');
        for (int i = 0; i < 10; i++) {
            p.setPosition(new Position(i * 4, i * 4, 60));
            try {
                Thread.sleep(200);
            } catch (InterruptedException inte) {
            }
        }
        wgen.end();
        p.stopPlayerRegen();
    }
}
