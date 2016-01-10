package base.items;

public class IronOre extends Item {

	public IronOre(String s, int id) {
		super(s);
		this.id = id;
		breakfactor = 0;
		assignedBlockType = 0;
		speed = 1;
		durability = Short.MIN_VALUE;
		name = "IronOre";
	}

}
