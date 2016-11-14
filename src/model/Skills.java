/**
 * 
 */
package model;

/**
 * @author Jonathon Davis
 *
 */
public class Skills {
	private int combatXP;
	private int farmingXP;
	private int miningXP;
	
	/**
	 * @param combatXP The ability with combat
	 * @param farmingXP The ability at farming
	 * @param miningXP The ability at mining
	 */
	public Skills(){
		this.combatXP = 0;
		this.farmingXP = 0;
		this.miningXP = 0;
	}
	
	/**
	 * @param combatXP The ability with combat
	 * @param farmingXP The ability at farming
	 * @param miningXP The ability at mining
	 */
	public Skills(int combat, int farming, int mining) {
		this.combatXP = combat;
		this.farmingXP = farming;
		this.miningXP = mining;
	}
	
	/**
	 * Add xp to this skill
	 * @param amount The amount of xp to add
	 */
	public void addCombatXP(int amount){
		combatXP += amount;
	}
	
	/**
	 * Add xp to this skill
	 * @param amount The amount of xp to add
	 */
	public void addFarmingXP(int amount){
		farmingXP += amount;
	}
	
	/**
	 * Add xp to this skill
	 * @param amount The amount of xp to add
	 */
	public void addMiningXP(int amount){
		miningXP += amount;
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
	 * @return the level of the skill
	 */
	public int getFarmingLevel(){
		if(farmingXP > 0)
			return (int) Math.log(farmingXP);
		else
			return 0;
	}
	
	/**
	 * @return the level of the skill
	 */
	public int getMiningLevel(){
		if(miningXP > 0)
			return (int) Math.log(miningXP);
		else
			return 0;
	}
	
	

}
