import java.util.concurrent.Semaphore;
 
public class Chopstick {
	private int id;
	public Chopstick(int id) { this.id = id; }
 	public int getId() { return id; }

	// Constructor class for Semaphore that accepts the number permits
	public Semaphore mutex = new Semaphore(1);
	// We create a method that allows a philosopher to grab a chopstick
	public void grab()
	{
		try
		{
			// acquiring permit from the sephamore
			mutex.acquire();
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
	}

	// We create a method that allows a philosopher to release a chopstick
	public void release()
	{
		// this releases an acquired permit and increases the number of available permits by one (chopstick)
		mutex.release();
	}

	// Now we create a method that checks whether a chopstick is available or not
	public boolean isFree()
	{
		// returns true if number of available permits is greater than 0, and false otherwise
		return mutex.availablePermits() > 0;
	}
}
