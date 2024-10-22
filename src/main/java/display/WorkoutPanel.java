package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;

import display.Frame.NamePanel;
import manager.Workouts;
import objects.Workout;
import javax.swing.JLabel;

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

	/**
	 * Create the panel.
	 */
	public WorkoutPanel(List<JPanel> panels) {
		
		JScrollPane scrollWorkouts = new JScrollPane();
		scrollWorkouts.setBounds(44, 216, 524, 200);
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
				
				 String[] options = {"0", "1", "2", "3", "4", "5", "6", "7","8","9"};
			        SpinnerListModel spinnerModel = new SpinnerListModel(options);

			        // Crear el JSpinner con el modelo
			        JSpinner spinner = new JSpinner(spinnerModel);
			        spinner.setBounds(468, 108, 100, 20);
			        add(spinner);
				
				JButton btnWorkouts = new JButton("Workouts");
				btnWorkouts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
					
							try {
								showAllWorkouts(modelo, new Workouts().showSameLowerLevelWorkouts(spinner.getValue().toString()));
							} catch (Exception e1) {
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
						new Frame().panelsvisibility(NamePanel.WORKOUT, panels);
					}
				});
				btnNewButton.setBackground(new Color(128, 128, 128));
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnNewButton.setBounds(10, 132, 142, 38);
				add(btnNewButton);
				
				btnNewButton_1 = new JButton("Profile");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Frame().panelsvisibility(NamePanel.PROFILE, panels);

					}
				});
				btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnNewButton_1.setBackground(Color.GRAY);
				btnNewButton_1.setBounds(10, 11, 142, 38);
				add(btnNewButton_1);
				
				btnHistoric = new JButton("Historic records");
				btnHistoric.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Frame().panelsvisibility(NamePanel.RECORDS, panels);

					}
				});
				btnHistoric.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnHistoric.setBackground(Color.GRAY);
				btnHistoric.setBounds(10, 72, 142, 38);
				add(btnHistoric);
				
				lblNewLabel = new JLabel("Filter");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel.setBounds(468, 83, 86, 14);
				add(lblNewLabel);
				
				btnStartTraining = new JButton("START");
				btnStartTraining.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Frame().panelsvisibility(NamePanel.TRAINING, panels);

					}
				});
				btnStartTraining.setBounds(444, 437, 110, 52);
				add(btnStartTraining);
				
			

		
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