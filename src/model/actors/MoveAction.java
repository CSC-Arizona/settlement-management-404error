/**
 * 
 */
package model.actors;

import java.util.LinkedList;
import java.util.TreeMap;

import model.game.Game;

/**
 * Creates a move action where the Actor attempts to move towards the location
 * 
 * @author Jonathon Davis
 */
public class MoveAction extends Action {

	private static final long serialVersionUID = 6366936476306048341L;
	private TreeMap<Position, Node> visited;
	private LinkedList<Node> searchQueue;
	private Position desiredDestination;

	/**
	 * Represents the data for each block
	 * 
	 * @author Jonathon Davis
	 *
	 */
	private class Node {
		int distance;
		Position position;
		Node prev;

		public Node(int distance, Position position, Node prev) {
			this.distance = distance;
			this.position = position;
			this.prev = prev;
		}
	}

	/**
	 * Creates a move Action, the path will not be calculated until The action
	 * is executed, so creating many MoveActions will not Waste too much extra
	 * memory
	 * 
	 * @param desiredDestination
	 *            The desiredDestination the actor wishes to reach
	 */
	public MoveAction(Position desiredDestination) {
		this.desiredDestination = desiredDestination;
	}

	/**
	 * Calculates the path between two actors
	 */
	private void calculatePath() {
		visited = new TreeMap<>();
		searchQueue = new LinkedList<>();
		Node firstNode = new Node(0, desiredDestination, null);
		searchQueue.add(firstNode);
		while (searchQueue.size() > 0) {
			calculatePath(searchQueue.poll());
		}
	}

	/**
	 * Recursively finds the shortest path from one node and all others
	 * 
	 * @param currentPos
	 *            The current Position
	 * @param currentNode
	 *            The current Node
	 */
	private void calculatePath(Node currentNode) {
		Position currentPos = currentNode.position;
<<<<<<< HEAD:src/model/Actors/MoveAction.java
		int row = currentPos.getRow(),  col = (currentPos.getCol() > 0) ? Math.floorMod(currentPos.getCol(), Game.getMap().getTotalWidth()) 
=======
		int row = currentPos.getRow(),
				col = (currentPos.getCol() > 0) ? Math.floorMod(currentPos.getCol(), Game.getMap().getTotalWidth())
>>>>>>> a678d7c4addc6c1b8d57d87eda252190a69e027e:src/model/actors/MoveAction.java
						: Game.getMap().getTotalWidth() + currentPos.getCol();
		int prow = Integer.MIN_VALUE, pcol = Integer.MIN_VALUE;
		if(currentNode.prev != null){
			prow = currentNode.prev.position.getRow();
			pcol = currentNode.prev.position.getCol();
		}
		if (currentPos.getCol() == Game.getMap().getTotalWidth() || col == Game.getMap().getTotalWidth()) {
			currentPos.setCol(0);
			currentNode.position.setCol(0);
			col = 0;
		}
		currentPos.setCol(col);
		currentPos.setRow(row);
		currentNode.position.setCol(col);
		currentNode.position.setRow(row);
		if (Game.validActorLocation(row, col)) {
			// check to see if this node already has a more efficient route
			if (visited.containsKey(currentPos) && currentNode.distance >= visited.get(currentPos).distance)
				return;
			// add this node to the list of routes
			visited.put(currentPos, currentNode);
			for (int r = row - 1; r <= row + 1; r++)
				for (int c = col - 1; c <= col + 1; c++)
					if (!(r == row && c == col) && Game.validActorLocation(r, c))
						if((currentNode.prev != null && !(r == prow && c == pcol)) || currentNode.prev == null)
							searchQueue.add(new Node(currentNode.distance + 1, new Position(r, c), currentNode));
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		if(!Game.validActorLocation(performer.getPosition().getRow(),performer.getPosition().getCol()))
			return Action.DELAY;
		if (visited == null) {
			calculatePath();
		}
		if (performer.getPosition().equals(desiredDestination))
			return Action.COMPLETED;
		if (visited.containsKey(performer.getPosition())) {
			performer.setPosition(visited.get(performer.getPosition()).prev.position);
		} else {
			return Action.Pool;
		}
		return (performer.getPosition().equals(desiredDestination)) ? Action.COMPLETED : Action.MADE_PROGRESS;
	}
	
	/**
	 * Finds an adjacent valid location near the block to move the actor to
	 * 
	 * @return The Position to move to
	 */
	public static Position getMoveLocationNear(Position position) {
		// check to see if a nearby location is valid
		Position nearest = null;
		double distance = Double.MAX_VALUE;
		for (int r = position.getRow() - 1; r <= position.getRow() + 1; r++) {
			for (int c = position.getCol() - 1; c <= position.getCol() + 1; c++) {
				if (Game.validActorLocation(r, Math.floorMod(c, Game.getMap().getTotalWidth()))){
					Position p = new Position(r, Math.floorMod(c, Game.getMap().getTotalWidth()));
					double calc = p.distance(position);
					if(calc < distance){
						distance = calc;
						nearest = p;
					}
				}
			}
		}
		return nearest;
	}

}
