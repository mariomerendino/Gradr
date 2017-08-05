package GUI;
import Logic.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.*;

import java.awt.Color;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grades extends JPanel {
	public static teacher currentTeacher;
	String username = currentTeacher.username;
	public JComboBox classDropDown = new JComboBox( );
	public JComboBox studentDropDown = new JComboBox( );
	String className;
	public static FileIO io = new FileIO( );

	public JLabel title = new JLabel( );
	public BorderLayout assignmentsPageLayout = new BorderLayout( );

	public static String classroom = new String( );
    private JPanel bottomBar;
    private JPanel topBar;
	public DataFileTable2 currentClass;
    
	public Grades(){
		init();

	}
	public Grades(String cn){
		init();
		update(cn);
	}
    
    // constructor
	public void init() {
        
        setLayout(assignmentsPageLayout);
		String classroom = null;
        classDropDown = createDropDown();
		setSize(250, 650);
		
		FlowLayout barLayout = new FlowLayout();
        topBar = new JPanel();
		topBar.setLayout(barLayout);

		title.setText((String)classDropDown.getSelectedItem());
		title.setFont(FontLoader.headerFont);
		title.setVisible(true);
		topBar.add(title);
	
		topBar.add(classDropDown);
		add(topBar, assignmentsPageLayout.NORTH);

        classDropDown.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if(e.getStateChange() == java.awt.event.ItemEvent.SELECTED){
                    update((String)classDropDown.getSelectedItem());
                }
            }
        });
	

		classroom = (String)classDropDown.getSelectedItem( );
		currentClass = new DataFileTable2('g', classroom);

		System.out.println("CLASSROOM FROM DROPDOWN" + classroom);
		
		bottomBar = new JPanel();
        FlowLayout bottomBarLayout = new FlowLayout();
        bottomBar.setLayout(bottomBarLayout);
        JButton editAssignments = new JButton("Edit Assignments");
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
		JButton addAssignmentButton = new JButton("Add Assignment");
		JButton statsButton = new JButton("Statistics");
		JButton attendance = new JButton("Attendance");

		bottomBar.add(addAssignmentButton);
        bottomBar.add(editAssignments);
		bottomBar.add(attendance);
		bottomBar.add(statsButton);
        bottomBar.add(saveButton);
        bottomBar.add(cancelButton);

        add(bottomBar, assignmentsPageLayout.SOUTH);

		addAssignmentButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				addAssignmentPage addPage = new addAssignmentPage((String)classDropDown.getSelectedItem( ));
				
				addPage.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						update((String)classDropDown.getSelectedItem());
					}
				});
							
			}
		});
		editAssignments.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				EditAssignment editPage = new EditAssignment((String)classDropDown.getSelectedItem());
                update((String)classDropDown.getSelectedItem());
			}
		});
        
		cancelButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				update((String)classDropDown.getSelectedItem());
			}
		});
		saveButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Vector v = new Vector(currentClass.model.data);
				io.saveAssignments(v,(String)classDropDown.getSelectedItem());
				update((String)classDropDown.getSelectedItem());
			}
		});
		attendance.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Attendance attendancePage = new Attendance((String)classDropDown.getSelectedItem());
			}
		});
		statsButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Stats page = new Stats((String)classDropDown.getSelectedItem());
			}
		});

		if (classroom == null){

			//add(gradesHomePage, assignmentsPageLayout.CENTER);
		}
		else
			add (currentClass, assignmentsPageLayout.CENTER);

	}

	public void update(String classroom) {
		JPanel blank = new JPanel( );

		title.setText(classroom);
		if (classroom == "null") {
			swapCenter(blank);
			
		}
		else {
			System.out.println("UPDATE CLASSROOM: " + classroom);
			currentClass = new DataFileTable2('g', classroom);
			swapCenter(currentClass);
		}
	}


//	public void updateStudent(String classroom) {
//		JPanel selectStudentPage = select


	public void swapCenter(JPanel replacementPanel) {
		if (assignmentsPageLayout.getLayoutComponent(assignmentsPageLayout.CENTER) != null) {
			remove(assignmentsPageLayout.getLayoutComponent(assignmentsPageLayout.CENTER));
		}

		add(replacementPanel, assignmentsPageLayout.CENTER);
		validate( );
		repaint( );
	}

	public JComboBox createDropDown( ) {
//		FileIO io = new FileIO( );
		List<String> classList = new ArrayList<String>( );
		classList = io.readClassList(username);
		String[ ] classArray = new String[classList.size( )];

//		classArray[0] = "Select a Class";		

		for (int i = 0; i < classList.size( ); i++)
			classArray[i] = classList.get(i);

		JComboBox box = new JComboBox(classArray);
		return box;
	}


	public JComboBox createStudentDropDown(String className) {
//		FileIO io = new FileIO( );		
		List<String> studentList = new ArrayList<String>( );
		
		System.out.println("CREATE STUDENT DROP DOWN CLASSROOM: " + className);

		studentList = io.readStudentID(className, 'i');
		String[ ] studentArray = new String[studentList.size( )];

		for (int i = 0; i < studentList.size( ); i++)
			studentArray[i] = studentList.get(i);

		JComboBox box = new JComboBox(studentArray);
		return box;
	}


	public JPanel createGradesHomePage( ) {
		JPanel gradePage = new JPanel( );
		JLabel gradeText = new JLabel( );
		Font textFont = new Font("Stencil", Font.PLAIN, 15);
		gradeText.setText("Please select a class from the dropdown menu.");
		classDropDown = createDropDown( );
		gradePage.add(classDropDown);

        gradeText.setFont(FontLoader.subheaderFont);
		gradeText.setVisible(true);
		gradePage.add(gradeText);
		return gradePage;
	}


	public JPanel createSelectClassPage(String className) {
		JButton updateClass = new JButton("Update");
		JPanel classSelect = new JPanel( );
		JLabel classText = new JLabel( );
		Font textFont = new Font("Stencil", Font.PLAIN, 15);
		classText.setText("Please select a class from the dropdown menu.");
		classDropDown = createDropDown( );
		classSelect.add(classDropDown);
		classSelect.add(updateClass);
		classText.setFont(FontLoader.subheaderFont);
		classText.setVisible(true);
		classSelect.add(classText);
		
		/*updateClass.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				className = (String)classDropDown.getSelectedItem( );
				System.out.println("Create select CLASS PAGE ACTION LISTENER CLASSROOM: " + className);
				JPanel selectStudentPage = createSelectStudentPage(classroom);
				swapCenter(selectStudentPage);
			}
		});*/

		return classSelect;
	}

	public JPanel createSelectStudentPage(String className) {
		JPanel studentSelect = new JPanel( );
		JLabel studentText = new JLabel( );
		Font textFont = new Font("Stencil", Font.PLAIN, 15);
		studentText.setText("Please select a student fron the dropdown menu.");
		studentDropDown = createStudentDropDown(classroom);
		studentSelect.add(studentDropDown);

		studentText.setFont(FontLoader.subheaderFont);
		studentText.setVisible(true);
		studentSelect.add(studentText);
		return studentSelect;
	}

	
}
