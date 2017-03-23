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

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		database = new NameSurferDataBase("names-data.txt");
		JLabel name = new JLabel("Name");
	    nameField = new JTextField(30);
	    add(name, SOUTH);
	    add(nameField, SOUTH);
	    add(new JButton("Graph"), SOUTH);
	    add(new JButton("Clear"), SOUTH);
	    graph = new NameSurferGraph();
	    add(graph);
	    addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph")) {
			NameSurferEntry entry = database.findEntry(nameField.getText());
			if (entry != null) {
				graph.addEntry(entry);
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}
	
	private JTextField nameField;
	
	private NameSurferGraph graph;
	
	private NameSurferDataBase database;
}
