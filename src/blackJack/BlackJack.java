package blackJack;
import java.util.Scanner;

/*************************************************************************************
 *				           BLACK JACK GAME                                           *
 *************************************************************************************/
/*************************************************************************************
 * This is a Black Jack game, the goal is to draw cards that total 21, or come closer 
 * to 21 than the dealer without going over.                                         
 *************************************************************************************/
public class BlackJack {
	public static void main(String []args) 
	{	
		/* GLOBAL VARIABLES INITIALIAZED */
		boolean gameOn = true;
		int initPlayerBalance = 3000;
		int gameNum=0;
		String pName="";
		
		/* INITIALIZE THE DECK CLASS */
		Deck deck = new Deck();
		// WE CREATE THE DECK USING createDeck method from
		deck.createDeck();
		
//		this is a comment

/*************************************************************************************
 *				                    WELCOME MESSAGE                                  *
 *************************************************************************************/
		System.out.println("Welcome to Black Jack by Carlos Serrano");
		System.out.println();
		
		// The Scanner class is used to ask the user to input your name in the console
		Scanner name = new Scanner(System.in);
		System.out.print("Enter your name: ");
		pName = name.nextLine();
		
		/* WITH THE NAME OF THE PLAYER THE PLAYER CLASS IS INITIALIZED AND SET THE INITIAL
		 * BALANCE WITH THE initPlayerBalance variable that has the value of 3000 by default 
		 * declared in the global variables*/
		Player player = new Player(pName,initPlayerBalance);
		
		System.out.println("\n \n \n");
		
		
/*************************************************************************************
 *				                    GAME START                                       *
 *************************************************************************************/
/*************************************************************************************
 * gameOn variable is initialized as true by default in the global variables so we 
 * start the game by player input we get the answer of the user to set the gameOn
 * variable to true or false, if we get the value to false the game will end.
 *************************************************************************************/
		
		//Game is ON
		
		while (gameOn == true)
		{
			// This variables are used during the game to count the cards from the
			// players deck and the dealers deck
			int playerCardNum = 0;
			int dealerCardNum = 0;
			String choicePlay = "";
			
			while (!choicePlay.equals("Y") && !choicePlay.equals("N"))
			{	// This sentence will be displayed if you are not playing for the first time.
				if(gameNum>0) 
				{   // The try and catch to catch any exception if the user write
					// words instead of numbers. (initially the program would break if 
					//                             the user wrote a word as an input)
					try {
						Scanner play = new Scanner(System.in);
						System.out.println("Do you want to play again " + player.getName() + "? (Y or N)");
						choicePlay = play.nextLine();
						// .toUpperCase() is used to match the answer even if the user inputs a lower case letter
						choicePlay = choicePlay.toUpperCase();
					} catch (Exception e){
						choicePlay="";
					}
				}
				// This sentence will be executed if you are playing for the first time.
				else if(gameNum==0) 
				{
					try 
					{	// The try and catch to catch any exception if the user write
						// words instead of numbers. (initially the program would break if 
						//                             the user wrote a word as an input)
						Scanner play = new Scanner(System.in);
						System.out.println("Do you want to play " + player.getName() + "? (Y or N)");
						choicePlay = play.nextLine();
						choicePlay = choicePlay.toUpperCase();
					} catch (Exception e)
					{
						choicePlay="";
					}
				}
				
				if(!choicePlay.equals("Y") && !choicePlay.equals("N"))
				{
					System.out.println("\n \n \n \n \n");
					System.out.println("INVALID INPUT....");
				}
				
				else if(choicePlay.equals("Y"))
				{
					gameOn = true;
				}
				
				else if(choicePlay.equals("N"))
				{
					gameOn = false;
				}
			}
			
			// The variable choicePlay is reseted to start the while loop and ask the user if is ready to play		
			choicePlay = "";
			
			while (!choicePlay.equals("Y") && !choicePlay.equals("N") && gameOn==true)
			{
				System.out.println("\n \n \n \n \n");
				System.out.println("For this game you will start with "+ player.getBalance() +" CAD");
				System.out.println("You can bet as much as you want and as many times as you want");
				System.out.println("but if you lose all your money you can't keep playing");
				System.out.println("Your goal is to draw cards that total 21, or come closer to 21 than the dealer without going over.");
				
				try 
				{
					// The try and catch to catch any exception if the user write
					// words instead of numbers. (initially the program would break if 
					//                             the user wrote a word as an input)
					Scanner play = new Scanner(System.in);
					System.out.println("Are you ready? (Y or N)");
					choicePlay = play.nextLine();
					choicePlay = choicePlay.toUpperCase();
					
				} catch (Exception e)
				{
					choicePlay="";
				}
				
				if(!choicePlay.equals("Y") && !choicePlay.equals("N"))
				{
					System.out.println("INVALID INPUT....");
				}
				
				else if(choicePlay.equals("N"))
				{
					System.out.println("Well you already said that you are going to play so...");
				}
			}
			
/*************************************************************************************
 *				                  PLACING THE BET                                    *
 *************************************************************************************/
/*************************************************************************************
 * This section is created to ask the user for a quantity of money to bet in the game
 * the amount selected should not be higher than the current balance of the player
 * and can not be 0 or lower.
 *************************************************************************************/
			
			int bet = 0;
			
			while (gameOn == true && (bet <= 0 || bet > player.getBalance()))
			{
				try 
				{	// The try and catch to catch any exception if the user write
					// words instead of numbers. (initially the program would break if 
					//                             the user wrote a word as an input)
					Scanner newBet = new Scanner(System.in);
					System.out.println("You have: $" + player.getBalance() + " CAD in your balance");
					System.out.println("How much do you want to bet? ");
					bet = newBet.nextInt();
					
				} catch (Exception e){
					bet = 0;
				}
				
				if(bet < 0)
				{
					System.out.println("\n");
					System.out.println("...The bet must be more than 0...");
				}
				
				else if(bet > player.getBalance())
				{
					System.out.println("\n");
					System.out.println("You don't have enough money on your account");
				}
			}
			player.withdraw(bet);
			System.out.println(player.getName()+" your new balance is: " + player.getBalance());
		
			
/*************************************************************************************
 *				                        ROUNDS BEGIN                                 *
 *                                    CARDS ARE DEALED                               *
 *************************************************************************************/
/*************************************************************************************
 * This section is created to ask the user for a quantity of money to bet in the game
 * the amount selected should not be higher than the current balance of the player
 * and can not be 0 or lower.
 *************************************************************************************/
			
			// playerSum will sum the value assigned to each card
			int playerSum = 0;
			// dealerSum will sum the value assigned to each card
			int dealerSum = 0;
			// Every time we hit or stay the round will increment by 1
			int round = 0;
			String hitOrStay = "";
			boolean winner = false;
			// This while loop should be executing until someone wins this game
			while (winner == false && gameOn == true) {
				// If it is the first round the game will deal two cards to the player
				if(round == 0)
				{	
					for (int i=0; i<2; i++)
					{
						// deck.dealOne function returns a random card from the main deck and is
						// stored in the dealedCard variable 
						String dealedCard = deck.dealOne();
						// addPlayerCards method stores the dealedCard into the Players Deck
						deck.addPlayerCards(dealedCard,playerCardNum);
						String playerCard = deck.getPlayerCard(playerCardNum);
						// getCardValue gets the value assigned to each card
						playerSum = playerSum + deck.getCardValue();
						playerCardNum++;
					}
				}
				// If it is not the first round the game will deal one random card to the player 
				else if (round>0 && hitOrStay.equals("HIT")) {
					// deck.dealOne function returns a random card from the main deck and is
					// stored in the dealedCard variable
					String dealedCard = deck.dealOne();
					// addPlayerCards method stores the dealedCard into the Players Deck
					deck.addPlayerCards(dealedCard,playerCardNum);
					// getCardValue gets the value assigned to each card
					playerSum = playerSum + deck.getCardValue();
					playerCardNum++;
				}
				
				// The dealer will receive one random card from the main deck
				String dealedCard = deck.dealOne();
				deck.addDealerCards(dealedCard,dealerCardNum);
				dealerSum = dealerSum + deck.getCardValue();
				dealerCardNum++;
				
				// Print Player Cards
				System.out.println("\n");
				System.out.println("Player Cards: ");
				for (int i=0;i<playerCardNum;i++) {
					System.out.println(" -" + deck.getPlayerCard(i));
				}
				// Print Player Points
				System.out.println("Player points: " + playerSum);
				
				// Print Dealer Cards
				System.out.println("---------------------------");
				System.out.println("Dealer Cards: ");
				for (int i=0;i<dealerCardNum;i++) {
					System.out.println(" -" + deck.getDealerCard(i));
				}
				// Print Dealer Points
				System.out.println("Dealer points: " + dealerSum);
				System.out.println("");
				
/*************************************************************************************
 *				                    CHECK WINNER                                     *
 *************************************************************************************/	
/*************************************************************************************
 * This section is created to check if one of the players have won the current game.
 * When someone wins winner variable will be setted to true.
 *************************************************************************************/
				// If the player cards sum more than 21 the player loose.
				if (playerSum > 21) {
					System.out.println(player.getName()+" got over 21 points");
					System.out.println(player.getName()+" LOST!");
					winner=true;
				}
				// If the dealer cards sum more than 21 player wins
				else if (dealerSum > 21) {
					System.out.println("Dealer got over 21 points");
					System.out.println(player.getName()+" WON!");
					winner = true;
					player.win(bet);
				}
				// If the player get 21 in the sum of the cards he wins
				else if (playerSum == 21) {
					System.out.println(player.getName()+" WON!");
					winner = true;
					player.win(bet);
				}
				// If the dealer get 21 in the sum of the cards player loose
				else if (dealerSum == 21) {
					System.out.println("Dealer got 21 points");
					System.out.println(player.getName()+" LOST!");
					winner = true;
				}
				// If the dealer sum is more than the players sum the player loose
				else if (dealerSum > playerSum) 
				{
					System.out.println("Dealer got more points than "+player.getName());
					System.out.println(player.getName()+" LOST!");
					winner = true;			
				}
				
/*************************************************************************************
 *				                    HIT OR STAY                                      *
 *************************************************************************************/	
/*************************************************************************************
 * This section will ask the user for an input. When the user writes HIT in the console
 * the program will repeat the card dealing process and will deal one card for the
 * player and the dealer.
 *************************************************************************************/
				if(winner == false) {
					try 
					{	// The try and catch to catch any exception if the user write
						// words instead of numbers. (initially the program would break if 
						//                             the user wrote a word as an input)
						Scanner hitStay = new Scanner(System.in);
						System.out.println("Hit or Stay?? (write HIT or STAY)");
						hitOrStay = hitStay.nextLine();
						hitOrStay = hitOrStay.toUpperCase();
						
					} catch (Exception e){
						hitOrStay="";
					}
					
					// This while loop will ask the user to choose from hit or stay
					while (!hitOrStay.equals("HIT") && !hitOrStay.equals("STAY"))
					{
						Scanner hitStay = new Scanner(System.in);
						System.out.println("Hit or Stay?? (write HIT or STAY)");
						hitOrStay = hitStay.nextLine();
						hitOrStay = hitOrStay.toUpperCase();
						
						if(!hitOrStay.equals("HIT") && !hitOrStay.equals("STAY"))
						{
							System.out.println("INVALID INPUT....");
						}
						
						else if(hitOrStay.equals("HIT") || hitOrStay.equals("STAY"))
						{
							System.out.println("\n \n");
							System.out.println("Dealers turn...");
						}
					}
				}
				
				// This variable will indicate what is the current round
				round++;
			}
			
			System.out.println("THE ROUND IS OVER");
			System.out.println("Your current balance is: " + player.getBalance() + " CAD");
			
			// If the balance of the player is 0 or below gameOn variable will set to false and the game will end
			if (player.getBalance()<=0)
			{
				System.out.println("You don't have more money to play");
				gameOn=false;
			}
			// If gameOn is false the game is over
			if (gameOn == false)
			{
				System.out.println("GAME OVER");
			}
			// If gameOn is true then we'll restart the decks and increment the gameNum
			else if (gameOn == true) 
			{
				deck.restartDecks();
				gameNum++;
			}
	/******************************************************************************************* 
	 * This commented code was created to check that there are no duplicated cards in the deck *
	 * I left this commented code to show part of the creative process.                        *
	 *******************************************************************************************/

/**************************************************************************************************/
			//TEST to check there are no duplicated cards
//			System.out.println("This is a TEST");	
//			for (int i = 0;i<deck.playerDeck.length;i++) {
//				String dealedCard = deck.dealOne();
//				
//				deck.addPlayerCards(dealedCard,playerCardNum);
//				System.out.println(i+" "+dealedCard);	
//			}
/**************************************************************************************************/				
		}
	}
}
