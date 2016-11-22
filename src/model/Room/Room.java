package model.Room;

import java.util.List;

import model.Actors.Position;
import model.Items.Item;

/**
 * Room is an abstract class that is extended by all the possible structures that can be built.
 * These include the EntertainmentRoom, BedRoom, InfirmaryRoom, StoreRoom, and KitchenRoom, as 
 * well as the vertical and horizontal tunnel structures. 
 *      - Each Room has a required height and width - the amount of space that must be made up 
 *        of destroyable blocks adjacent to the spot which the user chooses to order the Room 
 *        be built. During the building of a Room, if items are dropped from the BuildingBlocks 
 *        that are cleared to make room for the Room, the Agent(s) that  build the Room pick 
 *        them up. 
 *      - Each Room has a list of required building materials that are necessary for the building
 *        of the Room. These building materials correspond to the materials that are necessary to
 *        build the Furniture that each type of Room contains. The Furniture is constructed at the
 *        same time as the Room itself, so Furniture never really exists outside of the Room it 
 *        belongs to.
 *      - Each Room has a distinct room capacity - a new Agent can only enter the Room if the 
 *        current occupancy does not exceed the room's capacity. 
 *      - Each Room has a list of required upgrade materials that are necessary for the Room to be
 *        upgraded. Every time a Room is upgraded, its capacity increases. As of right now, there 
 *        is no limit in place as far as how many times a Room can be upgraded, but I can implement
 *        one if you guys want. When a Room is upgraded, the space it takes up on the Map does not 
 *        increase (for simplicity's sake). Max and I thought this would be a good way to avoid 
 *        conflicts if a cluster of rooms are built close together and then one in the middle gets 
 *        upgraded.
 *        
 * @author Katherine Walters
 */
public abstract class Room {

	private int requiredHeight;
	private int requiredWidth;
	private int roomCapacity;
	private int upgradesAllowed;
	private int numAgentsInRoom;
	private Position pos;
	
	public Room(int requiredHeight, int requiredWidth, int roomCapacity, int upgradesAllowed, Position pos) {
		this.requiredHeight = requiredHeight;
		this.requiredWidth = requiredWidth;
		this.roomCapacity = roomCapacity;
		this.upgradesAllowed = upgradesAllowed;
		this.pos = pos;
		this.numAgentsInRoom = 0;
	}
	
	/*
	 * returns the Position of the Room
	 */
	public Position getPosition() {
		return pos;
	}
	
	/*
	 * returns the width of the Room
	 */
	public int getRequiredWidth() {
		return this.requiredWidth;
	}
	
	/*
	 * returns the height of the Room
	 */
	public int getRequiredHeight() {
		return this.requiredHeight;
	}
	
	/*
	 * returns the capacity of the room
	 */
	public int getRoomCapacity() {
		return this.roomCapacity;
	}
	
	/*
	 * accepts a single integer argument and increases the Room's capacity by that amount.
	 */
	private void increaseRoomCapacity(int byThisMuch) {
		this.roomCapacity += byThisMuch;
	}
	
	/*
	 * decrements the remaining number of upgrades
	 */
	private void decreaseUpgradesAllowed() {
		this.upgradesAllowed--;
	}
	
	/*
	 * returns the remaining number of upgrades
	 */
	public int getUpgradesAllowed() {
		return this.upgradesAllowed;
	}
	
	/*
	 * returns the room's current occupancy
	 */
	public int getNumAgentsInRoom() {
		return this.numAgentsInRoom;
	}
	
	/*
	 * increments the room's occupancy by one (to be called when an Agent enters)
	 */
	public void increaseNumAgentsInRoom() {
		this.numAgentsInRoom++;
	}
	
	/*
	 * decrements the room's occupancy by one (to be called when an Agent exits)
	 */
	public void decreaseNumAgentsInRoom() {
		if (this.numAgentsInRoom != 0)
		    this.numAgentsInRoom--;
	}
	
	/*
	 * upgrades a Room if it still has a remaining upgrade and returns true, or returns false
	 */
	public boolean upgradeRoom() {
		if (this.getUpgradesAllowed() > 0) {
			this.decreaseUpgradesAllowed();
		    this.increaseRoomCapacity(this.increaseCapacityBy());
		    return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Returns a list of the Items required to build the Room 
	 */
	abstract public List<Item> getRequiredBuildMaterials();
	
	/*
	 * Returns a list of Items required to upgrade the Room
	 */
	abstract public List<Item> getRequiredUpgradeMaterials();
	
	/*
	 * Returns the amount a Room's capacity gets increased by with each upgrade
	 */
	abstract public int increaseCapacityBy();
}
