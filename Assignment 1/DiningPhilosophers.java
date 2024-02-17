public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {
    int np = Integer.parseInt(args[0]); // Number of philosophers
    int nc = Integer.parseInt(args[1]); // Number of cycles
    int tt = Integer.parseInt(args[2]); // Max thinking time
    int et = Integer.parseInt(args[3]); // Max eating time
    int rl = Integer.parseInt(args[4]); 
    // If 0 all philosophers are right-handed
    //and try to grab their right chopstick first.
    //If *rl* is 1, then even-numbered philosophers are right-handed and 
    //odd-numbered philosophers are left-handed.

    Philosopher[] philosophers = new Philosopher[np];
    Chopstick[] chopsticks = new Chopstick[np];
    
    for (int i = 0; i < np; i++)
    {
      chopsticks[i] = new Chopstick(i);
    }

    for (int i = 0; i < np; i++)
    {
      Chopstick leftChopstick = chopsticks[i];
      Chopstick rightChopstick = chopsticks[(i + 1) % np];

      if (rl == 1 && i % 2 == 1) 
      {
        // odd-numbered philosopher
        chopsticks[i] = rightChopstick;
        chopsticks[(i+1) % np] = leftChopstick;
        leftChopstick = chopsticks[i];
        rightChopstick = chopsticks[(i+1) % np];
      }

      philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i+1) % np], tt, et, nc);
      philosophers[i].start();
    }

    while (true)
    {
      try
      {
        // sleep
        Thread.sleep(1000);
        
        // check for deadlock
        boolean deadlock = true;
        for (Chopstick c : chopsticks)
        {
          if (c.isFree())
          {
            deadlock = false;
            break;
          }
        }
      
          if (deadlock)
          {
            Thread.sleep(1000);
            System.out.println("Everyone Eats");
            break; // Exit loop if deadlock is detected
          }
        }
      
         
      catch(Exception e)
      {
        e.printStackTrace(System.out);
      }
    }   
}
    
}
