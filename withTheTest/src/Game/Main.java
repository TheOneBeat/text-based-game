package Game;
import java.io.*;
import java.util.Scanner;

import AllExits.Door;
import Allcommands.Command;
import Allcommands.Help;


public class Main implements Serializable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		University theUniverse = new University();
		
		theUniverse.getPlayer().present();
		String[] thecmd= null;
		Scanner myObj = new Scanner(System.in);
		String command="";
		String arg="";
		boolean discover=true;

	while(Command.end(theUniverse.getPlayer()))
	{
		while (thecmd == null) {
				System.out.println("  Your Command \n");
				System.out.print("$  ");
				
				thecmd = myObj.nextLine().trim().split(" ");// Read user input
				
			}
			System.out.print("\n");
			try{
				command = thecmd[0].substring(0,1).toUpperCase() + thecmd[0].substring(1).toLowerCase();
			}
			catch (StringIndexOutOfBoundsException e)
			{
				System.out.println("\n\tYou need to enter a command \n");
				thecmd=null;
			}
			
			if (command.equals("Quit"))
			{
				System.out.println("You rage quit the game, you are bad");
				break;
			}

			switch (command) {

				case "Go":
					if (thecmd.length>1)
						Command.Goto(theUniverse.getPlayer(), thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase());
					else
						System.out.println("\n\tGo must be followed by a location's name ... \n");
				break;

				case "Help":
					if (thecmd.length==1)
						Help.printCommands();
					else
						Help.printACommandHelp(thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase());
					break;

				case "See_rooms":
					Command.printlocation(theUniverse.getPlayer());
					break;

				case "Take":
					if (thecmd.length>1)
						Command.take(theUniverse.getPlayer(), (thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase()));
					else
						System.out.println("\n\tTake must be followed by a valid object name ... \n");
					break;

				case "Check_bag":
					Command.Unbox(theUniverse.getPlayer());
					break;

				case "Throw":
					if (thecmd.length>1)
						Command.Throw(theUniverse.getPlayer(), thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase());
					else
						System.out.println("\n\tThrow must be followed by an item or weapon's name ... \n");
					break;

				case "Equip":
					if (thecmd.length>1)
						Command.use(theUniverse.getPlayer(), thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase());
					else
						System.out.println("\n\tEquip must be followed by an item or weapon's name ... \n");
					break;

				case "My_stats":
					Command.who(theUniverse.getPlayer());
					break;

				case "Check_hands":
					Command.hold(theUniverse.getPlayer());
					break;

				case "Disarm":
					Command.noWeap(theUniverse.getPlayer());
					break;

				case "Attack":
					Command.attack(theUniverse.getPlayer());
					break;

				case "Monster":
					Command.monster(theUniverse.getPlayer());
					break;

				case "Look":
					Command.printMylocation(theUniverse.getPlayer());
					break;

				case "Pack":
					Command.noItems(theUniverse.getPlayer());
					break;

				case "Pull":
					if (thecmd.length>1)
						Command.code(theUniverse.getPlayer(),(thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase()).equals(Command.CODE));
					try
					{
						arg = thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase();
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						thecmd=null;
					}
					if (arg.equals(Command.CODE))
						theUniverse.getAlllocs().get(0).AddExitLocation(theUniverse.getAlllocs().get(1).getName(), new Door(theUniverse.getAlllocs().get(1)));
					break;

				case "Check":
					if (thecmd.length>1)
						Command.pull(theUniverse.getPlayer(), thecmd[1].substring(0,1).toUpperCase() + thecmd[1].substring(1).toLowerCase());
					else
						Command.pull(theUniverse.getPlayer(),"");
					break;

				case "Save":
					try {
						FileOutputStream fos = new FileOutputStream("saveData.sav");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(theUniverse);
						oos.close();
						fos.close();
						System.out.println("\n\tGame saved successfully\n");
					}
					catch (Exception e)
					{
						System.out.println("\n\tError during the saving's proccess, please try again later\n");
					}
					break;

				case "Load":
					try {
						FileInputStream fis = new FileInputStream("saveData.sav");
						ObjectInputStream ois = new ObjectInputStream(fis);
						theUniverse= (University) ois.readObject();
						fis.close();
						ois.close();
						System.out.println("\n\tGame loaded successfully\n");
					}
					catch (Exception e)
					{
						System.out.println("\n\tThere is no saving data yet ,you must save your game before loading\n");
					}
					break;

				case "Clues":
					Command.clues(theUniverse.getPlayer());

				default:
					System.out.println("");
					break;
			}

				if (discover)
				{
					if (theUniverse.getPlayer().playerHaveAllHints())
					{
						System.out.println("\nCongratulations ! You have found all the papers, you may have a chance to get out of here..\n"+						
						"If the cold dose not kill you first. Try and figure out the hidden code using all the papers.\n"+ 
						"Let me help you out : the hidden message should give you a clue to find the right room.\n"+
						"Once you think you are in the right room use the command PULL followed by the hidden message. Who knows, maybe something will happen.\n"+
						"But like during your exams, trying random answers will have consequences : you will lose 5 life points if you do not type in the right hidden code. \n"
					);
						Help.getCmds().put("Pull", "\n\tPull. Allows you to use the secret password, if it is correct, you should notice some texts...\n\tExample : Pull Azerty \n");
						discover = false;
					}
				}

				thecmd=null;
		}
	}
}
	