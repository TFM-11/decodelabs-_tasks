import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Project 3: ATM Interface
 * Engineering a Resilient Financial System
 * Powered by DecodeLabs
 */

// Layer: Data Vault (Strict Encapsulation)
class BankAccount {
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Account opened with initial balance: $" + initialBalance);
    }

    // Getter: Read-only access
    public double getBalance() {
        return balance;
    }

    // Mutator: Security Checkpoint for Deposits
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("SUCCESS: $" + amount + " deposited successfully.");
        } else {
            System.out.println("ERROR: Deposit amount must be positive.");
        }
    }

    // Mutator: Security Checkpoint for Withdrawals (Business Rules)
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("ERROR: Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("ERROR: Insufficient funds. Current balance: $" + balance);
            return false;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: $" + amount);
        System.out.println("SUCCESS: $" + amount + " withdrawn successfully.");
        return true;
    }

    public void printHistory() {
        System.out.println("\n--- Transaction History ---");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}

// Layer: Customer Lobby (UI / Bridge)
class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    // Security Gate: Robust Input Validation
    private double getValidAmount() {
        while (true) {
            if (scanner.hasNextDouble()) {
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
                return amount;
            } else {
                System.out.println("ERROR: Invalid numeric input. Please enter a valid amount.");
                scanner.next(); // Clear invalid token
            }
        }
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Select an option (1-5): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    processWithdraw();
                    break;
                case "3":
                    processDeposit();
                    break;
                case "4":
                    userAccount.printHistory();
                    break;
                case "5":
                    System.out.println("Thank you for using DecodeLabs ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid choice. Please select 1-5.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n===========================================");
        System.out.println("           DECODELABS ATM MENU             ");
        System.out.println("===========================================");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Funds");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
        System.out.println("===========================================");
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", userAccount.getBalance());
    }

    private void processWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getValidAmount();
        userAccount.withdraw(amount);
    }

    private void processDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getValidAmount();
        userAccount.deposit(amount);
    }
}

// Operational Brain: Execution Layer
public class DecodeLabs_ATM_P3 {
    public static void main(String[] args) {
        System.out.println("Initializing DecodeLabs Financial Engine...");
        
        // Initializing with a starting balance of $1000
        BankAccount myAccount = new BankAccount(1000.00);
        ATM myATM = new ATM(myAccount);
        
        myATM.start();
    }
}
