package model.furniture;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;
import model.items.WoodItem;

//Author: Maxwell Faridian
//This class defines the Pool Table Furniture, which actors can use to satisfy their happiness need
//Pool table: 4 wood, 3 stone, 1 iron
public class PoolTable extends Furniture {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7516101050603185804L;
	private static int capacity = 4;
	private List<Item> poolTableList;

	public PoolTable() {
		super(capacity, 0, "pool table", null);
		poolTableList = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			poolTableList.add(new WoodItem());
		}
		for (int i = 0; i < 3; i++) {
			poolTableList.add(new StoneItem());
		}
		
		poolTableList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return poolTableList;
	}

	@Override
	public String toString() {
		return "Pool Table";
	}

}
