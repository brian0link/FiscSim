import java.util.Random;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FiscSim {
	public double FindKelly(double W, double R)
	{
		//Kelly =  W – ((1 – W) / R)
		double num = 1 - W;
		double fraction = num/R;
		double output = W - fraction;
		
		if(output > 1) {
			output = 1;
		} else if(output < 0) {
			output = 0;
		}
		
		return output;
	}
	
	public boolean CheckWinOrLose(double indLossRate)
	{
		Random rand = new Random();
		double Number = (double)rand.nextInt(100);
		
		if(Number > indLossRate*100){
			return true;
		} else {
			return false;
		}
	}
	
    public static void main(String args[])
	{
		FiscSim program = new FiscSim();
		program.start();
    }
	
	public void start()
	{
		DecimalFormat df = new DecimalFormat("#.00");
		
		double dLossAmount = 0.4;			// How much you keep in a loss in %
		double dWinAmount = 0.35;			// How much you gain in a win in %
		
		double dLossRate = 0.25;			// Percentage of the time you lose
		double dWinRate = 1 - dLossRate;	//Percentage of the time you win
		
		double dRatio = dWinAmount / dLossAmount;
		double dKelly = FindKelly(dWinRate, dRatio);
		
		double dPercentToKeep = dKelly;
		double dPercentFromKeep = 1 - dKelly;
		
		double dWealth = 1500;
		double dInvest = dWealth * dKelly;
		double dKeep = dWealth - dInvest;
		
		double dChange = 0;	//Profit or loss
		
		int iNumLoss = 0;
		int iNumWin = 0;
		
		int abc = 0;
		
		try {
			PrintStream PrintOut = new PrintStream(new FileOutputStream("C:\\projects\\OutFile.txt"));
		
			System.out.println("Ratio: " + df.format(dRatio) + " Kelly: " + dKelly);
					
			System.out.println("Invest: " + df.format(dInvest) + "\tKeep: " + df.format(dKeep) + 
				"\tChange: " + df.format(dChange) + "\t " + abc);
				
			PrintOut.println("Invest: " + "\tKeep:" + "\tChange:" + "\t" + 
				"\tRatio: " + df.format(dRatio) + "\tKelly: " + dKelly);
		
		
		
		
		
		
				// BEGIN MULTITEST
				double dMaxKeep = 0;
				double dMinKeep = 0;
				for(int j = 0; j < 100; j++){
					dInvest = dWealth * dKelly;
					dKeep = dWealth - dInvest;
					
					
					
					
					
			for(int i = 0; i < 52; i++){
				if(CheckWinOrLose(dLossRate)) {
					abc = 5;
					dChange = dWinAmount * dInvest;
					dInvest += dKelly * dChange;
					dKeep += (1 - dKelly) * dChange;
					iNumWin++;
				} else {
					abc = -5;
					dChange = dLossAmount * dInvest;
					dInvest -= dKelly * dChange;
					dKeep -= (1 - dKelly) * dChange;
					iNumLoss++;
				}
				
				PrintOut.println("" + df.format(dInvest) + "\t" + df.format(dKeep) + 
					"\t" + df.format(dChange) + "\t " + abc);
				
				System.out.println("Invest: " + df.format(dInvest) + "\tKeep: " + df.format(dKeep) + 
					"\tChange: " + df.format(dChange) + "\t " + abc);
					
					
					
					
					
					
			}
			int iNumTrades = iNumLoss + iNumWin;
			System.out.println("True Loss Percentage: " + (double)iNumLoss/iNumTrades);
			PrintOut.println("True Loss Percentage: " + (double)iNumLoss/iNumTrades);
			
			
			
			
			
			
					if(j == 0)
					{
						dMaxKeep = dKeep;
						dMinKeep = dKeep;
					}
					if(dMaxKeep < dKeep)
					{
						dMaxKeep = dKeep;
					}
					else if(dMinKeep > dKeep)
					{
						dMinKeep = dKeep;
					}
			

				}	// END MULTI TEST
				System.out.println("Max Keep: " + df.format(dMaxKeep) + "\tMin Keep: " + df.format(dMinKeep));
				PrintOut.println("Max Keep: " + df.format(dMaxKeep) + "\tMin Keep: " + df.format(dMinKeep));
				
				
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}