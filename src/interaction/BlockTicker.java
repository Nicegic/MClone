package interaction;

import java.util.ArrayList;
import base.Chunk;
import base.blox.Block;
import base.blox.InteractiveBlock;

public class BlockTicker extends Thread {				//Block-Ticker für alle interaktiven Blöcke

    ArrayList<Chunk> chunks;
    ArrayList<Block> chunkBlocks;
    boolean running = true;
    int tickrate;

    public BlockTicker() {
        chunks = new ArrayList<Chunk>();
        chunkBlocks = new ArrayList<Block>();
        tickrate = 50;
    }

    public void run() {
        // while (running) {
        tick();
        try {
            Thread.sleep(20);
        } catch (InterruptedException inte) {
        }
        // }

    }

    private synchronized void tick() { // find all active Chunks and active
        // Blocks and let
        // them do their work
        while (running) {
            for (Chunk c : chunks) {
                if (c.active()) {
                    chunkBlocks = c.getBlocks();
                    for (Block b : chunkBlocks) {
                        if (b.interactive()) {
                            InteractiveBlock ib = (InteractiveBlock) b;
                            if (ib.active()) {
                                ib.doWork();
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(1000 / tickrate);
            } catch (InterruptedException inte) {
            }
        }
    }

    public void stopIt() {
        running = false;
    }

    public void giveChunks(ArrayList<Chunk> chunks) {
        this.chunks = chunks;
    }
}
