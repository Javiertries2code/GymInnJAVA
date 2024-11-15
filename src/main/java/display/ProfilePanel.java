package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProfilePanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnHistoric;
	private JButton btnNewButton_3;

	/**
	 * Create the panel.
	 */
	public ProfilePanel(List<JPanel> panels) {


		btnNewButton = new JButton("Workouts");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelsvisibility(NamePanel.WORKOUT, panels);
			}
		});
		btnNewButton.setBounds(34, 208, 142, 38);
		add(btnNewButton);

		btnNewButton_1 = new JButton("Profile");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelsvisibility(NamePanel.PROFILE, panels);

			}
		});
		btnNewButton_1.setBounds(34, 87, 142, 38);
		add(btnNewButton_1);


		btnHistoric = new JButton("Historic records");
		btnHistoric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelsvisibility(NamePanel.RECORDS, panels);

			}
		});

		btnHistoric.setBounds(34, 148, 142, 38);
		add(btnHistoric);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(264, 119, 350, 248);
		add(textArea);

		JButton btnNewButton_2 = new JButton("See User profile");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



			}
		});
		btnNewButton_2.setBounds(279, 53, 155, 23);
		add(btnNewButton_2);

	}
}

