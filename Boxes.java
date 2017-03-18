import acm.program.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import acm.graphics.*;

public class Boxes extends GraphicsProgram {
	
	public void init() {
		boxes = new HashMap<String, GCompound>();
		nameField = new JTextField(MAX_FIELD_SIZE);
		initX = (getHeight() - BOX_HEIGHT) / 2;
		initY = (getWidth() - BOX_WIDTH) / 2;
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
			GCompound box = new GCompound();
			String input = nameField.getText();
			GLabel text = new GLabel(input);
			text.setLocation((getHeight() - text.getAscent() - text.getDescent()) / 2, (getWidth() - text.getWidth()) / 2);
			add(new GRect(initX, initY, BOX_WIDTH, BOX_HEIGHT));
			add(text);
			boxes.put(input, box);
		}
	}
	
	
	
	private JTextField nameField;

	private static final int MAX_FIELD_SIZE = 30;
	
	private static final double BOX_WIDTH = 120;
	
	private static final double BOX_HEIGHT = 50;
	
	private double initX;
	
	private double initY;
	
	private HashMap boxes;
}