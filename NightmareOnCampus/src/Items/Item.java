package Items;
import java.io.*;

public class Item implements Serializable{
	private final int volume;
	private final String surname;
	
	public Item(String name, int vol)
	{
		this.surname = name;
		this.volume = vol;
	}

	public int getVolume() {
		return this.volume;
	}

	public String getSurname() {
		return this.surname;
	}
	
	
	
}
