package Logic;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.swing.*;

//GradeBook Class
public class gradebook{
    public teacher currentTeacher;
    public gradebook(){

    }
    public gradebook(teacher t){
        currentTeacher = t;

    }
    //creates a list of classrooms
    public static List<classroom> classList = new ArrayList<classroom>();
    public static List<String> classString = new ArrayList<String>( );
    public void run(){
    	FileIO io = new FileIO( );
        gradebook g = new gradebook();
        System.out.println("Welcome to the gradebook");
        int choice = 1;
        while (choice != 0){
            Scanner userInput = new Scanner (System.in);
            System.out.print("What would you like to do?\n");
            System.out.println("\t1)Add Class\n\t2)Display List of Classes\n\t3)Edit Class\n\t4)Delete Class\n\t0)Exit\n");
            choice = userInput.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Whats the name of the class?");
                    String n;
                    n = userInput.next();
                    g.addClass(n);
                    break;
                case 2:
                    g.displayClasses();
                    break;
                case 3:
                    g.editClass();
                    break;
                case 4:
                    g.deleteClass();
                    break;
                case 0:
                    break;
            }
        }
    }
    public void displayClasses(){
        FileIO io = new FileIO( );
        classString = io.readClassList(currentTeacher.username);
        for (int i = 0; i < classString.size( ); i++){
            System.out.println("Class " + (i + 1) + ") "  + classString.get(i));
        }
    }
    public void addClass(String className){

        String userName = currentTeacher.username;
        Scanner userInput = new Scanner (System.in);
        FileIO io = new FileIO();
        classroom c = new classroom(className);
        classList.add(c);

        String nameOfClass = c.className;
        String path = "data/" + userName  + "/" + c.className + '/' + c.className;  
        
        String gradePath = "data/" + userName + "/" +c.className + "/grades";

        io.createDirectory("data/" + userName + "/class_list");
        io.createDirectory(path);
        io.createDirectory(gradePath);
        io.addToClassList(userName, nameOfClass);
    }

    public void deleteClass(){
        System.out.println("Which one?");
        displayClasses();
        int n; 
        Scanner userInput = new Scanner (System.in);
        n = userInput.nextInt();
        classList.remove(n);
    }
    public void editClass(){
        System.out.println("Which one?");
        displayClasses();
        int n; 
        Scanner userInput = new Scanner (System.in);
        n = userInput.nextInt();
        n = n - 1;
        String className = classString.get(n);
        classroom c = new classroom(className);
        c.edit( );
        System.out.println(className);

    }

}
