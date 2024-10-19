package display;

import javax.swing.JPanel;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import accesoDatos.AccesoDatos;


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


public class RegisterPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public RegisterPanel(List<JPanel> panels) {
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setBounds(211, 161, 46, 14);
		add(lblNewLabel);
		
		JButton btnRegister = new JButton("Go to resigster");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRegister.setBounds(136, 96, 121, 23);
		add(btnRegister);

	}
}
