import java.util.*;

/**
 * @author Joy Lan
 */
public class Exam {
	
	private int examNum;		//the exam number of the current exam
	private double mean;		//the mean of all the scores for this exam
	private double median;		//the median of all the scores for this exam
	//A linkedlist is used to keep track of all the modes for the exam because the exam can have more than 1 mode. If the most common 
	//grades appear in the list the same number of times, all those grades would be included in modesList.
	private LinkedList<Double> modesList = new LinkedList<Double>();		
	private LinkedList<Double> allGrades = new LinkedList<Double>();	//the arraylist that contains the grade that each student 
																		//received for this exam
	
	

	/**
	 * @param examNum the exam number of the current exam
	 * This is the constructor for the class Exam.
	 */
	public Exam (int examNum) {
		this.examNum = examNum;
	}
	
	/**
	 * @return the mean of all the scores for this exam
	 * The mean of the scores for this exam is calculated by summing together all the grades for this exam and dividing it by the number 
	 * of grades there are for this exam.
	 */
	public double calcMean() {
		Iterator<Double> allGradesItr = allGrades.iterator();	//allGradesItr iterates over allGrades linkedlist
		double total = 0;
		while (allGradesItr.hasNext() == true) {		//While loop runs until each grade has been added to the total
			total += allGradesItr.next();
		}
		mean = total / allGrades.size();
		return mean;
	}
	
	/**
	 * @return the mean of all the scores for this exam
	 * Getter method for the mean of this exam
	 */
	public double getMean() {
		return mean;
	}
	
	/**
	 * @return the median of all the scores for this exam
	 * The median for this exam is calculated by sorting all the exam grades then finding the middle exam grade.
	 */
	public double calcMedian() {
		Collections.sort(allGrades);
		int size = allGrades.size();
		if ((allGrades.size()) % 2 == 1) {		//If the size of allGrades is currently odd, the median of the list 
			median = allGrades.get(size/2);		//is the grade right in the middle of the list
		} else {		//if the size of allGrades is currently even, the median is found by finding the average of the 
			median = (allGrades.get(size/2) + allGrades.get(size/2 - 1)) / 2;		//2 grades in the middle of the list 
		}
		return median;
	}
	
	/**
	 * @return the median of all the scores for this exam
	 * Getter method for the median of this exam
	 */
	public double getMedian() {
		return median;
	}
	
	/**
	 * @return the list of mode(s) for the list allGrades
	 * This method finds all the modes of the allGrades list and adds it to the linkedlist called modesList.
	 */
	public LinkedList calcMode() {
		Collections.sort(allGrades);		//allGrades list is sorted to make it easier to count the number of each grade
		double currentGrade = allGrades.get(0);
		int highestCount = 1;		//Greatest number of times any grade has appeared in the allGrades list so far
		int currentCount = 1;		//Number of times the current grade has appeared in the allGrades list so far
		
		//For loop iterates through entire allGrades linkedlist to find all mode(s)
		for (int i = 1; i < allGrades.size(); i++) {
			if (allGrades.get(i) == currentGrade) {		
				currentCount++;					//Count of the currentGrade is being kept track of using currentcount
				
			//Else-if statement is mainly for when the program encounters a new grade and needs to check if the previous grade is a mode
			//in the allGrades linkedlist.Program also goes into else-if statement below if the last index of the allGrades list is 
			//currently being checked because the last grade can be a mode of the allGrades list.
			} else if ((allGrades.get(i) != currentGrade) || i == allGrades.size()-1) {
				if (currentCount > highestCount) {
					highestCount = currentCount;
					modesList.clear();		//If a grade has been found to appear more frequently than the previous mode, modesList 
					modesList.add(allGrades.get(i-1));		//is cleared and that grade is added as the only grade in modesList
				} else if (currentCount == highestCount) {		//If a grade has been found to appear the same number of times as  
					modesList.add(allGrades.get(i-1));			//the current mode(s), it is added to the linkedlist modesList.
				}												
				currentGrade = allGrades.get(i);
				currentCount = 1;		//currentGrade and currentCount are reset every time the for loop encounters a new grade
			}
		}
		return modesList;
	}
	
	/**
	 * @return the mode of all the scores for this exam
	 * Getter method for the mode of this exam
	 */
	public LinkedList getMode() {
		return modesList;
	}
	
	/**
	 * @param grade a grade received by a student for this exam
	 * This method allows the user to add a grade that was received for this exam. The grade gets added to the linkedlist allGrades.
	 */
	public void addGrade(double grade) {
		allGrades.add(grade);
	}
	
	/**
	 * @return the arraylist that contains the grade that each student received for this exam
	 * Getter method for the arraylist of grades that each student received for this exam 
	 */
	public LinkedList getAllGrades() { 
		return allGrades;
	}
}
