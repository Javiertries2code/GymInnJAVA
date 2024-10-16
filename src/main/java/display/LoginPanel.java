package display;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import app.Login;
import java.util.List;
import display.*;
import display.Frame.NamePanel;


public class LoginPanel extends AbstractPanel  {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;

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
		
		//TO-DO
		//must be removed, this is for quick entry while coding
		textFieldUser.setText("2222");
		textFieldPassword.setText("password2");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(225, 322, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("in action btnlogin");
					if ((new Login().verifyUser(textFieldUser.getText(), textFieldPassword.getText())) == true) {
						//
						System.out.println("Hola");
					
						new Frame().panelsvisibility(NamePanel.REGISTER, panels);
				
					}
					else
					{
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

	}
}
