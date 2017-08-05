package GUI;
import Logic.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Login {
    
    // variables declaration
    private JFrame frame;
    private Container contentPane;
    private JPanel center;
    private JPanel header;
    private BorderLayout layout;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JLabel logoLabel;
    private Color darkTextColor;
    private Color headerBackground;
    public static teacher t1;
    
    
    // constructor
    public Login(){
        frame = new JFrame("Login to Gradr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(loginButton);
        
        contentPane = frame.getContentPane();
        layout = new BorderLayout(10, 10);
        contentPane.setLayout(layout);
        
        darkTextColor = new Color(50, 50, 50);
        headerBackground = new Color(255, 255, 255);
        
        // generate header with logo
        header = new JPanel();
        header.setBackground(headerBackground);
        ImageIcon gradrlogo = new ImageIcon(new ImageIcon("gradrlogo.png").getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH));
        logoLabel = new JLabel(gradrlogo);
        header.add(logoLabel);
        contentPane.add(header, BorderLayout.NORTH);
        
        // create panel for center
        center = new JPanel();
        center.setPreferredSize(new Dimension(500, 150));
        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(FontLoader.SFDisplay);
        usernameLabel.setForeground(darkTextColor);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        center.add(usernameLabel, gbc);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(FontLoader.SFDisplay);
        passwordLabel.setForeground(darkTextColor);
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        center.add(passwordLabel, gbc);
        
        usernameField = new JTextField(8);
        usernameField.setFont(FontLoader.loginFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 50;
        gbc.anchor = GridBagConstraints.CENTER;
        center.add(usernameField, gbc);
        
        passwordField = new JPasswordField(8);
        passwordField.setFont(FontLoader.loginFont);
        // add listener for ENTER key press
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                loginButtonActionPerformed(evt);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 50;
        gbc.anchor = GridBagConstraints.CENTER;
        center.add(passwordField, gbc);
        
        loginButton = new JButton("Login");
        loginButton.setFont(FontLoader.loginFont);
        loginButton.setForeground(darkTextColor);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.ipadx = 0;
        center.add(loginButton, gbc);
        
        registerButton = new JButton("Register New User");
        registerButton.setFont(FontLoader.loginFont);
        registerButton.setForeground(darkTextColor);
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 1;
        gbc.ipadx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        center.add(registerButton, gbc);
        
        contentPane.add(center, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

    
    
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt){
        FileIO io = new FileIO();
        io.teacherRegistration();
    }
    
    
    
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        FileIO io = new FileIO();
        String pass = new String(passwordField.getPassword());
        if(io.login(usernameField.getText(), pass)){
            t1 = new teacher();
            t1 = io.readTeacher(usernameField.getText());
            gradebook g = new gradebook(t1);
            Gradr GradrMainProgram = new Gradr();
            frame.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null,"ERROR: Incorrect username or password.");
        }
    }
    
    
    public static void main(String[] args){
        Login login = new Login();
    }
}
