package tempconv;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class TemperatureConverter {

	private JFrame frame;
	private JTextField textField;
	private final Action action = new SwingAction();
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemperatureConverter window = new TemperatureConverter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TemperatureConverter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Convert");
		btnNewButton.setAction(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(88, 124, 136, 51);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField("32 Fahreneit");
		textField.setBackground(SystemColor.control);
		textField.setBounds(246, 139, 93, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JFormattedTextField formattedTextField = new JFormattedTextField("Celsius");
		formattedTextField.setBackground(SystemColor.control);
		formattedTextField.setBounds(246, 58, 93, 20);
		frame.getContentPane().add(formattedTextField);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setBounds(88, 47, 136, 43);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Convert");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			float newTemp;
			newTemp = Float.parseFloat(textField_1.getText());
			newTemp = (newTemp * ((float) 9 / (float) 5)) + 32;
			textField.setText(Float.toString(newTemp) + " Fahreneit");
		}
	}
}
