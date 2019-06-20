import java.util.*;

/**
 * @author Joy Lan
 */
public class Section {
	//the section number for this specific section (Each class can have more than one section that is taught by the same professor.)
	int sectionNumber;	
	int maxStudents;		//the maximum number of students that can be in this section of the class at once
	int totalStudents; 		//the total number of students in this section of the class 
	LinkedList<Exam> exams = new LinkedList<Exam>();		//List of Exams given to this section so far
	LinkedList<Student> students = new LinkedList<Student>();		//List of students in this section currently 
	LinkedList<String> rankedStudents = new LinkedList<String>(); 	//List of the names of the ranked student for this section
	HashMap<String, Double> grades = new HashMap<String, Double>();	//HashMap that maps each student's name to their overall grade
																							
	/**
	 * @param sectionNumber the section number for this specific section 
	 * @param maxStudents the maximum number of students that can be in this section of the class at once
	 * This is the constructor for the Section class.
	 */
	public Section (int sectionNumber, int maxStudents) {
		this.sectionNumber = sectionNumber; 
		this.maxStudents = maxStudents;
	}
	
	/**
	 * @return the section number for this specific section 
	 * Getter method for the section number of this specific section
	 */
	public int getSectionNumber() {
		return sectionNumber;
	}
	
	/**
	 * @param totalStudents the total number of students in this section of the class 
	 * Setter method for totalStudents
	 */
	public void setTotalStudents (int totalStudents) {
		this.totalStudents = totalStudents; 
	}
	
	/**
	 * @return the total number of students in this section of the class 
	 * Getter method for the total number of students in this section of the class 
	 */
	public int getTotalStudents() {
		return totalStudents;
	}
	
	/**
	 * @param student the student that is being added to the list of students currently in this section
	 * This method allows the user to add a student to the list of students currently in this section of the class
	 */
	public void addStudent(Student student) {
		if (totalStudents < maxStudents) {
			students.add(student);
			totalStudents++;
		} else {
			System.out.println("This section already has the maximum number of students");
		}		
	}
	
	/**
	 * @param student the student that is being removed from the list of students currently in this section
	 * This method allows the user to remove a student from the list of students currently in this section of the class
	 */
	public void removeStudent(Student student) {
		boolean removed = false;
		int count = 0;		//Keeps track of current index of students linkedlist that the program is checking right now
		//While loop runs either until the student being removed is found in the students linkedlist or until every student in the 
		//student linkedlist has been compared with the student that the user is trying to remove
		while ((removed == false) && (count < totalStudents)) {
			if (students.get(count).equals(student)) {		//Using the equals method of the student class to check if the student
				students.remove(count);						//that the user wants to remove is the same student that is found at 
				removed = true;								//the current index of the linkedlist students.
			} else {
				count++;
			}			
		}
		//If every student in the students linkedlist does not match the student entered by the user, the following message is printed.
		if (count == totalStudents) {
			System.out.println("This student was not found in this section of the class.");
		}
	}
	
	/**
	 * @return the list of students in this section currently
	 * Getter method for the list of students in this section currently
	 */
	public LinkedList getStudentsList() {
		return students;
	}
	
	/**
	 * @param exam an exam that has been given to this class
	 * This method allows the user to add an exam to the linkedlist exams, which stores all the exams given to this class so far.
	 */
	public void addExam(Exam exam) {
		exams.add(exam);
	}
	
	/**
	 * @return the linkedlist exams, which stores all the exams given to this class so far
	 * This is the getter method for all the exams given to this class so far.
	 */
	public LinkedList getExams() {
		return exams;
	}
	
	/**
	 * @return List of maps that map each student's name to their overall grade.
	 * Getter method for the List of maps that map each student's name to their overall grade.
	 */
	public HashMap getAllGrades() {
		return grades;
	}
	
	public void rankStudents() {
		//orderedGrades is a temporary linked list created to store the list of all the grades of this section in order
		LinkedList<Double> orderedGrades = new LinkedList<Double>();	
		//ranked is a temporary linked list created to store the list of ranked students so far 
		LinkedList<String> ranked = new LinkedList<String>();
		Iterator<Double> valueItr = grades.values().iterator();
		while (valueItr.hasNext() == true) {
			orderedGrades.add(valueItr.next());			
		}
		Collections.sort(orderedGrades);		//Sorting list of ordered grades from lowest to highest
		Collections.reverse(orderedGrades);		//Reversing list of ordered grades so that it is now sorted from highest to lowest grade
		for (int i = 0; i < orderedGrades.size(); i++) {
			int count = 1;
			double currentGrade = orderedGrades.get(i);			
			while ((i < orderedGrades.size() - 1) && (orderedGrades.get(i+1) == currentGrade)) {	//While loop counts the number of students who has this specific grade
				count++;
				i++;
			}
			Iterator<String> keyItr = grades.keySet().iterator();
			while (count > 0) {
				String currentStudent = keyItr.next();				//Iterate through the list of students until all students with  
				if (grades.get(currentStudent) == currentGrade) {	//the grade stored in the currentGrade variable are found and   
					ranked.add(currentStudent);						//added to the linked list called ranked
					count--;
					boolean setRank = false;		
					int studentToCheck = 0;
					while ((setRank == false) && studentToCheck < students.size()) {		
						if (students.get(studentToCheck).getName().equals(currentStudent)) {
							students.get(studentToCheck).setRank(ranked.size());		//Setting the rank field in student object to 
							setRank = true;												//the rank of that student
						} else {
							studentToCheck++;
						}
					}
				}
			}
			rankedStudents = ranked;		//Setting rankedStudents field equal to the linked list "ranked," which is now sorted
		}									//with the list of students from highest to lowest rank
	}
	
	/**
	 * @return a list of the names of the ranked student for this section
	 * This method returns a list of the names of the ranked student for this section
	 */
	public LinkedList getRankedStudents() {
		return rankedStudents;
	}
	
	/**
	 * @param name the name of the student being added to the grades HashMap
	 * @param grade the grade of the student being added to the grades HashMap
	 * This method allows the user to add a student's name, along with their grade, into the grades HashMap.
	 */
	public void addStudentGrade (String name, double grade) {
		grades.put(name, grade);
	}
	
	/**
	 * @return the HashMap that maps each student's name to their overall grade
	 * Getter method for the HashMap that maps each student's name to their overall grade
	 */
	public HashMap getAllStudentGrades() {
		return grades;
	}
}
