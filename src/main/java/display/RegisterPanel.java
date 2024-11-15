package display;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import manager.Register;
import objects.Usuario;


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

		textFieldName.setBounds(334, 87, 93, 20);

		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		//textFieldSurname.setText("Surname here");
		textFieldSurname.setColumns(10);

		textFieldSurname.setBounds(334, 118, 93, 20);

		add(textFieldSurname);
		
		textFieldBirthday = new JTextField();
		//textFieldBirthday.setText("Birthday");
		textFieldBirthday.setColumns(10);

		textFieldBirthday.setBounds(334, 152, 93, 20);

		add(textFieldBirthday);
		
		textFieldPassword = new JTextField();
		//textFieldPassword.setText("Password");
		textFieldPassword.setColumns(10);

		textFieldPassword.setBounds(334, 188, 93, 20);

		add(textFieldPassword);
		
		textFieldEmail = new JTextField();
		//textFieldEmail.setText("email");
		textFieldEmail.setColumns(10);

		textFieldEmail.setBounds(334, 223, 93, 20);

		add(textFieldEmail);
		
	    String[] options = {"false","true" };
        SpinnerListModel spinnerModel = new SpinnerListModel(options);

     
        JSpinner spinner = new JSpinner(spinnerModel);

        spinner.setBounds(334, 254, 93, 20);

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
						panelsvisibility(NamePanel.LOGIN, panels);

				
				}
				
			}

		});

		btnRegister.setBounds(248, 339, 121, 23);

		add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("Is Trainer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNewLabel.setBounds(187, 257, 76, 17);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(187, 90, 76, 17);

		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNewLabel_2.setBounds(187, 121, 76, 17);

		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Birthday");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNewLabel_3.setBounds(187, 155, 76, 17);

		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNewLabel_4.setBounds(187, 191, 76, 17);

		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNewLabel_5.setBounds(187, 222, 76, 17);

		add(lblNewLabel_5);
		


	}
	

	private boolean validateInputs() {
		// TODO Auto-generated method stub
		//gotta validate jtext inputs
		return true;
	}
}
