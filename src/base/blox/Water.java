package base.blox;

import base.Position;
import base.Chunk;

public class Water extends Block implements Fluid{				//erstes Fluid

	private static final long serialVersionUID = -4331053607721858677L;

	public Water(Position p, Facing f, int id) {				//ID-Konstruktor
		super(p, f);
		this.id=id;
		setThings();
	}
	
	public Water(Position p, Facing f, int id, Chunk c){		//normaler Konstruktor
		super(p,f,c);
		this.id=id;
		setThings();
	}
	
	private void setThings(){									//immer
		breakswith=4;
		breakby=5;
		breaktime=0;
		name="Water";
	}
			
	public void flow(){											//wie auch immer.... :3
		System.out.println("Not flowing yet!");
	}

}
