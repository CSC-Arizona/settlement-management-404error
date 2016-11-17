package model.Items;


/**
 * AntLarvaItem is dropped when an AntTunnelBlock is destroyed. It can
 * be eaten for hp or cooked into an AntLarvaPieItem.
 * 
 * @author Katherine Walters
 */
public class AntLarvaItem extends Item {

	private final static int attackMod = 1;
	private final static int healthPts = 2;
	
	public AntLarvaItem() {
		super(true, attackMod, healthPts, 0.5);
	}
}
