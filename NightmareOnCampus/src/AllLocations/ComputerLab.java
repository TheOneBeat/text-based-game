package AllLocations;
import java.io.*;

import Items.Key;

public class ComputerLab extends Location implements Serializable{

	public ComputerLab()
	{
		super("Computerlab");
		this.getLocationItems().add(new Key("Skapinoffice", 15));
		this.fillPapers("Desk", "");
		this.fillPapers("Computer", "");
		this.fillPapers("Board", "");
		this.setDescription("You are now in the computer lab, there is an interactive smart BOARD, the professor's DESK and all the student's computers. For some reason, one COMPUTER is on.. ");
	}
}
