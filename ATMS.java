
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMS {

    public static double balance = 5000.0;
    private static final int PIN = 1234;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ATM");
        System.out.print("Enter your PIN: ");
        int enteredPin;
        try {
            enteredPin = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid PIN input. Exiting...");
            scanner.close();
            return;
        }

        if (enteredPin != PIN) {
            System.out.println("Incorrect PIN. Exiting...");
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using ATM.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("Your balance is ₹" + balance);
        sendSMS("Your current balance is ₹" + balance);
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ₹");
        double amount;
        try {
            amount = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a number.");
            scanner.nextLine(); // clear invalid input
            return;
        }

        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Please collect your cash.");
            System.out.println("Remaining balance: ₹" + balance);
            sendSMS("₹" + amount + " withdrawn. Remaining balance ₹" + balance);
        }
    }

    private static void sendSMS(String message) {
        // Simulating SMS - Replace with Twilio or real SMS API if needed
        System.out.println("[SMS Sent]: " + message);
    }
}
