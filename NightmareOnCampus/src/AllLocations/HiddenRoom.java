package AllLocations;
import java.io.*;

import Characters.Enemy;
public class HiddenRoom extends Location implements Serializable{
	public HiddenRoom()
	{
		super("Hiddenroom");
		this.getAllEnemies().add(new Enemy("Samuel",45,200));
		this.setQuitLocation(false);
		this.setDescription("You did it !! You can finally see the exit ! There is only one problem, you see a terrible monster between you and the door, it's none other than Samuel");
	}
}

