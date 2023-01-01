package Allcommands;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

import AllPlayers.Player;

public abstract class Command implements Serializable{
	public static final String CODE = "Pythagore";
	public Command() {
		
	}
	
	public static void digin(Player p) // fouiller
	{
		p.getLocation().printLocationItem();//on print les items de la location
	}
	
	public static void noWeap(Player p) // désarmer le joueur
	{
		p.noWeapon();
	}
	
	public static void noItems(Player p) // enlever son item...
	{
		p.noItem();
	}
	
	public static void Goto(Player p , String loc) { //changer de <location>
			
		Set<String> theListLocations = p.getLocation().getAllWays(); //cree un set des nom de tous les locations liés à la current location du player
		
		if (p.getLocation().getQuitLocation()==false) //si le joueur ne peut pas quitter la location, c'est parce qu'il y a des monstres qu'il doit battre...
		{
			System.out.print("\n\nYou need to KILL ALL of Monsters in this location in order to advance\n");
			return;
		}
		
		if (theListLocations.contains(loc)==false)
		{
			System.out.println("\tYour current location does not have access to the room you want to go\n \n\t Make sure it is a room shown by the command 'See_rooms' \n");
			return;
		}
		
		if (p.getLocation() == p.getLocation().getDict().get(loc).getDoorLocation())//si le joueur est dejà dans la location loc alors...
			System.out.println("\n\tYou are already in this room\n");
		else
		{
			if (p.getLocation().getDict().get(loc).getDoorLocation().getIsLock()==true)// et que la porte vers la location est verouillee
			{
				if (p.getPlayerCurrentItem()!=null)// si le player porte une cle dans sa main...
				{
					if (p.getPlayerCurrentItem().getSurname().equals(loc))//si c'est la bonne cle
					{
						p.getLocation().getDict().get(loc).getDoorLocation().setIsLock(false);
						p.changeLocation(p.getLocation().getDict().get(loc).getDoorLocation());
						System.out.print("\nThe room is now unlocked...  ");
						System.out.println("\tYou changed room\n");
					}
					else// si cette cle est differente de la cle pour ouvrir la porte
					{
						System.out.println("\n\tYou have not equiped the right key\n");
						return;
					}
				}
				else//si le player ne porte pas de cle...
				{
					System.out.println("\tTry equiping a key...\n");
					return;
				}	
			}
			else//si la porte n'est pas verouillee alors le joueur peut changer de location
			{
				p.changeLocation(p.getLocation().getDict().get(loc).getDoorLocation());	
				System.out.println("\tYou changed room\n");
			}
			
		}
	}
	
	public static void hold(Player p) { // ce que j'ai dans la main
		p.printItem();
	}
	
	public static void printlocation(Player p) // afficher la loc
	{
		p.getLocation().presentLoc();//donne la description de la location
	}
	
	public static void Unbox(Player p) // afficher inventaire
	{
		p.getPlayerBag().printItemInTheBag();
	}
	
	static public void take(Player p, String v) // prendre un item
	{
		p.addItemToBag(v);
	}
	
	public static void Throw(Player p, String v) // enlever un item <v> de l'inventaire
	{
		p.removeItemFromBag(v);
	}
	
	public static void use(Player p, String v) // utiliser un objet de l'inventaire
	{
		p.useItem(v);
	}
	
	public static void printMylocation(Player p) // savoir où on se trouve
	{
		p.getLocation().printlocation();
		digin(p);//+ les items et meubles presents
	}
	
	public static void who(Player p)
	{
		p.present();//donne les stats et presentation du joueur
	}
	
	public static void monster(Player p)//Pour savoir si il y a des monstres dans la current_location
	{
		p.getLocation().showMonsters();
	}
	
	public static void attack(Player p)// pour attaquer les monstres de la current location
	{
		p.attackEnemy(p,p.getLocation());
	}
	
	public static void pull(Player p,String v)//pour pull des meubles et trouver des indices
	{
		p.draw(v);
	}
	
	public static void clues(Player p)//pour savoir ce qu'on a comme clues...
	{
		p.printPlayerHints();
	}
	
	public static void code(Player p, boolean v)
	{
		if (p.getLocation().getName()=="Mathematicsroom")
		{
			if (v)
			{
				System.out.println("Pythagore ? Interesting, there is a book of pythagore here on the shelf..\n" + 
				"maybe you should try and pull it.\n" +
				"You pull the book, it moves a bit but it won't leave the shelf. \n" +
				"Instead you see the whole shelf sliding to the side. As the shelf openes you see a new secret passage way !! \n" +
				"Should you enter the HIDDEN_ROOM ?");
			}
			else
			{
				System.out.println("\n\tWrong Password, you should try again, you have lost 5 life points for being stupid\n");
				p.setNbLife(-5);
			}
		}
		else
			System.out.println("\n \tYou are in the wrong room to tap the command 'Pull'\n");
	}

	public static boolean end(Player p) {
		boolean ending = true;
		if (p.getLocation().getName().equals("Hiddenroom")) {
			System.out.println("\nWell done you have finally gotten out !!\n" +
					"However there is a weird ringing sound in the background...\n" +
					"It's your alarm clock.\n" +
					"Looks like you where just having a horrible nightmare on your University and now you're incredibly late for your class of POO. \n" +
					"You wonder if it's worth even going at that point.. Should go to the lesson or watch Netflix ?");
			System.out.println("You can choose between 'Go to the lesson' or 'Watch Netflix' by type '1' or '2'");

			String name = null;
			

			while(name==null || name=="")
			{
				
				Scanner thescan = new Scanner(System.in);
				name= thescan.nextLine();
			}
			if (name.equals("1")) {
				name="You have made the right choice, you will become an incredible computer scientist create games and overtake UBISOFT in the business world. Good choice !";
				ending = false;
			} else if (name.equals("2")) {
				name="You have made the wrong choice, you will never manage to make a game and will die alone.";
				ending = false;
			}
			System.out.println(name);
			
		}
		return ending;
	}
}
