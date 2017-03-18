import acm.program.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import acm.graphics.*;

public class Boxes extends GraphicsProgram {
	
	public void init() {
		boxes = new HashMap<String, GRect>();
		nameField = new JTextField(MAX_FIELD_SIZE);
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		nameField.addActionListener(this);
		addMouseListeners();
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add")) {
			
		}
	}
	
	private JTextField nameField;

	private static final int MAX_FIELD_SIZE = 30;
	
	private static final double BOX_WIDTH = 120;
	
	private static final double BOX_HEIGHT = 50;
	
	private HashMap boxes;
}