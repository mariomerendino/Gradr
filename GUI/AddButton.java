package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.event.*;

public class AddButton extends JPanel {
    
    // variables declaration
    private JLabel addMessage;
    private JButton studentButton;
    private JButton classButton;
    private Color darkTextColor;
    private Color defaultBackground;
    
    // constructor
    public AddButton(){
        // initialize fonts and colors
        darkTextColor = new Color(50, 50, 50);
        defaultBackground = new Color(235, 235, 235);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(defaultBackground);
        setPreferredSize(new Dimension(600, 800));
        
        addMessage  = new JLabel("Create New...");
        addMessage.setFont(FontLoader.headerFont);
        addMessage.setForeground(darkTextColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 60;
        gbc.gridwidth = 2;
        add(addMessage, gbc);
        
        studentButton = new JButton("Student");
        studentButton.setFont(FontLoader.subheaderFont);
        studentButton.setForeground(darkTextColor);
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStudent studentPage = new AddStudent();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.ipadx = 90;
        gbc.gridwidth = 1;
        add(studentButton, gbc);
        
        classButton = new JButton("   Class   ");
        classButton.setFont(FontLoader.subheaderFont);
        classButton.setForeground(darkTextColor);
        classButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddClass classPage = new AddClass();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(classButton, gbc);
    
    }
}
