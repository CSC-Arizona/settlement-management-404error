package model.Weapons;

import model.Items.Craftable;

//Author: Maxwell Faridian
//This class defines the Weapon object
public abstract class Weapon extends Craftable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590825761249784692L;

	//Weapons can not be eaten
	public Weapon(int attackModifier, int healthPts, double weight) {
		super(false, attackModifier, healthPts, weight);
	}

}
