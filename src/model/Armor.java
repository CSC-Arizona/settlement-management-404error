package model;

//Author: Maxwell Faridian
//This class constructs Armor objects, which are themselves extensions of the Item class
public class Armor extends Item {

	private int defenseModifier;
	
	public Armor (boolean edible, int attackModifier, int healthPts, double weight, int defenseMod) {
		super(edible, attackModifier, healthPts, weight);
		this.defenseModifier = defenseMod;
	}
	
	//Returns the amount of health points that this piece of armor adds to an actor when worn by said actor
	public int getDefenseModifier() {
		return this.defenseModifier;
	}

}
