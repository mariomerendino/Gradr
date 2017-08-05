package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.event.*;

public class addAssignmentPage extends JFrame{
    
    // variables declaration
    private JPanel contentPanel;
    private JLabel nameLabel;
    private JLabel weightLabel;
    private JLabel totalPointsLabel;
    private JLabel header;
    private Color darkTextColor;
    private Color backgroundColor;
    private JTextField weightField;
    private JTextField nameField;
    private JTextField totalPointsField;
    private JButton saveButton;
    private JButton cancelButton;
    
    // constructor
    public addAssignmentPage(String className){
        
        setTitle("New Assignment");
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new javax.swing.border.EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        add(contentPanel);
        
        darkTextColor = new Color(50, 50, 50);
        backgroundColor = new Color(235, 235, 235);
        
        header = new JLabel("Add New Assignment");
        header.setFont(FontLoader.SFDisplayBold);
        header.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.ipady = 30;
        contentPanel.add(header, gbc);
        
        nameLabel = new JLabel("Assignment Name");
        nameLabel.setFont(FontLoader.SFDisplay);
        nameLabel.setForeground(darkTextColor);
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(nameLabel, gbc);
        
        nameField = new JTextField(12);
        nameField.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(nameField, gbc);
        
        totalPointsLabel = new JLabel("Points Possible");
        totalPointsLabel.setFont(FontLoader.SFDisplay);
        totalPointsLabel.setForeground(darkTextColor);
        totalPointsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(nameLabel, gbc);
        
        weightLabel = new JLabel("Weight (percentage)");
        weightLabel.setFont(FontLoader.SFDisplay);
        weightLabel.setForeground(darkTextColor);
        weightLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(weightLabel, gbc);

        totalPointsLabel = new JLabel("Total Points Possible");
        totalPointsLabel.setFont(FontLoader.SFDisplay);
        totalPointsLabel.setForeground(darkTextColor);
        totalPointsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(totalPointsLabel, gbc);
        
        weightField = new JTextField(6);
        weightField.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        contentPanel.add(weightField, gbc);
        
        totalPointsField = new JTextField(6);
        totalPointsField.setFont(FontLoader.loginFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPanel.add(totalPointsField, gbc);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(FontLoader.loginFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.ipady = 0;
        gbc.ipadx = 60;
        gbc.insets = new Insets(20, 0, 0, 20);
        contentPanel.add(cancelButton, gbc);
        
        saveButton = new JButton("Submit");
        saveButton.setFont(FontLoader.loginFont);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = nameField.getText();
                    int weight = Integer.parseInt(weightField.getText());
                    int totalPoints = Integer.parseInt(totalPointsField.getText());
                    FileIO io = new FileIO();
                    io.addAssignment(className,name,weight,totalPoints);
                    io.setDefaultValuesForGradesWhenAddingANewAssignment(className);

                    dispose();
                }
                catch(Exception x){
                    JOptionPane.showMessageDialog(null,"ERROR: Incorrect entry for weight or total points!\nIt must be an Interger.");
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        contentPanel.add(saveButton, gbc);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
}
