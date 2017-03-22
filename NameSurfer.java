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
			if (nameDatabase.containsKey(nameField.getText())) {
				graph.addEntry(nameDatabase.get(nameField.getText()));
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
