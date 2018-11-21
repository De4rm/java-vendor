import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Money {

	static MoneyT[] moneyDict;
	
	static Float doNotHaveMoney = (float)0.0;
	
	static Float moneyIn=(float)0;
	
	
	//Class constructor
	// get attribute path to that 
	//show where we store data about
	//what kind of moneds we use and 
	//quantity of them
	Money(String path){
		
		try {
			createMoneyDictionary(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//method that help to read file from the path
	// after that all data is stored in HashMap moneyDict
	private static void createMoneyDictionary(String path) throws IOException {
		
		FileManager monedsQuantity = new FileManager(path);
		moneyDict = FileManager.readMoney();
	}
	
	
	
	public static void insertMoney(Float inputMoney) {
		
		int index = getIndexOfMoneyType(String.valueOf(inputMoney));
		
		if (index != -1) {
			moneyDict[index].quantity ++;
			moneyIn += inputMoney;
		}
		
	}
	
	private static int getIndexOfMoneyType(String moneyString) {
		for(int i=0;i<moneyDict.length;i++) {
			if (moneyString.equals(moneyDict[i].moneyT)) {
				return i;
			}
		}
		return -1;
	}
	
	public static String getMoneyOut() {
		Float[] moneyType = { (float)1.0, (float)0.5, (float)0.2, 
					(float)0.1, (float)0.05 };
		int[] quantity = { 0, 0, 0, 0, 0};
		quantity = quantityCalc(moneyIn, moneyType, quantity);
		String answer ="";
		for (int i=0;i<moneyType.length;i++) {
			if (quantity[i]>0) {
				answer += String.valueOf(quantity[i]) + "x" + String.valueOf(moneyType[i]);
				answer += " ";
			}
		}
		
		if (doNotHaveMoney > 0) {
			answer += String.valueOf(doNotHaveMoney);
		}
		
		return answer;
		
	}
	
	
	
	
	public static int[] quantityCalc(Float moneyIn, Float[] moneyType, int[] quantity) {
		
		
		for(int i=0;i<moneyType.length;i++) {
			Float currentMoneyType = moneyType[i];
			int ind = getIndexOfMoneyType(String.valueOf(currentMoneyType));
			
			if ((moneyIn != 0) || (moneyIn >= (float) 0.05)){
				if ((moneyIn >= currentMoneyType) && (moneyDict[ind].quantity>0)) {
					
					int monedToReturn = (int) (moneyIn / currentMoneyType);
					
					DecimalFormat df = new DecimalFormat("#.##");
					
					if (moneyDict[ind].quantity >= monedToReturn) {
						moneyDict[ind].quantity -= monedToReturn;
						quantity[i] = monedToReturn;
						System.out.println("1: " + (currentMoneyType * monedToReturn));
						System.out.println("2: " + moneyIn);
						 
						moneyIn -= Float.valueOf(df.format((currentMoneyType * monedToReturn)));
						System.out.println("3: " + moneyIn);
					} else {
						quantity[i] = moneyDict[ind].quantity;
						moneyIn -= Float.valueOf(df.format((currentMoneyType * quantity[i])));
						moneyDict[ind].quantity = 0;
					}	
				}
			
			} 
		}
		
		if (moneyIn>0)
		{
			doNotHaveMoney = moneyIn;
			System.out.println("4: " + doNotHaveMoney);
		}
		
		return quantity;
	}
	
	
	
	
	public static boolean CheckMoned(Float moned) {
		
		String[] range_moneds = {"1.0","0.5","0.2","0.1","0.05"};
		for(int i=0;i<range_moneds.length;i++) {
			if (Float.parseFloat(range_moneds[i]) == moned) {
				return true;
			}
		}
		
		return false;
	}
	
	
}




