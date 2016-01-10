package base;

import java.io.Serializable;

public class Position2D implements Serializable {		//Chunk-Position ebenfalls mit ein paar Methoden zum Füttern und ändern
	/**
	 * 
	 */
	private static final long serialVersionUID = 8876394776450262004L;
	int x, y;

	public Position2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position2D(Position2D p) {
		this.x = p.x;
		this.y = p.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void set(Position2D that) {
		this.x = that.x;
		this.y = that.y;
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Position2D that) {
		if (this.x == that.x && this.y == that.y)
			return true;
		else
			return false;
	}

	public String toString() {
		return "" + this.x + " | " + this.y;
	}
}
