package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import manager.Record;
import objects.Routine;
import objects.Usuario;
import thread.Chrono;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import thread.Chrono;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;

public class TrainingPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Thread chronometer = null;

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
		btnNewRecord.setBounds(427, 383, 201, 47);
		add(btnNewRecord);
		
		JTextArea textDescription = new JTextArea();
		textDescription.setBounds(223, 67, 183, 40);
		add(textDescription);
		
		JLabel lblRoutine = new JLabel("routine name");
		lblRoutine.setBounds(268, 11, 108, 23);
		add(lblRoutine);
		
	
		JLabel lblWorkout = new JLabel("Workout name");
		lblWorkout.setBounds(507, 11, 108, 23);
		add(lblWorkout);
		
		
		JPanel panel = new JPanel();
		LayoutManager layout = new GridLayout(6, 2);
		panel.setLayout(null);
		panel.setBounds(26, 353, 289, 77);
		panel.setBorder(BorderFactory.createTitledBorder("Timers"));

		NumberFormat chronoFormat = NumberFormat.getNumberInstance();
		
		
		Component chronoTextField = new JFormattedTextField(chronoFormat);
		chronoTextField.setBounds(10, 39, 109, 30);
		
		chronoTextField.setVisible(true);
		chronoTextField.setName("Chronometer");
		((JTextField) chronoTextField).setColumns(10);
		
		((JFormattedTextField) chronoTextField).setValue(0);
		
		JLabel chronoLabel = new JLabel("Chronometer Workout");
		chronoLabel.setVisible(true);
		chronoLabel.setBounds(10, 11, 109, 30);
		chronoLabel.setLabelFor(chronoTextField);
		panel.add(chronoLabel);
		panel.add(chronoTextField);
		
		NumberFormat countDownFormat = NumberFormat.getNumberInstance();
		add(panel);
		
		Component countDownTextField = new JFormattedTextField(countDownFormat);
		countDownTextField.setBounds(150, 39, 109, 30);
		
		countDownTextField.setVisible(true);
		countDownTextField.setName("Countdown");
		((JTextField) countDownTextField).setColumns(10);
		((JFormattedTextField) countDownTextField).setValue(0);
		
		JLabel countDownLabel = new JLabel("CountDown Routine:");
		countDownLabel.setVisible(true);
		countDownLabel.setBounds(150, 11, 109, 30);
		countDownLabel.setLabelFor(countDownTextField);
		panel.add(countDownLabel);
		panel.add(countDownTextField);
		//CREATING THREAD
		   chronometer = new Chrono(countDownTextField, chronoTextField, new ArrayList<Routine>() );

		JButton btnNewButton = new JButton("Start Stop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				if (chronometer.isAlive())
					((Chrono) chronometer).startStop();
					else
						chronometer.start();
			}
		});
		btnNewButton.setBounds(83, 294, 162, 32);
		add(btnNewButton);
		

	}
}
