package display;

import javax.swing.JPanel;
import java.util.List;
import javax.swing.JLabel;


public class RegisterPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public RegisterPanel(List<JPanel> panels) {
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setBounds(211, 161, 46, 14);
		add(lblNewLabel);

	}
}
