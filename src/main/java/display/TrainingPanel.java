package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import manager.Record;
import objects.Usuario;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTable;

public class TrainingPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

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
		btnNewRecord.setBounds(243, 442, 201, 47);
		add(btnNewRecord);
		
		JTextArea textDescription = new JTextArea();
		textDescription.setBounds(228, 41, 183, 40);
		add(textDescription);
		
		JLabel lblRoutine = new JLabel("routine name");
		lblRoutine.setBounds(268, 11, 108, 23);
		add(lblRoutine);
		
	
		JLabel lblWorkout = new JLabel("Workout name");
		lblWorkout.setBounds(507, 11, 108, 23);
		add(lblWorkout);
		
		

	}
}
