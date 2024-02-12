import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;
  private int thinkCount;
  private int tt; //maximum thinking time in units of milliseconds
  private int et; //is the maximum eating time, in units of milliseconds.
  private int nc; //is the number of cycles that each philosopher will go through.

  public Philosopher(Chopstick left, Chopstick right, int tt, int et, int nc) {
    this.left = left; this.right = right;
    this.random = new Random();
    this.tt = tt;
    this.et = et;
    this.nc = nc;

  }

  public void run() {
    try {
      while(true) {
        ++thinkCount;
        if (thinkCount % 10 == 0)
          System.out.println("Philosopher " + this + " has thought " + thinkCount + " times");
        Thread.sleep(random.nextInt(tt)));     // Think for a while
        System.out.println("Philosopher " + this + " wants right chopstick");
        synchronized(right) {       // Grab right chopstick 
          System.out.println("Philosopher " + this + "has right chopstick");
          System.out.println("Philosopher " + this + "wants left chopstick");
          synchronized(left) {                    // Grab left chopstick 
            System.out.println("Philosopher " + this + "has left chopstick");
            System.out.println("Philosopher " + this + " eats for " + et + " units ");
            Thread.sleep(random.nextInt(et)); // Eat for a while

            // Release chopsticks
            System.out.println("Philosopher " + this + "releases left chopstick");
          }
          System.out.println("Philosopher " + this + "releases right chopstick");
        }
      }
    } catch(InterruptedException e) {}
  }
}
