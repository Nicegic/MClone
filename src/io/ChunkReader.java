package io;

import base.Chunk;
import java.io.*;

public class ChunkReader {								//lädt Chunks aus Dateien
	public static Chunk read(String s) throws FileNotFoundException{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(s);
			ois = new ObjectInputStream(fis);
			Chunk c = (Chunk) ois.readObject();
			if(ois!=null)
				ois.close();
			if(fis!=null)
				fis.close();
			return c;
		} catch (IOException ioe) {						//sollte ein Fehler auftreten, ist davon auszugehen, dass die Datei nicht existiert
			throw new FileNotFoundException ();			//also wird der Chunk (WorldGenerator Ausnahme-Behandlung) neu generiert
		} catch (ClassNotFoundException cnfe) {
			try{fis.close();}catch(IOException ioe){}
			throw new FileNotFoundException();
		}
	}

}
