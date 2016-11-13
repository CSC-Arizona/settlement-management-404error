package model;

import java.util.LinkedList;
import java.util.List;

public class AntTunnelBlock extends BuildingBlock {

	private final static int durability = 7;
	private List<Item> itemsInBlock;
	
	public AntTunnelBlock() {
		super(durability, true, true);
		itemsInBlock = new LinkedList<>();
		itemsInBlock.add(new AntLarvaItem());
	}

	@Override
	public List<Item> lootBlock() {
		return itemsInBlock;
	}

}
