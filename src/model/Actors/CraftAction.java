package model.Actors;

import java.util.List;

import model.Items.Item;

//Author: Maxwell Faridian
//This class accepts a craftable item as an argument in the constructor, and then crafts that item
public class CraftAction implements Action {
	
	private Item toBeCrafted;
	private List<Item> requiredMaterials;
	
	//TODO: Could we have list of required items passed to Craft Action constructor?
	//If not, I think we want to make craftableItem its own class, so that we can call the getRequiredMaterials method
	//Or, we could use the Craft Menu to find the required materials for an item based off of the String "name" of the item
	public CraftAction(Item craftableItem, List<Item> requiredMaterials) {
		toBeCrafted = craftableItem;
		this.requiredMaterials = requiredMaterials;
	}

	//Could check if actor has all required resources
	//If actor doesn't have resources, send him to store room to get them
	@Override
	public int execute(Actor performer) {
		// TODO Auto-generated method stub
		//Find actor's inventory
		//For loop through required materials, make sure actor has all in inventory
		//Then, use up those items and literally create a new object of the craftableItem that they wanted, and add that 
		//to the actor's inventory
		return 0;
	}

}
