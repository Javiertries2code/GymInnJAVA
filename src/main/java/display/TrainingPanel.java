package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import manager.Record;
import objects.Usuario;

public class TrainingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TrainingPanel(List<JPanel> panels ) {
		JButton btnNewRecord = new JButton("ADD NEW RECORD, UNTILL it goes automatic");
		btnNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario usuario = new Record().getOneUsuario();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewRecord.setBounds(383, 40, 89, 23);
		add(btnNewRecord);
		
		

	}

}
