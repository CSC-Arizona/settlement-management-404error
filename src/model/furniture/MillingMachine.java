package model.furniture;

import java.util.LinkedList;
import java.util.List;

import model.items.IronItem;
import model.items.Item;
import model.items.StoneItem;

//Author: Maxwell Faridian
//This class defines the Milling Machine Furniture, which will be used in the kitchen
//Milling Machine: 2 stone, 2 iron
public class MillingMachine extends Furniture{

	/**
	 * 
	 */
	private static final long serialVersionUID = 689879412991241956L;
	private static int capacity = 0; //This value should be irrelevant since Milling Machine is decorative
	private List<Item> millingMachineList;
	
	public MillingMachine() {
		super(capacity, 0, "milling machine");
		millingMachineList = new LinkedList<>();
		millingMachineList.add(new StoneItem());
		millingMachineList.add(new StoneItem());
		millingMachineList.add(new IronItem());
		millingMachineList.add(new IronItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return millingMachineList;
	}

	@Override
	public String toString() {
		return "Milling Machine";
	}
}
