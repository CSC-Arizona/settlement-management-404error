package model;

/**
 * Item is an abstract class that can be extended by any items that can be held
 * in an Inventory. This encompasses resources, armor, and weapons.
 * 
 * @author Katherine Walters
 */
public abstract class Item {
	
    private boolean edible;
    private int attackModifier;
    private int healthPoints;
    private double weight;
    
    public Item(boolean edible, int attackModifier, int healthPts, double weight) {
    	this.edible = edible;
    	this.attackModifier = attackModifier;
    	this.healthPoints = healthPts;
    	this.weight = weight;
    }
    
    /*
     * returns true if this item is edible, false otherwise
     */
    public boolean getIsEdible() {
    	return edible;
    }
    
    /*
     * returns the strength level of this item (how much damage it 
     * inflicts if wielded as a weapon
     */
    public int getAttackModifier() {
    	return attackModifier;
    }
    
    /*
     * returns how many health points this item is worth, if eaten.
     */
    public int getHealthPoints() {
    	return healthPoints;
    }

	/*
	 * returns the weight of the item
	 */
    public double getWeight() {
		return weight;
	}
}
