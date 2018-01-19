//Dona Anda 29856735
//Jessica Kim 54875433

import java.util.Random;

public class CrapsGame
{
	private int n;
	private int numOfRolls;
	private Random dice;
	
	public CrapsGame(CrapsMetricsMonitor monitor)
	{
		n = 0;
		numOfRolls = 0;
		dice = new Random();
	}
	
	public int getGameStatus()
	{
		return n;
	}
	
	public void resetNumOfRolls()
	{
		numOfRolls = 0;
	}
	
	public int getNumOfRolls()
	{
		return numOfRolls;
	}
	
	public boolean playGame()
	{
		boolean running = true;
		while (running == true)
		{
			int dice1 = dice.nextInt(6) + 1;
			int dice2 = dice.nextInt(6) + 1;
			int total = dice1+dice2;
			numOfRolls++;
			System.out.printf("Rolled a %d\n", total);
	
			if (total == 7 || total == 11)
			{
				running = false;
				n = 1;
			}
			else if (total == 2 || total == 3 || total == 12)
			{
				running = false;
				n = 2;
				return false;
			}
			else
			{
				int point = total;
				while (running == true) 
				{
					dice1 = dice.nextInt(6) + 1;
					dice2 = dice.nextInt(6) + 1;
					total = dice1+dice2;
					numOfRolls++;
					System.out.printf("Rolled a %d\n", total);
					
					if (total == point)
					{
						running = false;
						n = 3;
					}
					else if (total == 7)
					{
						running = false;
						n = 4;
						return false;
					}			
				}
			}
		}
		return true;
	}
}
