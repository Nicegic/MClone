package junit;

import base.IDGenerator;
import base.Position;
import base.figures.Player;
import generation.WorldGenerator;
import interaction.Ticker;

public class GeneratorAndTicker {                       //Der erste Versuch, das Ticken der aktiven Blöcke zu simulieren. Allerdings noch nicht komplett
                                                        //testfertig implementiert
	public static void main(String[]args) {
		Ticker t = new Ticker();
		IDGenerator.generateIDs();
		WorldGenerator wgen = new WorldGenerator((byte) 4, t.bt);
		Player p = new Player(wgen);
		wgen.start();
		p.deployPlayer(new Position(0,0,80));
		for (int i = 0; i < 10; i++) {
			p.setPosition(new Position(i * 4, i * 4, 60));
			try {
				Thread.sleep(200);
			} catch (InterruptedException inte) {
			}
		}
		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException inte) {
		}
		t.stopIt();
		wgen.end();
		p.stopPlayerRegen();
	}

}
