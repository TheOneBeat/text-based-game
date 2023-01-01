package AllLocations;
import java.io.*;

import Clothes.Jacket;
import Items.Key;

public class EnglishRoom extends Location implements Serializable{
	public EnglishRoom()
	{
		super("Englishroom");
		this.getLocationItems().add(new Key("Defayeoffice", 15));
		this.getLocationItems().add(new Jacket());
		this.fillPapers("Blackboard", "");
		this.fillPapers("Tables", "");
		this.fillPapers("Desk", "");
		this.fillPapers("Bookshelf", "");
		this.fillPapers("Posters", "");
		this.setDescription("The English classroom I think, it's the first time I see it to be honest. You see a BLACKBOARD, the professor's DESK, TABLES, a BOOKSHELF with English books and POSTERS on the wall.");
	}
}
