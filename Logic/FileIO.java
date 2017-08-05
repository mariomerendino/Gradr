/* * * * * * * * * * * * * * * * * * * * *
 * GRADEBOOK                             *
 * FILE I/O CLASS                        *
* * * * * * * * * * * * * * * * * * * * */
package Logic;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class FileIO{
	public static teacher currentTeacher;

	String username = teacher.getuserName();

	String userPath = (System.getProperty("user.dir") + "/data/" + username + '/');

//	public FiloIO(teacher t1){
//		currentTeacher = t1;
//	}

	//Creates Main DATA folder if it doesnt already exist. 
	public void createMainDirectory(){
	        try{
            String currentDir = System.getProperty("user.dir");
            File dir = new File (currentDir + "/data");
            if (dir.exists()){

            }
            else{
                dir.mkdir();
				
            }
        }
        catch(Exception e){

        }   
	}
	public void teacherRegistration(){
		try{
			//Attempts to create the main directory.
			createMainDirectory();
			//Opens the data Folder.
			String currentDir = System.getProperty("user.dir");
            File dir = new File (currentDir + "/data");
			//Creates a New Teacher Object.
			teacher t1 = new teacher();

			//inputs all info
			t1.setuserName(JOptionPane.showInputDialog("Please Create a UserName: "));
			t1.setpassword(JOptionPane.showInputDialog("Enter a password: "));
			t1.setfirstName(JOptionPane.showInputDialog("Enter your First Name: "));
			t1.setlastName(JOptionPane.showInputDialog("Enter your Last Name: "));
			File userDirectory = new File (currentDir + "/data/" + t1.getuserName());
			userDirectory.mkdir();
			File passwordFile = new File (userDirectory + "/" + t1.getuserName() + ".txt");
			FileWriter fw = new FileWriter(passwordFile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(t1.getuserName() + "\n");
			bw.write(t1.getpassword() + "\n");
			bw.write(t1.getfirstName() + "\n");
			bw.write(t1.getlastName() + "\n");
			bw.close();
		}
		catch (Exception e){

		}
	}
	//CREATE DIRECTORY, CALLED FROM ADD CLASS
	public void createDirectory(String path) {
		try {
			//create string for path
			File outputFile = new File(System.getProperty("user.dir") + '/' + path + ".csv");
			//check for dubplicate classes by checking
			//directories
			if (outputFile.getParentFile( ).exists( )) {
				System.out.println("This class already exists.");
			}
			else
			//create the directory if it doesn't exist
			if (!outputFile.getParentFile( ).exists( )) {
				outputFile.getParentFile( ).mkdirs( );
				System.out.println("New directory created.");
			}
			//create the file in the directory if it doesn't exist
			if (!outputFile.exists( )) {
				//instantiate streams/buffers
				FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
				BufferedWriter bw = new BufferedWriter(fw);
				//create class file for student info
				outputFile.createNewFile( );
				System.out.println("New file created in directory.");
			}
		 }catch (IOException e) {
				System.out.println("Error");
		}

	}

	public void addToClassList(String username, String className) {
		List<String> tempList = new ArrayList<String>( );
		boolean newClass = true;		

		System.out.println("ADD TO CLASS LIST current teacher: " + currentTeacher.username);

		try {
			File outputFile = new File(userPath + "/class_list.csv");
			createDirectory("/data/" + username + '/' + className + "/attendance.csv");	
//			File outputFile = new File(System.getProperty("user.dir") + "/data/" + username + "/class_list.csv");
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println("try addToClastList");
			

			if (!outputFile.exists( )) {
				System.out.println("CLASS FILE");
				outputFile.createNewFile( );
			}

			//read file to stop duplicates
			tempList = readClassList(username);			
			//search for string in file
			String search = className;
			for(String str: tempList) {			
				if(str.trim( ).contains(search))
					newClass = false;		

			}	

			//append class name if it's new
			if (newClass == true) {
				System.out.println("new class");
				bw.write(className);
				bw.write('\n');
				bw.close( );
			
			}
			else
				System.out.println("Class already in list.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}	

	//read class names in from file
	public List<String> readClassList(String username) {
		String fileName = (System.getProperty("user.dir") + "/data/" +username + "/class_list.csv");
		String thisLine = null;
		boolean empty = false;
		List<String> classList = new ArrayList<String>( );
		int counter = 0;

		System.out.println("Reading class file...");

		try {
			InputStream ips = new FileInputStream(fileName);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			while ((line = br.readLine( )) != null) {
				classList.add(line);				
				System.out.println(line + " class read");

			}
			br.close( );
		} catch (IOException e) {
			System.out.println(e);
		}
		return classList;
	}

/*			
	public void editClassInfo(String className, char t) {
		String fileName = (System.getProperty("user.dir") + '/' + className);
	
		try {
			File outputFile = new File(fileName);
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (!outputFile.getParentFile( ).exists( ))
				System.out.println("This class does not exist.");
			else {
				student s = new student( );
				s.inputStudent( );				
				appendStudent(s, className);			


			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}			
*/

	//APPEND STUDENT, CALLED FROM ADD STUDENT
    public static void appendStudent(student s, String className) {
	    String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username +'/' + className + '/' + className +  ".csv");
		String gradesFileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username +'/' + className + '/' + "grades.csv");
		
		try {
			File outputFile = new File(fileName);
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
			BufferedWriter bw = new BufferedWriter(fw);

			File gradesOutputFile = new File(gradesFileName);
			FileWriter gfw = new FileWriter(gradesOutputFile.getAbsoluteFile( ), true);
			BufferedWriter gbw = new BufferedWriter(gfw);

			//alert user if file doesn't exist
			if (!outputFile.exists( )){
				System.out.println("This class does not exist.");

				bw.write("IdNumber,First Name,Last Name, Age");
				bw.newLine();
			}
			//if the file exists	
			else {
				bw.write(s.lastName + ',' + s.firstName + ',' + s.age + ',' + s.idNumber);
				bw.newLine();
				bw.close( );
				int amountOfGrades = amountOfAssignments(className);
				String zeros = new String();
				for (int i = 0; i < amountOfGrades; i++){
					zeros = zeros + ",0";
					System.out.println(zeros);
				}

				gbw.write(s.lastName + ',' + s.firstName + ',' + s.age + ',' + s.idNumber + zeros);
				gbw.newLine();
				gbw.close( );
				addToMasterStudentList(s);
			}
		} catch(IOException ex) {
			System.out.println("Error opening file.");
		}

	}


	public static void addToMasterStudentList(student s) {
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + "/master_student_list.csv");
//		List<String> studentList = new ArrayList<String>( );
		boolean newStudent = true;

		try {
			File outputFile = new File(fileName);
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
			BufferedWriter bw = new BufferedWriter(fw);

			if(!outputFile.exists( )){
				outputFile.createNewFile( );
				bw.write("IdNumber,First Name,Last Name, Age");
				bw.newLine();
			}

			List<String> tempList = new ArrayList<String>( );

			tempList = readMasterStudentList(currentTeacher.username);

			String search = s.idNumber;

			//System.out.println("s.idNumber: " + s.idNumber);

			for (String str:tempList) {
				if(str.trim( ).contains(search)){
					newStudent = false;
					JOptionPane.showMessageDialog(null, "You have entered a duplicate, all students MUST have a unique ID");
				}
			}

			if (newStudent == true) {
				bw.write(s.idNumber + ',' + s.lastName + ',' + s.firstName + ',' + s.age);
				bw.newLine();
				bw.close( );
			}
			else
				System.out.println("Not a new student");

		}
		catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static List<String> readMasterStudentList(String username) {
		String fileName = (System.getProperty("user.dir") + "/data/" + username + "/master_student_list.csv");
		List<String> studentIDList = new ArrayList<String>( );
		String studentID;

		List<String> studentLine = new ArrayList<String>( );

		try {
			InputStream ips = new FileInputStream(fileName);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
//			Scanner s = new Scanner(fileName);
			String line;
			int counter = 0;
			
			while((line = br.readLine( )) != null) {
				String[ ] s = line.split(",");
				System.out.println(s[counter]);
				studentIDList.add(s[counter]);
				studentLine.add(line);
//				System.out.println(studentLine);
//				studentIDList[counter] = br.nextInt( );
//				br.nextLine( );
//				studentList[counter] = studentID;
//				counter++;
			}


			br.close( );
		} catch(IOException e) {
			System.out.println(e);
		}

		System.out.println("User ID List");
		for (int i = 0; i < studentIDList.size( ); i++)
			System.out.println(studentIDList.get(i) + "\n");

		return studentIDList;
	}
			

	public void appendGrade(student s, classroom c, gradedAssignment g, String className) {
		
		String fileName = (System.getProperty("user.dir") + '/' + className + "/grades.csv");		
		
		System.out.println("\n" + System.getProperty("user.dir"));
		System.out.println(fileName);		

		try {
			File outputFile = new File(fileName);
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
			BufferedWriter bw = new BufferedWriter(fw);

			if (!outputFile.exists( ))
				System.out.println("Grades file does not exist.");
			else {
				bw.write(s.idNumber + ',' + s.lastName + ',' + s.firstName + ',' + g.name + ',' + g.score + "\n");
				bw.close( );
			}
		} catch(IOException ex) {
			System.out.println("Error opening grade file.");
		}

	}

	public void readStudent(String className) {
		//create full path to file
		String fileName = (System.getProperty("user.dir") + '/' + className + '/' + className + ".csv");
		String thisLine = null;
		//used to return true/false to appendStudent( ) to tell if
		//the file is empty	
		boolean empty = false;
		List<String> studentList = new ArrayList<String>( );

		System.out.println("Reading student information from file...");


		try {
			//create input stream, input stream reader, and buffered reader
			InputStream ips = new FileInputStream(fileName);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;

			//read from file while the next line is filled
			while ((line = br.readLine( )) != null) {
				System.out.println(line);
				studentList.add(line);
			}

			//close buffered reader stream
			br.close( );
		} catch (IOException e) {
			System.out.println("Error");

		}
//		return studentList;
	
	}

	public List<String> readStudentID(String className, char x) {
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + className + ".csv");
		List<String> studentIDList = new ArrayList<String>( );
		List<String> studentNameList = new ArrayList<String>( );
		try {
			InputStream ips = new FileInputStream(fileName);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			String line;

			while((line = br.readLine( )) != null) {
				//student tempStudent = new student( );
				String[ ] s = line.split(",");
				//s[0] = tempStudent.lastName;
				//s[1] = tempStudent.firstName;
				//s[2] = tempStudent.age;
				//s[3] = tempStudent.idNumber;
				studentIDList.add(line);
				studentNameList.add(s[0] + ',' + s[1]);
			}
		} catch (IOException ex) {
			System.out.println("Unable to read student IDs");
		}
		if (x == 'i')
			return studentIDList;
		else
			return studentNameList;	
	}

	public boolean login(String username, String password){
	    try{
            String currentDir = System.getProperty("user.dir");
            File dir = new File (currentDir + "/data/" + username);
            File passwordFile = new File (dir + "/" + username + ".txt");
            FileReader passFileReader = new FileReader(passwordFile);
            BufferedReader reader = new BufferedReader (passFileReader);
			String pass = new String();
			for (int i=0; i < 2; i++){
            	pass = reader.readLine();
			}
            reader.close();
            if (dir.exists() && password.equals(pass)){
				return true;
            }
            else{
				return false;
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: Exception");
        }
		return false;
    }

	public teacher readTeacher(String usr){
		teacher t1 = new teacher();
		try{
			String currentDir = System.getProperty("user.dir");
			File dir = new File (currentDir + "/data/" + usr);
			File userFile = new File (dir + "/" + usr + ".txt");
			FileReader userFileReader = new FileReader(userFile);
			BufferedReader reader = new BufferedReader (userFileReader);
			
			t1.setuserName(reader.readLine());
			t1.setpassword(reader.readLine());
			t1.setfirstName(reader.readLine());
			t1.setpassword(reader.readLine());

			reader.close();
		}
		catch (Exception e){

		}
		return t1;
	}

	public void addAssignment(String className, String aName, int weight, int totalPoints) {
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outputFile = new File(fileName);

		if(outputFile.exists( )) {
			try {
				FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("\n*\n");
				bw.write(aName + ",\n" + weight + ",\n" + totalPoints + ',');
				bw.close( );
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		else {
			try {
				outputFile.createNewFile( );
				FileWriter fw = new FileWriter(outputFile.getAbsoluteFile( ), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("*\n");
				bw.write(aName + ",\n" + weight + ",\n" + totalPoints + ',');
				bw.close( );
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	public static int amountOfAssignments(String className){
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outputFile = new File(fileName);
		int counter = 0;
		if(outputFile.exists( )) {
			try{
				FileReader fReader = new FileReader(outputFile);
				BufferedReader reader = new BufferedReader (fReader);
				String line = new String();
				while((line = reader.readLine( )) != null) {
					System.out.println(line);
					if (line.contains("*")){
						counter++;
					}
				}
			}
			catch(Exception e){

			}
		}

		return counter;
	}
	public void setDefaultValuesForGradesWhenAddingANewAssignment(String className){
		String gradesFilePath = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "grades.csv");
		String tempGradesFilePath = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "temp.csv");
		File gradesFile = new File(gradesFilePath);
		File tempFile = new File(tempGradesFilePath);
		try{
			FileReader gradesFileReader = new FileReader(gradesFile);
			BufferedReader gReader = new BufferedReader (gradesFileReader);

			FileWriter tempFileWriter = new FileWriter(tempFile);
			BufferedWriter tWriter = new BufferedWriter(tempFileWriter);

			String line = new String();
			while((line = gReader.readLine( )) != null) {
				tWriter.write(line + ",0\n");
				
			}
			gradesFile.delete();
			File newGradesFile = new File(gradesFilePath);
			tempFile.renameTo(newGradesFile);	
			tWriter.close();
		}
	
		catch(Exception e){

		}
	}
	public void createClassInfoFile(classroom c){
		try{
			String gradesFilePath = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + c.className + '/' + "classInfo.csv");
			File classInfoFile = new File(gradesFilePath);
			FileWriter fw = new FileWriter(classInfoFile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(c.className+"\n"+c.crn+"\n"+c.section+"\n"+c.semester+"\n"+c.meetingTime+"\n"+c.location+"\n");
			for (int i = 0; i < c.daysOfTheWeek.size(); i++){
				bw.write(c.daysOfTheWeek.get(i) + ",");
			}
			bw.close();
		}
		catch (Exception e){

		}

	}
	public classroom getClassInfo(String className){
		classroom c = new classroom();
		try{
			String gradesFilePath = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "classInfo.csv");
			File classInfoFile = new File(gradesFilePath);
			FileReader fw = new FileReader(classInfoFile);
			BufferedReader br = new BufferedReader(fw);
			c.className = br.readLine();
			c.crn = br.readLine();
			c.section = br.readLine();
			c.semester = br.readLine();
			c.meetingTime= br.readLine();
			c.location= br.readLine();
			StringTokenizer st1 = new StringTokenizer(br.readLine(), ",");
			for (int i = 0; i < 5; i++){
				c.daysOfTheWeek.add(st1.nextToken());
			}
		}
		catch(Exception e){

		}
		return c;
	}
	public static void saveAssignments(Vector obj, String className){
		int numOfGrades = amountOfAssignments(className);
		String gradesFilePath = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "grades.csv");
		try{
			File grades = new File(gradesFilePath);
			FileWriter fw = new FileWriter(grades);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.flush();
			bw.close();
			File newgrades = new File(gradesFilePath);
			FileWriter nfw = new FileWriter(newgrades);
			BufferedWriter nbw = new BufferedWriter(nfw);
			for(int i=0; i < obj.size(); i++){
				if ((i+1)%(numOfGrades+4) != 0){
					nbw.write(obj.get(i) + ",");
				}
				else{
					nbw.write(obj.get(i) +"\n");
				}
			}
			nbw.close();
		}
		catch(Exception e){

		}
	}
	public static List<String> assignmentNames(String className){
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outputFile = new File(fileName);
		List<String> assignmentList = new ArrayList<String>(); 
		int counter = 0;
		if(outputFile.exists( )) {
			try{
				FileReader fReader = new FileReader(outputFile);
				BufferedReader reader = new BufferedReader (fReader);
				String line = new String();
				while((line = reader.readLine( )) != null) {
					if (line.contains("*")){
						String[] split = reader.readLine().split(","); 
						assignmentList.add(split[0]);
					}
				}
			}
			catch(Exception e){

			}
		}

		return assignmentList;
	}
	public String getAssignmentAverage(String className, String assignmentName, String type){
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outputFile = new File(fileName);
		int counter = 0;
		Boolean found = false;
		String totalPointsString = new String();
		if(outputFile.exists( )) {
			try{
				FileReader fReader = new FileReader(outputFile);
				BufferedReader reader = new BufferedReader (fReader);
				String line = new String();
				while((line = reader.readLine( )) != null) {
					if (line.contains("*") && found == false){
						counter++;
					}
					if (line.contains(assignmentName)){
						reader.readLine();
						String[] points = reader.readLine().split(",");
						totalPointsString = points[0];
						found = true;
					}
				}
			}
			catch(Exception e){

			}
		}
		if (type == "Average"){
			int gradeSum = 0;
			int numStudents =0;
			int indexInCSV = 4 + counter;
			int average = 0;
			int averagePercent = 0;
			int totalPointsPossible = Integer.parseInt(totalPointsString);
			String gradesFileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "grades.csv");
			File gradesOutputFile = new File(gradesFileName);
			try{
				FileReader gradeFileReader = new FileReader(gradesOutputFile);
				BufferedReader gReader = new BufferedReader (gradeFileReader);
				String line = new String();
				while((line = gReader.readLine( )) != null) {
					String[] s = line.split(",");
					int gradeForAssignment = Integer.parseInt(s[indexInCSV-1]);

					gradeSum = gradeSum + gradeForAssignment;
					numStudents++;
				}
				average = gradeSum/numStudents;
				averagePercent = ((average/totalPointsPossible)*100);
			}
			catch(Exception e){
				return "ERROR!";
			}
			//Integer.parseInt(split[0];
			return String.valueOf(average);
		}
		else{
			return fileName;
		}
	}
	public String getStudentAverage(String className, String studentNameCombo){
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outPutFile = new File(fileName);
		
		String gradesFileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "grades.csv");
		File gradesOutputFile = new File(gradesFileName);
		List<Integer> weights = new ArrayList<Integer>();
		List<Integer> totalPoints = new ArrayList<Integer>();
		List<Integer> studentGrades = new ArrayList<Integer>();
		int counter =0;
		
		if(outPutFile.exists( )) {
			try{
				FileReader fReader = new FileReader(outPutFile);
				BufferedReader reader = new BufferedReader (fReader);
				String line = new String();
				while((line = reader.readLine( )) != null) {
					if (line.contains("*")){
						counter++;
						reader.readLine();
						String[] w = reader.readLine().split(",");
						weights.add(Integer.parseInt(w[0]));
						String[] points = reader.readLine().split(",");
						totalPoints.add(Integer.parseInt(points[0]));
					}
				}
			}
			catch(Exception e){

			}

		}

		int indexInCSV = 4 + counter;
		if(gradesOutputFile.exists( )) {
			try{
				FileReader fReader = new FileReader(gradesOutputFile);
				BufferedReader reader = new BufferedReader (fReader);
				String line = new String();
				while((line = reader.readLine( )) != null) {
					if (line.contains(studentNameCombo)){
						String[] info = line.split(",");
						for (int i=4; i < indexInCSV; i++){
							studentGrades.add(Integer.parseInt(info[i]));
						}
					}
				}
			}
			catch(Exception e){
				return "ERROR";
			}

		}
		double sumOfWeightedGrades =0;
		double sumOfWeights = 0;
		List<Double> listOfWeightedGrades = new ArrayList<Double>();
		for(int i= 0; i < studentGrades.size(); i++){
			double studentGrade = studentGrades.get(i);
			double totalpoints = totalPoints.get(i);
			double w = weights.get(i);
			double test = (studentGrade/totalpoints);
			listOfWeightedGrades.add(test*w);
		}
		for(int i= 0; i < studentGrades.size(); i++){
			sumOfWeightedGrades= sumOfWeightedGrades+ listOfWeightedGrades.get(i);
			sumOfWeights = sumOfWeights + weights.get(i);
		}
		double average = (sumOfWeightedGrades/sumOfWeights) * 100;

		return String.valueOf(average);
	}
	public String getTotalPoints(String className, String assignmentName){
		String totalPoints = new String();
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outputFile = new File(fileName);
		try{
			
			FileReader fReader = new FileReader(outputFile);
			BufferedReader reader = new BufferedReader (fReader);
			String line = new String();
			while((line = reader.readLine( )) != null) {
				if (line.contains(assignmentName)){
					reader.readLine();
					String[] s = reader.readLine().split(","); 
					totalPoints = s[0];
				}
			}
		}
		catch(Exception e){

		}

		return totalPoints;
	}
	public String getWeight(String className, String assignmentName){
		String weight = new String();
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outputFile = new File(fileName);
		try{
			
			FileReader fReader = new FileReader(outputFile);
			BufferedReader reader = new BufferedReader (fReader);
			String line = new String();
			while((line = reader.readLine( )) != null) {
				if (line.contains(assignmentName)){
					String[] s = reader.readLine().split(","); 
					weight = s[0];
				}
			}
		}
		catch(Exception e){

		}
		return weight;
	}
	public void saveEditsToAssignment(String className, String assignmentName, String w, String tp){
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo.csv");
		File outputFile = new File(fileName);

		String newfileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "assignmentInfo2.csv");
		File newoutputFile = new File(newfileName);
		try{
			
			FileReader fReader = new FileReader(outputFile);
			BufferedReader reader = new BufferedReader (fReader);

			FileWriter fWriter = new FileWriter(newoutputFile);
			BufferedWriter writer = new BufferedWriter (fWriter);
			String line = new String();
			while((line = reader.readLine( )) != null) {
				System.out.println(line);
				writer.write(line + "\n");
				if (line.contains(assignmentName)){
					writer.write(w+",\n");
					writer.write(tp+",\n");
					reader.readLine();
					reader.readLine();

				}
				
			}
			writer.close();
			outputFile.delete();
			File newFile = new File(fileName);
			newoutputFile.renameTo(newFile);

		}
		catch(Exception e){

		}
	}
	public void printAttendance(String className, String amountAbs, String studentName){
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "attendance.csv");
		File outputFile = new File(fileName);

		String newfileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "attendance2.csv");
		File newoutputFile = new File(newfileName);
		try{
			if (outputFile.exists()){
				FileReader fReader = new FileReader(outputFile);
				BufferedReader reader = new BufferedReader (fReader);
				Boolean studentExists = false;
				String line = new String();
				while((line = reader.readLine( )) != null) {
					if (line.contains(studentName)){
						studentExists = true;
					}
				}
				if (studentExists){
					FileReader fReader2 = new FileReader(outputFile);
					BufferedReader reader2 = new BufferedReader (fReader2);

					FileWriter fwriter = new FileWriter(newoutputFile.getAbsoluteFile( ), true);
					BufferedWriter writer = new BufferedWriter(fwriter);
					
					String line2 = new String();
					while((line2 = reader2.readLine( )) != null) {
						if (line2.contains(studentName)){
							writer.write(studentName + "," + amountAbs + "\n");
						}
						else{
							writer.write(line2 + "\n");
						}
					}
					writer.close();
					outputFile.delete();
					File newFile = new File(fileName);
					newoutputFile.renameTo(newFile);
				}
				else{
					FileWriter fwriter = new FileWriter(outputFile.getAbsoluteFile( ), true);
					BufferedWriter writer = new BufferedWriter(fwriter);
					writer.write(studentName + "," + amountAbs + "\n");
					writer.close();

				}
			}
			else{

				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter writer = new BufferedWriter(fwriter);
				writer.write(studentName + "," + amountAbs + "\n");
				writer.close();

			}
		}
		catch(Exception e){

		}

	}
	public String getAttendance(String className, String studentName){
		String amountAbs = new String("0");
		String fileName = (System.getProperty("user.dir") + "/data/" + currentTeacher.username + '/' + className + '/' + "attendance.csv");
		File outputFile = new File(fileName);
		try{
			FileReader fReader = new FileReader(outputFile);
			BufferedReader reader = new BufferedReader (fReader);
			Boolean studentExists = false;
			String line = new String();
			while((line = reader.readLine( )) != null) {
				if (line.contains(studentName)){
					String[] split = line.split(",");
					amountAbs = split[2];
				}
			}
		}
		catch(Exception e){

		}
		return amountAbs;
	}
}

