
public class Restaurant implements Runnable {
    private boolean waitingForPickup = false;
    private static final Object lock = new Object();
    private static int FoodNumber = 1;
    
    @Override
    public void run() {
        while (true) {
            makeFood();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    }
    public void setWaitingForPickup(boolean waitingForPickup) {
        this.waitingForPickup = waitingForPickup;
    }
    
    public static int getfoodNumber() {
        return FoodNumber;
    }
    public void makeFood() {
        synchronized (Restaurant.lock) {

            if (this.waitingForPickup) {
                try {
                    System.out.println("Restaurant: Waiting for the Waiter to deliver the food.");
                    Restaurant.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            waitingForPickup = true;

            System.out.println("Restaurant: Making Food no." + FoodNumber++);
            Restaurant.lock.notifyAll();

            System.out.println("Restaurant: Telling the waiter to get the food.\n");
        }
    }
    public static Object getLock() {
        return lock;
    }
}