import java.util.*; 

/**
 * @author Joy Lan
 */
public class Student {
	
	private String name;		//the name of the student
	private int numOfAbsences; 	//total number of absences the student has had so far 
	//homeworkGrades is a linked list of all the homework grades that the student has received for the assignments assigned so far
	private LinkedList<Double> homeworkGrades = new LinkedList<Double>();
	//examGrades is a linked list of all the exam grades that the student has received for all the exams given so far
	private LinkedList<Double> examGrades = new LinkedList<Double>();
	//gradeComponents maps the grade the student received in each category to the percentage that it is worth in the overall grade
	private HashMap<String, double[]> gradeComponents = new HashMap<String, double[]>();
	private double homeworkAvg;		//the student's homework average
	private double examAvg;			//the student's exam average
	private double overallGrade;	//the student's overall grade in the class
	private int rank;		//the student's current rank in the class
	
	/**
	 * @param name the name of the student 
	 * This is the constructor for the student class.
	 */
	public Student(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name of the student 
	 * Getter method for the name of the student
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the total number of absences the student has had so far 
	 * Getter method for the total number of absences the student has had so far 
	 */
	public int getNumOfAbsences() {
		return numOfAbsences;
	}
	
	/**
	 * @param numOfAbsences the total number of absences the student has had so far 
	 * Setter method for the total number of absences the student has had so far 
	 */
	public void setNumOfAbsences(int numOfAbsences) {
		this.numOfAbsences = numOfAbsences;
	}
	
	/**
	 * The number of absences is incremented using this method (Every time a student is absent, this method can be used to update the 
	 * student's number of absences.)
	 */
	public void incrementAbsences() {
		numOfAbsences++;
	}
	
	/**
	 * @param homeworkGrade the homework grade that the student received on the most recent homework assignment
	 * This method will be the one that is mainly used to add homework grades (as opposed to the method right below this one) because
	 * teachers will usually add homework grades in the order that they were due. Since arraylist automatically adds new elements to the 
	 * end of the list, the list will already be in order. 
	 */
	public void addHomeworkGrade(double homeworkGrade) {
		homeworkGrades.add(homeworkGrade);
	}
	
	/**
	 * @param index the index of the homeworkGrades arraylist -- also known as the assignment number
	 * @param homeworkGrade the homework grade that the student received on the homework that is being added 
	 * This method is used when the user wants to add a homework grade for an assignment that is not the most recent assignment or 
	 * when the assignment grade they are adding is for an assignment for which the grade has not been inputted yet 
	 */
	public void addHomeworkGrade(int index, double homeworkGrade) {
		if (index > homeworkGrades.size()) {
			for (int i = homeworkGrades.size(); i < index; i++) {
				homeworkGrades.add(-1.0);		//-1.0 represents that the homework grade at that index has not yet been inputted
			}									//ex: Having a -1.0 at index 2 means that the homework grade for the 3rd assignment 
		}							//(since index starts at 0, index 0 stores the first assignment grade) has not yet been inputted
		homeworkGrades.add(index, homeworkGrade);
	}
	
	/**
	 * @param index the index / the assignment number of the homework grade that is being edited 
	 * @param homeworkGrade the new homework grade that is replacing the old homework grade
	 * This method allows the user to edit a previous inputted homework grade
	 */
	public void editHomeworkGrade(int index, double homeworkGrade) {
		homeworkGrades.set(index, homeworkGrade);
	}
	
	/**
	 * @return the arraylist of all the homework grades that the student has received for the assignments assigned so far
	 * Getter method for the arraylist of all the homework grades that the student has received for the assignments assigned so far
	 */
	public LinkedList getHomeworkGrades() {
		return homeworkGrades;
	}
	
	/**
	 * @return the student's homework average
	 * Getter method for the student's homework average 
	 */
	public double getHomeworkAvg() {
		return homeworkAvg;
	}
	
	/**
	 * @param examGrade the exam grade that the student received on the most recent exam
	 * This method will be the one that is mainly used to add exam grades (as opposed to the method right below this one) because 
	 * teachers will usually grade and add exam grades in the order that the exams were given. Since arraylist automatically adds 
	 * new elements to the end of the list, the list will already be in order. 
	 * 
	 */
	public void addExamGrade(double examGrade) {
		examGrades.add(examGrade);
	}
	
	/**
	 * @param index the index of the examGrades arraylist -- also known as the exam number 
	 * @param examGrade the exam grade that the student received on the most recent exam
	 * This method is used when the user wants to add an exam grade for an exam that is not the most recent exam or when the exam grade 
	 * they are adding is for an exam for which the grade has not been inputted yet 
	 */
	public void addExamGrade(int index, double examGrade) {
		if (index > examGrades.size()) {
			for (int i = examGrades.size(); i < index; i++) {
				examGrades.add(-1.0);		//-1.0 represents that the exam grade at that index has not yet been inputted
			}						//ex: Having a -1.0 at index 2 means that the exam grade for the 3rd exam 
		}							//(since index starts at 0, index 0 stores the first assignment grade) has not yet been inputted
		examGrades.add(index, examGrade);
	}
	
	/**
	 * @param index the index / the exam number of the exam grade that is being edited 
	 * @param examGrade the new exam grade that is replacing the old exam grade
	 * This method allows the user to edit a previous inputted exam grade
	 */
	public void editExamGrade(int index, double examGrade) {
		examGrades.set(index, examGrade);
	}
	
	/**
	 * @return the arraylist of all the exam grades that the student has received for all the exams given so far
	 * Getter method for the arraylist of all the exam grades that the student has received for all the exams given so far
	 */
	public LinkedList getExamGrades() {
		return examGrades;
	}
	
	/**
	 * @return the student's exam average
	 * Getter method for the student's exam average
	 */
	public double getexamAvg() {
		return examAvg;
	}
	
	/**
	 * @return the student's homework average 
	 * This method calls the calculateAvg method to calculate the student's homework average and returns the homework average
	 */
	public double calculateHomeworkAvg() { 
		double homeworkAverage = calculateAvg(homeworkGrades);
		homeworkAvg = homeworkAverage;
		return homeworkAvg;
	}
	
	/**
	 * @return the student's exam average 
	 * This method calls the calculateAvg method to calculate the student's exam average and returns the exam average
	 */
	public double calculateExamAvg() { 
		double examAverage = calculateAvg(examGrades);
		examAvg = examAverage;
		return examAvg;
	}
	
	/**
	 * @param gradesList the list of grades (homework or exam grades) 
	 * @return the average of the list of grades 
	 * This method is called every time the average of a list of grades need to be calculated. 
	 * calculateHomeworkAvg and calculateExamAvg calls this method to calculate the homework and exam averages for a list of 
	 * homework or exam grades.
	 */
	public double calculateAvg(LinkedList gradesList) {
		Iterator<Double> gradeItr = gradesList.iterator();		//Iterator to iterate over the list of grades
		double total = 0;				//Keeps track of the sum of all the grades seen so far
		int numOfGrades = 0;			//Keeps track of the number of grades that the iterator has iterated over so far
		while (gradeItr.hasNext() == true) {
			double currentGrade = gradeItr.next(); 
			if (currentGrade != -1.0) {
				total += currentGrade;
				numOfGrades++;			//Number of grades is incremented with every iteration
			}
		}
		return total / numOfGrades;		//Returns average value, which is calculated by dividing the sum of 
	}									//all the numbers in the list by the number of numbers in the list.
	
	/**
	 * @param overallGrade the student's overall grade in the class
	 * Setter method for the student's overall grade
	 */
	public void setOverallGrade (int overallGrade) {
		this.overallGrade = overallGrade;
	}
	
	/**
	 * @return the student's overall grade in the class
	 * Getter method for the student's overall grade in the class
	 */
	public double getOverallGrade() {
		return overallGrade;
	}
	
//EXPLAIN THAT THIS METHOD IS USED BY THE SECTION CLASS TO SET RANK.
	public void setRank (int rank) {
		this.rank = rank;
	}
	
	/**
	 * @return the student's current rank in the class
	 * Getter method for the student's current rank in the class
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * @param component a component / category of a student's grade (ex: exams, homeworks, participation, etc) 
	 * @param grade the grade that the student received for that component of the grade
	 * @param percentageOfGrade the percentage of the student's grade that that component will be counted for 
	 * This method This method adds a component to the map that stores all the components of a student's grade, along with the grade
	 * they received for every component and the percentage that that componenet makes up of the overall grade
	 */
	public void addComponents(String component, double grade, double percentageOfGrade) {
		double[] gradeContribution = new double[2];
		gradeContribution[0] = grade;		//first index of 2-index array stores the grade the student received for a specific component
		gradeContribution[1] = percentageOfGrade;//second index stores the percentage of the grade that the component will be counted for
		gradeComponents.put(component, gradeContribution);
	}
	
	/**
	 * This method calculates and returns the student's overall grade.
	 */
	public double calculateOverallGrade() {
		//Iterator keyItr iterates over the keys of the gradeComponents HashMap
		Iterator<String> keyItr = gradeComponents.keySet().iterator();
		double[] gradeContribution = new double[2];
		double overall = 0;			//Overall grade is updated every time the while loop below finishes with a new grade component. 
		while (keyItr.hasNext() == true) {
			String currentComponent = keyItr.next();
			gradeContribution = gradeComponents.get(currentComponent);
			overall += gradeContribution[0] * (gradeContribution[1] * .01);
		}
		overallGrade = overall;
		return overallGrade;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * This equals method is used to compare two students to check if they are the same student or not. The method checks to make sure
	 * that the name of the students and their ranks match. The ranks are also checked because 2 or more students may have the same name.
	 */
	public boolean equals(Object o) {
		if (o instanceof Student) {
			Student other = (Student)o;
			if (name.equals(other.getName())) {		//If names of the 2 students being compared are the same, ranks are checked
				return rank == other.getRank();		//Return true if ranks are the same as well, false if ranks are different
			} else {
				return false;		//False is returned if the name of the students being compared are not the same
			}
		} else {
			return false;	//False is returned if the user is trying to compare an object that is not a student to this student object
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * toString method for the class Student, which will return the name of the student.
	 */
	public String toString() {
		return name;
	}
	
}
