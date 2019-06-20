import java.util.*;

/**
 * @author Joy Lan
 */
public class Class {
	
	String name;		//name of the class
	LinkedList<Section> sections = new LinkedList<Section>();		//linkedlist of all sections of the class
	
	/**
	 * @param name the name of the class
	 * This is the constructor for the class.
	 */
	public Class(String name) {
		this.name = name;
	}
	
	/**
	 * @param name the name of the class
	 * The name of the class is set to the name given by the user. This method is only used when the name of the class is being changed
	 * because the user will set the name when they construct the class and that name usually remains the same for the entire existence
	 * of the class.
	 */
	public void setName(String name) {
		this.name = name; 
	}
	
	/**
	 * @param section a section of the class
	 * This method adds a section of the class to the linkedlist sections.
	 */
	public void addSection(Section section) {
		sections.add(section);
	}
	
	/**
	 * @return the linkedlist sections, which contains all the sections of this class
	 * This is the getter method for the sections of this class.
	 */
	public LinkedList getSections() {
		return sections;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * toString method for class Class, which will return the name of the class.
	 */
	public String toString() {
		return name;
	}
	
}
