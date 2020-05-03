
public class Task {
	
	String name;
	String description;
	String date;
	boolean isComplete = false;
	
	public Task(String name, String description, String date) {
		this.name = name;
		this.description = description;
		this.date = date;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public boolean getComplete () {
		return isComplete;
	}
	
}
