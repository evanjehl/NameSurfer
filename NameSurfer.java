/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    JLabel name = new JLabel("Name");
	    nameField = new JTextField(30);
	    JButton graphButton = new JButton("Graph");
	    JButton clearButton = new JButton("Clear");
	    add(name, SOUTH);
	    add(nameField, SOUTH);
	    add(graphButton, SOUTH);
	    add(clearButton, SOUTH);
	    rd = openFileReader("names-data.txt");
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph")) {
			
		}
	}
	
	private JTextField nameField;
	
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
	
	private BufferedReader rd;
}
