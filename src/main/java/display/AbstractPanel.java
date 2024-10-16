package display;

import java.awt.Color;

import javax.swing.JPanel;
import java.util.List;


public abstract class AbstractPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AbstractPanel() {
		setBounds(0, 0, 650, 500);
		setBackground(new Color(255, 128, 128));
		setLayout(null);

	}

}
