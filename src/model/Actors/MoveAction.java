/**
 * 
 */
package model.Actors;

import java.awt.Point;
import java.util.TreeMap;

import model.GameMap;

/**
 * @author Jonathon Davis
 */
public class MoveAction implements Action {

	private TreeMap<Position, Node> visited;
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

	public MoveAction(Position desiredDestination) {
		this.desiredDestination = desiredDestination;
		calculatePath();
	}

	private void calculatePath() {
		visited = new TreeMap<>();
		Position destination = new Position(desiredDestination.getRow(), desiredDestination.getCol());
		Node firstNode = new Node(0, new Position(destination.getRow(), destination.getCol()), null);
		calculatePath(destination, firstNode);
	}

	private void calculatePath(Position currentPos, Node currentNode) {
		int row = currentPos.getRow(), col = currentPos.getCol();
		// Check to make sure this is in the bounds of the map
		if (row < 0 || col < 0 || row >= GameMap.mapHeight() || col >= GameMap.mapWidth())
			return;
		// Check to make sure this is a valid block
		if (!GameMap.getBlock(row, col).getID().equals("Air"))
			return;
		// check to make sure there is a valid block to stand on
		if (row + 1 < GameMap.mapHeight() && !GameMap.getBlock(row+1, col).getID().equals("Air")) {
			// check to see if this node already has a more efficient route
			if (visited.containsKey(currentPos) && currentNode.distance > visited.get(currentPos).distance)
				return;
			// add this node to the list of routes
			visited.put(currentPos, currentNode);
			for (int r = row - 1; r <= row + 1; r++)
				for (int c = col - 1; c <= col + 1; c++)
					if (!(r == row && c == col) && r<GameMap.mapHeight() && c < GameMap.mapWidth() && r >= 0 && c >= 0)
						calculatePath(new Position(r, c),
								new Node(currentNode.distance + 1, new Position(r, c), currentNode));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Action#execute(model.Actor)
	 */
	@Override
	public boolean execute(Actor performer) {
		Position currentPosition = new Position(performer.getPosition().getRow(), performer.getPosition().getCol());
		if (visited.containsKey(currentPosition))
			performer.setPosition(visited.get(currentPosition).prev.position);
		else {
			calculatePath();
			if (visited.containsKey(currentPosition))
				performer.setPosition(visited.get(currentPosition).prev.position);
			else
				throw new IllegalArgumentException();
		}
		return performer.getPosition().equals(desiredDestination);
	}

}
