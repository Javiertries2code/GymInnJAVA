package display;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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


import com.google.cloud.firestore.DocumentReference;
import accesoDatos.Reader;
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
	private JButton btnProfile;
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
		scrollWorkouts.setBounds(44, 275, 524, 95);
		add(scrollWorkouts);

		// creating model for table of most liked
		modelo = new DefaultTableModel();
		modelo.addColumn("Workout");
		modelo.addColumn("Level");
		modelo.addColumn("Num Sets");

		// create table
		table = new JTable(modelo);
		scrollWorkouts.setViewportView(table);
		
		Reader reader = new Reader();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
					try {
						panelsvisibility(NamePanel.TRAINING, panels);


						Workout wk=new Workout();

						String workoutName = (String) table.getValueAt(table.getSelectedRow(), 0);

						DocumentReference workoutRef =reader.getWorkoutReferenceByName(workoutName);
					
						TrainingPanel miTrainingPanel = (TrainingPanel) panels.get(5); /*NamePanel.TRAINING*/

						miTrainingPanel.actualizarNombreWorkout(workoutName);
						wk=reader.getOneWorkout(workoutRef);
						List<Routine> routine = wk.getRefSets();
						for (Routine rutina : routine) {
							miTrainingPanel.nombreEjercicioDescripcion("Routine: " +""+rutina.getName()+" Description: "+rutina.getDescription()); 
					    break;        
						}

						
						} catch (InterruptedException | ExecutionException | IOException e1) {
						e1.printStackTrace();
					}					
				}
			}
		});

		// Other buttons and components
		String[] options = {"all","0", "1", "2", "3", "4", "5", "6", "7","8","9"};
		SpinnerListModel spinnerModel = new SpinnerListModel(options);

		// Create the JSpinner with the model 
		JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setBounds(468, 201, 100, 20);
		add(spinner);
		
		JButton btnWorkouts = new JButton("Workouts");
		btnWorkouts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (spinner.getValue().toString().equalsIgnoreCase("all"))
                        showAllWorkouts(modelo, new Workouts().showSameLowerLevelWorkouts("-1"));
                    else
                        showAllWorkouts(modelo, new Workouts().showSameLowerLevelWorkouts(spinner.getValue().toString()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnWorkouts.setBounds(414, 26, 156, 23);
		add(btnWorkouts);
		
		btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelsvisibility(NamePanel.PROFILE, panels);

			}
		});
	
		btnProfile.setBounds(44, 26, 156, 23);
		add(btnProfile);
		
		btnHistoric = new JButton("Historic records");
		btnHistoric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelsvisibility(NamePanel.RECORDS, panels);

			}
		});
		btnHistoric.setBounds(218, 26, 170, 23);
		add(btnHistoric);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelsvisibility(NamePanel.LOGIN, panels);
			}
		});
		btnVolver.setBounds(218, 403, 156, 23);
		add(btnVolver);

		
	}


	// Additional methods
	public DefaultTableModel getModelo() {
		return modelo;
	}


	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

	public void showAllWorkouts(DefaultTableModel modelWorkouts, ArrayList<Workout> workouts) {
		modelWorkouts.setRowCount(0);
		
		for (Workout workout : workouts) {
			String[] linea = { workout.getName(), String.valueOf(workout.getLevel()),  String.valueOf(workout.getNumSets()), };
			modelWorkouts.addRow(linea);
		}
	}
}



