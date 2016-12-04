package model.armor;

import java.util.LinkedList;
import java.util.List;

import model.items.AntLarvaItem;
import model.items.IronItem;
import model.items.Item;

//Author: Maxwell Faridian
//This class defines the Ant Armor, which ants wear and are dropped when an ant dies
public class AntArmor extends Armor{
	
	/**
	 * 
	 */
	
	//TODO: Test cases
	
	private static final long serialVersionUID = 6162833299006847256L;
	private final static int attackMod = 10;
	private final static int defenseMod = 10;
	private List<Item> antArmorList;

	public AntArmor() {
		super(false, attackMod, 0, 20.0, defenseMod, "Ant armor");
		antArmorList = new LinkedList<>();
		antArmorList.add(new IronItem());
		antArmorList.add(new IronItem());
		antArmorList.add(new AntLarvaItem());
		antArmorList.add(new AntLarvaItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return antArmorList;
	}

}
