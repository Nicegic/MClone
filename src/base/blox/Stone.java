package base.blox;

import base.Chunk;
import base.Position;

public class Stone extends Block {									//einer der Hauptblöcke

	private static final long serialVersionUID = -3477636466732458927L;

	public Stone(Position p, Facing f, int id, Chunk c) {			//ID-Konstruktor
		super(p, f, c);
		this.id = id;
		setThings();
	}

	public Stone(Position p, Facing f, int id) {					//normaler Konstruktor
		super(p, f);
		this.id = id;
		setThings();
	}

	private void setThings() {										//immer
		name = "Stone";
		breakswith = 2;
		breakby = 1;
		breaktime = 64;
	}

}
