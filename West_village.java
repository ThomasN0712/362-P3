import java.util.Random;

public class West_village implements Runnable {
   private int villager_number;

   public West_village(int villager_number) {
      this.villager_number = villager_number;
   }

   public String get_villager_identity() {
      return "West villager " + this.villager_number;
   }

   @Override
   public void run() {
      // Sleep for random time from 1 to 3 seconds
      Random rand = new Random();
      int randomNum = rand.nextInt(30) + 1;
      try {
         Thread.sleep(randomNum * 100);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println(this.get_villager_identity() + " wake up after " + randomNum * 100
            + "ms nap. They are ready to cross the road now. Entering queue.");
      RoadController.enter_queue(Thread.currentThread());
      RoadController.enter_identity(get_villager_identity());
   }
}
