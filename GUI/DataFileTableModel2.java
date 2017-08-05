package GUI;
import Logic.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class DataFileTableModel2 extends AbstractTableModel {
    
    public Vector data;
    protected Vector columnNames ;
    protected String datafile;
    protected char type;
    public FileIO io = new FileIO();
    
    public DataFileTableModel2(String f, char t){
        datafile = f;
        type = t;
        initVectors();
    }
    
    public void initVectors() {
        
        String aLine ;
        data = new Vector();
        columnNames = new Vector();
        
        try {
            FileInputStream fin =  new FileInputStream(datafile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            int location = 0;
            if (type == 'g'){
                String[] parts = datafile.split("/");
                for(int i = 0; i < parts.length; i++){
                  if(parts[i].contains("data")){
                    location = i + 2;
                  }
                }
                List<String> assignmentList = io.assignmentNames(parts[location]);
                //int numOfAssignmnets = io.amountOfAssignments(parts[7]);
                columnNames.addElement("Last Name");
                columnNames.addElement("First Name");
                columnNames.addElement("Age");
                columnNames.addElement("Id Number");
                for(int i=0; i < assignmentList.size(); i++){
                    columnNames.addElement(assignmentList.get(i));
                }
            }
            if (type == 'm'){
                columnNames.addElement("Id Number");
                columnNames.addElement("Last Name");
                columnNames.addElement("First Name");
                columnNames.addElement("Age");
            }
            if (type == 's'){
                columnNames.addElement("Last Name");
                columnNames.addElement("First Name");
                columnNames.addElement("Age");
                columnNames.addElement("Id Number");
            }
            // extract data
            while ((aLine = br.readLine()) != null) {
                StringTokenizer st2 =
                new StringTokenizer(aLine, ",");
                while(st2.hasMoreTokens())
                    data.addElement(st2.nextToken());
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: No Students Created");
            System.out.println(e);
        }
    }
    
    public int getRowCount() {
        if (getColumnCount() == 0){
            return 0;
        }
        else{
            return data.size() / getColumnCount();
        }
    }
    
    public int getColumnCount(){
        return columnNames.size();
    }
    
    public String getColumnName(int columnIndex) {
        String colName = "";
        
        if (columnIndex <= getColumnCount())
            colName = (String)columnNames.elementAt(columnIndex);
        
        return colName;
    }
    public Class getColumnClass(int columnIndex){
        return String.class;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (String)data.elementAt( (rowIndex * getColumnCount()) + columnIndex);
    }
    public boolean isCellEditable(int row, int col)
    {
        if (col >= 4){
            return true;
        }
        else{
            return false;
        }
    }
    public void setValueAt(Object value, int row, int col){
        data.setElementAt(value, (row * getColumnCount()) + col);
        fireTableCellUpdated(row, col);
    }
    
}
