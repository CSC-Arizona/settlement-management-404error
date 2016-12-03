package model.building_blocks;

import java.awt.Color;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import controller.Designation;
import images.ImageEnum;
import model.actors.Actor;
import model.furniture.Furniture;
import model.items.Item;

/**
 * BuildingBlock is an abstract class that is extended by all the different
 * kinds of blocks that make up a Map.
 * 
 * @author Katherine Walters
 */
public abstract class BuildingBlock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134053393304725614L;
	// durability represents the amount of time(?) required to destroy the block
	private int durability;
	// represents whether the block can be destroyed (air and cavern) cannot be
	// destroyed
	private boolean destroyable;
	// represents whether or not an actor or crafted item can occupy the block
	private boolean occupiable;

	private Color color;
	private Color backgroundColor;
	private String id;
	private ImageEnum img;
	private Designation designation = Designation.NONE;
	private boolean isVisible = false;
	
	public BuildingBlock(int durability, boolean destroyable,
			boolean occupiable, Color color, Color backgroundColor, String id,
			ImageEnum img) {// , List<Item>
		// itemsInBlock) {
		this.durability = durability;
		this.destroyable = destroyable;
		this.occupiable = occupiable;
		this.color = color;
		this.backgroundColor = backgroundColor;
		this.id = id;
		this.img = img;
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

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public String getID() {
		return id;
	}

	public ImageEnum getImage() {
		return img;
	}
	
	public boolean getVisibility() {
		return isVisible;
	}
	
	public void setVisibility(boolean visibility) {
		isVisible = visibility;
	}

	/*
	 * returns a list of the items the BuildingBlock drops if it is destroyed,
	 * or null if it drops nothing.
	 */
	abstract public List<Item> lootBlock();

	abstract public List<Item> itemsOnGround();

	abstract public boolean addActor(Actor actor);

	abstract public boolean removeActor(Actor actor);

	abstract public List<Actor> getActors();

	abstract public boolean addFurniture(Furniture furniture);

	abstract public boolean removeFurniture();

	abstract public Furniture getFurniture();
	
	abstract public BuildingBlock getAppropriateReplacement();

	@Override
	public String toString() {
		String result = "";

		result += "<br>&nbsp;&nbsp;&nbsp;&nbsp;Block type: " + id;

		if (getActors() != null) {
			List<Actor> actors = getActors();
			LinkedList<Actor> newGetActors = new LinkedList<>();
			for (Actor actor : actors){
				newGetActors.add(actor);
			}
			Iterator<Actor> iter = newGetActors.iterator();
			while(iter.hasNext()) {
				result += "<br>&nbsp;&nbsp;&nbsp;&nbsp;Actor: "
						+ iter.next().toString();
			}
			
			/*for (Actor actor : getActors()) {
				result += "<br>&nbsp;&nbsp;&nbsp;&nbsp;Actor: "
						+ actor.toString();
			}*/
		}

		if (getFurniture() != null) {
			result += "<br>&nbsp;&nbsp;&nbsp;&nbsp;Furniture: "
					+ getFurniture().toString();
		}

		if (itemsOnGround() != null) {
			result += "<br>&nbsp;&nbsp;&nbsp;&nbsp;Items on the ground here:";
			for (Item item : itemsOnGround()) {
				result += "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ item.toString();
			}
		}

		return result;

	}

	public void addDesignation(Designation designation) {
		this.designation = designation;
	}

	public void removeDesignation() {
		this.designation = Designation.NONE;
	}

	public boolean isDesignated() {
		return designation != Designation.NONE;
	}

	public Designation getDesignation() {
		return designation;
	}

	public boolean addItemToGround(Item item) {
		if (isOccupiable()) {
			if (itemsOnGround() != null) {
				itemsOnGround().add(item);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param required
	 * @return
	 */
	public boolean contains(Item required) {
		for (Item it : itemsOnGround()) {
			if (it.getClass().equals(required.getClass()))
				return true;
		}
		return false;
	}

	public boolean removeItemFromGround(Item item) {
//		if (isOccupiable()) {
//			if (itemsOnGround() != null) {
//				itemsOnGround().remove(0);
//				return true;
//			}
//		}
//		return false;
		if (isOccupiable()) {
			if (itemsOnGround() != null) {
				for (Item it : itemsOnGround()) {
					if (it.getClass().equals(item.getClass()))
						itemsOnGround().remove(it);
						return true;
				}
			}
		}
		return false;
	}
	
	

}
