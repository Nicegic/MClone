package base.recipes;

import base.items.Item;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import base.IDGenerator;

public class RecipeLoader {						//lädt die Rezepte aus definierten Dateien

    static FileInputStream fis;
    static InputStreamReader isr;
    static BufferedReader br;
    static ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    public RecipeLoader(String location) {
    }

    public static ArrayList<Recipe> readRecipes(String s) {
        File f = new File(s);
        Class<?> c = null;
        if (f.exists()) {
            try {
                fis = new FileInputStream(f);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String st;
                while ((st = br.readLine()) != null) {
                    if (st.charAt(0) == '*') {
                        continue;
                    }
                    c = Class.forName("base.items." + st);
                    Item i = (Item) c.getConstructor(String.class, int.class)
                            .newInstance(c.getName(), getId(c.getName().substring(6)));
                    st = br.readLine();
                    if (st.charAt(0) == '*') {
                        while (st != null && st.charAt(0) == '*') {
                            st = br.readLine();
                        }
                        if (st == null) {
                            exit("You didn't write the cfg-Data in the right way. Please make sure you followed the instructions!");
                        }
                    }
                    c = Class.forName("items." + st);
                    Item it = (Item) c.getConstructor(String.class, int.class)
                            .newInstance(c.getName(), getId(c.getName().substring(6)));
                    recipes.add(new Recipe(i, it));
                }
                return recipes;
            } catch (IllegalAccessException illae) {
                System.out.println("illegalAccess... " + illae.getMessage());
                exit(c);
            } catch (InvocationTargetException ite) {
                System.out.println("Invocation Exception: " + ite.getMessage());
                ite.printStackTrace();
                exit(c);
            } catch (NoSuchMethodException nsme) {
                System.out.println("NoSuchMethod: " + nsme.getMessage());
                exit(c);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("ClassNotFound: " + cnfe.getMessage());
                exit(c);
            } catch (FileNotFoundException fnfe) {
                System.out.println("FileNotFound: " + fnfe.getMessage());
                exit(c);
            } catch (IOException ioe) {
                System.out.println("IO-Fehler: " + ioe.getMessage());
                exit(c);
            } catch (InstantiationException e) {
                System.out.println("Instanziierung: " + e.getMessage());
                exit(c);
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArguments: " + e.getMessage());
                exit(c);
            } catch (SecurityException e) {
                System.out.println("SecurityException: " + e.getMessage());
                exit(c);
            }
        }
        return null;
    }

    private static int getId(String s) throws NullPointerException { // to get
        // the
        // right
        // ID of
        // the
        // Block
        int ret = 0;
        for (int i = 0; i < IDGenerator.allItems.size(); i++) {
            if (IDGenerator.allItems.get(i).toString().equals(s)) {
                ret = IDGenerator.allIDs.get(i + IDGenerator.allBlocks.size());
            }
        }
        if (ret == 0) {
            throw new NullPointerException("Item " + s
                    + " not found... Did you add it to the config-Data?");
        }
        return ret;
    }

    private static void exit(String s) {
        System.out.println(s);
        System.exit(0);
    }

    private static void exit(Class<?> c) {
        System.out
                .println("One or more items (at least "
                + c.getName()
                + ") aren't added to the code (in the right way) yet. Please add them as a class into the package base.items");
        System.exit(0);
    }
}
