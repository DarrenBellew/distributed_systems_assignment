import java.util.Timer;
import java.util.TimerTask;

public class Reminder  {

	Timer timer;
	String message;
	int seconds;


	public Reminder(int seconds, String message)  {
		this.seconds = seconds;
		this.message = message;
		
		this.Schedule();
	}

	public Reminder(int seconds)  {
		this(seconds, "Time's up!");
	}

	class ReminderTask extends TimerTask  {
		public void run()  {
			System.out.println(message);
			timer.cancel();
		}
	}

	private void schedule()  {
		timer = new Timer();
		timer.schedule(new ReminderTask(), seconds*1000);
	}

	public void reset()  {
		this.Schedule();
	}

	public static void main(String args[])  {
		new Reminder(5, "Task 1 ended");
		System.out.println("Task 1 scheduled");
	}
	
	
}