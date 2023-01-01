package AllLocations;
import java.io.*;

import Clothes.Coat;


public class DefayeOffice extends Location implements Serializable{
	public DefayeOffice() {
		super("Defayeoffice");
		this.getLocationItems().add(new Coat());
		this.setIsLock(true);
		this.fillPapers("Desk", "");
		this.fillPapers("Cupboard", "");
		this.fillPapers("Posters", "");
		this.setDescription("You are now in Mr Defayes office. You see his DESK, a CUPBOARD, and POSTERS on typical English stuff like the queen, brunch and the Beatles.");
	}
}
