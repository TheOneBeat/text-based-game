package AllLocations;
import java.io.*;

import Characters.Enemy;
import Clothes.Jacket;

public class Amphitheater extends Location implements Serializable{
	public Amphitheater()
	{
		super("Amphitheater");
		this.getLocationItems().add(new Jacket());
		this.getAllEnemies().add(new Enemy("Chainsawman",25,140));
		this.setQuitLocation(false);
		this.fillPapers("Chairs", "");
		this.fillPapers("Board", "");
		this.fillPapers("Tables", "");
		this.setDescription("You've just entered the Amphitheatre and what a mess ! All the CHAIRS and TABLES are broken and standing in front of the BOARD is a giant holding a chainsaw. No pressure but you might die. Remember my friend, you can always check the monster's stats using the command MONSTER.");
	}
}
