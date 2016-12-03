package model.actors;

import model.armor.Armor;
import model.items.Item;
import model.weapons.Weapon;

/**
 * @author Jonathon Davis
 *
 */
public class AttackAction extends Action {

	private static final long serialVersionUID = -2606344516527539211L;
	private Actor target;
	private Position previousLocation;
	private MoveAction move;
	
	private Item bestWeapon;
	private Armor bestArmor;

	public AttackAction(Actor target) {
		this.target = target;
		previousLocation = target.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		if (target.getHealth() <= 0)
			return Action.COMPLETED;

		// if adjacent fight, else move towards target
		if (performer.getPosition().isAdjacent(target.getPosition())) {
			// TODO: add actual combat system
			//getBestWeapon()
			//Iterate through performer's inventory to find best weapon
			for(Item currItem : performer.getInventory()) {
				if(bestWeapon == null) {
					bestWeapon = currItem;
				}
				else if(currItem.getAttackModifier() > bestWeapon.getAttackModifier()) {
					bestWeapon = currItem;
				}
			}
			
			//Iterate through performer's inventory to find best armor
			for(Item currItem : performer.getInventory()) {
				//TODO: Make sure this comparison is doing what I want it to do
				if(currItem.getClass().equals(Armor.class)) {
					//TODO: Make sure this comparison works, or use .equals
					if(bestArmor == null && (currItem != bestWeapon)) {
						bestArmor = (Armor) currItem;
					}
					else if(((Armor)currItem).getDefenseModifier() > bestArmor.getDefenseModifier()) {
						bestArmor = (Armor) currItem;
					}
				}
			}
			
			int damage = 1 + performer.getSkills().getCombatLevel();
			target.setHealth(target.getHealth() - damage);
			performer.getSkills().addCombatXP(damage);
			if (target.getHealth() <= 0) {
				target.setHealth(0);
				return Action.COMPLETED;
			} else
				return Action.MADE_PROGRESS;
		} else {
			if (target.getPosition().equals(previousLocation) && move != null) {
				move.execute(performer);
			} else {
				move = new MoveAction(target.getPosition());
				previousLocation = target.getPosition();
				move.execute(performer);
			}
			return Action.MADE_PROGRESS;
		}
	}

}
