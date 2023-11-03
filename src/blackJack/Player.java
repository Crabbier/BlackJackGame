package blackJack;

/*************************************************************************************
 *				                    PLAYER CLASS                                     *
 *************************************************************************************/
/*************************************************************************************
 *  Player class created to manage the player profile with the name of the player,   *
 * and the money balance setted for the game.                                        * 
 * ************************************************************************************/

public class Player {
	// Global variables for Players class.
	String name;
	int balance;
	
	//Create the constructor of the class with the 2 arguments name and balance.
	public Player(String name, int balance)
	{
		this.name = name;
		this.balance = balance;	
	}
	
	//Setter for name.
	public void setName(String name) 
	{
		this.name = name;
	}
	//Getter for name.
	public String getName()
	{
		return this.name;
	}
	//Setter for balance.
	public void setBalance(int balance) 
	{
		this.balance = balance;
	}
	//Getter balance.
	public int getBalance()
	{
		return this.balance;
	}
	//withdraw amount from the current balance. 
	public int withdraw(int bet) 
	{
		this.balance = balance - bet;
		return this.balance;	
	}
	// update current balance with the amount of the bet and the earnings that
	// would be the same amount of what you bet.
	public int win(int bet)
	{
		this.balance = this.balance + (bet*2);
		return this.balance;
	}	
}
