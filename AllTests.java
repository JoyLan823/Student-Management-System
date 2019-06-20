import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AllTests {

	/**
	 * This method tests the addStudent method of the Section class. 
	 */
	@Test
	void testAddStudent() {
		Class compSci = new Class("compSci");		//New class called compSci
		//Making 3 new sections for the compSci Class, each having a maximum of 3 students in it
		Section s1 = new Section(1, 3);
		Section s2 = new Section(2, 3);
		Section s3 = new Section(3, 3);
		compSci.addSection(s1);
		compSci.addSection(s2);
		compSci.addSection(s3);
		s1.addStudent(new Student("Joy"));
		s1.addStudent(new Student("Angie"));
		s1.addStudent(new Student("Bo"));
		//When user tries to add a 4th student to s1, a message should be printed to tell the user that the section is already full.
		s1.addStudent(new Student("Jess"));		
		s2.addStudent(new Student("Ben"));
		s2.addStudent(new Student("Fay"));
		s3.addStudent(new Student("Dexter"));
		s3.addStudent(new Student("Howard"));
		s3.addStudent(new Student("Cam"));
		String allStudents = "";
		//allStudents is a string that will contain the names of all the students from each of the sections and display them in order
		//of section number (Section 1 students followed by Section 2 students followed by Section 3 students).
		for (int i = 0; i < compSci.getSections().size(); i++) {
			Section currentSection = (Section)compSci.getSections().get(i);
			allStudents += "Section " + currentSection.getSectionNumber() + " students: ";
			for (int j = 0; j < currentSection.getTotalStudents(); j++) {
				allStudents += ((Student)currentSection.getStudentsList().get(j)).getName();
				if (j != currentSection.getTotalStudents() - 1) {
					allStudents += ", ";
				}
			}
			if (i != compSci.getSections().size() - 1) {
				allStudents += "; ";
			}
		}
		assertEquals("Section 1 students: Joy, Angie, Bo; Section 2 students: Ben, Fay; " +
				     "Section 3 students: Dexter, Howard, Cam", allStudents);
	}
	
	/**
	 * This method tests several methods of the Student and Section class, including methods that allow the user to add exam and 
	 * homework grades for any specific student, add components of a student's grade to the gradeComponents hashmap, the addStudentGrade 
	 * method, the calculateOverallGrade method, and the rankStudents method.
	 */
	@Test 
	void testStudentAndSection() {
		//3 new student objects are created and added to section1. 
		Section section1 = new Section(1, 3);
		Student student1 = new Student("Abe");
		Student student2 = new Student("Bo");
		Student student3 = new Student("Cam");
		section1.addStudent(student1);
		section1.addStudent(student2);
		section1.addStudent(student3);
		student1.addExamGrade(95);
		student1.addExamGrade(90);
		//calculateExamAvg method is tested below by calculating Abe's exam average and adding "Exam" and student1's exam average 
		//to the gradeComponents hashmap for the student1 object
		student1.addComponents("Exam", student1.calculateExamAvg(), 50);
		student1.addComponents("Homework", 100, 25);
		student1.addComponents("Participation", 100, 25);
		//"Abe" and Abe's overall grade are added to the grades hashmap for the section1 object
		section1.addStudentGrade(student1.getName(), student1.calculateOverallGrade());
		student2.addHomeworkGrade(100);
		student2.addHomeworkGrade(85);
		student2.addComponents("Exam", 80.765, 50);
		//calculateHomeworkAvg method for the student class is tested by calculating Bo's homework average in the line below and adding
		//"Homework" and Bo's homework average to the gradeComponents hashmap for the student2 object
		student2.addComponents("Homework", student2.calculateHomeworkAvg(), 25);
		student2.addComponents("Participation", 100, 25);
		section1.addStudentGrade(student2.getName(),student2.calculateOverallGrade());
		student3.addComponents("Exam", 99.5, 50);
		student3.addComponents("Homework", 100, 25);
		student3.addComponents("Participation", 100, 25);
		section1.addStudentGrade(student3.getName(),student3.calculateOverallGrade());
		//After each student's overall grade has been calculated and added to the grades hashmap for the section1 object, the students
		//are ranked according to their overall grades using the rankStudents method.
		section1.rankStudents();
		//String s stores the list of students in section1 in ranked order. Students in the list are displayed in order from highest
		//overall grade to lowest overall grade.
		String s = "Section 1 students from highest to lowest rank: ";
		s += section1.getRankedStudents();
		System.out.println(student1.getOverallGrade()); 	//student1 = Abe with an overall grade of 96.25
		System.out.println(student2.getOverallGrade());		//student2 = Bo with an overall grade of 88.5075
		System.out.println(student3.getOverallGrade());		//student3 = Cam with an overall grade of 99.75
		//Cam should be rank #1 with an overall grade of 99.75, Abe should be rank #2 with an overall grade of 96.25, and Bo should be 
		//rank #3 with an overall grade of 88.5075.
		assertEquals("Section 1 students from highest to lowest rank: [Cam, Abe, Bo]", s);
	}
	
	/**
	 * This method tests the Exam class, specifically, the methods to add a grade and the methods to calculate the mean, median, and 
	 * mode of the list of grades.
	 */
	@Test 
	void testExam() {
		Exam firstExam = new Exam(1);
		firstExam.addGrade(90);
		firstExam.addGrade(90);
		firstExam.addGrade(100);
		firstExam.addGrade(99);
		firstExam.addGrade(95.5);
		firstExam.addGrade(95.5);
		String s = "Mean: " + firstExam.calcMean() + "; Median: " + firstExam.calcMedian() + "; Mode(s): " + firstExam.calcMode();
		assertEquals("Mean: 95.0; Median: 95.5; Mode(s): [90.0, 95.5]", s);
	}
	
	//Method for which section got the highest exam average, lowest exam average, lists the averages in order (for a specific exam) 

}
