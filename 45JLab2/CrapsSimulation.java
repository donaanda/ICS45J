//Dona Anda 29856735
//Jessica Kim 54875433

import java.util.Scanner;

public class CrapsSimulation
{
	private CrapsGame CG;
	private CrapsMetricsMonitor CMM;
	private String userName;
	private int userBalance;
	private int userBet;
	private int userOriginalBet;
	private int currentWinStreak;
	private int currentLoseStreak;
	private Scanner input;
	
	public CrapsSimulation()
	{
		CMM = new CrapsMetricsMonitor();
		CG = new CrapsGame(CMM);
		userName = "";
		userBalance = 0;
		userBet = 0;
		currentWinStreak = 0;
		currentLoseStreak = 0;
		input = new Scanner(System.in);
	}
	
	public void start()
	{
		System.out.print("Welcome to SimCraps! Enter your user name: ");
		userName = input.nextLine();
		System.out.print("Hello " + userName + "!\n");
		System.out.print("Enter the amount of money you will bring to the table: ");
		userBalance = input.nextInt();
		System.out.printf("Enter the bet amount between $1 and $%d: ", userBalance);
		userBet = input.nextInt();
		
		while (userBet > userBalance || userBet <= 0) 
		{
			System.out.printf("Invalid bet! Please enter a bet amount between $1 and $%d: ", userBalance);
			userBet = input.nextInt();
		}
		userOriginalBet = userBet;
	
		while (userBalance > 0)
		{
			if (userBalance < userOriginalBet)
			{
				userBet = userBalance;
			}
			else if (userBalance >= userOriginalBet)
			{
				userBet = userOriginalBet;
			}
			
			System.out.println(userName + " bets $" + userBet);
			boolean win = CG.playGame();
			CMM.incrementGamePlayed();
			CMM.setGameNumberWhenMaxBalance(userBalance);
			int numOfRollsInOneGame = CG.getNumOfRolls();
			CMM.setMaxRollsInSingleGame(numOfRollsInOneGame);
			CG.resetNumOfRolls();
			int n = CG.getGameStatus();
			if (win == true)
			{
				CMM.incrementGamesWon();
				userBalance += userBet;
				currentWinStreak++;
				currentLoseStreak = 0;
				CMM.setMaxWinningStreak(currentWinStreak);
				if (n == 1)
				{
					CMM.incrementNaturalCount();
					System.out.println("*****Natural! You win!*****");
				}
				else
				{
					System.out.println("*****Rolled the point! You win!*****");
				}
			}
			else
			{
				userBalance -= userBet;
				currentLoseStreak++;
				currentWinStreak = 0;
				CMM.setMaxLosingStreak(currentLoseStreak);
				if (n == 2)
				{
					CMM.incrementCrapsCount();
					System.out.println("*****Craps! You lose!*****");
				}
				else
				{
					System.out.println("*****Crap out! You lose!*****");
				}
			}
			
			if (userBalance > 0)
			{
				System.out.println(userName + "'s balance: " + userBalance + ". Playing a new game...");
			}
			else
			{
				System.out.println(userName + "'s balance: " + userBalance);
			}
		}
		CMM.printStatics();
		CMM.reset();
	}
	
	
}
