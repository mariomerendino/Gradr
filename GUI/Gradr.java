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
import java.io.*;

public class Gradr {
    
    // variables declaration
    private static teacher currentTeacher;
    private Color navBarGrey;
    private Color lightTextColor;
    private Color defaultBackground;
    private Color footerBackground;
    private Color lightSelectedText;
    private Color darkTextColor;
    private Container contentPane;
    private JFrame frame;
    private JFrame addButtonFrame;
    private JPanel homeScreen;
    private JPanel studentScreen;
    private JPanel classesScreen;
    private JPanel leftPadding;
    private JPanel rightPadding;
    private JPanel footer;
    private JPanel recentClassesContainer;
    private JPanel fillerSpace;
    private JLabel recentClass1;
    private JLabel recentClass2;
    private JLabel recentClass3;
    private JLabel welcomeMessage;
    private JLabel studentMessage;
    private JLabel classesMessage;
    private JLabel logoLabel;
    private JLabel recentClasses;
    private JLabel recentMessages;
    private JLabel messages;
    private BorderLayout mainLayout;
    private JLabel home;
    private JLabel students;
    private JLabel classes;
    private JLabel addButton;
    private JLabel grades;
    
    
    // constructor initializes GUI
    public Gradr(){
        initializeGUI();
    }

    
    
    // initializes GUI to home screen
    public void initializeGUI(){
        
        frame = new JFrame("Gradr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // instantiate Gradr GUI and create content pane in border layout
        contentPane = frame.getContentPane();
        mainLayout = new BorderLayout(4, 40);
        contentPane.setLayout(mainLayout);
        
        // set colors and fonts
        defaultBackground = new Color(235, 235, 235);
        footerBackground = new Color(255, 255, 255);
        navBarGrey = new Color(50, 50, 50);
        lightTextColor = new Color(255, 255, 255);
        lightSelectedText = new Color(180, 180, 180);
        darkTextColor = new Color(50, 50, 50);
        
        // create panel to house navigation bar
        contentPane.add(createNavigationBar(), BorderLayout.NORTH);
        contentPane.setBackground(defaultBackground);
        
        // create panel to pad left and right sides
        leftPadding = new JPanel();
        rightPadding = new JPanel();
        contentPane.add(leftPadding, BorderLayout.WEST);
        contentPane.add(rightPadding, BorderLayout.EAST);

        // create footer panel for Gradr logo
        footer = new JPanel();
        footer.setBackground(footerBackground);
        ImageIcon gradrlogo = new ImageIcon(new ImageIcon("gradrlogo.png").getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH));
        logoLabel = new JLabel(gradrlogo);
        footer.add(logoLabel);
        contentPane.add(footer, BorderLayout.SOUTH);
        
        showHomeScreen();
        
        // size, position, and display frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    // removes current center pane and replaces with home screen
    public void showHomeScreen(){
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            contentPane.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        
        // create center pane to house current page display
        homeScreen = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        homeScreen.setBackground(defaultBackground);
        homeScreen.setPreferredSize(new Dimension(600, 800));
        
        // add welcome message to homeScreen
        welcomeMessage = new JLabel("Welcome, " + currentTeacher.firstName + ".");
        welcomeMessage.setFont(FontLoader.headerFont);
        welcomeMessage.setForeground(darkTextColor);
        welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 60;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 10, 10);
        homeScreen.add(welcomeMessage, gbc);
        
        // add recentClasses label to homeScreen
        recentClasses = new JLabel("Recent Classes:");
        recentClasses.setFont(FontLoader.subheaderFont);
        recentClasses.setForeground(darkTextColor);
        recentClasses.setHorizontalAlignment(JLabel.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        homeScreen.add(recentClasses, gbc);
        
        try {
            // import classList
            FileIO io = new FileIO();
            List<String> classList = new ArrayList<String>();
            classList = io.readClassList(currentTeacher.username);

            try {
                recentClass1 = new JLabel(classList.get(0));
                recentClass1.setFont(FontLoader.subheaderFont);
                recentClass1.setForeground(lightTextColor);
                recentClass1.setBackground(new Color(22, 120, 202));
                recentClass1.setOpaque(true);
                recentClass1.setPreferredSize(new Dimension(300, 100));
                recentClass1.setHorizontalAlignment(JLabel.CENTER);
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.ipady = 0;
                gbc.gridwidth = 1;
                gbc.anchor = GridBagConstraints.CENTER;
                homeScreen.add(recentClass1, gbc);
                
                // add event listeners for each recent classes button
                recentClass1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                recentClass1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        home.setForeground(lightTextColor);
                        students.setForeground(lightTextColor);
                        classes.setForeground(lightSelectedText);
                        addButton.setForeground(lightTextColor);
                        swapCenter(new Classes());
                    }
                });
            } catch (Exception e){
            }
            
            try {
                recentClass2 = new JLabel(classList.get(1));
                recentClass2.setFont(FontLoader.subheaderFont);
                recentClass2.setForeground(lightTextColor);
                recentClass2.setBackground(new Color(206, 21, 51));
                recentClass2.setOpaque(true);
                recentClass2.setPreferredSize(new Dimension(300, 100));
                recentClass2.setHorizontalAlignment(JLabel.CENTER);
                String recentClass2String = recentClass2.getText();
                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.ipady = 0;
                gbc.gridwidth = 1;
                homeScreen.add(recentClass2, gbc);
                
                // add event listeners for each recent classes button
                recentClass2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                recentClass2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        home.setForeground(lightTextColor);
                        students.setForeground(lightTextColor);
                        classes.setForeground(lightSelectedText);
                        addButton.setForeground(lightTextColor);
                        swapCenter(new Classes(recentClass2String));
                    }
                });
            } catch (Exception e){
            }
            
            try {
                recentClass3 = new JLabel(classList.get(2));
                recentClass3.setFont(FontLoader.subheaderFont);
                recentClass3.setForeground(lightTextColor);
                recentClass3.setBackground(new Color(64, 177, 78));
                recentClass3.setOpaque(true);
                recentClass3.setPreferredSize(new Dimension(300, 100));
                recentClass3.setHorizontalAlignment(JLabel.CENTER);
                String recentClass3String = recentClass3.getText();
                gbc.gridx = 2;
                gbc.gridy = 2;
                gbc.ipady = 0;
                gbc.gridwidth = 1;
                homeScreen.add(recentClass3, gbc);
            
                // add event listeners for each recent classes button
                recentClass3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                recentClass3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        home.setForeground(lightTextColor);
                        students.setForeground(lightTextColor);
                        classes.setForeground(lightSelectedText);
                        addButton.setForeground(lightTextColor);
                        swapCenter(new Classes(recentClass3String));
                    }
                });
            } catch (Exception e){
            }
        }
        catch (java.lang.NullPointerException ex){
            JLabel noClassesMessage = new JLabel("No Classes Added Yet.");
            noClassesMessage.setFont(FontLoader.subheaderFont);
            noClassesMessage.setForeground(darkTextColor);
            noClassesMessage.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            homeScreen.add(noClassesMessage, gbc);
        }

        
        fillerSpace = new JPanel();
        fillerSpace.setBackground(defaultBackground);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 50;
        gbc.gridwidth = 3;
        homeScreen.add(fillerSpace, gbc);
        
        recentMessages = new JLabel("Recent Messages:");
        recentMessages.setFont(FontLoader.subheaderFont);
        recentMessages.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.ipady = 0;
        gbc.gridwidth = 3;
        homeScreen.add(recentMessages, gbc);
        
        messages = new JLabel("No recent messages. Check back later!");
        messages.setFont(FontLoader.tableFont);
        messages.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        homeScreen.add(messages, gbc);
        
        contentPane.add(homeScreen, BorderLayout.CENTER);
        contentPane.validate();
        contentPane.repaint();
    }
    
    
    
    // applet houses navigation bar at the top of program
    public JApplet createNavigationBar(){
        
        //Creates JApplet(Container) to eventually be returned
        JApplet navbar = new JApplet();
        navbar.getContentPane().setBackground(navBarGrey);
        
        //gets Screen Size, used later...
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Double widthDouble = screenSize.getWidth()/1.7;
        int width = widthDouble.intValue();
        
        //Sets up the flowlayout for the nav bar.
        FlowLayout fLayout = new FlowLayout(FlowLayout.LEFT, 40, 15);
        navbar.setLayout(fLayout);
        
        //Adds the exclamation point button icon.
        ImageIcon exclam = new ImageIcon(new ImageIcon("exclam.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JLabel exclamIcon = new JLabel(exclam);
        exclamIcon.setSize(30,30);
        
        //Adds the exclamation point button icon.
        ImageIcon plus = new ImageIcon(new ImageIcon("plus.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JLabel plusIcon = new JLabel(plus);
        plusIcon.setSize(30,30);
        
        //Creates Labels for home, students, and classes.
        home = new JLabel();
        students = new JLabel();
        classes = new JLabel();
        addButton = new JLabel();
        grades = new JLabel();
        
        //Sets the text and default bolding of each jlabel.
        home.setFont(FontLoader.navBarFont);
        home.setText("Home");
        home.setForeground(lightSelectedText);
        students.setFont(FontLoader.navBarFont);
        students.setText("Students");
        students.setForeground(lightTextColor);
        classes.setFont(FontLoader.navBarFont);
        classes.setText("Classes");
        classes.setForeground(lightTextColor);
        grades.setText("Grades");
        grades.setFont(FontLoader.navBarFont);
        grades.setForeground(lightTextColor);
        addButton.setFont(FontLoader.navBarFont);
        addButton.setText(" + ");
        addButton.setForeground(lightTextColor);
        
        
        //adds listeners for clicks on the Home, classes, students
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.setForeground(lightSelectedText);
                students.setForeground(lightTextColor);
                classes.setForeground(lightTextColor);
                addButton.setForeground(lightTextColor);
                grades.setForeground(lightTextColor);
                swapCenter(homeScreen);
            }
        });
        classes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        classes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.setForeground(lightTextColor);
                students.setForeground(lightTextColor);
                classes.setForeground(lightSelectedText);
                addButton.setForeground(lightTextColor);
                grades.setForeground(lightTextColor);
                swapCenter(new Classes());
            }
        });
        students.setCursor(new Cursor(Cursor.HAND_CURSOR));
        students.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.setForeground(lightTextColor);
                students.setForeground(lightSelectedText);
                classes.setForeground(lightTextColor);
                addButton.setForeground(lightTextColor);
                grades.setForeground(lightTextColor);
                swapCenter(new Students());
            }
        });
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.setForeground(lightTextColor);
                students.setForeground(lightTextColor);
                classes.setForeground(lightTextColor);
                addButton.setForeground(lightSelectedText);
                grades.setForeground(lightTextColor);
                swapCenter(new AddButton());
            }
        });
        grades.setCursor(new Cursor(Cursor.HAND_CURSOR));
        grades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.setForeground(lightTextColor);
                students.setForeground(lightTextColor);
                classes.setForeground(lightTextColor);
                grades.setForeground(lightSelectedText);
                addButton.setForeground(lightTextColor);
                swapCenter(new Grades());
            }
        });
//        exclamIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        exclamIcon.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showMessageDialog(null, "ExcalmationPoint", "Button Clickeed", JOptionPane.PLAIN_MESSAGE);
//            }
//        });
//        plusIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        plusIcon.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showMessageDialog(null, "PlusIcon", "Button Clickeed", JOptionPane.PLAIN_MESSAGE);
//            }
//        });
        
        //adds the labels to the applet.
        navbar.add(home);
        navbar.add(classes);
        navbar.add(students);
        navbar.add(grades);
        
        //creates a horizontalspace
        navbar.add(Box.createHorizontalStrut(width));
        
        // add icon labels
        navbar.add(addButton);
        //navbar.add(plusIcon);
        //navbar.add(exclamIcon);
        
        navbar.setVisible(true);
        return navbar;
    }

    
    
    // receives panel and swaps with the current CENTER screen
    public void swapCenter(JPanel replacementPanel){
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            contentPane.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        
        contentPane.add(replacementPanel, BorderLayout.CENTER);
        contentPane.validate();
        contentPane.repaint();
    }
    
    
    
    // main function
    public static void main ( String [] args ){
        Gradr gradr = new Gradr();
    }
    
}
