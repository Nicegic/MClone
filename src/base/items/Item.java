package base.items;

import base.blox.Block;

public class Item {					//alles was rumliegt, in Kisten oder beim Spieler ist
	int id;
	String name;
	byte breakfactor; // Gegenstück zu Block.breakby
	float speed; // um wie viel wird die Abbauzeit reduziert?
	byte assignedBlockType; //Gilt der Geschwindigkeitszuwachs für diese Blockart überhaupt? (Block.breakswith)
	short durability; // wie viele Blöcke kriegst du zerstört?

	public Item() {
		this("Item");
	}

	public Item(String s) {
		id = 0;
		name = s;
		breakfactor = 0;
		speed = 1;
		durability = 0;
		assignedBlockType = 0;
	}

	public byte getBreakfactor() {
		return this.breakfactor;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public byte getAssignedBlockType(){
		return assignedBlockType;
	}

	public void destroyBlock(Block b) {
			b.destroy(this);
	}
	
	public String toString(){
		return this.name;
	}
}
