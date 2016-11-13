package model;

import java.util.List;

/**
 * BuildingBlock is an abstract class that is extended by all the different kinds of blocks
 * that make up a Map.
 * 
 * @author Katherine Walters
 */
public abstract class BuildingBlock {
	
	// durability represents the amount of time(?) required to destroy the block
    private int durability;
    // represents whether the block can be destroyed (air and cavern) cannot be destroyed
    private boolean destroyable;
    // represents whether or not an actor or crafted item can occupy the block
    private boolean occupiable;
    
    public BuildingBlock(int durability, boolean destroyable, boolean occupiable) {//, List<Item> itemsInBlock) {
    	this.durability = durability;
    	this.destroyable = destroyable;
    	this.occupiable = occupiable;
    }
    
    /*
     * returns the durability level of the BuildingBlock
     */
    public int getDurability() {
    	return durability;
    }
    
    /*
     * returns whether or not the BuildingBlock is lootable (can be destroyed)
     */
    public boolean isDestroyable() {
    	return destroyable;
    }
    
    /*
     * returns true is an actor or constructed item can occupy the space taken 
     * up by this BuildingBlock, and false otherwise
     */
    public boolean isOccupiable() {
    	return occupiable;
    }
    
    /*
     * returns a list of the items the BuildingBlock drops if it is destroyed,
     * or null if it drops nothing.
     */
    abstract public List<Item> lootBlock();
}
