/**
 * 
 */
package model;

/**
 * @author Jonathon Davis
 *
 */
public interface Action {
	
	/**
	 * Execute the current action
	 * @param performer The Actor that will be performing the action
	 * @return whether the action was completed, true if complete false otherwise
	 */
	public boolean execute(Actor performer);
	
}
