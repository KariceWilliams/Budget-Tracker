import java.util.*;

public class BudgetTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Double>> expenseLog = new HashMap<>();

        //basic inputs name, income, and then budget estimate
        System.out.print("Hello! What's your name?: ");
        String name = scanner.nextLine();
        System.out.println("Welcome, " + name + "! Let's start budgeting.");

        System.out.print("Enter your yearly income: ");
        double yearlyIncome = scanner.nextDouble();
        scanner.nextLine();

        double monthlyPaycheck = yearlyIncome / 12;
        double budget = monthlyPaycheck;
        System.out.println("Estimated monthly paycheck: $" + monthlyPaycheck);
        System.out.println("Starting budget: $" + budget);

        boolean tracking = true;

        while (tracking) {
            System.out.println("\nCurrent balance: $" + budget);
            System.out.print("Choose: (expense / view / next / exit): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            //Where the money is being organized into
            switch (choice) {
                case "expense":
                    System.out.print("Category: ");
                    String category = scanner.nextLine().trim().toLowerCase();
                    //amount estimate after 
                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    //just checks to see if funds are greater than the given amount
                    if (amount > budget) {
                        System.out.println("Not enough funds.");
                    } else {
                        budget -= amount;
                        expenseLog.putIfAbsent(category, new ArrayList<>());
                        expenseLog.get(category).add(amount);
                        System.out.println("Recorded under '" + category + "'.");
                    }
                    break;

                    //history breakdown
                case "view":
                    System.out.println("\n--- Expenses ---");
                    for (String cat : expenseLog.keySet()) {
                        double total = expenseLog.get(cat).stream().mapToDouble(Double::doubleValue).sum();
                        System.out.println(cat + ": $" + total);
                    }
                    break;
                    //estimate of paycheck extracted from yearly income as well as the next month and what carries on after that
                case "next":
                    budget += monthlyPaycheck;
                    System.out.println("New month. Paycheck added.");
                    break;
                    
                    //thank you + offical balance 
                case "exit":
                    tracking = false;
                    System.out.println("Thanks for budgeting with us today, " + name + "!");
                    System.out.println("Your final balance is: $" + budget);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
																																																																																																																																																																																																							