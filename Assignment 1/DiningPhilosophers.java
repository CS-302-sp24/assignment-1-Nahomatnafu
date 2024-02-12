public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {
    int np = Integer.parseInt(args[0]);
    int nc = Integer.parseInt(args[1]);
    int tt = Integer.parseInt(args[2]);
    int et = Integer.parseInt(args[3]);
    int rl = Integer.parseInt(args[4]);

    Philosopher[] philosophers = new Philosopher[np];
    Chopstick[] chopsticks = new Chopstick[np+1];
    
    for (int i = 0; i < np; ++i)
    {
      chopsticks[i] = new Chopstick(i);
    }

    for (int i = 0; i < np; ++i)
    {
      Chopstick leftChopstick = chopsticks[i];
      Chopstick rightChopstick = chopsticks[(i + 1) % np];

      if (rl == 1 && i % 2 == 1) 
      {
        // odd-numbered philosophers are  left-handed
        chopsticks[i] = rightChopstick;
        chopsticks[(i+1) % np] = leftChopstick;
        leftChopstick = chopsticks[i];
        rightChopstick = chopsticks[(i+1) % np];
      }

      philosophers[i] = new Philosopher(leftChopstick, rightChopstick, tt, et, nc);
      philosophers[i].start();
    }
    for (int i = 0; i < np; ++i)
      philosophers[i].join();
  }
}
