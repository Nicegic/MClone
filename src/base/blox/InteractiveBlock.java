package base.blox;

import base.Chunk;
import base.Position;

public abstract class InteractiveBlock extends Block{		//eine "Interface"-Klasse, die selber nichts tut, sondern nur ein Wrapper für alle
															//interaktiven Blöcke ist

	private static final long serialVersionUID = -4417654089789506233L;
	boolean active=false;

	public InteractiveBlock(Position p, Facing f) {
		super(p, f);
		interactive=true;
	}

	public InteractiveBlock(Position p, Facing f, Chunk c) {
		super(p, f, c);
		interactive=true;
	}

	public abstract void doWork();
	
	public boolean active(){
		return active;
	}
	
	public void setActive(){
		if(!active){
			active=true;
			locatedInChunk.countUp();
		}
	}
	public void setInactive(){
		if(active){
			active=false;
			locatedInChunk.countDown();
		}
	}

}
