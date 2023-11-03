package blackJack;
import java.util.Random;

/*************************************************************************************
 *				                    DECK CLASS                                       *
 *************************************************************************************/
/*************************************************************************************
 *  Deck class created to manage the main deck, players deck and the dealers deck.   *
 *************************************************************************************/
public class Deck {
	String[] allCards;
	String[] deck;
	String[] playerDeck;
	String[] dealerDeck;
	
	// This are the initial arrays that contains all the suits and ranks 
	// the deck will be created with a method that will generate all the possible combinations
	// so we have a complete deck. 
	String suits[] = {"Hearts", "Diamonds", "Spades", "Clubs"};
	String ranks[] = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
	        "Jack", "Queen", "King", "Ace"};
	// As an array has to be declared as type string, int etc. it is necessary to create another array
	// that contains the values of each card that will be stored in the deck in the correct order.
	// THIS PROBLEM COULD BE SOLVED WITH ArrayList to have a two dimensions array with type STRING and INT.
	int cardValues[] = {2,3,4,5,6,7,8,9,10,10,10,10,11,
			2,3,4,5,6,7,8,9,10,10,10,10,11,
			2,3,4,5,6,7,8,9,10,10,10,10,11,
			2,3,4,5,6,7,8,9,10,10,10,10,11};
	
	// Here we get the number of all the possible combinations of suits and ranks that will be
	// 52, that is the same number of cards in a poker deck.
	int totalCombinations = suits.length*ranks.length;
	int dealedCardNum;
	
	// This is the Deck class constructor that initializes the lengths of each array.
	public Deck()
	{
		allCards = new String[totalCombinations];
		deck = new String[totalCombinations];
		playerDeck = new String[totalCombinations];
		dealerDeck = new String[totalCombinations];
	}
	
	//Setter for the cards.
	public void setCards(String[] cards) 
	{
		this.allCards = cards;
	}
	//Getter for cards.
	public String[] getCards(int i)
	{
		return this.allCards;
	}
	//Setter for the deck.
	public void setDeck(String[] deck) 
	{
		this.deck = deck;
	}
	//Getter for deck.
	public String[] getDeck()
	{
		return this.deck;
	}
	//setter for the cardValue.
	public void setCardValue(int[] cardValues) 
	{
		this.cardValues = cardValues;
	}
	//Getter for the value of the card.
	public int getCardValue()
	{
		return this.cardValues[dealedCardNum];
	}
	//Will return one random card from the deck as a string.
	public String dealOne()
	{   // Initialize the random library.
		Random random = new Random();
		// A random number higher than 0 and less than the total length of the deck
		// is setted to the randomIndex variable.
		int randomIndex = random.nextInt(deck.length);
		// If the value of the card in the deck is null it will look for another random
		// until it gets a value that is not null.
		while (this.deck[randomIndex] == null) 
		{
			randomIndex = random.nextInt(deck.length);
		}
		// The last randomIndex value is stored in a global variable named dealedCardNum to use it
		// when we look for the numeric value of the card.
		this.dealedCardNum = randomIndex;
		return this.deck[randomIndex];
	}
	// This method will automatically create all the possible combinations between suits and ranks
	// with a format that will show the proper name of the card in a poker deck.
	public void createDeck()
	{
		int cardNum = 0;
		for (int i = 0; i < this.suits.length; i++)
		{
			for (int j = 0; j < this.ranks.length; j++)
			{
				this.allCards[cardNum] = this.ranks[j]+ " of " + suits[i];
				this.deck[cardNum] = this.allCards[cardNum];
				
				cardNum++;
			}
		}
	}
	
	// This method will add the current card dealed by the main deck to the players Deck
	// also when the card is already stored in the players deck the card is removed from the main deck 
	// setting it to null.
	public void addPlayerCards(String currentCard, int cardNum)
	{
		this.playerDeck[cardNum]=currentCard;
		// This line will remove the card form the deck and convert the value into null.
		this.deck[dealedCardNum] = null;
	}
	
	// This method will add the current card dealed by the main deck to the dealers Deck.
	public void addDealerCards(String currentCard, int cardNum)
	{
		this.dealerDeck[cardNum]=currentCard;
		this.deck[dealedCardNum] = null;
	}
	
	// This method will return the value of the selected position of the players deck.
	public String getPlayerCard(int cardNum)
	{
		return this.playerDeck[cardNum];
	}
	// This method will return the value of the selected position of the dealers deck.
	public String getDealerCard(int cardNum)
	{
		return this.dealerDeck[cardNum];
	}
	// This method will set to null all the positions in the players and dealers deck
	// and finally will create the main deck again so all the positions setted to null
	// will have the correct card stored.
	public void restartDecks() 
	{
		for (int i=0;i<playerDeck.length;i++) {
			playerDeck[i]=null;
			dealerDeck[i]=null;
			createDeck();
		}
	}
}

