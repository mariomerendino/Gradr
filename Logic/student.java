//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
package Logic;
import java.util.*;
import java.io.*;

public class student{   
    public String firstName;
    public String lastName;
    public double average;
    public int age;
    public String idNumber;
    public static List<gradedAssignment> AssignmentList = new ArrayList<gradedAssignment>();
    public student(){

    }
    public student(String fName, String lName, double avg, int a, String id){
        firstName = fName;
        lastName = lName;
        average = avg;
        age = a;
        idNumber = id;
    }

    /*public static void main( String[] args ){
        student x = new student();
        x.inputStudent();
        x.displayStudent();
        
    }*/
    public student(student s){
        firstName = s.firstName;
        lastName = s.lastName;
        average = s.average;
        age = s.age;
        idNumber = s.idNumber;
    }
    public void setFirstName(String f){
        firstName = f;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String l){
        lastName = l;
    }
    public String getLastName(){
        return lastName;
    }
    public void setStudentInfo(String fName, String lName, int a, String id){
        firstName = fName;
        lastName = lName;
        age = a;
        idNumber = id;
    }
    public student inputStudent(){
        Scanner user_input = new Scanner( System.in );
        System.out.println("Input info for student");
        System.out.println("Sutdents First name: ");
        firstName = user_input.next();
        System.out.println("Sutdents Last name: ");
        lastName = user_input.next();
        System.out.println("Sutdents age: ");
        age = user_input.nextInt();
        System.out.println("Student Identification Number");
        idNumber = user_input.next();
        return this;
    }
    public void displayStudent(){
        System.out.print("Name: " + firstName + " " + lastName + "\n");
        System.out.print("Age: " + age + "\n");
        System.out.print("Id: " + idNumber+ "\n");
        System.out.print("Grades" + AssignmentList.size() + ": \n\n");
        for(int i = 0; i < AssignmentList.size(); i++){
            System.out.print(AssignmentList.get(i).getName() + " " + AssignmentList.get(i).getScore());
        }
    }

    public void addAssignment(){
        Scanner user_input = new Scanner( System.in );
        gradedAssignment assignment = new gradedAssignment();
        System.out.print("Add a new graded Assignment\n");
        assignment.inputInfo();
        AssignmentList.add(assignment);

//	FileIO assignDirectory = new FileIO( );

    }

/*
    public void appendStudent(String className) {
        String fileName;
        fileName = (System.getProperty("user.dir") + "/" + className + "/" + className + ".txt");

	try {
        	File outputFile = new File(fileName);
	        FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
		BufferedWriter bw = new BufferedWriter(fw);

		if (!outputFile.exists( ))
			System.out.println("This class does not exist.");
            
           	else {
	               bw.write("Name: " + firstName + " " + lastName + "\t");
			bw.write("Age: " + age + "\t");
			bw.write("ID Number: " + idNumber + "\n");
			bw.close( );

	   	}
	} catch (IOException ex) {
		System.out.println("Error");
	}

   
   }
*/        

}
