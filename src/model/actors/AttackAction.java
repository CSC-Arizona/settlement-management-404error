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
			//TODO: Check boolean for enemy attacker
			getBestWeapon(performer);
			getBestArmor(target);
			//Add attack mod to total damage
			int performerAttack = 1 + performer.getSkills().getCombatLevel() + bestWeapon.getAttackModifier() - bestArmor.getDefenseModifier();
			if(performerAttack < 0) {
				performerAttack = 0;
			}
			target.setHealth(target.getHealth() - performerAttack);
			performer.getSkills().addCombatXP(performerAttack);
			if (target.getHealth() <= 0) {
				target.setHealth(0);
				//Have target drop all items in inventory upon death
				for(Item item : target.getInventory()) {
					Game.getMap().getBuildingBlock(target.getPosition()).addItemToGround(item);
				}
				
				return Action.COMPLETED;
			}/* else {
				//Deal out enemy damage to performer
				int enemyAttack = 1 + target.getSkills().getCombatLevel() + enemySword.getAttackModifier() - bestArmor.getDefenseModifier();
				if(enemyAttack < 0) {
					enemyAttack = 0;
				}
				performer.setHealth(performer.getHealth() - enemyAttack);
				if(performer.getHealth() <= 0) {
					return Action.COMPLETED;
				}
				//Actor.update will get rid of the performer if health <= 0 
			} */
			
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

	private void getBestArmor(Actor performer) {
		//Iterate through performer's inventory to find best armor
		for(Item currItem : performer.getInventory()) {
			//TODO: Make sure this comparison is doing what I want it to do
			if(currItem.getClass().equals(Armor.class)) {
				//TODO: Make sure this comparison works, or use .equals
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
