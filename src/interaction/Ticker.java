package interaction;

public class Ticker extends Thread {		//Anfang der Ticker-Architektur
	public BlockTicker bt;

	public Ticker() {
		bt = new BlockTicker();
	}

	public void run() {
		bt.start();
	}
	
	public void stopIt(){
		bt.stopIt();
	}
}
