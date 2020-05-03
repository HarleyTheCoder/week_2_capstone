import java.util.List;
import java.util.Scanner;

public class Manager {

	
	//Brings together showing the menu and selecting the task
	public static void showAndDo (List<Task> tasks, Scanner scan) {
		Manager.showMenu();
		System.out.println(""); 
		int task = Manager.selectOption(scan);
		Manager.doOption(tasks, task, scan);
	}
	
	//Display the menu
	public static void showMenu() {
		System.out.println("Menu\n------------------" +
					"\n1. List tasks\n2. Add task\n3. Delete task" +
					"\n4. Mark task complete\n5. Quit");
	}
	
	//Show a specific task
	public static void showTask(List<Task> tasks, int taskNum) {
		System.out.println("\n\tTask " + (taskNum + 1));
		System.out.println(String.format("%-15s", "\t").replace(" ", "-"));
		System.out.println("\tTeam Member: " + tasks.get(taskNum).getName());
		System.out.println("\tDue date: " + tasks.get(taskNum).getDate());
		System.out.print("\tTask finished?: ");
		if (tasks.get(taskNum).getComplete()) {
			System.out.print("Yes");
		} else {
			System.out.print("No");
		}
		System.out.println("\n\tDescription: " + tasks.get(taskNum).getDescription());
	}
	
	//Get selection from user based on menu, return to compare
	public static int selectOption(Scanner scan) {
		int num = 0;
		System.out.println("Please select a menu option. (1-5)");
		num = Validation.getIntInRange(1, 5, scan);
		return num;
	}
		
	//Call method based of selected task
	public static void doOption(List<Task> tasks, int taskNum, Scanner scan) {
		switch (taskNum) {
		case 1: Manager.listTasks(tasks, scan);
		break;
		case 2: addTasks(tasks, scan);
		break;
		case 3: deleteTasks(tasks, scan);
		break;
		case 4: markTasksComplete(tasks, scan);
		break;
		case 5: quit(tasks, scan);
		break;
		
		}
	}
	
	//List tasks
	public static void listTasks(List<Task> tasks, Scanner scan) {
		System.out.println("LIST TASKS");
		if (tasks.size() == 0) {
			System.out.println("There are currently no tasks.\n");
		} else {
			for (int i = 0; i < tasks.size(); i++) {
				showTask(tasks, i);
			}
		}
		Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
		Manager.showAndDo(tasks, scan);
	}
	
	//Add tasks
	public static void addTasks(List<Task> tasks, Scanner scan) {
		while (true) {
			String nameRegex = "[-A-Za-z]{2,20}\\s[-A-Za-z]{2,20}";
			String name;
			String dueDate;
			String description;
			
			System.out.println("ADD TASKS");
			
			System.out.println("\nPlease enter your first and last name. (Ex. Joe Smith)");
			name = Validation.getRegexString(nameRegex, 
					"Invalid answer. Please enter both first and last name.", scan);
			
			System.out.println("Please enter the due date. (mm/dd/yyyy)");
			dueDate = Validation.getValidDate(scan);
			
			System.out.println("Please enter a description of the task.");
			description = Validation.getRegexString(".*", //just to check that the string isn't empty
					"Something went wrong. Please try again.", scan); 
			
			//add task
			tasks.add(new Task(name, description, dueDate));
			
			//Return to menu
			System.out.println("\nTask added!");
			Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
			Manager.showAndDo(tasks, scan);
		}
	}
	
	//Delete tasks
	public static void deleteTasks(List<Task> tasks, Scanner scan) {
		int num = 0;
		boolean showedTasks = false;
		System.out.println("DELETE TASK");
		System.out.println("\nNumber of tasks: " + tasks.size());
		
		//I just figure it will be more convenient for the user if this is asked
		System.out.println("\nYou must enter a task's number to delete it.");
		System.out.println("Would you first like to see the tasks? (Y/N)");
		if (Validation.isYesNo(scan)) {
			for (int i = 0; i < tasks.size(); i++) {
				Manager.showTask(tasks, i);
			} 
			showedTasks = true;
		}
		
		//Ask for task number
		System.out.println("\nWhich task would you like to delete?\n(Enter \"0\" to cancel.)");
		num = Validation.getIntInRange(0, tasks.size() + 1, scan);
		//Gives option to cancel request
		if (num == 0) {
			System.out.println("Cancelling request.");
			Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
			Manager.showAndDo(tasks, scan);
			
		//Retrieve task, ask to delete
		} else {
			num -= 1; //to make it match the arraylist correctly
			
			//Program shows selected task IF it did not just display all the tasks to
			//the user. If so, it's kind of annoying.
			//Either way, the task gets shown to user before deletion, as required.
			if (!showedTasks) {
				Manager.showTask(tasks, num); 
			}
			//Verify deletion
			if (showedTasks) {
				System.out.println("\nAre you sure you want to delete Task " + 
									(num + 1) + "? (Y/N)");
			} else {
				System.out.println("\nAre you sure you want to delete this task? (Y/N)");
			}
			if (Validation.isYesNo(scan)) {
				tasks.remove(num);
				System.out.println("\nTask removed!");
				Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
				Manager.showAndDo(tasks, scan);
			} else {
				System.out.println("\nCancelling request.");
				Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
				Manager.showAndDo(tasks, scan);
			}
		}
		
	}
	
	//Mark task as complete (using same model as delete tasks)
		public static void markTasksComplete(List<Task> tasks, Scanner scan) {
			int num = 0;
			boolean showedTasks = false;
			System.out.println("MARK TASK COMPLETE");
			System.out.println("\nNumber of tasks: " + tasks.size());
			
			//I just figure it will be more convenient for the user if this is asked
			System.out.println("\nYou must enter a task's number to mark it as complete.");
			System.out.println("Would you first like to see the tasks? (Y/N)");
			if (Validation.isYesNo(scan)) {
				for (int i = 0; i < tasks.size(); i++) {
					Manager.showTask(tasks, i);
				} 
				showedTasks = true;
			}
			
			//Ask for task number
			System.out.println("\nWhich task would you like to mark as complete?\n(Enter \"0\" to cancel.)");
			num = Validation.getIntInRange(0, tasks.size() + 1, scan);
			//Gives option to cancel request
			if (num == 0) {
				System.out.println("Cancelling request.");
				Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
				Manager.showAndDo(tasks, scan);
				
			//Retrieve task, verify they want to mark it complete
			} else {
				num -= 1; //to make it match the arraylist correctly
				
				//Program shows selected task IF it did not just display all the tasks to
				//the user. If so, it's kind of annoying.
				//Either way, the task gets shown to user before marking it, as required.
				if (!showedTasks) {
					Manager.showTask(tasks, num); 
				}
				//Verify marking
				if (showedTasks) {
					System.out.println("\nAre you sure you want to mark Task " + 
										(num + 1) + " as complete? (Y/N");
				} else {
					System.out.println("\nAre you sure you want to mark this task as complete? (Y/N)");
				}
				if (Validation.isYesNo(scan)) {
					tasks.get(num).setComplete(true);
					System.out.println("\nTask is complete!");
					Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
					Manager.showAndDo(tasks, scan);
				} else {
					System.out.println("\nCancelling request.");
					Manager.pause("\n(Hit \"enter\" to return to the menu.)", scan);
					Manager.showAndDo(tasks, scan);
				}
			}
			
		}
	
	
	//Quit the program
	public static void quit(List<Task> tasks, Scanner scan) {
		System.out.println("QUIT");
		System.out.println("\nAre you sure you want to quit? (Y/N)");
		boolean quitProgram = Validation.isYesNo(scan);
		//String filepath = ""; //Try storing objects into file and reading later
		if (quitProgram) {
			//Add code to save file
			System.out.println("\nHave a great day!");
			System.exit(0);
		} else {
			Manager.showAndDo(tasks, scan);
		}
	}
	
	//Makes the program pause until the user hits enter
	public static void pause(String message, Scanner scan) {
		System.out.println(message);
		scan.nextLine();
	}
	
}
	
