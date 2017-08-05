package GUI;
import Logic.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.Color;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Classes extends JPanel {

    // variables declaration
    private JLabel classesMessage= new JLabel();
    private JPanel contentPane;
    private Color darkTextColor;
    private Color daysOfTheWeekDefault;
    private Color daysOfTheWeekSelected;
    private Color defaultBackground;
    private BorderLayout layout;
    private JLabel className = new JLabel();
    private JLabel section= new JLabel();
    private JLabel semester= new JLabel();
    private JLabel meetingDates= new JLabel();
    private JLabel meetingTime= new JLabel();
    private JLabel location= new JLabel();
    private JLabel crn= new JLabel();
    private JLabel sectionLabel= new JLabel();
    private JLabel crnLabel= new JLabel();
    private JLabel monday= new JLabel();
    private JLabel tuesday= new JLabel();
    private JLabel wednesday= new JLabel();
    private JLabel thursday= new JLabel();
    private JLabel friday= new JLabel();
    private JComboBox classDropDown;
    private JLabel dropDownInstructions;
    private JButton gradeLink;

    
    public Classes(){
        init();
    }
    public Classes(String className){
        init();
        update(className);
    }
    public void init(){

        // initialize fonts and colors
        darkTextColor = new Color(50, 50, 50);
        defaultBackground = new Color(235, 235, 235);
        daysOfTheWeekDefault = new Color(255, 255, 255);
        daysOfTheWeekSelected = new Color(161,202,241);
    
        setBackground(defaultBackground);
        setPreferredSize(new Dimension(600, 800));
        layout = new BorderLayout(4, 40);
        setLayout(layout);
        
        setBackground(defaultBackground);
        setPreferredSize(new Dimension(600, 800));
        classesMessage.setText("Classes");
        classesMessage.setFont(FontLoader.headerFont);
        classesMessage.setForeground(darkTextColor);
        classesMessage.setHorizontalAlignment(JLabel.CENTER);
        add(classesMessage, BorderLayout.NORTH);
        
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(defaultBackground);
        GridBagConstraints gbc = new GridBagConstraints();
        add(contentPane, BorderLayout.CENTER);
        
        dropDownInstructions = new JLabel("Select a Class:  ");
        dropDownInstructions.setFont(FontLoader.loginFont);
        dropDownInstructions.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(dropDownInstructions, gbc);
        
        classDropDown = createDropDown();
        classDropDown.setFont(FontLoader.tableFont);
        classDropDown.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if(e.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                    update((String)classDropDown.getSelectedItem());
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(classDropDown, gbc);
        
        className.setText((String)classDropDown.getSelectedItem());
        className.setFont(FontLoader.SFDisplayBold);
        className.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(50, 0, 0, 0);
        contentPane.add(className, gbc);
        
        section.setText("Section: R01");
        section.setFont(FontLoader.subheaderFont);
        section.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(5, 0, 0, 0);
        contentPane.add(section, gbc);
        
        crn.setText("CRN: 12244");
        crn.setFont(FontLoader.subheaderFont);
        crn.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        contentPane.add(crn, gbc);
        
        semester.setText("Semester: Fall");
        semester.setFont(FontLoader.subheaderFont);
        semester.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        contentPane.add(semester, gbc);
        
        monday.setText("Monday");
        monday.setFont(FontLoader.navBarFont);
        monday.setForeground(darkTextColor);
        monday.setBackground(daysOfTheWeekDefault);
        monday.setOpaque(true);
        monday.setPreferredSize(new Dimension(100, 40));
        monday.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 10);
        contentPane.add(monday, gbc);
    
        tuesday.setText("Tuesday");
        tuesday.setFont(FontLoader.navBarFont);
        tuesday.setForeground(darkTextColor);
        tuesday.setBackground(daysOfTheWeekDefault);
        tuesday.setOpaque(true);
        tuesday.setPreferredSize(new Dimension(100, 40));
        tuesday.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        contentPane.add(tuesday, gbc);
        
        wednesday.setText("Wednesday");
        wednesday.setFont(FontLoader.navBarFont);
        wednesday.setForeground(darkTextColor);
        wednesday.setBackground(daysOfTheWeekDefault);
        wednesday.setOpaque(true);
        wednesday.setPreferredSize(new Dimension(100, 40));
        wednesday.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        contentPane.add(wednesday, gbc);
        
        thursday.setText("Thursday");
        thursday.setFont(FontLoader.navBarFont);
        thursday.setForeground(darkTextColor);
        thursday.setBackground(daysOfTheWeekDefault);
        thursday.setOpaque(true);
        thursday.setPreferredSize(new Dimension(100, 40));
        thursday.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        contentPane.add(thursday, gbc);
        
        friday.setText("Friday");
        friday.setFont(FontLoader.navBarFont);
        friday.setForeground(darkTextColor);
        friday.setBackground(daysOfTheWeekDefault);
        friday.setOpaque(true);
        friday.setPreferredSize(new Dimension(100, 40));
        friday.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        contentPane.add(friday, gbc);

        location.setText("Location: JMH 342");
        location.setFont(FontLoader.subheaderFont);
        location.setForeground(darkTextColor);
        location.setHorizontalAlignment(JLabel.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(location, gbc);
        
        gradeLink = new JButton("Grade This Class");
        gradeLink.setFont(FontLoader.loginFont);
        gradeLink.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gradeLink.addActionListener(new ActionListener( ) {
            @Override
			public void actionPerformed(ActionEvent e) {
                System.out.println((String)classDropDown.getSelectedItem());
                swapCenter(new Grades((String)classDropDown.getSelectedItem()));
            }
        });
        contentPane.add(gradeLink, gbc);


        update((String)classDropDown.getSelectedItem());
    }
    
    
    
    // dynamically create dropdown menu
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
    
    
    
    // update class information when new class is selected from dropdown
    private void update(String newClassName){
        FileIO io = new FileIO();
        className.setText(newClassName);
        classroom c = new classroom();
        c = io.getClassInfo(newClassName);
        className.setText(c.className);
        section.setText("Section: " +c.section);
        semester.setText("Semester: " + c.semester);
        meetingTime.setText("Meeting Time: " + c.meetingTime);
        location.setText("Location " + c.location);
        crn.setText("CRN: " + c.crn);
        String daysString = new String();
        for (int i = 0; i < c.daysOfTheWeek.size(); i++){
            daysString = daysString + c.daysOfTheWeek.get(i);
        }
        if(daysString.contains("Monday")){
            monday.setBackground(daysOfTheWeekSelected);
        }
        else{
            monday.setBackground(daysOfTheWeekDefault);
        }
        if(daysString.contains("Tuesday")){
            tuesday.setBackground(daysOfTheWeekSelected);
        }        
        else{
            tuesday.setBackground(daysOfTheWeekDefault);
        }
        if(daysString.contains("Wednesday")){
            wednesday.setBackground(daysOfTheWeekSelected);
        }
        else{
            wednesday.setBackground(daysOfTheWeekDefault);
        }
        if(daysString.contains("Thursday")){
            thursday.setBackground(daysOfTheWeekSelected);
        }
        else{
            thursday.setBackground(daysOfTheWeekDefault);
        }
        if(daysString.contains("Friday")){
            friday.setBackground(daysOfTheWeekSelected);
        }
        else{
            friday.setBackground(daysOfTheWeekDefault);
        }
    }
    public void swapCenter(JPanel replacementPanel) {
		if (layout.getLayoutComponent(layout.CENTER) != null) {
			remove(layout.getLayoutComponent(layout.CENTER));
		}

		add(replacementPanel, layout.CENTER);
		validate( );
		repaint( );
	}
    
    
}
