//Dona Anda 29856735
//Jessica Kim 54875433

public class CrapsMetricsMonitor
{
	private int gamePlayed;
	private int gamesWon;
	private int maxRollsInSingleGame;
	private int naturalCount;
	private int crapsCount;
	private int maxWinningStreak;
	private int maxLosingStreak;
	private int MaxBalance;
	private int gameNumber;
	
	public CrapsMetricsMonitor()
	{
		gamePlayed = 0;
		gamesWon = 0;
		maxRollsInSingleGame = 0;
		naturalCount = 0;
		crapsCount = 0;
		maxWinningStreak = 0;
		maxLosingStreak = 0;
		MaxBalance = 0;
		gameNumber = 0;
	}
	
	public void printStatics()
	{
		System.out.print("\n" + "*****************************" + "\n"
						 + "*** SIMULATION STATISTICS ***" + "\n"
						 + "*****************************" + "\n"
						 + "Games Played: " + gamePlayed + "\n"
						 + "Games won: " + gamesWon + "\n"
						 + "Games lost: " + (gamePlayed - gamesWon) + "\n"
						 + "Maximum Rolls in a single game: " + maxRollsInSingleGame + "\n"
						 + "Natural Count: " + naturalCount + "\n"
						 + "Craps Count: " + crapsCount + "\n"
						 + "Maximum Winning Streak: " + maxWinningStreak + "\n"
						 + "Maximum Losing Streak: " + maxLosingStreak + "\n"
						 + "Maximum balance: " + MaxBalance + " during game " + gameNumber + "\n");
	}
	
	public void reset()
	{
		this.gamePlayed = 0;
		this.gamesWon = 0;
		this.maxRollsInSingleGame = 0;
		this.naturalCount = 0;
		this.crapsCount = 0;
		this.maxWinningStreak = 0;
		this.maxLosingStreak = 0;
		this.MaxBalance = 0;
		this.gameNumber = 0;
	}
	
	public void incrementGamePlayed()
	{
		this.gamePlayed++;
	}
	
	public void incrementGamesWon()
	{
		this.gamesWon++;
	}
	
	public void setMaxRollsInSingleGame(int maxRollsInSingleGame)
	{
		if (this.maxRollsInSingleGame < maxRollsInSingleGame)
		{
			this.maxRollsInSingleGame = maxRollsInSingleGame;
		}
	}
	
	public void incrementNaturalCount()
	{
		this.naturalCount++;
	}
	
	public void incrementCrapsCount()
	{
		this.crapsCount++;
	}
	
	public void setMaxWinningStreak(int maxWinningStreak)
	{
		if (this.maxWinningStreak < maxWinningStreak)
		{
			this.maxWinningStreak = maxWinningStreak;
		}
	}

	public void setMaxLosingStreak(int maxLosingStreak)
	{
		if (this.maxLosingStreak < maxLosingStreak)
		{
			this.maxLosingStreak = maxLosingStreak;
		}
	}
	
	public void setGameNumberWhenMaxBalance(int MaxBalance)
	{
		if (this.MaxBalance < MaxBalance)
		{
			this.MaxBalance = MaxBalance;
			this.gameNumber = this.gamePlayed;
		}
	}
}
