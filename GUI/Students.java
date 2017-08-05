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

public class Students extends JPanel{
    public static teacher currentTeacher;
    public JComboBox classDropDown = new JComboBox();
    public JLabel title = new JLabel();
    public BorderLayout studentsPageLayout;
    public static String classroom = new String();
    private JPanel topBar;
    private Color darkTextColor;
    private Color backgroundColor;
    
    
    public Students(){
        darkTextColor = new Color(50, 50, 50);
        backgroundColor = new Color(235, 235, 235);
        
        topBar = new JPanel();
        topBar.setLayout(new FlowLayout());
        studentsPageLayout = new BorderLayout();
        setLayout(studentsPageLayout);

        title.setText("Master Students List");
        title.setFont(FontLoader.headerFont);
        title.setForeground(darkTextColor);
        title.setVisible(true);
        topBar.add(title);
        
        classDropDown = createDropDown();
        classDropDown.setFont(FontLoader.tableFont);
        classDropDown.setForeground(darkTextColor);
        classDropDown.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if(e.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                    System.out.println((String)classDropDown.getSelectedItem());
                    update((String)classDropDown.getSelectedItem());
                }
            }
        });
        topBar.add(classDropDown);
        
        add(topBar, studentsPageLayout.NORTH);
        add(new DataFileTable2(), studentsPageLayout.CENTER);
        
    }
    
    
    
    // update informaiton
    public void update(String classroom){
        title.setText(classroom);
        if (classroom == "Master Students List"){
            swapCenter(new DataFileTable2());
        }
        else{
            swapCenter(new DataFileTable2(classroom));
            System.out.println("hi");
        }
        
    }
    
    
    // swap center for tables
    public void swapCenter(JPanel replacementPanel){
        if (studentsPageLayout.getLayoutComponent(studentsPageLayout.CENTER) != null) {
            remove(studentsPageLayout.getLayoutComponent(studentsPageLayout.CENTER));
        }
        add(replacementPanel, studentsPageLayout.CENTER);
        validate();
        repaint();
    }
    
    
    
    // create dropdown menu
    public JComboBox createDropDown(){
        FileIO io = new FileIO();
        List<String> classList = new ArrayList<String>();
        classList = io.readClassList(currentTeacher.username);
        String[] classArray = new String[classList.size()+1];
        classArray[0] = "Master Students List";
        for(int i=1; i < classList.size()+1; i++){
            classArray[i] = classList.get(i-1);
        }
        JComboBox box = new JComboBox(classArray);
        return box;
    }
}
