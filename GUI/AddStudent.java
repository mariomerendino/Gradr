package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.event.*;

public class AddStudent extends JFrame {

    // variables declaration
    private JPanel contentPanel;
    private JLabel headerMessage;
    private JLabel instructionsLabel;
    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JLabel ageLabel;
    private JLabel idLabel;
    private JLabel dropDownLabel;
    private JTextField fnameField;
    private JTextField lnameField;
    private JTextField ageField;
    private JTextField idField;
    private JComboBox dropDown;
    private JComboBox classDropDown;
    private Color darkTextColor;
    private Color backgroundColor;
    private JButton cancelButton;
    private JButton submitButton;
    
    // constructor
    public AddStudent(){
        setTitle("New Student");
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new javax.swing.border.EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        add(contentPanel);
        
        darkTextColor = new Color(50, 50, 50);
        backgroundColor = new Color(235, 235, 235);
        
        headerMessage = new JLabel("Add New Student");
        headerMessage.setFont(FontLoader.SFDisplayBold);
        headerMessage.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.ipady = 30;
        contentPanel.add(headerMessage, gbc);
        
        fnameLabel = new JLabel("First Name");
        fnameLabel.setFont(FontLoader.SFDisplay);
        fnameLabel.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(fnameLabel, gbc);

        lnameLabel = new JLabel("Last Name");
        lnameLabel.setFont(FontLoader.SFDisplay);
        lnameLabel.setForeground(darkTextColor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(lnameLabel, gbc);
        
        fnameField = new JTextField(12);
        fnameField.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(fnameField, gbc);
        
        lnameField = new JTextField(12);
        lnameField.setFont(FontLoader.loginFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(lnameField, gbc);
        
        idLabel = new JLabel("Fordham ID Number");
        idLabel.setFont(FontLoader.SFDisplay);
        idLabel.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(idLabel, gbc);
        
        ageLabel = new JLabel("Age");
        ageLabel.setFont(FontLoader.SFDisplay);
        ageLabel.setForeground(darkTextColor);
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(ageLabel, gbc);
        
        idField = new JTextField(12);
        idField.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(idField, gbc);
        
        ageField = new JTextField(4);
        ageField.setFont(FontLoader.loginFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPanel.add(ageField, gbc);
        
        dropDownLabel = new JLabel("Class");
        dropDownLabel.setFont(FontLoader.SFDisplay);
        dropDownLabel.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPanel.add(dropDownLabel, gbc);
        
        classDropDown = createDropDown();
        classDropDown.setFont(FontLoader.loginFont);
        classDropDown.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPanel.add(classDropDown, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(FontLoader.loginFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.ipady = 0;
        gbc.ipadx = 60;
        gbc.insets = new Insets(20, 0, 0, 20);
        contentPanel.add(cancelButton, gbc);

        submitButton = new JButton("Submit");
        submitButton.setFont(FontLoader.loginFont);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileIO addStudent = new FileIO();
                String className = (String)classDropDown.getSelectedItem();
                String firstName = fnameField.getText();
                String lastName = lnameField.getText();
                int age =  Integer.parseInt(ageField.getText());
                String id = idField.getText();
                System.out.println(0);
                student newStudent = new student();
                newStudent.setStudentInfo(firstName, lastName, age, id);
                System.out.println(1);
                addStudent.appendStudent(newStudent, className);
                System.out.println(2);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 7;
        contentPanel.add(submitButton, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    
    
    public JComboBox createDropDown(){
        FileIO io = new FileIO();
        List<String> classList = new ArrayList<String>();
        classList = io.readClassList(Login.t1.username);
        String[] classArray = new String[classList.size()];
        
        for (int i = 0; i < classList.size( ); i++)
            classArray[i] = classList.get(i);
        
        JComboBox box = new JComboBox(classArray);
        return box;
    }
}
