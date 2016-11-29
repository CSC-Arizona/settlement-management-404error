package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;
import model.Items.WoodItem;

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
		super(capacity, 0, "pool table");
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
