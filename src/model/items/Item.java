package model.items;

import images.ImageEnum;

import java.io.Serializable;

/**
 * Item is an abstract class that can be extended by any items that can be held
 * in an Inventory. This encompasses resources, armor, and weapons.
 * 
 * @author Katherine Walters
 * 
 * Maxwell Faridian: Any item can be used as a weapon, but we also have specific weapons that can be built
 * 
 * Weapons
 * Basic Stone Axe: 2 wood and 1 stone
 * Fortified Stone Axe: 2 wood, 2 stone
 * Basic Iron Axe: 2 wood, 1 stone, 1 iron
 * Fortified Iron Axe: 2 wood, 1 stone, 2 iron
 * Lure Axe (lures ants to you): 2 wood, 1 stone, 1 antLarvaItem
 * Basic sword: 1 wood, 2 stone, 2 iron
 * Ultra sword (best weapon): 1 wood, 3 stone, 3 iron
 * Do we want weapons to only have a certain number of uses or attacks?
 * 
 * Armor (Do I want to create an Armor class that extends Item and adds a defenseModifier?)
 * Wood Shield: 3 wood
 * Stone Shield: 3 stone
 * Iron Shield: 3 iron
 * Great Shield: 1 wood, 1 stone, 2 iron
 * Wood Chest Plate: 4 wood
 * Stone Chest Plate: 4 stone
 * Iron Chest Plate: 4 iron
 * Great Chest Plate: 2 wood, 2 stone, 3 iron
 * 
 * Furniture (doesn't need to extend item, but you do want capacity for actors, get required materials function)
 * Planting plot extends furniture
 * Bed: 4 wood, 2 stone, 4 wheat stems
 * Healing Bed: 4 wood, 2 stone, 5 wheat stems, 1 iron
 * Couch (for entertainment room): 4 wood, 1 stone, 4 wheat stems 
 * Pool table: 4 wood, 3 stone, 1 iron
 * Fireplace (for kitchen): 4 stone, 2 iron
 * Milling Machine: 2 stone, 2 iron
 * Wheat Plant Plot: 2 Wheat Kernels, 2 Ant Larva 
 * Apple Tree Plant Plot: 2 Apple Seeds, 2 Ant Larva
 * Basic Crate (6 wood): 30lb weight capacity
 * Reinforced Crate (6 wood, 2 stone): 50lb weight capacity
 * Metal Crate (6 wood, 2 stone, 2 iron): 70lb weight capacity
 */
public abstract class Item implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8597391980298465998L;
	private boolean edible;
    private int attackModifier;
    private int healthPoints;
    private double weight;
    private ImageEnum image;
    
    public Item(boolean edible, int attackModifier, int healthPts, double weight, ImageEnum image) {
    	this.edible = edible;
    	this.attackModifier = attackModifier;
    	this.healthPoints = healthPts;
    	this.weight = weight;
    	this.image = image;
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
    
    public ImageEnum getImage() {
    	return image;
    }
    
    abstract public String toString();
}
