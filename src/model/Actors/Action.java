/**
 * 
 */
package model.Actors;

/**
 * @author Jonathon Davis
 *
 */
public interface Action {
	public static final int COMPLETED = 0, MADE_PROGRESS = 1, DELAY = 2, CANCELL = 3;
	
	/**
	 * Execute the current action
	 * @param performer The Actor that will be performing the action
	 * @return whether the action was completed, true if complete false otherwise
	 */
	public int execute(Actor performer);
	
}
