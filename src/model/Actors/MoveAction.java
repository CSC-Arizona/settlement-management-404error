/**
 * 
 */
package model.Actors;

import java.util.TreeMap;

import model.GameMap;
import model.Map;

/**
 * Creates a move action where the Actor attempts to move towards the location
 * 
 * @author Jonathon Davis
 */
public class MoveAction implements Action {

	private TreeMap<Position, Node> visited;
	private Position desiredDestination;
	private Map map;

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
	public MoveAction(Position desiredDestination, Map map) {
		this.map = map;
		this.desiredDestination = desiredDestination;
	}

	/**
	 * Calculates the path between two actors
	 */
	private void calculatePath() {
		visited = new TreeMap<>();
		Position destination = new Position(desiredDestination.getRow(),
				desiredDestination.getCol());
		Node firstNode = new Node(0, new Position(destination.getRow(),
				destination.getCol()), null);
		calculatePath(destination, firstNode);
	}

	/**
	 * Recursively finds the shortest path from one node and all others
	 * 
	 * @param currentPos
	 *            The current Position
	 * @param currentNode
	 *            The current Node
	 * 
	 *            TODO: make this function handle the discontinuity from end of
	 *            map -> beginning (wrap around)
	 */
	private void calculatePath(Position currentPos, Node currentNode) {
		int row = currentPos.getRow(), col = currentPos.getCol();
		// Check to make sure this is in the bounds of the map
		if (row < 0 || col < 0 || row >= map.getTotalHeight()
				|| col >= map.getTotalWidth()
				|| !map.getBuildingBlock(row, col).isOccupiable())
			return;
		// check to make sure there is a valid block to stand on

		if (row + 1 < map.getTotalHeight()
				&& !map.getBuildingBlock(row + 1, col).getID().equals("Air")) {
			// check to see if this node already has a more efficient route
			if (visited.containsKey(currentPos)
					&& currentNode.distance > visited.get(currentPos).distance)
				return;
			// add this node to the list of routes
			visited.put(currentPos, currentNode);
			for (int r = row - 1; r <= row + 1; r++)
				for (int c = col - 1; c <= col + 1; c++)
					if (!(r == row && c == col) && r < map.getTotalHeight()
							&& c < map.getTotalWidth() && r >= 0 && c >= 0)
						calculatePath(new Position(r, c), new Node(
								currentNode.distance + 1, new Position(r, c),
								currentNode));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		if (visited == null)
			calculatePath();
		if (performer.getPosition().equals(desiredDestination))
			return Action.COMPLETED;
		Position currentPosition = new Position(performer.getPosition()
				.getRow(), performer.getPosition().getCol());
		if (visited.containsKey(currentPosition))
			performer.setPosition(visited.get(currentPosition).prev.position);
		else {
			/*
			 * TODO: implement once Map can be changed calculatePath(); if
			 * (visited.containsKey(currentPosition))
			 * performer.setPosition(visited
			 * .get(currentPosition).prev.position); else
			 */
			return Action.CANCELL;
		}
		return (performer.getPosition().equals(desiredDestination)) ? Action.COMPLETED
				: Action.MADE_PROGRESS;
	}

}
