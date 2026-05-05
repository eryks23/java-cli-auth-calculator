import java.util.Locale;
import java.util.Scanner;

public class SecureCalculator {
    public static void main(String[] args) {
        final String LOGIN = "admin";
        final long PASSWORD_HASH = 7974089; 
        
        int attempts = 3;
        boolean authenticated = false;
        
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("=== System Login ===");
        
        while (attempts > 0 && !authenticated) {
            System.out.print("Username: ");
            String inputLogin = scanner.next();
            
            System.out.print("Password: ");
            String inputPassword = scanner.next();

            if (inputLogin.equals(LOGIN) && simpleHash(inputPassword) == PASSWORD_HASH) {
                System.out.println("\nLogin successful! Welcome, " + LOGIN + ".\n");
                authenticated = true;

            } else {
                attempts--;

                if (attempts > 0) {
                    System.out.println("Invalid credentials. Attempts left: " + attempts + "\n");

                } else {
                    System.out.println("Access denied. System locked\n");
                    scanner.close();
                    return;
                }

            }
        }

        while (true) {
            System.out.println("=== Calculator Menu ===");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Exit");
            System.out.print("Selection: ");
            
            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("\nClosing application...");
                break;
            }

            if (choice < 1 || choice > 4) {
                System.out.println("\nInvalid selection. Try again!\n");
                continue;
            }

            System.out.print("\nEnter first number: ");
            double num1 = scanner.nextDouble();
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            System.out.print("\nResult: ");
            switch (choice) {
                case 1:
                    System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
                    break;
                case 2:
                    System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
                    break;
                case 3:
                    System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
                    break;
                case 4:
                    if (num2 != 0) {
                        System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
                    } else {
                        System.out.println("Error: Division by zero!");
                    }
                    break;
            }
            System.out.println("-----------------------\n");
        }
        
        scanner.close();
    }

    private static long simpleHash(String input) {
        long hash = 7;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * 31 + input.charAt(i);
        }
        return hash;
    }
}