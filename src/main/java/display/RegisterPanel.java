package display;

import javax.swing.JPanel;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.Reader;
import manager.Register;
import objects.Usuario;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class RegisterPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldBirthday;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private Usuario usuario;
	/**
	 * Create the panel.
	 */
	public RegisterPanel(List<JPanel> panels) {
		
		usuario = new Usuario();
		
		textFieldName = new JTextField();
		textFieldName.setText("your name here");
		textFieldName.setBounds(361, 171, 86, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setText("Surname here");
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(361, 202, 86, 20);
		add(textFieldSurname);
		
		textFieldBirthday = new JTextField();
		textFieldBirthday.setText("Birthday");
		textFieldBirthday.setColumns(10);
		textFieldBirthday.setBounds(361, 236, 86, 20);
		add(textFieldBirthday);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setText("Password");
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(361, 272, 86, 20);
		add(textFieldPassword);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setText("email");
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(361, 307, 86, 20);
		add(textFieldEmail);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateInputs() == true) {
					
					usuario.setName(textFieldName.getText());
					usuario.setSurname(textFieldSurname.getText());
					usuario.setBirthday(textFieldBirthday.getText());
					usuario.setEmail(textFieldEmail.getText());
					usuario.setPassword(textFieldPassword.getText());
					
						try {
							new Register().addNewUser(usuario);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
				}
				
			}

		});
		btnRegister.setBounds(333, 365, 121, 23);
		add(btnRegister);

	}
	

	private boolean validateInputs() {
		// TODO Auto-generated method stub
		//gotta validate jtext inputs
		return true;
	}
}
