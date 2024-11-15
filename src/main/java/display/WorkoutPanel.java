package display;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;

import manager.Login;
import manager.Workouts;
import objects.Workout;
import objects.*;
import thread.*;

public class WorkoutPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelo;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnHistoric;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel;
	private JButton btnStartTraining;
	private Thread chronometer = null;
	private Countdown countdown = null;

	/**
	 * Create the panel.
	 */
	public WorkoutPanel(List<JPanel> panels) {
		
		JScrollPane scrollWorkouts = new JScrollPane();
		scrollWorkouts.setBounds(44, 338, 524, 78);
		add(scrollWorkouts);

		// creating model fro table of most liked
				modelo = new DefaultTableModel();
				modelo.addColumn("Workout");
				modelo.addColumn("Level");
				modelo.addColumn("Num Sets");
				//modelo.addColumn("sets");
				
				// create table
				table = new JTable(modelo);
				scrollWorkouts.setViewportView(table);
				
				 String[] options = {"all","0", "1", "2", "3", "4", "5", "6", "7","8","9"};
			        SpinnerListModel spinnerModel = new SpinnerListModel(options);

			        // Crear el JSpinner con el modelo
			        JSpinner spinner = new JSpinner(spinnerModel);
			        spinner.setBounds(468, 108, 100, 20);
			        add(spinner);
				
				JButton btnWorkouts = new JButton("Workouts");
				btnWorkouts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
					
							try {
								  if (spinner.getValue().toString().equalsIgnoreCase("all"))
	                                    showAllWorkouts(modelo, new Workouts().showSameLowerLevelWorkouts("-1"));
	                                else
	                                    showAllWorkouts(modelo, new Workouts().showSameLowerLevelWorkouts(spinner.getValue().toString()));							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
						
					}
				});
				btnWorkouts.setBounds(484, 10, 89, 23);
				add(btnWorkouts);
				
			

				btnNewButton = new JButton("Workouts");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					panelsvisibility(NamePanel.WORKOUT, panels);
					}
				});
				btnNewButton.setBackground(new Color(128, 128, 128));
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnNewButton.setBounds(337, 12, 120, 20);
				add(btnNewButton);
				
				btnNewButton_1 = new JButton("Profile");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				panelsvisibility(NamePanel.PROFILE, panels);

					}
				});
				btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnNewButton_1.setBackground(Color.GRAY);
				btnNewButton_1.setBounds(10, 11, 110, 23);
				add(btnNewButton_1);
				
				btnHistoric = new JButton("Historic records");
				btnHistoric.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panelsvisibility(NamePanel.RECORDS, panels);

					}
				});
				btnHistoric.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnHistoric.setBackground(Color.GRAY);
				btnHistoric.setBounds(155, 9, 148, 20);
				add(btnHistoric);
				
				lblNewLabel = new JLabel("Filter");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel.setBounds(468, 83, 86, 14);
				add(lblNewLabel);
				
				btnStartTraining = new JButton("START");
				btnStartTraining.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					panelsvisibility(NamePanel.TRAINING, panels);

					}
				});
				btnStartTraining.setBounds(444, 437, 110, 52);
				add(btnStartTraining);
				
		
				
				JPanel panel = new JPanel();
				LayoutManager layout = new GridLayout(6, 2);
				panel.setLayout(null);
				panel.setBounds(10, 86, 400, 80);
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
				   countdown = new Countdown(countDownTextField, chronoTextField, new ArrayList<Routine>() );
				JButton btnNewButton = new JButton("Start Stop");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if ((countdown).isAlive())
							((Countdown) countdown).startStop();
							else
								countdown.start();
						

						if (chronometer.isAlive())
							((Chrono) chronometer).startStop();
							else
								chronometer.start();
					}
				});
				btnNewButton.setBounds(20, 42, 79, 29);
				add(btnNewButton);

		
	}

	
public DefaultTableModel getModelo() {
		return modelo;
	}


	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}


public void showAllWorkouts(DefaultTableModel modelWorkouts, ArrayList<Workout> workouts) {
	
	System.out.println("inshowAllWorkouts");
	modelWorkouts.setRowCount(0);
	for (Workout workout: workouts)
		System.out.println(workout.toString());
	for (Workout workout : workouts) {
		String[] linea = { workout.getName(), String.valueOf(workout.getLevel()),  String.valueOf(workout.getNumSets()), };
		modelWorkouts.addRow(linea);
		}
	
}
}