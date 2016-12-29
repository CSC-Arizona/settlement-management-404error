package model.weapons;

import java.util.LinkedList;
import java.util.List;

import model.items.AntLarvaItem;
import model.items.Item;
import model.items.StoneItem;

//Author: Maxwell Faridian
//This class defines the AntSword, which ants carry on them as their weapon
//Actors should not be able to craft an AntSword
public class AntSword extends Weapon{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6693266367268802009L;
	private final static int attackMod = 17;
	private List<Item> basicAntSwordList;
	
	//TODO: Test cases

	public AntSword() {
		super(attackMod, 0, 18.0, "Ant sword", null);
	}

	public List<Item> getRequiredMaterials() {
		basicAntSwordList = new LinkedList<>();
		basicAntSwordList.add(new StoneItem());
		basicAntSwordList.add(new StoneItem());
		basicAntSwordList.add(new AntLarvaItem());
		basicAntSwordList.add(new AntLarvaItem());
		return basicAntSwordList;
	}

}
