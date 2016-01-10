package base;

public class Position {					//Lokalisierung von Blöcken, Spielern, fallengelassenen Items, etc.
	double x, y, z;

	public Position(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Position() {
		x = y = z = 0.0;
	}
	
	public Position(Position p){
		this.x=p.x;
		this.y=p.y;
		this.z=p.z;
	}
	
	// alle möglichen Methoden, die man vielleicht später mal braucht für physikalische Berechnungen, irgendwelche spannenden
	// Austausche zwischen mehreren Positionen,...
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	public void set(Position p){
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
	}

	public void add(double t, char s) {
		switch (s) {
		case 'x':
			this.x += t;
			break;
		case 'y':
			this.y += t;
			break;
		case 'z':
			this.z += t;
			break;
		}
	}

	public void sub(double t, char s) {
		switch (s) {
		case 'x':
			this.x -= t;
			break;
		case 'y':
			this.y -= t;
			break;
		case 'z':
			this.z -= t;
			break;
		}
	}
	
	public boolean equals (Position that){
		if(this.x==that.x&&this.y==that.y&&this.z==that.z)
			return true;
		else
			return false;
	}
	
	public Position add (Position that){
		return new Position(this.x+that.x,this.y+that.y,this.z+that.z);
	}
	
	public Position sub (Position that){
		return new Position(this.x-that.x,this.y-that.y,this.z-that.z);
	}
	
	public Position scale (double t){
		return new Position(this.x*t,this.y*t,this.z*t);
	}
	
	public double vectorLength(){
		return Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
	}
}
