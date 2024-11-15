package display;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import display.AbstractPanel.NamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TrainingPanel extends AbstractPanel {

    private static final long serialVersionUID = 1L;
	protected JLabel nombreWk;
	protected JLabel lblRoutine;
     
 


    public TrainingPanel(List<JPanel> panels) {
     	

        JButton btnNewRecord = new JButton("ADD NEW RECORD, UNTILL it goes automatic");
        btnNewRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             }
        });
        
        nombreWk = new JLabel("nombre");
        nombreWk.setBounds(482, 31, 137, 40);
        add(nombreWk);
        
        btnNewRecord.setBounds(382, 363, 201, 47);
        add(btnNewRecord);

        JTextArea textDescription = new JTextArea();
        textDescription.setBounds(126, 230, 380, 40);
        add(textDescription);

        lblRoutine = new JLabel("Routine : "+"Descripción");
        lblRoutine.setBounds(121, 132, 400, 23);
        add(lblRoutine);

        JButton btnVolver = new JButton("Return");
        btnVolver.setBounds(68, 363, 234, 50);
        add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelsvisibility(NamePanel.WORKOUT, panels);
            }
        });
        
    }

    public void actualizarNombreWorkout(String workoutName) {
        nombreWk.setText(workoutName);
    }
    public void nombreEjercicioDescripcion(String nombre) {
    	lblRoutine.setText(nombre);
    }
}

