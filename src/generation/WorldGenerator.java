package generation;

import io.ChunkReader;
import io.ChunkWriter;
import interaction.BlockTicker;
import java.util.Date;
import java.util.ArrayList;
import base.figures.Player;
import base.*;
import java.io.FileNotFoundException;

public class WorldGenerator extends Thread {

    ArrayList<Player> waitList; 				// die Warteliste für ankommenden Generierungsanfragen
    ArrayList<Chunk> loadedChunks; 				// alle geladenen Chunks
    ArrayList<Position2D> chunkPositions; 		// alle Positionen der aktuell geladenen Chunks
    Position2D actpos; 							// Position des aktuellen Chunks
    byte sight; 								// Sichtweite des Spielers
    boolean running = true;
    OpenSimplexNoise osn;
    BlockTicker bt;

    public WorldGenerator(byte sight, BlockTicker bt) { // Block-Ticker wird vom Generator mit den aktuellen Chunks versorgt! Daher hier initialisiert
        this.bt = bt;
        this.sight = sight;
        waitList = new ArrayList<Player>();
        loadedChunks = new ArrayList<Chunk>();
        chunkPositions = new ArrayList<Position2D>();
        osn = new OpenSimplexNoise();
        actpos = new Position2D(0,0);
    }

    public void run() {
        while (running) {
            if (!waitList.isEmpty()) { 				// ersten aus der Warteliste mit neuen Chunks versorgen
                updateGeneration(waitList.get(0)); 	// Generierung durchführen
                waitList.remove(0); 				// aus der Liste entfernen
            }
            try {
                Thread.sleep(50); 					// den Prozessor für 50ms in Ruhe lassen, damit andere Sachen Platz haben
            } catch (InterruptedException inte) {
                running = false;
            }
        }
        for (int i = waitList.size() - 1; i >= 0; i--) { 	// beim Schließen des Spiels noch einmal die aktuelle
            // Generation für alle Anfragen durchführen
            updateGeneration(waitList.get(i));
            waitList.remove(i);
        }
        saveChunks(); // alle noch geladenen Chunks abspeichern

    }

    public void end() { // selbsterklärend
        running = false;
    }

    public void addToList(Player p) { // Spieler registriert sich in der Warteliste für die Generation
        waitList.add(p);
    }

    private void updateGeneration(Player p) { // Hauptgenerierungsmethode
        boolean drin = false;
        int actualx = (int) p.getPosition().getX();
        actualx -= actualx % 16;
        int actualy = (int) p.getPosition().getY();
        actualy -= actualy % 16;
        int minx = actualx - (16 * sight);
        int miny = actualy - (16 * sight);
        int maxx = actualx + (16 * sight);
        int maxy = actualy + (16 * sight); // Randwerte für Generierungsbereich festgelegt
        int chunkx, chunky;
        chunkx = maxx;
        chunky = maxy;
        Date start = new Date();
        while (chunkx >= minx) {
            while (chunky >= miny) { // für jeden möglichen dort drin
                // befindlichen Chunk
                drin = false;
                actpos.set(chunkx, chunky);
                for (int i = 0; i < chunkPositions.size(); i++) { 	// schon geladener Chunk in der Liste?
                    if (actpos.equals(chunkPositions.get(i))) {
                        drin = true;
                        break;
                    }
                }
                if (!drin) { 										// gucken, ob schon Datei dafür existiert
                    String s = "chunks/" + chunkx + "." + chunky + ".chunk";
                    try {
                        Chunk c = ChunkReader.read(s);
                        loadedChunks.add(c);
                        chunkPositions.add(c.getPosition());
                    } catch (FileNotFoundException fnfe) { 			// wenn keine Datei existiert, neu generieren
                        Chunk c = new Chunk(new Position2D(actpos));
                        c.generateChunk("GRASSLAND"); 				// momentan auf Grasland fixiert, hier werden dann Biome verändert
                        chunkPositions.add(c.getPosition());
                        loadedChunks.add(c);
                    }
                }
                chunky -= 16;
            }
            chunky = maxy;
            chunkx -= 16;
        }
        for (int i = 0; i < chunkPositions.size(); i++) { // alle inaktiven und außerhalb des Sichtbereichs liegenden Chunks löschen
            actpos = chunkPositions.get(i);
            if (actpos.getX() < minx || actpos.getX() > maxx
                    || actpos.getY() < miny || actpos.getY() > maxy) {
                if (!loadedChunks.get(i).active()) {
                    ChunkWriter.write(loadedChunks.get(i));
                    chunkPositions.remove(i);
                    loadedChunks.remove(i);
                    i--;
                }
            }
        }
        System.out.println("Regen-Duration: " //Zeitzähler zum Testen der Generierungsgeschwindigkeit
                + (new Date().getTime() - start.getTime()));
        bt.giveChunks(loadedChunks);
    }

    public Chunk getChunkAt(int i, int j) {
        i*=16;
        j*=16;
        for (int t = 0; t < chunkPositions.size(); t++) {
            Position2D pos = chunkPositions.get(t);
            if (i == pos.getX() && j == pos.getY()) {
                return loadedChunks.get(t);
            }
        }
        return null;
    }

    private void saveChunks() {								//selbsterklärend
        for (int i = 0; i < loadedChunks.size(); i++) {
            ChunkWriter.write(loadedChunks.get(i));
        }
    }
}