package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.event.*;

public class EditAssignment extends JFrame{
    
    protected JPanel mainPanel = new JPanel();
    protected String className = new String();
    FileIO io = new FileIO();

    JTextField weightField = new JTextField();
    JTextField totalField = new JTextField();

    public EditAssignment(String cn){

        className = cn;
        init();
    }

    public void init(){
        JPanel mainPanel = new JPanel();
        BorderLayout mainLayout = new BorderLayout();

        mainPanel.setLayout(mainLayout);
        add(mainPanel);
        setSize(500,275);
        setVisible(true);

        JPanel footer = new JPanel();
        ImageIcon gradrlogo = new ImageIcon(new ImageIcon("gradrlogo.png").getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(gradrlogo);
        footer.add(logoLabel);
        footer.setBackground(new Color(255, 255, 255));
        mainPanel.add(footer, BorderLayout.SOUTH);



        JPanel header = new JPanel();
        JLabel headerLabel = new JLabel(className + " Assignmnets");
        header.add(headerLabel);
        mainPanel.add(header, BorderLayout.NORTH);

        List<String> aList = new ArrayList<String>();
        aList = io.assignmentNames(className);
        String[] assignmentArray = new String[aList.size()+1];
        assignmentArray[0] = "Select An Assignment";
        for (int i = 1; i < aList.size( )+1; i++)
            assignmentArray[i] = aList.get(i-1);
        
        JComboBox assignmentsBox = new JComboBox(assignmentArray);

        JPanel center = new JPanel();
        GridLayout centerLayout = new GridLayout(0,3);
        center.setLayout(centerLayout);

        JLabel assignments = new JLabel("Assignmnets");
        JLabel weight = new JLabel("Weight");
        JLabel totalPoints = new JLabel("Total Points");

        JButton save = new JButton("Save");

        center.add(assignments);
        center.add(weight);
        center.add(totalPoints);
        center.add(assignmentsBox);
        center.add(weightField);
        center.add(totalField);
        center.add(Box.createRigidArea(new Dimension(0,0)));
        center.add(Box.createRigidArea(new Dimension(0,0)));
        center.add(save);

        assignmentsBox.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if(e.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                    update((String)assignmentsBox.getSelectedItem());
                }
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer.parseInt(weightField.getText());
                    Integer.parseInt(totalField.getText());
                    io.saveEditsToAssignment(className, (String)assignmentsBox.getSelectedItem(), weightField.getText(), totalField.getText());
                    dispose();
                }
                catch(Exception x){
                    JOptionPane.showMessageDialog(null,"Error: Weight & Total points must be Integers!");
                }
                
            }
        });


        mainPanel.add(center, BorderLayout.CENTER);

    }
    public void update(String assignmentName){
        weightField.setText(io.getWeight(className, assignmentName));
        totalField.setText(io.getTotalPoints(className, assignmentName));
    }




}