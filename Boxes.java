import acm.program.*;
import javax.swing.*;
import acm.graphics.*;

public class Boxes extends GraphicsProgram {
	
	public void init() {
		nameField = new JTextField(MAX_FIELD_SIZE);
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
	}
	
	private JTextField nameField;
	
	private JButton addButton;
	
	private JButton removeButton;
	
	private JButton clearButton;
	
	private static final int MAX_FIELD_SIZE = 30;
}