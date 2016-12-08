package model.items;

public class GoldItem extends Item {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6948560675067355700L;
	private final static int attackMod = 20;
	
	public GoldItem() {
		super(false, attackMod, 0, 20.0, null);
	}

	@Override
	public String toString() {
		return "Iron";
	}

}
