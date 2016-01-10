package base.blox;

import base.Position;
import base.Chunk;

public class Dirt extends Block{
	private static final long serialVersionUID = 5523592048916064177L;

	public Dirt(Position p, Facing f, int id){			//ID-Konstruktor
		super(p,f);
		this.id=id;
		setThings();
	}
	
	public Dirt(Position p, Facing f, int id, Chunk c){	//normaler Konstruktor	
		super(p,f,c);
		this.id=id;
		setThings();
	}
	
	private void setThings(){						//wird immer ausgeführt
		name="Dirt";
		breakswith=1;
		breakby=0;
		breaktime = 50;
	}
}
