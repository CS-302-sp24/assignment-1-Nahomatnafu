import java.util.concurrent.ThreadLocalRandom;


class Philosopher extends Thread
{
  public int number;
  Chopstick left, right;
  public int tt; //maximum thinking time in units of milliseconds
  public int et; //is the maximum eating time, in units of milliseconds.
  public int nc; //is the number of cycles that each philosopher will go through.

  public Philosopher(Chopstick left, Chopstick right, int tt, int et, int nc) 
  {  
    this.left = left; 
    this.right = right;
    this.tt = tt;
    this.et = et;
    this.nc = nc;
  }

  public void run() 
  {
    while(true)
    {
      // Philosopher thinking
      think();
      // We'd like the philosopher to grab the chopsticks if they're both free
      synchronized (left)
      {
        left.grab();
        System.out.println("Philospopher " + (number + 1) + " grabs left chopstick.");
      }
        synchronized (right)
        {
          right.grab();
          System.out.println("Philospopher " + (number + 1) + " grabs right chopstick.");
          eat();
          right.release();
          System.out.println("Philosopher " + (number + 1) + " releases right chopstick.");
        }
      

      
      // after eating the philosopher releases left and right chopsticks
      left.release();
      System.out.println("Philosopher " + (number + 1) + " releases left chopstick.");
      
    }
  }
      void think()
      {
        try 
        {
          int sleepTime = ThreadLocalRandom.current().nextInt(0, tt+1); // thinks for this range amount
          System.out.println("Philosopher " + (number + 1) + " thinks for  "+ sleepTime + "ms");
          Thread.sleep(sleepTime);
        }
        catch (Exception e)
        {
          e.printStackTrace(System.out);
        }
      }
      void eat()
      {
        try
        {
            // selecting a random number between 0 and 1000 representing sleep time in milliseconds
            int sleepTime = ThreadLocalRandom.current().nextInt(0, et + 1); // eats for this range
            // Thread sleeping
            System.out.println("Philosopher " + (number + 1) + " eats for "+ sleepTime + "ms"); 
            Thread.sleep(sleepTime);
        }
        catch (Exception e)
        {
          e.printStackTrace(System.out);
        }
      }
  

}

