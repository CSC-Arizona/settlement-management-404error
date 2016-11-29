/**
 * 
 */
package model.Actors;

import java.util.LinkedList;
import java.util.TreeMap;

import model.Game.Game;

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
		while(searchQueue.size() > 0){
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
		int row = currentPos.getRow(),
				col = (currentPos.getCol() > 0) ? currentPos.getCol() % (Game.getMap().getTotalWidth())
						: Game.getMap().getTotalWidth() + currentPos.getCol();
		if (currentPos.getCol() == Game.getMap().getTotalWidth()
				|| col == Game.getMap().getTotalWidth()) {
			currentPos.setCol(0);
			currentNode.position.setCol(0);
			col = 0;
		}
		currentPos.setCol(col);
		currentPos.setRow(row);
		currentNode.position.setCol(col);
		currentNode.position.setRow(row);
		if (!Game.getMap().getBuildingBlock(row, col).isOccupiable())
			return;
		// check to make sure there is a valid block to stand on

		if (row + 1 < Game.getMap().getTotalHeight()
				&& !Game.getMap().getBuildingBlock(row + 1, col).isOccupiable()) {
			// check to see if this node already has a more efficient route
			if (visited.containsKey(currentPos) && currentNode.distance > visited.get(currentPos).distance)
				return;
			// add this node to the list of routes
			visited.put(currentPos, currentNode);
			for (int r = row - 1; r <= row + 1; r++)
				for (int c = col - 1; c <= col + 1; c++)
					if (!(r == row && c == col) && r < Game.getMap().getTotalHeight() && r >= 0)
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
		if (visited == null) {
			calculatePath();
		}
		if (performer.getPosition().equals(desiredDestination))
			return Action.COMPLETED;
		if (visited.containsKey(performer.getPosition())) {
				performer.setPosition(visited.get(performer.getPosition()).prev.position);
		} else {
			return Action.CANCELL;
		}
		return (performer.getPosition().equals(desiredDestination)) ? Action.COMPLETED : Action.MADE_PROGRESS;
	}

}
