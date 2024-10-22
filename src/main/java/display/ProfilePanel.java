package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import display.Frame.NamePanel;

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
				new Frame().panelsvisibility(NamePanel.WORKOUT, panels);
			}
		});
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(34, 208, 142, 38);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Profile");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Frame().panelsvisibility(NamePanel.PROFILE, panels);

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBounds(34, 87, 142, 38);
		add(btnNewButton_1);
		
		btnHistoric = new JButton("Historic records");
		btnHistoric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Frame().panelsvisibility(NamePanel.RECORDS, panels);

			}
		});
		btnHistoric.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHistoric.setBackground(Color.GRAY);
		btnHistoric.setBounds(34, 148, 142, 38);
		add(btnHistoric);
		
		

		
	}

}