import acm.program.*;
import javax.swing.*;
import acm.graphics.*;

public class Boxes extends GraphicsProgram {
	
	public void init() {
		nameField = new JTextField(MAX_FIELD_SIZE);
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
	}
	
	private JTextField nameField;
	
	private JButton addButton;
	
	private JButton removeButton;
	
	private JButton clearButton;
	
	private static final int MAX_FIELD_SIZE = 30;
}