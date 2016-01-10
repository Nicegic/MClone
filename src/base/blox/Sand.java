package base.blox;

import base.Position;
import base.Chunk;

public class Sand extends Block {			//erster fallender Block, allerdings ist das noch nicht implementiert :3
	
	private static final long serialVersionUID = 8247868025579126139L;

	public Sand(Position p, Facing f, int id) {					//ID-Konstruktor
		super(p, f);
		this.id = id;
		setThings();
	}

	public Sand(Position p, Facing f, int id, Chunk c) {		//normaler Konstruktor
		super(p, f, c);
		this.id = id;
		setThings();
	}

	private void setThings() {									//immer
		breakswith = 1;
		breakby = 0;
		breaktime = 48;
		name = "Sand";
	}
}
