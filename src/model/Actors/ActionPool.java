/**
 * 
 */
package model.Actors;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Jonathon Davis
 *
 */
public class ActionPool implements Serializable {
	private LinkedList<Action> actionQueue;
	
	public ActionPool(){
		actionQueue = new LinkedList<>();
	}
	
	public Action get(){
		return actionQueue.poll();
	}
	
	public void add(Action newAction){
		actionQueue.add(newAction);
	}

}
