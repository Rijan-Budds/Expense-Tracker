import java.util.ArrayList;
import java.util.Scanner;

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

public class ExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Filter Expenses by Category");
            System.out.println("4. Calculate Total Expenses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewAllExpenses();
                    break;
                case 3:
                    filterByCategory(scanner);
                    break;
                case 4:
                    calculateTotalExpenses();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter category (e.g., Food, Travel, etc.): ");
        String category = scanner.nextLine();

        expenses.add(new Expense(description, amount, category));
        System.out.println("Expense added successfully!");
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

    private static void filterByCategory(Scanner scanner) {
        System.out.print("Enter category to filter by: ");
        String category = scanner.nextLine();

        boolean found = false;
        System.out.println("\n--- Expenses in Category: " + category + " ---");
        for (Expense expense : expenses) {
            if (expense.category.equalsIgnoreCase(category)) {
                System.out.println(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in this category.");
        }
    }

    private static void calculateTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        System.out.println("Total Expenses: " + total);
    }
}
