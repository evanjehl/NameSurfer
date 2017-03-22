/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import acm.util.ErrorException;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		nameDatabase = new HashMap<String, NameSurferEntry>();
		JLabel name = new JLabel("Name");
	    nameField = new JTextField(30);
	    add(name, SOUTH);
	    add(nameField, SOUTH);
	    add(new JButton("Graph"), SOUTH);
	    add(new JButton("Clear"), SOUTH);
	    graph = new NameSurferGraph();
	    add(graph);
	    loadDatabase();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph")) {
			println("This button works.");
			if (nameDatabase.containsKey(nameField.getText())) {
				graph.addEntry(nameDatabase.get(nameField.getText()));
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}
	
	private void loadDatabase() {
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
	
	private BufferedReader openFileReader(String file) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(file));
			} catch (IOException ex) {
				println("Can't open that file.");
			}
		}
		return rd;
	}
	
	private JTextField nameField;
	
	private HashMap<String, NameSurferEntry> nameDatabase;
	
	private NameSurferGraph graph;
}
