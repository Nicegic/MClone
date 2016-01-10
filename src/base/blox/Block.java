package base.blox;

import base.Position;
import base.Chunk;
import base.items.Item;
import java.io.Serializable;

public class Block implements Serializable{					//Die Hauptklasse des Spiels
	private static final long serialVersionUID = -4687238035195433640L;
	int id;
	Chunk locatedInChunk=null;								//der "smarte" Block kennt seinen Chunk ;)
	Position p;
	Facing f;
	boolean interactive;
	byte breakby; // 0=Hand, 1=wood, 2=stone, 3=iron, 4=diamond
	byte breakswith; //0=hand, 1=shovel, 2=pickaxe, 3=axe, 4=nothing
	short breaktime; // nach wie vielen Ticks ist der Block kaputt?
	String name;

	public Block(Position p, Facing f) {				//ID-Konstruktor
		id = 0;
		this.p = p;
		this.f = f;
		interactive = false;
		name = "Block";
		breakby = -1;
		breakswith = 4;
		breaktime = -1;
	}
	
	public Block(Position p, Facing f, Chunk c){	//normaler Konstruktor
		this(p,f);
		this.locatedInChunk=c;
	}

	public boolean destroyable(Item i) {			//kann man den Block mit dem Gegenstand in der Hand abbauen?
		if (this.breakby > i.getBreakfactor()) {
			this.breaktime *= 10;
			return false;
		} else {
			return true;
		}
	}

	public void destroy(Item i) {					//das passiert, wenn der Spieler auf den Block haut
		if(i.getAssignedBlockType()==breakswith){
			breaktime= (short)(breaktime/i.getSpeed());
		}
		destroyable(i);
		this.breaktime--;
		if (breaktime == 0) {
			Item fallen=new Item(locatedInChunk.delete(this.p).name);		//Beim Abbauen transformiert sich der Block in ein gleichnamiges Item ;)
		}
	}
	
	public void setChunk(Chunk c){
		locatedInChunk = c;
	}
	
	public boolean interactive() {							//ist das ein InteractiveBlock?
		return this.interactive;
	}
	
	public Position getPosition(){
		return this.p;
	}
	
	public Facing getFacing(){								//in welche Richtung zeigt der Block?
		return this.f;
	}

	public String toString() {
		return this.name;
	}
}
