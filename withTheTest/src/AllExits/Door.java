package AllExits;
import AllLocations.Location;

import java.io.*;

public class Door implements Serializable{
	private Boolean isOpen;
	private Location linkedLocation;
	
	
	public Door(Location linkedTo) {
		this.linkedLocation = linkedTo;
		this.setDoorOpened();
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setDoorOpened() {
		this.isOpen = true;
	}
	
	public void setDoorClosed() {
		this.isOpen = false;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ", isOpened: " + this.getIsOpen();
	}
	
	/*public void changeExitLocation(Location newOne) {
		this.linkedLocation = newOne;
	}*/
	
	public Location getDoorLocation() {
		return this.linkedLocation;
	}
	
}