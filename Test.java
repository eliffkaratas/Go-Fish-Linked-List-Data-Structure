import java.util.Random;
import java.util.Scanner;
public class Test {
	static Random rnd = new Random(); // for using more than one
	static SingleLinkedList1 Player = new SingleLinkedList1(); 
	static SingleLinkedList2 Computer = new SingleLinkedList2(); 
	static SingleLinkedList3 Table = new SingleLinkedList3();
	static boolean flag1 = false; // turn control
	static int player_count = 0; // book control for player
	static int computer_count = 0; // book control for computer
	
	public static void main(String[] args) {
		for(int i=0;i<7;i++) // random cards
		{
			int dataToAdd = rnd.nextInt(6) + 1;
			Player.addToEnd(dataToAdd);
		}
		for(int i=0;i<7;i++)
		{
			int dataToAdd = rnd.nextInt(6) + 1;
			Computer.addToEnd(dataToAdd);
		}
		for(int i=0;i<7;i++)
		{
			int dataToAdd = rnd.nextInt(6) + 1;
			Table.addToEnd(dataToAdd);
		}
		
		while(!(Computer.size() == 0 && Player.size() == 0)) { // if computer or player finish the cards, game is over
			while(!flag1) { // for player turn
				System.out.println("  ");
				System.out.println("--------------------");
				System.out.print("You: " + Player.display());
				System.out.print("                    ");
				System.out.print("book: " + player_count);
				System.out.print("                    ");
				System.out.print("Table: " + Table.display());
				System.out.println("");
				System.out.print("Computer: " + Computer.display());
				System.out.print("                    ");
				System.out.print("book: " + computer_count);
				System.out.print("                    ");
				System.out.println("  ");
				System.out.println("  ");
				System.out.print("You ask: ");
				Scanner s = new Scanner(System.in);
				int input  = s.nextInt();
				System.out.println("  ");
				
				if(Computer.book(input) == 0) { // if computer haven't any card that value is input, player takes card from table
					Player.addToEnd(Table.getFirst()); // first card add to player
					Table.remove(Table.getFirst()); // card will be removed from table
					flag1 = true; // player's turn is finished
					System.out.println("  ");
					System.out.print("Computer says 'Go Fish'."); // the turn is computer
				}
				else if(Computer.size() == 0) { // if computer haven't any card any value
					System.out.println("  ");
					System.out.print("Computer says 'Go Fish'."); // the turn is computer
					for(int i=0;i<7;i++)
					{
						int dataToAdd = rnd.nextInt(4) + 1; // computer gets random four cards
						Computer.addToEnd(dataToAdd);
					}
					flag1 = true; // player's turn is finished
				}
				Book2(input); // for book controls
			}

			while(flag1) {  // for computer turn
				int random = rnd.nextInt(6) + 1; // card value is random integer
				System.out.println("  ");
				System.out.println("--------------------");
				System.out.print("You: " + Player.display());
				System.out.print("                    ");
				System.out.print("book: " + player_count);
				System.out.print("                    ");
				System.out.print("Table: " + Table.display());
				System.out.println("");
				System.out.print("Computer: " + Computer.display());
				System.out.print("                    ");
				System.out.print("book: " + computer_count);
				System.out.print("                    ");
				System.out.println("  ");
				System.out.println("  ");
				System.out.print("Computer asks: " + random);
				
				if(Player.book(random) == 0) { // if computer haven't any card that value is random integer, computer takes card from table
					Computer.addToEnd(Table.getFirst()); // first card add to computer
					Table.remove(Table.getFirst()); // card will be removed from table
					flag1 = false; // computer's turn is finished
					System.out.println("  ");
					System.out.print("You say 'Go Fish' "); // the turn is player
				}
				else if	(Player.size() == 0) { // if player haven't any card any value
					System.out.println("  "); 
					System.out.print("You say 'Go Fish' "); // the turn is player
					for(int i=0;i<7;i++)
					{
						int dataToAdd = rnd.nextInt(4) + 1; // player gets random four cards
						Player.addToEnd(dataToAdd);
					}
					flag1 = false; // computer's turn is finished
				}
				Book1(random); // for book controls
			}
		}
		
		if(player_count > computer_count) // winning conditions
		{
			System.out.println("  ");
			System.out.println("  ");
			System.out.print("Game is over.");
			System.out.print("You win the game!");
		}
		else if(computer_count > player_count)
		{
			System.out.println("  ");
			System.out.println("  ");
			System.out.print("Game is over.");
			System.out.print("Computer wins the game!");
			
		}
		else if(computer_count == player_count)
		{
			System.out.println("  ");
			System.out.println("  ");
			System.out.print("Game is over. ");
			System.out.println("  ");
			System.out.print("No one is winner. Tie.");
		}
	}
	
	public static void Book1(int value) { // computer book control
		System.out.println("  ");
		if(Player.book(value) + Computer.book(value) >= 4 && flag1 == true) { // if computer's turn and total cards are four
			System.out.println(value + " " + value + " " + value + " " + value);
			int dataToRemove = value;
			int temp1 = Player.book(value); // it controls card's number for adding
			int temp2 = Computer.book(value); // it controls card's number for adding
			Player.remove(dataToRemove);
			Computer.remove(dataToRemove);
			for(int i = 0; i < Math.abs((temp1+temp2)-4); i++) { // over card will be adding end of the computer
				Computer.addToEnd(value);
			}
			computer_count++; // book count increases for computer
		} 
		else if((Player.book(value) + Computer.book(value) < 4) && flag1 == true) { // if computer's turn and total cards are less than four
			for(int i = 0; i < Player.book(value); i++) { // player's cards are adding to the computer
				Computer.addToEnd(value);
			}
			Player.remove(value); // removing cards from player
		}
	}
	
	public static void Book2(int value) { // player book control
		if(Player.book(value) + Computer.book(value) >= 4 && flag1 == false) { // if player's turn and total cards are four
			System.out.println(value + " " + value + " " + value + " " + value);
			int dataToRemove = value;
			int temp1 = Player.book(value); // it controls card's number for adding
			int temp2 = Computer.book(value);
			Player.remove(dataToRemove);
			Computer.remove(dataToRemove);
			for(int i = 0; i < Math.abs((temp1+temp2)-4); i++) { // over card will be adding end of the player
				Player.addToEnd(value);
			}
			player_count++; // book count increases for player
		}
		else if((Player.book(value) + Computer.book(value) < 4) && flag1 == false) { // if player's turn and total cards are less than four
			
			for(int i = 0; i < Computer.book(value); i++) { // computer's cards are adding to the player
				Player.addToEnd(value);
			}
			Computer.remove(value); // removing cards from computer
		}
	}
}
