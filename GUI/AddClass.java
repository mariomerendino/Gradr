package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.event.*;

public class AddClass extends JFrame{
    
    // variables declaration
    private JPanel contentPanel;
    private JLabel headerMessage;
    private Color backgroundColor;
    private Color darkTextColor;
    private JLabel nameLabel;
    private JLabel sectionLabel;
    private JLabel crnLabel;
    private JLabel meetingTimeLabel;
    private JLabel meetingDays;
    private JLabel semesterLabel;
    private JLabel locationLabel;
    private JTextField name;
    private JTextField section;
    private JTextField crn;
    private JTextField location;
    private JComboBox semester;
    private JComboBox meetingTime;
    private JCheckBox monday;
    private JCheckBox tuesday;
    private JCheckBox wednesday;
    private JCheckBox thursday;
    private JCheckBox friday;
    private JButton cancelButton;
    private JButton submitButton;
    
    
    
    // constructor
    public AddClass(){
        
        setTitle("New Class");
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new javax.swing.border.EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        add(contentPanel);
        
        darkTextColor = new Color(50, 50, 50);
        backgroundColor = new Color(235, 235, 235);

        headerMessage = new JLabel("Add New Class");
        headerMessage.setFont(FontLoader.SFDisplayBold);
        headerMessage.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.ipady = 30;
        contentPanel.add(headerMessage, gbc);
        
        nameLabel = new JLabel("Class Name");
        nameLabel.setFont(FontLoader.SFDisplay);
        nameLabel.setForeground(darkTextColor);
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(nameLabel, gbc);
        
        crnLabel = new JLabel("CRN");
        crnLabel.setFont(FontLoader.SFDisplay);
        crnLabel.setForeground(darkTextColor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(crnLabel, gbc);
        
        name = new JTextField(12);
        name.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPanel.add(name, gbc);
        
        crn = new JTextField(12);
        crn.setFont(FontLoader.loginFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPanel.add(crn, gbc);
        
        sectionLabel = new JLabel("Section");
        sectionLabel.setFont(FontLoader.SFDisplay);
        sectionLabel.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPanel.add(sectionLabel, gbc);
        
        meetingTimeLabel = new JLabel("Meeting Time");
        meetingTimeLabel.setFont(FontLoader.SFDisplay);
        meetingTimeLabel.setForeground(darkTextColor);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPanel.add(meetingTimeLabel, gbc);
        
        section = new JTextField(12);
        section.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        contentPanel.add(section, gbc);
        
        String[] classTimes = {"8:30 AM", "9:00 AM", "10:00 AM", "11:30 AM", "1:00 PM", "2:30 PM", "4:00 PM", "5:30 PM"};
        meetingTime = new JComboBox(classTimes);
        meetingTime.setFont(FontLoader.loginFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        contentPanel.add(meetingTime, gbc);
        
        meetingDays = new JLabel("Meeting Days");
        meetingDays.setFont(FontLoader.SFDisplay);
        meetingDays.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        contentPanel.add(meetingDays, gbc);
        
        monday = new JCheckBox("Monday");
        monday.setFont(FontLoader.SFDisplay);
        monday.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        contentPanel.add(monday, gbc);
        
        tuesday = new JCheckBox("Tuesday");
        tuesday.setFont(FontLoader.SFDisplay);
        tuesday.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 7;
        contentPanel.add(tuesday, gbc);
        
        wednesday = new JCheckBox("Wednesday");
        wednesday.setFont(FontLoader.SFDisplay);
        wednesday.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 8;
        contentPanel.add(wednesday, gbc);
        
        thursday = new JCheckBox("Thursday");
        thursday.setFont(FontLoader.SFDisplay);
        thursday.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 9;
        contentPanel.add(thursday, gbc);
        
        friday = new JCheckBox("Friday");
        friday.setFont(FontLoader.SFDisplay);
        friday.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 10;
        contentPanel.add(friday, gbc);
        
        semesterLabel = new JLabel("Semester");
        semesterLabel.setFont(FontLoader.SFDisplay);
        semesterLabel.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 11;
        contentPanel.add(semesterLabel, gbc);
        
        String[] semesterOptions = {"Fall", "Spring", "Summer"};
        semester = new JComboBox(semesterOptions);
        semester.setFont(FontLoader.loginFont);
        semester.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 12;
        contentPanel.add(semester, gbc);
        
        locationLabel = new JLabel("Location");
        locationLabel.setFont(FontLoader.SFDisplay);
        semesterLabel.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 13;
        contentPanel.add(locationLabel, gbc);
        
        location = new JTextField(12);
        location.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 14;
        contentPanel.add(location, gbc);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(FontLoader.loginFont);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.ipady = 0;
        gbc.ipadx = 60;
        gbc.insets = new Insets(20, 10, 10, 20);
        contentPanel.add(cancelButton, gbc);
        
        // submit button including action listener to pass information to classroom
        submitButton = new JButton("Submit");
        submitButton.setFont(FontLoader.loginFont);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileIO addClass = new FileIO();
                String nameString = name.getText();
                String crnString = crn.getText();
                String sectionString = section.getText();
                String meetingTimeString = (String)meetingTime.getSelectedItem();
                String semesterString = (String)semester.getSelectedItem();
                String locationString = location.getText();
                
                // create list of meeting days to pass to classroom
                List<String> meetingDaysList = new ArrayList<String>();
                if(monday.isSelected()){
                    meetingDaysList.add("Monday");
                }
                if(tuesday.isSelected()){
                    meetingDaysList.add("Tuesday");
                }
                if(wednesday.isSelected()){
                    meetingDaysList.add("Wednesday");
                }
                if(thursday.isSelected()){
                    meetingDaysList.add("Thursday");
                }
                if(friday.isSelected()){
                    meetingDaysList.add("Friday");
                }
                
                classroom newClassRoom = new classroom(nameString, crnString, semesterString, sectionString, meetingTimeString, meetingDaysList, locationString);

                newClassRoom.addClass();
                addClass.createClassInfoFile(newClassRoom);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 15;
        contentPanel.add(submitButton, gbc);
        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}