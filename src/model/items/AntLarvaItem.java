package model.items;


/**
 * AntLarvaItem is dropped when an AntTunnelBlock is destroyed. It can
 * be eaten for hp or cooked into an AntLarvaPieItem.
 * 
 * @author Katherine Walters
 */
public class AntLarvaItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7965917895844782451L;
	private final static int attackMod = 1;
	private final static int healthPts = 2;
	
	public AntLarvaItem() {
		super(true, attackMod, healthPts, 0.5, null);
	}

	@Override
	public String toString() {
		return "Ant larva";
	}
}
