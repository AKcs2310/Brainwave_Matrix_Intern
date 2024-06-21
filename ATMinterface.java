import java.util.Scanner;

public class ATMinterface {
    public static class BankAccount {
        private double balance;
        private String pin;

        public BankAccount(double initialBalance, String pin) {
            this.balance = initialBalance;
            this.pin = pin;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            } else {
                System.out.println("Invalid deposit amount. Deposit failed.");
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            } else {
                System.out.println("Insufficient funds or invalid amount. Withdrawal failed.");
                return false;
            }
        }

        public boolean checkPin(String inputPin) {
            return this.pin.equals(inputPin);
        }
    }

    public static class ATM {
        private static final String FIXED_PIN = "140803";
        private static Scanner scanner = new Scanner(System.in);

        public static void startATM(BankAccount userAccount) {
            if (authenticate()) {
                int choice;
                do {
                    displayMenu();
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    handleChoice(userAccount, choice);
                } while (choice != 4);
                System.out.println("Exiting ATM. Thank you!");
            } else {
                System.out.println("Invalid PIN. Exiting ATM.");
            }
            scanner.close();
        }

        private static boolean authenticate() {
            System.out.print("Enter PIN: ");
            String inputPin = scanner.next();
            return FIXED_PIN.equals(inputPin);
        }

        private static void displayMenu() {
            System.out.println("\nATM Interface");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
        }

        private static void handleChoice(BankAccount userAccount, int choice) {
            switch (choice) {
                case 1:
                    checkBalance(userAccount);
                    break;
                case 2:
                    deposit(userAccount);
                    break;
                case 3:
                    withdraw(userAccount);
                    break;
                case 4:
                    // Exit handled in the loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        private static void checkBalance(BankAccount userAccount) {
            System.out.println("Your account balance: $" + userAccount.getBalance());
        }

        private static void deposit(BankAccount userAccount) {
            System.out.print("Enter deposit amount: $");
            double amount = scanner.nextDouble();
            userAccount.deposit(amount);
            System.out.println("Deposit successful. Your new balance: $" + userAccount.getBalance());
        }

        private static void withdraw(BankAccount userAccount) {
            System.out.print("Enter withdrawal amount: $");
            double amount = scanner.nextDouble();
            if (userAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful. Your new balance: $" + userAccount.getBalance());
            }
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000, "140803");
        ATM.startATM(userAccount);
    }
}
