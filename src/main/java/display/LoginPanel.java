package display;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import java.util.List;
import display.*;
import display.Frame.NamePanel;
import manager.Login;

public class LoginPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private JButton btnRegister;

	/**
	 * Create the panel.
	 */
	public LoginPanel(List<JPanel> panels) {
		setLayout(null);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(228, 208, 86, 20);
		add(textFieldUser);
		textFieldUser.setColumns(10);

		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(228, 262, 86, 20);
		add(textFieldPassword);
		textFieldPassword.setColumns(10);

		// TO-DO
		// must be removed, this is for quick entry while coding
		textFieldUser.setText("1111");
		textFieldPassword.setText("password1");

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(225, 322, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if ((new Login().verifyUser(textFieldUser.getText(), textFieldPassword.getText())) == true) {
						new Frame().panelsvisibility(NamePanel.WORKOUT, panels);
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect user or password");
						textFieldUser.setText("Try again");
						textFieldPassword.setText("Try again");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new Frame().panelsvisibility(NamePanel.REGISTER, panels);

			}
		});
		btnRegister.setBounds(225, 370, 89, 23);
		add(btnRegister);

	}
}
