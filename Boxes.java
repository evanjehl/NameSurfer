import acm.program.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import acm.graphics.*;

public class Boxes extends GraphicsProgram {
	
	public void init() {
		boxes = new HashMap<String, GCompound>();
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
			GCompound box = new GCompound();
			String input = nameField.getText();
			GLabel text = new GLabel(input);
			text.setLocation((getWidth() - text.getWidth()) / 2, (getHeight() + text.getAscent() - text.getDescent()) / 2);
			box.add(text);
			box.add(new GRect((getWidth() - BOX_WIDTH) / 2, (getHeight() - BOX_HEIGHT) / 2, BOX_WIDTH, BOX_HEIGHT));
			add(box);
			boxes.put(input, box);
		}
		if (e.getActionCommand().equals("Remove")) {
			String input = nameField.getText();
			if (boxes.containsKey(input)) {
				remove(boxes.get(input));
				boxes.remove(input);
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			removeAll();
			boxes.clear();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
		gobj = getElementAt(lastX, lastY);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX() - lastX, e.getY() - lastY);
			lastX = e.getX();
			lastY = e.getY();
		}
	}
	
	private JTextField nameField;

	private static final int MAX_FIELD_SIZE = 30;
	
	private static final double BOX_WIDTH = 120;
	
	private static final double BOX_HEIGHT = 50;
	
	private GObject gobj;
	
	private HashMap<String, GCompound> boxes;
	
	private double lastX;
	
	private double lastY;
}