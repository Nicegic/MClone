package base.blox;

import base.Position;
import base.items.Item;
import base.Chunk;
import java.util.ArrayList;
import base.recipes.*;

public class Furnace extends InteractiveBlock {			//erster interaktiver Block

    private static final long serialVersionUID = 3453507403082872638L;
    Item input, fuel, output, futureOutput;
    int burntime;
    ArrayList<Recipe> recipes;							//alle registrierten Rezepte

    public Furnace(Position p, Facing f, int id) {		//ID-Konstruktor
        super(p, f);
        this.id = id;
        setThings(false);
    }

    public Furnace(Position p, Facing f, int id, Chunk c) {	//normaler Konstruktor
        super(p, f, c);
        setActive();
        this.id = id;
        setThings(true);
    }

    private void setThings(boolean withRecipes) {		//immer
        breakby = 2;
        breakswith = 1;
        breaktime = 100;
        this.name = "Furnace";
        if (withRecipes) {
            recipes = RecipeLoader.readRecipes("/recipes/processing_recipes/FurnaceRecipes.cfg");	//lade die Rezepte aus der angegebenen Datei
        }
    }

    public void doWork() {				//hier wird die Aktion reinprogrammiert
    }
}
