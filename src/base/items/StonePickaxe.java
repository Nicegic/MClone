package base.items;

public class StonePickaxe extends Tool {

	public StonePickaxe(String name, int id) {
		super(name);
		this.id = id;
		breakfactor = 2;
		assignedBlockType = 2;
		speed = 1.2f;
		durability = 128;
	}

}
