/**
 * 
 */
package model.Actors;

/**
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

}
