/**
 * 
 */
package model.actors;

import java.io.Serializable;

import model.game.Game;

/**
 * A position on the map, uses row col coordinates
 * 
 * @author Jonathon Davis
 *
 */
public class Position implements Comparable<Position>, Serializable {

	private static final long serialVersionUID = 2355700358974842805L;
	private int row, col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Compare the two positions for storing in data structures
	 */
	public int compareTo(Position o) {
		if (row != o.row)
			if (row < o.row)
				return -1;
			else
				return 1;
		else if (col < o.col)
			return -1;
		else if (col > o.col)
			return 1;
		else
			return 0;
	}

	public boolean equals(Position obj) {
		return (this.row == obj.row && this.col == obj.col);
	}

	@Override
	public String toString() {
		return Integer.toString(row) + " " + Integer.toString(col);
	}


	public void setCol(int col) {
		this.col = col;	
	}

	/**
	 * @param row2
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	public boolean isAdjacent(Position position){
<<<<<<< HEAD:src/model/Actors/Position.java
		System.out.println("Map's width: " + Game.getMap().getTotalWidth());
		return Math.abs(Math.floorMod(this.getCol(),Game.getMap().getTotalWidth()-1) - Math.floorMod(position.getCol(),Game.getMap().getTotalWidth()-1)) <= 1
=======
		int col = Math.abs(Math.floorMod(this.getCol(),Game.getMap().getTotalWidth()) - Math.floorMod(position.getCol(),Game.getMap().getTotalWidth()));
		return (col <= 1 || col == Game.getMap().getTotalWidth() -1)
>>>>>>> a678d7c4addc6c1b8d57d87eda252190a69e027e:src/model/actors/Position.java
				&& Math.abs(this.getRow()
						- position.getRow()) <= 1;
	}
	
	public boolean isTwoAbove(Position position) {
		return Math.abs(Math.floorMod(this.getCol(),Game.getMap().getTotalWidth()-1) - Math.floorMod(position.getCol(),Game.getMap().getTotalWidth()-1)) <= 1 && ((position.getRow() - this.getRow()) == 2 || (position.getRow() - this.getRow()) == 1);
	}
	
	public double distance(Position position){
		return Math.sqrt(Math.pow((Math.floorMod(col,Game.getMap().getTotalWidth())-Math.floorMod(position.col,Game.getMap().getTotalWidth())),2) + Math.pow((row-position.row),2));
	}

}
