
/**
 * RoadController.java
 *
 *
 */

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.Random;

public class RoadController {
   // Create a queue of threads
   static ArrayList<Thread> thread_queue = new ArrayList<>();

   static ArrayList<String> villager_identity = new ArrayList<>();

   static ReentrantLock road = new ReentrantLock();

   static Condition condition = road.newCondition();

   public static void enter_identity(String identity) {
      villager_identity.add(identity);
   }

   public static void enter_queue(Thread T) {
      thread_queue.add(T);
   }

   public static void enter_road(Thread T, String identity) {
      String[] actions = { " is watching the sunset for ", " is doing 326 Project 3 for ",
            " is Netflix and waiting for ", " is happy happy happy (Jumping cat meme) for " };
      int action = new Random().nextInt(actions.length);
      // if road is not locked, open to use
      Random rand = new Random();
      // walks on the road for like 3 seconds
      int randomNum = rand.nextInt(3) + 1;
      try {
         System.out.println(identity + actions[action] + randomNum + " seconds");
         Thread.sleep(randomNum * 1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println(identity + " is done with the road. They are leaving the road now.");
      road.unlock();
   }

   public static void test(Thread T) {
      // if road is not locked, open to use
      if (road.isLocked() == false) {
         road.lock();
         System.out.println(villager_identity.get(0) + " is traveling on the road.");
         enter_road(thread_queue.get(0), villager_identity.get(0));
         thread_queue.remove(0);
         villager_identity.remove(0);
      }
      if (road.isLocked() == true) {
         try {
            condition.await();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         System.out.println("Road is locked. " + villager_identity.get(0) + " is waiting.");
         test(T);
      }
   }

   public static void main(String[] args) {
      // Create threads
      Thread east_village_1 = new Thread(new East_village(1));
      Thread west_village_1 = new Thread(new West_village(1));
      Thread east_village_2 = new Thread(new East_village(2));
      Thread west_village_2 = new Thread(new West_village(2));
      Thread east_village_3 = new Thread(new East_village(3));
      Thread west_village_3 = new Thread(new West_village(3));
      Thread east_village_4 = new Thread(new East_village(4));
      Thread west_village_4 = new Thread(new West_village(4));

      // Start threads
      east_village_1.start();
      west_village_1.start();
      east_village_2.start();
      west_village_2.start();
      east_village_3.start();
      west_village_3.start();
      east_village_4.start();
      west_village_4.start();

      // Wait for threads to finish
      try {
         east_village_1.join();
         west_village_1.join();
         east_village_2.join();
         west_village_2.join();
         east_village_3.join();
         west_village_3.join();
         east_village_4.join();
         west_village_4.join();
      } catch (InterruptedException e) {
         System.out.println("Interrupted");
      }

      while (thread_queue.size() > 0) {
         test(thread_queue.get(0));
      }
   }
}
