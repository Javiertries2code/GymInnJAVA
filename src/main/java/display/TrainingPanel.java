package display;
import java.awt.Component;
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
import javax.swing.JTextField;

import objects.Routine;
import thread.Chrono;
import thread.Countdown;

public class TrainingPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	protected JLabel nombreWk;
	protected JLabel lblRoutine;
	private Thread chronometer = null;
	private JButton btnStartTraining;
    private Countdown countdown = null;


	public TrainingPanel(List<JPanel> panels) {


		JButton btnNewRecord = new JButton("ADD NEW RECORD, UNTILL it goes automatic");
		btnNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		nombreWk = new JLabel("nombre");
		nombreWk.setBounds(503, 31, 137, 40);
		add(nombreWk);

		btnNewRecord.setBounds(382, 365, 201, 47);
		add(btnNewRecord);

		lblRoutine = new JLabel("Routine : "+"Descripción");
		lblRoutine.setBounds(10, 40, 400, 23);
		add(lblRoutine);

		JButton btnVolver = new JButton("Return");
		btnVolver.setBounds(68, 363, 234, 50);
		add(btnVolver);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelsvisibility(NamePanel.WORKOUT, panels);
			}
		});
		
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
        btnNewButton.setBounds(20, 177, 150, 29);
        add(btnNewButton);
		
	}

	public void actualizarNombreWorkout(String workoutName) {
		nombreWk.setText(workoutName);
	}
	public void nombreEjercicioDescripcion(String nombre) {
		lblRoutine.setText(nombre);
	}
}

