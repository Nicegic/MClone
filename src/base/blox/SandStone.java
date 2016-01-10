package base.blox;

import base.Position;
import base.Chunk;

public class SandStone extends Block {						//unter dem Sand
	/**
	 * 
	 */
	private static final long serialVersionUID = 4654779546826244019L;

	public SandStone(Position p, Facing f, int id){					//ID-Konstruktor
		super(p,f);
		this.id=id;
		setThings();
	}
	
	public SandStone(Position p, Facing f, int id, Chunk c){		//normaler Konstruktor
		super(p,f,c);
		this.id=id;
		setThings();
	}
	
	private void setThings(){										//immer
		name="Sandstone";
		breakswith=2;
		breakby=1;
		breaktime=50;
	}
}
