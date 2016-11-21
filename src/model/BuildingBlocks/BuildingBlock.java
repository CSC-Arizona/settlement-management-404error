package model.BuildingBlocks;

import java.awt.Color;
import java.util.List;

import model.Items.Item;

/**
 * BuildingBlock is an abstract class that is extended by all the different
 * kinds of blocks that make up a Map.
 * 
 * @author Katherine Walters
 */
public abstract class BuildingBlock {

	// durability represents the amount of time(?) required to destroy the block
	private int durability;
	// represents whether the block can be destroyed (air and cavern) cannot be
	// destroyed
	private boolean destroyable;
	// represents whether or not an actor or crafted item can occupy the block
	private boolean occupiable;

	private Color color;
	private String id;
	
	public BuildingBlock(int durability, boolean destroyable,
			boolean occupiable, Color color, String id) {// , List<Item>
															// itemsInBlock) {
		this.durability = durability;
		this.destroyable = destroyable;
		this.occupiable = occupiable;
		this.color = color;
		this.id = id;
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

	public Color getColor() {
		return color;
	}

	public String getID() {
		return id;
	}

	/*
	 * returns a list of the items the BuildingBlock drops if it is destroyed,
	 * or null if it drops nothing.
	 */
	abstract public List<Item> lootBlock();
	
}