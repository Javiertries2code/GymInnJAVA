package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import display.Frame.NamePanel;
import manager.Workouts;

import javax.swing.JTable;


import objects.Workout;
import javax.swing.JTextField;

public class WorkoutPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelo;
	private JTextField texLevel;
	/**
	 * Create the panel.
	 */
	public WorkoutPanel(List<JPanel> panels) {
		
		JScrollPane scrollWorkouts = new JScrollPane();
		scrollWorkouts.setBounds(26, 190, 600, 200);
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
				
				//input level search
				texLevel = new JTextField();
				texLevel.setBounds(484, 55, 86, 20);
				add(texLevel);
				texLevel.setColumns(10);
				
				JButton btnWorkouts = new JButton("Workouts");
				btnWorkouts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						try {
							showAllWorkouts(modelo, new Workouts().showSameLowerLevelWorkouts(texLevel.getText()));
						} catch (InvalidClassException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (StreamCorruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ExecutionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				btnWorkouts.setBounds(484, 10, 89, 23);
				add(btnWorkouts);
				
			
		
		
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