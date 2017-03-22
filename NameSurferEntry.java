/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(line) {
		int tokenEnd = line.indexOf(" ");
		name = line.substring(0, tokenEnd);
		rankings = new int[NDECADES];
		for (int i = 0; i < NDECADES; i++) {
			int tokenStart = tokenEnd + 1;
			if (i < NDECADES - 1) {
				tokenEnd = (line.indexOf(" ", tokenStart));
			} else {
				tokenEnd = line.length();
			}
			rankings[i] = Integer.parseInt(line.substring(tokenStart, tokenEnd));
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return rankings[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String nameSurferEntryString = name + " [" + rankings[0];
		for (int i = 1; i < rankings.length; i++) {
			nameSurferEntryString += " " + rankings[i];
		}
		nameSurferEntryString += "]";
		return nameSurferEntryString;
	}
	
	private String name;
	
	private int[] rankings;
}

