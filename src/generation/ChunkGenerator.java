package generation;

import base.blox.*;
import base.Chunk;
import base.IDGenerator;
import base.Position;

public class ChunkGenerator {						//Terrain- (und Block-)Generierung

    static OpenSimplexNoise osn = new OpenSimplexNoise();
    static int height, cx, cy;

    public static void generate(Chunk c, String s) { // Generierung abhängig vom Biom
        if (s.equals(Biomes.GRASSLAND.toString())) {
            generateGrassland(c);
        } else if (s.equals(Biomes.BEACH.toString())) {
            generateBeach(c);
        } else if (s.equals(Biomes.DESERT.toString())) {
            System.out.println(s + "not yet implemented");
        } else if (s.equals(Biomes.FOREST.toString())) {
            System.out.println(s + "not yet implemented");
        } else if (s.equals(Biomes.MOUNTAINS.toString())) {
            System.out.println(s + "not yet implemented");
        } else if (s.equals(Biomes.OCEAN.toString())) {
            System.out.println(s + "not yet implemented");
        } else {
            System.out
                    .println("The Biome you are looking for doesn't exist! Generating Block-Terrain....");
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    int height = (int) (osn.eval((x + c.idx) / 16.0,
                            (y + c.idy) / 16.0) * 60) + 60;
                    for (int z = 0; z < height; z++) {
                        c.blocks[x][y][z] = normalGeneration(x, y, z, c.idx,
                                c.idy, c);
                    }
                }
            }
        }
    }

    private static int generateHeight(int x, int y, int cx, int cy) { // Auslagerung des langen Befehls
        return (int) (osn.eval((x + cx) / 16, (y + cy) / 16));
    }

    private static void generateGrassland(Chunk c) {		//Grassland-Biom
        height = 0;
        cx = c.idx;
        cy = c.idy;
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                height = generateHeight(x, y, cx, cy);
                for (int z = 0; z < height; z++) {
                    c.blocks[x][y][z] = normalGeneration(x, y, z, cx, cy, c);			//ist gerade nur Stein
                }
                try {
                    c.blocks[x][y][height] = new Dirt(new Position(x + cx, y + cy, height),
                            Facing.NORTH, getId("Dirt"), c);
                } catch (NullPointerException npe) {
                    System.out.println(npe.getMessage());
                    System.exit(0);
                }
            }//ja, jeder Block kann ne NullPointer werfen, wenn die ID nicht registriert ist. Ist der einfachste Weg, fehlende IDs abzufangen!
        }
        try{c.blocks[8][8][120] = new Furnace(new Position(8+cx,8+cy,120), Facing.NORTH, getId("Furnace"),c);}catch(NullPointerException npe){System.out.println(npe.getMessage());System.exit(0);}
    }

    private static void generateBeach(Chunk c) { // Strand-Biom
        height = 0;
        cx = c.idx;
        cy = c.idy;
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                height = generateHeight(x, y, cx, cy);
                for (int z = 0; z < height - 4; z++) {
                    c.blocks[x][y][z] = normalGeneration(x, y, z, cx, cy, c);
                }
                for (int z = height - 4; z < height - 2; z++) {
                    try {
                        c.blocks[x][y][z] = new SandStone(new Position(x + cx,
                                y + cy, z), Facing.NORTH, getId("SandStone"), c);
                    } catch (NullPointerException npe) {
                        System.out.println(npe.getMessage());
                        System.exit(0);
                    }
                }
                for (int z = height - 2; z <= height; z++) {
                    try {
                        c.blocks[x][y][z] = new Sand(new Position(x + cx, y
                                + cy, z), Facing.NORTH, getId("Sand"), c);
                    } catch (NullPointerException npe) {
                        System.out.println(npe.getMessage());
                        System.exit(0);
                    }
                }
                if (height < 60) {
                    for (int z = height + 1; z <= 60; z++) {
                        try {
                            c.blocks[x][y][z] = new Water(new Position(x + cx,
                                    y + cy, z), Facing.NORTH, getId("Water"), c);
                        } catch (NullPointerException npe) {
                            System.out.println(npe.getMessage());
                            System.exit(0);
                        }
                    }
                }
            }
        }
        c.blocks[8][8][120]=new Furnace(new Position(8+cx,8+cy,120),Facing.NORTH,getId("Furnace"),c);
    }

    private static Block normalGeneration(int x, int y, int z, int cx, int cy, Chunk c) { // Aufgrund noch nicht überlegter Generierungsroutine einfach Steingenerierung
        return new Stone(new Position(x + cx, y + cy, z), Facing.NORTH,
                getId("Stone"), c);
    }

    private static int getId(String s) throws NullPointerException { // ID für den Block finden
        int ret = 0;
        for (int i = 0; i < IDGenerator.allBlocks.size(); i++) {
            if (IDGenerator.allBlocks.get(i).toString().equals(s)) {
                ret = IDGenerator.allIDs.get(i);
            }
        }
        if (ret == 0) {
            throw new NullPointerException("Block " + s + " not found... Did you add it to the config-Data?");
        }
        return ret;
    }
}
