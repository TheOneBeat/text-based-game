package AllLocations;
import java.io.*;
import Weapons.Ironbar;

public class Corridor extends Location implements Serializable{
	
	public Corridor() {
		
		super("Corridor");
		this.getLocationItems().add(new Ironbar("Godarm", 20, 35, 65));
		
		this.fillPapers("Table", "");
		this.fillPapers("Window", "");
		this.fillPapers("Computer", "");
		this.setDescription("You are in the corridor, there is an old COMPUTER at the end it. You see through the WINDOW that it's very dark outside. You also see a TABLE");
	}
}
