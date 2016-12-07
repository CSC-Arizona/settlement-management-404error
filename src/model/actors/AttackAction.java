package model.actors;

import model.armor.Armor;
import model.game.Game;
import model.items.Item;

/**
 * @author Jonathon Davis
 * @author Maxwell Faridian
 */
public class AttackAction extends Action {

	private static final long serialVersionUID = -2606344516527539211L;
	private Actor target;
	private Position previousLocation;
	private MoveAction move;
	
	private Item bestWeapon;
	private Armor bestArmor;

	public AttackAction(Actor target) {
		target.setAttack(true);
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

		if (target.getHealth() <= 0)
			return Action.COMPLETED; // moved this from below the x,y declaration
        
		// if adjacent fight, else move towards target
		if (performer.getPosition().isAdjacent(target.getPosition())) {
			int attack = attack(performer,target);
			int counter = -1;
			if(attack != Action.COMPLETED)
				counter = attack(target,performer);
			if(attack == Action.COMPLETED || counter == Action.COMPLETED)
				return Action.COMPLETED;
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
	
	private int attack(Actor attacker, Actor defender){
		getBestWeapon(attacker);
		getBestArmor(defender);
		int attackMod = (bestWeapon == null)?0:bestWeapon.getAttackModifier();
		int defenseMod = (bestArmor == null)?0:bestArmor.getDefenseModifier();
		//Add attack mod to total damage
		int performerAttack = attacker.getSkills().getCombatLevel() + (attackMod/2) - (defenseMod/2);
		performerAttack = (performerAttack <= 0)?0:performerAttack;
		defender.setHealth(defender.getHealth() - performerAttack);
		attacker.getSkills().addCombatXP(performerAttack);
		if (defender.getHealth() <= 0) {
			defender.setAttack(false);
			attacker.setAttack(false);
			defender.setHealth(0);
			//Have target drop all items in inventory upon death
			for(Item item : defender.getInventory()) {
				Game.getMap().getBuildingBlock(defender.getPosition()).addItemToGround(item);
			}
			
			return Action.COMPLETED;
		}
		return Action.MADE_PROGRESS; 
	}

	private void getBestArmor(Actor performer) {
		//Iterate through performer's inventory to find best armor
		for(Item currItem : performer.getInventory()) {
			if(currItem.getClass().equals(Armor.class)) {
				if(bestArmor == null) {
					if(currItem != bestWeapon) {
						bestArmor = (Armor) currItem;
					}
				}
				else if(((Armor)currItem).getDefenseModifier() > bestArmor.getDefenseModifier()) {
					bestArmor = (Armor) currItem;
				}
			}
		}
	}

	private void getBestWeapon(Actor performer) {
		//Iterate through performer's inventory to find best weapon
		for(Item currItem : performer.getInventory()) {
			if(bestWeapon == null) {
				bestWeapon = currItem;
			}
			else if(currItem.getAttackModifier() > bestWeapon.getAttackModifier()) {
				bestWeapon = currItem;
			}
		}
	}

}
