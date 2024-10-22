package display;

import javax.swing.JPanel;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.Reader;
import display.Frame.NamePanel;
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
import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;

import java.awt.Color;
import java.awt.Font;


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
		//textFieldName.setText("your name here");
		textFieldName.setBounds(361, 171, 86, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		//textFieldSurname.setText("Surname here");
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(361, 202, 86, 20);
		add(textFieldSurname);
		
		textFieldBirthday = new JTextField();
		//textFieldBirthday.setText("Birthday");
		textFieldBirthday.setColumns(10);
		textFieldBirthday.setBounds(361, 236, 86, 20);
		add(textFieldBirthday);
		
		textFieldPassword = new JTextField();
		//textFieldPassword.setText("Password");
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(361, 272, 86, 20);
		add(textFieldPassword);
		
		textFieldEmail = new JTextField();
		//textFieldEmail.setText("email");
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(361, 307, 86, 20);
		add(textFieldEmail);
		
		   String[] options = {"false","true" };
	        SpinnerListModel spinnerModel = new SpinnerListModel(options);

	     
	        JSpinner spinner = new JSpinner(spinnerModel);
	        spinner.setBounds(361, 338, 93, 20);
	        add(spinner);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateInputs() == true) {
					
					String selectedValue = spinner.getValue().toString();
					boolean isTrainer = Boolean.parseBoolean(selectedValue);
					
					usuario.setName(textFieldName.getText());
					usuario.setSurname(textFieldSurname.getText());
					usuario.setBirthday(textFieldBirthday.getText());
					usuario.setEmail(textFieldEmail.getText());
					usuario.setPassword(textFieldPassword.getText());
					usuario.setTrainer(isTrainer);
						try {
							new Register().addNewUser(usuario);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new Frame().panelsvisibility(NamePanel.LOGIN, panels);

				
				}
				
			}

		});
		btnRegister.setBounds(333, 403, 121, 23);
		add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("Is Trainer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(214, 341, 76, 17);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(214, 174, 76, 17);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(214, 205, 76, 17);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Birthday");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(214, 239, 76, 17);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(214, 275, 76, 17);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(214, 306, 76, 17);
		add(lblNewLabel_5);
		


	}
	

	private boolean validateInputs() {
		// TODO Auto-generated method stub
		//gotta validate jtext inputs
		return true;
	}
}
