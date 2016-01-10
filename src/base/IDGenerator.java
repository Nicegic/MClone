package base;

import base.blox.*;
import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import base.items.*;

public class IDGenerator {			//generiert für alle Blöcke und Items anhand zweier Input-Dateien die IDs

    static int id = 1;
    public static ArrayList<Block> allBlocks = new ArrayList<Block>();
    public static ArrayList<Item> allItems = new ArrayList<Item>();
    public static ArrayList<Integer> allIDs = new ArrayList<Integer>();
    static FileInputStream fis;
    static InputStreamReader isr;
    static BufferedReader br;

    public static void generateIDs() {
        blockIDs();
        itemIDs();
        System.out.println("ID's successfully loaded!");
    }

    public static void blockIDs() {
        File f = new File("blocks.cfg");
        Class<?> c = null;
        if (f.exists()) {
            try {
                fis = new FileInputStream(f);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String s;
                while ((s = br.readLine()) != null) {		//ja, ist nicht soooo schön, aber durchaus faszinierend :D und es klappt ;)
                    if (s.charAt(0) == '*') {
                        continue;
                    }
                    c = Class.forName("base.blox." + s);						//Hol dir die gleichnamige Klasse zum String
                    allIDs.add(IDGenerator.generateIntId());				//generier eine neue ID
                    Block b = (Block) c.getConstructor(Position.class, //hol dir den passenden ID-Generierungs-Konstruktor
                            Facing.class, int.class).newInstance( //und bau damit ne neue Instanz von der Klasse
                            new Position(Double.MAX_VALUE, Double.MAX_VALUE,
                            Double.MAX_VALUE), Facing.NORTH,
                            allIDs.get(allIDs.size() - 1));
                    allBlocks.add(b);										//für spätere Verwendung Block und ID abspeichern
                }
            } catch (IllegalAccessException illae) {						//wie man anhand der ganzen Exceptions sieht, nicht ganz Formvollendet :D
                System.out.println("illegalAccess... " + illae.getMessage());	//aber immerhin mit einem halbwegs passenden Hilfstext versehen ;)
                exitB(c);
            } catch (InvocationTargetException ite) {
                System.out.println("Invocation Exception: " + ite.getMessage());
                exitB(c);
            } catch (NoSuchMethodException nsme) {
                System.out.println("NoSuchMethod: " + nsme.getMessage());
                exitB(c);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("ClassNotFound: " + cnfe.getMessage());
                exitB(c);
            } catch (FileNotFoundException fnfe) {
                System.out.println("FileNotFound: " + fnfe.getMessage());
                exitB(c);
            } catch (IOException ioe) {
                System.out.println("IO-Fehler: " + ioe.getMessage());
                exitB(c);
            } catch (InstantiationException e) {
                System.out.println("Instanziierung: " + e.getMessage());
                exitB(c);
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArguments: " + e.getMessage());
                exitB(c);
            } catch (SecurityException e) {
                System.out.println("SecurityException: " + e.getMessage());
                exitB(c);
            }
        }
        System.out.println("Block-ID's loaded successfully!");			//um den Erfolg dann auch mal feiern zu können
    }

    public static void itemIDs() {						//und der ganze Spaß nochmal mit den Items :D
        File f = new File("items.cfg");
        Class<?> c = null;
        if (f.exists()) {
            try {
                fis = new FileInputStream(f);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String s;
                while ((s = br.readLine()) != null) {
                    if (s.charAt(0) == '*') {
                        continue;
                    }
                    c = Class.forName("base.items." + s);
                    allIDs.add(IDGenerator.generateIntId());
                    Item i = (Item) c.getConstructor(String.class, int.class)
                            .newInstance(s, allIDs.get(allIDs.size() - 1));
                    allItems.add(i);
                }
            } catch (IllegalAccessException illae) {
                System.out.println("illegalAccess... " + illae.getMessage());
                exitI(c);
            } catch (InvocationTargetException ite) {
                System.out.println("Invocation Exception: " + ite.getMessage());
                exitI(c);
            } catch (NoSuchMethodException nsme) {
                System.out.println("NoSuchMethod: " + nsme.getMessage());
                exitI(c);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("ClassNotFound: " + cnfe.getMessage());
                exitI(c);
            } catch (FileNotFoundException fnfe) {
                System.out.println("FileNotFound: " + fnfe.getMessage());
                exitI(c);
            } catch (IOException ioe) {
                System.out.println("IO-Fehler: " + ioe.getMessage());
                exitI(c);
            } catch (InstantiationException e) {
                System.out.println("Instanziierung: " + e.getMessage());
                exitI(c);
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArguments: " + e.getMessage());
                exitI(c);
            } catch (SecurityException e) {
                System.out.println("SecurityException: " + e.getMessage());
                exitI(c);
            }
        }
        System.out.println("Item-ID's successfully loaded");
    }

    //die beiden "Hilfstexte", damit du immerhin weißt, dass du Mist programmiert hast ;)
    private static void exitB(Class<?> c) {
        System.out
                .println("One or more blocks (at least "
                + c.getName()
                + ") aren't added to the code (in the right way) yet. Please add them as a class into the package blocks");
        System.exit(0);
    }

    private static void exitI(Class<?> c) {
        System.out
                .println("One or more items (at least "
                + c.getName()
                + ") aren't added to the code (in the right way) yet. Please add them as a class into the package items");
        System.exit(0);
    }

    public static ArrayList<Integer> getIDList() {

        return allIDs;
    }

    public static byte generateByteId() {
        byte that = (byte) id++;
        return that;
    }

    public static short generateShortId() {
        short that = (short) id++;
        return that;
    }

    public static int generateIntId() {
        return id++;
    }
}
