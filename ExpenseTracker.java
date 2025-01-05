import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.out.println("\n--- Expense Tracker ---");
                System.out.println("1. Add Expense");
                System.out.println("2. View All Expenses");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addExpense(scanner);
                        break;
                    case 2:
                        viewAllExpenses();
                        break;
                    case 3:
                        System.out.println("Exiting... Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void addExpense(Scanner scanner) {
        try {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            if (description.isBlank()) {
                System.out.println("Description cannot be empty.");
                return;
            }

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return;
            }

            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            if (category.isBlank()) {
                System.out.println("Category cannot be empty.");
                return;
            }

            expenses.add(new Expense(description, amount, category));
            System.out.println("Expense added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
        }
    }

    private static void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
        } else {
            System.out.println("\n--- All Expenses ---");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }
}

class Expense {
    String description;
    double amount;
    String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Description: %s | Amount: %.2f | Category: %s", description, amount, category);
    }
}
