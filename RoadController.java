/**
 * RoadController.java
 *
 *
 */

public class RoadController
{  
   public static void main(String[] args)
   {
      // Initilize east and west villagers
   for (int i = 1; i <= 3; i++)
   {
      Thread east_villager = new Thread(new East_village(i));
      Thread west_villager = new Thread(new West_village(i));
      east_villager.start();
      west_villager.start();
   }

   // public RoadController()
   // {
   //    // initialize the queue


   // }
   // public void enter_queue(Thread t)
   // {
   //    // add t to the queue
   // }
   }
     
}
