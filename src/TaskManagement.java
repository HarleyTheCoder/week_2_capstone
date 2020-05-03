import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagement {
	
	//I have this program so it displays a little differently
	//than in the example on the homework sheet.
	
	//I don't know if this is ok or if it will get points knocked
	//off. I'll do it exact next time, if so.

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		List<Task> tasks = new ArrayList<>();
		Validation.addTestTasks(tasks); //testing purposes,
										//creates and adds initial tasks to list
		Manager.showAndDo(tasks, scan);
		
		
		
		
	}

}
