import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            try (Scanner sc = new Scanner(System.in)) {
                int customerID, orderQty;
      
                System.out.print("Enter Customer ID: ");
                customerID = sc.nextInt();
                
                System.out.print("Enter How Much Food to Made: ");
                orderQty = sc.nextInt();
                
                Restaurant restoran = new Restaurant();
                Thread t1 = new Thread(restoran);
                Waiters waiter = new Waiters(customerID, orderQty);
                Thread t2 = new Thread(waiter);

                t1.start();
                t2.start();
                
                t1.join();
                t2.join();
            }

        } catch (Exception e) {
            System.out.println("The Input Must be Integer!");
        }
    }
}