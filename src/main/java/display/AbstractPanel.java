package display;

import java.awt.Color;

import javax.swing.JPanel;
import java.util.List;
import javax.swing.JTextField;


public abstract class AbstractPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textName;

	/**
	 * Create the panel.
	 */
	public AbstractPanel() {
		setBounds(0, 0, 650, 500);
		setBackground(new Color(255, 128, 128));
		setLayout(null);
		
		textName = new JTextField();
		textName.setText("GynInn");
		textName.setBounds(203, 11, 86, 20);
		add(textName);
		textName.setColumns(10);

	}
}
