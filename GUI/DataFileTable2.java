package GUI;
import Logic.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DataFileTable2 extends JPanel {
    public static teacher currentTeacher;
    public JTable table;
    public DataFileTableModel2 model;

    public DataFileTable2() {
        Font f;
        
        setFont(FontLoader.tableFont);
        setLayout(new BorderLayout());
        
        table = new JTable();
        try{
            model = new DataFileTableModel2(System.getProperty("user.dir") + "/data/" + currentTeacher.username + "/master_student_list.csv", 'm');
            table.setModel(model);
            table.createDefaultColumnsFromModel();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERROR: You have not created Any Students");
        }
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane);
        setVisible(true);
        
    }
    public DataFileTable2(String classname) {

        
        setFont(FontLoader.tableFont);
        setLayout(new BorderLayout());
        
        model = new DataFileTableModel2(System.getProperty("user.dir") + "/data/" + currentTeacher.username + "/" +classname+ "/"+classname+".csv", 's');
        table = new JTable();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane);
        setVisible(true);
        
    }
    
    public DataFileTable2(char g, String classname) {
        
        setFont(FontLoader.tableFont);
        setLayout(new BorderLayout( ));
        model = new DataFileTableModel2(System.getProperty("user.dir") + "/data/" + currentTeacher.username + "/" + classname + "/grades.csv", 'g');
        table = new JTable( );
        table.setModel(model);
        table.createDefaultColumnsFromModel( );
        
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane);
        setVisible(true);
    }
    
    
    public void display( ) {
        JFrame frame = new JFrame("Data File Table");
        DataFileTable2 panel;
        
        panel = new DataFileTable2();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public boolean isCellEditable(int row, int col)
    {
        return true;
    }
}
