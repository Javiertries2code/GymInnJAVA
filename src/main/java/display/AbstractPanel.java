package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import manager.Login;

public abstract class AbstractPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public enum NamePanel {
		LOGIN, REGISTER, WORKOUT, RECORDS, PROFILE, TRAINING,
	}

	/**
	 * Create the panel.
	 */
	public AbstractPanel() {
		setBounds(0, 0, 650, 500);
		setBackground(new Color(255, 128, 128));
		setLayout(null);

		

	}

	public  void panelsvisibility(NamePanel panelName, List<JPanel> panels2) {
		for (int i = 0; i < panels2.size(); i++) {
			if (i == panelName.ordinal())
				panels2.get(i).setVisible(true);
			else
				panels2.get(i).setVisible(false);
		}
	}
	
}
