package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.event.*;

public class Attendance extends JFrame{
    
    protected JPanel mainPanel = new JPanel();
    protected String className = new String();
    FileIO io = new FileIO();

    JTextField weightField = new JTextField();
    JTextField totalField = new JTextField();

    public Attendance(String cn){

        className = cn;
        init();
    }

    public void init(){
        JPanel mainPanel = new JPanel();
        BorderLayout mainLayout = new BorderLayout();

        mainPanel.setLayout(mainLayout);
        add(mainPanel);
        setSize(650,275);
        setVisible(true);

        JPanel footer = new JPanel();
        ImageIcon gradrlogo = new ImageIcon(new ImageIcon("gradrlogo.png").getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(gradrlogo);
        footer.add(logoLabel);
        footer.setBackground(new Color(255, 255, 255));
        mainPanel.add(footer, BorderLayout.SOUTH);

        JPanel header = new JPanel();
        JLabel headerLabel = new JLabel(className + " Attendance");
        header.add(headerLabel);
        mainPanel.add(header, BorderLayout.NORTH);
    

        JPanel center = new JPanel();
        GridLayout centerLayout = new GridLayout(0,4);
        center.setLayout(centerLayout);

        JLabel studentLabel = new JLabel("Student");
        JLabel numLabel = new JLabel("Number of absences");
        JButton saveButton = new JButton("Save");
        JLabel numberOfAbs = new JLabel("0");
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        plus.setEnabled(false);
        minus.setEnabled(false);

        List<String> sList = new ArrayList<String>();
        sList = io.readStudentID(className, 'x');
        String[] studentArray = new String[sList.size()+1];
        studentArray[0] = "Select A Student";
        for (int i = 1; i < sList.size( )+1; i++)
            studentArray[i] = sList.get(i-1);
        JComboBox studentsBox = new JComboBox(studentArray);

        center.add(studentLabel);
        center.add(numLabel);
        center.add(Box.createRigidArea(new Dimension(3,3)));
        center.add(Box.createRigidArea(new Dimension(3,3)));

        center.add(studentsBox);
        center.add(numberOfAbs);
        center.add(plus);
        center.add(minus);

        center.add(Box.createRigidArea(new Dimension(3,3)));
        center.add(Box.createRigidArea(new Dimension(3,3)));
        center.add(Box.createRigidArea(new Dimension(3,3)));
        center.add(saveButton);
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(numberOfAbs.getText().contains("0")){

                }
                else{
                    System.out.println("savebttn");
                    io.printAttendance(className, numberOfAbs.getText(),(String)studentsBox.getSelectedItem());
                }
            }
        });
        studentsBox.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if(e.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                    numberOfAbs.setText("0");
                    if ((String)studentsBox.getSelectedItem() == "Select A Student"){
                        plus.setEnabled(false);
                        minus.setEnabled(false);
                    }
                    else{
                        numberOfAbs.setText(io.getAttendance(className, (String)studentsBox.getSelectedItem()));
                        plus.setEnabled(true);
                        minus.setEnabled(true);
                    }
                }
            }
        });
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(numberOfAbs.getText());
                number++;
                numberOfAbs.setText(String.valueOf(number));
            }
        });
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(numberOfAbs.getText());
                number--;
                numberOfAbs.setText(String.valueOf(number));
            }
        });
        


        mainPanel.add(center, BorderLayout.CENTER);

    }




}