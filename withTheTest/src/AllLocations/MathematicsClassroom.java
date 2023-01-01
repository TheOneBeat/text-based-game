package AllLocations;

import java.io.Serializable;

public class MathematicsClassroom extends Location implements Serializable{
	
	public MathematicsClassroom()
	{
		super("Mathematicsroom");
		this.fillPapers("Tables", "");
		this.fillPapers("Blackboard", "");
		this.fillPapers("Desk", "");
		this.fillPapers("Cupboard", "");
		this.fillPapers("Bookshelf", "");
		this.setDescription("You are in the Maths classroom, many fond memories here.. just kidding. You see a BLACKBOARD, the professor's DESK, TABLES, a CUPBOARD and a BOOKSHELF with mathematics books.");
	}
	
}