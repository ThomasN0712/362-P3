
/**
 * RoadController.java
 *
 *
 */

import java.util.ArrayList;

public class RoadController {
   // Create a queue of threads
   static ArrayList<Thread> thread_queue = new ArrayList<Thread>();

   public static void enter_queue(Thread T) {
      thread_queue.add(T);
   }

   public static void main(String[] args) {
      // Initilize east and west villagers
      for (int i = 1; i <= 3; i++) {
         Thread east_villager = new Thread(new East_village(i));
         Thread west_villager = new Thread(new West_village(i));
         east_villager.start();
         west_villager.start();
         try {
            east_villager.join();
            west_villager.join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      // Wait for all threads to finish
      System.out.println(thread_queue);

   }

}
