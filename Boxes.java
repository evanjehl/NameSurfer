import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;

public class Boxes extends GraphicsProgram {
	
	public void init() {
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
		
	}
	
	private JTextField nameField;

	private static final int MAX_FIELD_SIZE = 30;
}