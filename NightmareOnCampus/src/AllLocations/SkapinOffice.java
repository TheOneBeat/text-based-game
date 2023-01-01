package AllLocations;
import java.io.*;

import Characters.Enemy;
import Items.Key;

public class SkapinOffice extends Location implements Serializable{
	
	public SkapinOffice() {
		super("Skapinoffice");
		this.fillPapers("Posters", "");
		this.fillPapers("Desk", "");
		this.fillPapers("Cupboards", "");
		this.getAllEnemies().add(new Enemy("Zombieman",15,80));
		this.getAllEnemies().add(new Enemy("Gunman",15,90));
		this.getLocationItems().add(new Key("Closet", 15));
		this.setQuitLocation(false);
		this.setIsLock(true);
		this.setDescription("This is Mr Skapin's office, you probably shouldn't be there... Oh well. You see his DESK, two CUPBOARDS and POSTERS with computers on them. You take another step in and you realise in horror two terrible monsters looking at you ! Don't panic, equip a weapon if you have any, and put on a jacket !! The resistance to the cold will buff your life points. DON'T DIE !");
		
	}
}
