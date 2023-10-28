import java.util.Random;

public class East_village implements Runnable {
   private int villager_number;

   public East_village(int villager_number) {
      this.villager_number = villager_number;
   }

   @Override
   public void run() {
      // Sleep for random time from 1 to 6 seconds
      Random rand = new Random();
      int randomNum = rand.nextInt(6) + 1;
      try {
         Thread.sleep(randomNum * 1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println("East villager " + this.villager_number + "is ready to cross the bridge");
      RoadController.enter_queue(Thread.currentThread());
   }
}
