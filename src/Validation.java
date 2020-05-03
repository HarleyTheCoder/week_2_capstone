import java.util.List;
import java.util.Scanner;

public class Validation {
	
	//Make sure they enter a string with correct specifications that isn't empty
	public static String getRegexString(String regex, String errorMsg, Scanner scan) {
		while (true) {
			String input = scan.nextLine();
			if (input.isEmpty()) {
				System.out.println("Nothing was entered. Please try again.");
			} else if (!input.matches(regex)) {
				System.out.println(errorMsg);
			} else {
				return input;
			}

		}
		
	}
	
	//Make sure they enter an integer in a certain range
	public static int getIntInRange(int a, int b, Scanner scan) {
		boolean cont = true;
		while(cont) {
			try {
				int num = Integer.parseInt(scan.nextLine());
				if (num < a || num > b) {
					throw new IndexOutOfBoundsException();
				} else {
					return num;
				}
			} catch (IndexOutOfBoundsException ie) {
				System.out.println("Selection must be between " + a +
						" and " +  b + ". Please try again. (" + a + "-" + b + ")");
			} catch (NumberFormatException e) {
				System.out.println("Input must be a number (" + a + "-" + b + 
									"). Please try again.");
			}
		}
		return -1;
	}
	
	//Get a valid date
	//test this out right after
	public static String getValidDate(Scanner scan) {
		String dateRegex = "\\d{2}\\/\\d{2}\\/\\d{4}";
		String date = "";
		while (true) {
			date = Validation.getRegexString(dateRegex, 
					"Invalid format. Please try again. (mm/dd/yyyy)", scan);
			if (Validation.isValidDate(date)) {
				return date;
			} else {
				System.out.println("Invalid date. Please try again. (mm/dd/yyyy)");
			}
		}
	}
	
	//Check if it's a valid date
	//I think there was an easier way to do this but oh well
	public static boolean isValidDate(String date) {
		
		String[] sEntries = date.split("/");
		
		//The try catch ensures they entered numbers
		try {
			//convert each string to integer
			int[] iEntries = new int[sEntries.length];
			for (int i = 0; i < sEntries.length; i++) {
				iEntries[i] = Integer.parseInt(sEntries[i]);
			}
			//First check the month
			if(iEntries[0] < 1 || iEntries[0] > 12) {
				return false;
			} else { 
				//Check that the year is at least this year
				if (iEntries[2] < 2020) { 
					System.out.println("The year must be this year or later.");
					return false;
				} else {
					int maxDays = 0;
					//check day
					switch (iEntries[0]) { 
						case 1: maxDays = 31;
						break;
						case 2: maxDays = 28; //not gonna worry about checking for leap year
						break;				   //this time, but it is noted
						case 3: maxDays = 31;
						break;
						case 4: maxDays = 30;
						break;
						case 5: maxDays = 31;
						break;
						case 6: maxDays = 30;
						break;
						case 7: maxDays = 31;
						break;
						case 8: maxDays = 31;
						break;
						case 9: maxDays = 30;
						break;
						case 10: maxDays = 31;
						break;
						case 11: maxDays = 30;
						break;
						case 12: maxDays = 31;
						break;
						default : maxDays = -1;
						break;
					} 
					if (iEntries[1] < 1 || iEntries[1] > maxDays) {
						return false;
					} else {
						return true;
					}
				}
			}
			
		} catch (Exception e) {
			return false;
		}
	}
	
	
	//Check if they answered yes or no, ask again if not
	public static boolean isYesNo(Scanner scan) {
		String input = scan.nextLine().toUpperCase();
		while (true) {
			if (input.startsWith("Y")) {
				return true;
			} else if (input.startsWith("N")) {
				return false;
			} else {
				System.out.println("Invalid input. Please try again. (Y/N)");
				input = scan.nextLine().toUpperCase();
			}
		}
	}
	
	//Add test tasks at the start to make testing easie
	public static void addTestTasks(List<Task> tasks) {
		tasks.add(new Task("John Baker", "Organize the library.", "05/16/2020"));
		tasks.add(new Task("Jill Smith", "Do the thing.", "05/31/2020"));
		tasks.add(new Task("Angie Cooper", "Do that other thing.", "05/25/2020"));
	}
	
	
}
