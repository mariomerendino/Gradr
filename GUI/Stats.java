package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.event.*;

public class Stats extends JFrame {
    public static teacher currentTeacher;
    public FileIO io = new FileIO();
    JLabel studentAverage = new JLabel("0");
    JLabel assignmentAverage = new JLabel("0");
    public Stats(String className){
        String gradesFilePath = new String(System.getProperty("user.dir") + "/data/" + currentTeacher.username + "/" + className + "/grades.csv");
        File gradesFile = new File(gradesFilePath);
        
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
        JLabel headerLabel = new JLabel (className + " Statistics");
        header.add(headerLabel);
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel();
        GridLayout centerLayout = new GridLayout(0,3);
        center.setLayout(centerLayout);

        JLabel students = new JLabel("Students");
        JLabel assignments = new JLabel ("Assignments");

        List<String> aList = new ArrayList<String>();
        aList = io.assignmentNames(className);
        String[] assignmentArray = new String[aList.size()+1];
        assignmentArray[0] = "Select An Assignment";
        for (int i = 1; i < aList.size( )+1; i++)
            assignmentArray[i] = aList.get(i-1);

        List<String> sList = new ArrayList<String>();
        sList = io.readStudentID(className, 'x');
        String[] studentArray = new String[sList.size()+1];
        studentArray[0] = "Select A Student";
        for (int i = 1; i < sList.size( )+1; i++)
            studentArray[i] = sList.get(i-1);
        
        JComboBox assignmentsBox = new JComboBox(assignmentArray);
        JComboBox studentsBox = new JComboBox(studentArray);

        center.add(students);
        center.add(Box.createRigidArea(new Dimension(3,3)));
        center.add(Box.createRigidArea(new Dimension(3,3)));
        
        center.add(studentsBox);
        center.add(new JLabel("Average: "));
        center.add(studentAverage);

        center.add(assignments);
        center.add(Box.createRigidArea(new Dimension(0,0)));
        center.add(Box.createRigidArea(new Dimension(0,0)));
        
        center.add(assignmentsBox);
        center.add(new JLabel("Average: "));
        center.add(assignmentAverage);
        

        mainPanel.add(center, BorderLayout.CENTER);
        assignmentsBox.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if(e.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                    if ((String)assignmentsBox.getSelectedItem() == "Select An Assignment"){
                        assignmentAverage.setText("0");
                    }
                    else{
                        assignmentAverage.setText(io.getAssignmentAverage(className, (String)assignmentsBox.getSelectedItem(), "Average"));
                    }
                }
            }
        });
        studentsBox.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if(e.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                    studentAverage.setText(io.getStudentAverage(className, (String)studentsBox.getSelectedItem()));
                }
            }
        });
    }

}