package generation;

//pretty sure TO DELETE

import java.util.Comparator;

import base.Position2D;

public class Sorter implements Comparator<Position2D>{			//Wofür dachte ich brauchen wir die Klasse?
																//naja, fliegt wohl beim Nächsten durchgucken raus
	@Override
	public int compare(Position2D p1, Position2D p2){
		if(p1.getX()>p2.getX())
			return 1;
		if(p1.getX()<p2.getX())
			return -1;
		else
			return 0;
	}
}
