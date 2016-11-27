package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.IronItem;
import model.Items.Item;
import model.Items.StoneItem;

//Author: Maxwell Faridian
//This class defines an Incubation Chamber, which exists in an Incubation Room,
//An egg item is placed in an incubation chamber to start the hatching process
//Incubation Chamber: 2 iron, 2 stone
public class IncubationChamber extends Furniture {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7475523515065091093L;
	
	//Only 1 egg can be placed in an incubation chamber at a time
	private static int capacity = 1;
	private final static int weightCapacity = 3; //If capacity is full, then egg is already in chamber
	private final static String id = "incubation chamber";
	private List<Item> incubationChamberList;

	public IncubationChamber() {
		super(capacity, weightCapacity, id);
		incubationChamberList = new LinkedList<>();
		incubationChamberList.add(new IronItem());
		incubationChamberList.add(new IronItem());
		incubationChamberList.add(new StoneItem());
		incubationChamberList.add(new StoneItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return incubationChamberList;
	}

}
