package base.items;

public class IronIngot extends Item {

	public IronIngot(String s, int id) {
		super(s);
		this.id = id;
		name = "IronIngot";
		breakfactor = 0;
		assignedBlockType = 0;
		durability = Short.MIN_VALUE;
		speed = 1;
	}

}
