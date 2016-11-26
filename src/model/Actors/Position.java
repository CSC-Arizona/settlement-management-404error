/**
 * 
 */
package model.Actors;

import java.io.Serializable;

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

}
