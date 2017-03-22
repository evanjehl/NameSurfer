import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		nameDatabase = new HashMap<String, NameSurferEntry>();
		BufferedReader rd = openFileReader(NAMES_DATA_FILE);
	    while (true) {
	    	try {
	    		String line = rd.readLine();
	    		if (line == null) break;
	    		int tokenEnd = line.indexOf(" ");
	    		String name = line.substring(0, tokenEnd);
	    		int [] rankings = new int[NDECADES];
	    		for (int i = 0; i < NDECADES; i++) {
	    			int tokenStart = tokenEnd + 1;
	    			if (i < NDECADES - 1) {
	    				tokenEnd = (line.indexOf(" ", tokenStart));
	    			} else {
	    				tokenEnd = line.length();
	    			}
	    			rankings[i] = Integer.parseInt(line.substring(tokenStart, tokenEnd));
	    		}
	    		NameSurferEntry entry = new NameSurferEntry(name, rankings);
	    		nameDatabase.put(name, entry);
	    	} catch (IOException ex) {
	    		throw new ErrorException(ex);
	    	}
	    }
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		return null;
	}
	
	private BufferedReader openFileReader(String file) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(file));
			} catch (IOException ex) {
				System.out.println("Can't open that file.");
			}
		}
		return rd;
	}
	
	private HashMap<String, NameSurferEntry> nameDatabase;
}

