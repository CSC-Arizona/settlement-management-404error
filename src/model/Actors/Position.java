/**
 * 
 */
package model.Actors;

/**
 * A position on the map, uses row col coordinates
 * 
 * @author Jonathon Davis
 *
 */
public class Position implements Comparable<Position> {
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

}
