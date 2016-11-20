package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;

//Author: Maxwell Faridian
//This class defines the Milling Machine Furniture, which will be used in the kitchen
//Milling Machine: 2 stone, 2 iron
public class MillingMachine extends Furniture{

	private static int capacity = 0; //This value should be irrelevant since Milling Machine is decorative
	private List<Item> millingMachineList;
	
	public MillingMachine() {
		super(capacity);
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
}
