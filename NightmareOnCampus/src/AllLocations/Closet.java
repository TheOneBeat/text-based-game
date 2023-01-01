package AllLocations;
import java.io.*;
import Weapons.Knife;

public class Closet extends Location implements Serializable{
	
	public Closet()
	{
		super("Closet");
		this.getLocationItems().add(new Knife("Knife", 30, 45, 60));
		this.setIsLock(true);
		this.fillPapers("Cables", "");
		this.fillPapers("Routers", "");
		this.setDescription("You are in the closet, you see CABLES and ROUTERS.");
	}
	
}
