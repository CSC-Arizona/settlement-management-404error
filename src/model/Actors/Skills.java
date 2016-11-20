/**
 * 
 */
package model.Actors;

/**
 * @author Jonathon Davis
 *
 */
public class Skills {
	private int combatXP;
	
	/**
	 * @param combatXP The ability with combat
	 * @param farmingXP The ability at farming
	 * @param miningXP The ability at mining
	 */
	public Skills(){
		this.combatXP = 0;
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
	

}
