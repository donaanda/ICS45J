//Dona Anda 29856735
//Jessica Kim 54875433

import java.util.Scanner;

public class Lab2
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String response = "y";
		while (true)
		{
			if (response.compareTo("y") == 0)
			{
				CrapsSimulation CS = new CrapsSimulation();
				CS.start();
			}
			else if (response.compareTo("n") == 0)
			{
				break;
			}
			System.out.print("Replay? Enter 'y' or 'n': ");
			response = in.nextLine();
		}
		in.close();
	}
}
