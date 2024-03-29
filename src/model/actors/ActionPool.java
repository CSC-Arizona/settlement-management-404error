package model.actors;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Jonathon Davis
 *
 */
public class ActionPool implements Serializable {
	private static final long serialVersionUID = 7831232228894564187L;
	private LinkedList<Action> actionQueue;
	
	public ActionPool(){
		actionQueue = new LinkedList<>();
	}
	
	public Action get() {
		return actionQueue.poll();
	}
	
	public void add(Action newAction) {
		actionQueue.add(newAction);
	}

	/**
	 * @param action
	 */
	public void priorityAdd(Action newAction) {
		actionQueue.addFirst(newAction);
	}

	/**
	 * @return
	 */
	public int size() {
		return actionQueue.size();
	}

}
