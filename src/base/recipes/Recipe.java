package base.recipes;

import base.items.Item;

public class Recipe {				//alle Rezepte für Prozessblöcke (sprich Ofen, Pulverizer,...)
	Item input;                             //für den Anfang sollten einzelne Ausgangs- und Ergebnisstoffe ausreichen
	Item output;                            //kann ja nachfolgend noch komplexer gestaltet werden ;)

	public Recipe() {
		input = new Item();
		output = new Item();
	}

	public Recipe(Item input, Item output) {
		this.input = input;
		this.output = output;
	}

	public Item getInput() {
		return input;
	}

	public Item getOutput() {
		return output;
	}

}
