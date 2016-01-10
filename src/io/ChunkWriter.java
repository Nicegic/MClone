package io;

import base.Chunk;
import java.io.*;

public class ChunkWriter {
	
	public static void write(Chunk c){		//speicher den übergebenen Chunk ab
		try{
			FileOutputStream fos = new FileOutputStream("chunks/"+c.toString()+".chunk");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
			fos.close();
		}catch(FileNotFoundException fnfe){}		//"Fehler sind ausgeschlossen :D"
		catch(IOException ioe){}
	}
}
