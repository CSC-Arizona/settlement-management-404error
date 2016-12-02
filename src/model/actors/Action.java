/**
 * 
 */
package model.actors;

import java.io.Serializable;

public abstract class Action implements Serializable  {
	private static final long serialVersionUID = 7904826388362296409L;
	public static final int COMPLETED = 0, MADE_PROGRESS = 1, DELAY = 2, Pool = 3;
	
	/**
	 * Execute the current action
	 * @param performer The Actor that will be performing the action
	 * @return whether the action was completed, true if complete false otherwise
	 */
	public abstract int execute(Actor performer);
	
}
