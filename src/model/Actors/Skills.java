/**
 * 
 */
package model.Actors;

import java.io.Serializable;

/**
 * @author Jonathon Davis
 *
 */
public class Skills implements Serializable {
	private static final long serialVersionUID = 1817211288165732804L;
	private int combatXP;
	private int gatheringXP;
	
	/**
	 * @param combatXP The ability with combat
	 * @param farmingXP The ability at farming
	 * @param gatheringXP The ability at mining
	 */
	public Skills(){
		this.combatXP = 0;
		this.gatheringXP = 0;
	}
	
	/**
	 * Add xp to this skill
	 * @param amount The amount of xp to add
	 */
	public void addCombatXP(int amount){
		combatXP += amount;
	}
	
	/**
	 * @return the level of the skill
	 */
	public int getCombatLevel(){
		if(combatXP > 0)
			return (int) Math.log(combatXP);
		else
			return 0;
	}
	
	/**
	 * Add xp to this skill
	 * @param amount The amount of xp to add
	 */
	public void addGatheringXP(int amount){
		gatheringXP += amount;
	}
	
	/**
	 * @return the level of the skill
	 */
	public int getGatheringLevel(){
		if(gatheringXP/100 > 0)
			return (int) Math.log(gatheringXP/100);
		else
			return 0;
	}	
	

}
