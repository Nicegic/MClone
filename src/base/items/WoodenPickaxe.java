package base.items;

public class WoodenPickaxe extends Tool {

	public WoodenPickaxe(String name, int id) {
		super(name);
		this.id=id;
		speed=1.05f;
		durability=64;
		breakfactor = 1;
		assignedBlockType = 2;
	}

}
