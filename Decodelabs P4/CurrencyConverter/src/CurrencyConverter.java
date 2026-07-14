import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    // Using LinkedHashMap to maintain insertion order for menu display
    private static final Map<String, BigDecimal> EXCHANGE_RATES_TO_USD = new LinkedHashMap<>();

    static {
        // Exchange rates relative to 1 USD
        EXCHANGE_RATES_TO_USD.put("USD", BigDecimal.ONE);
        EXCHANGE_RATES_TO_USD.put("EUR", new BigDecimal("0.92")); // 1 USD = 0.92 EUR
        EXCHANGE_RATES_TO_USD.put("GBP", new BigDecimal("0.79")); // 1 USD = 0.79 GBP
        EXCHANGE_RATES_TO_USD.put("JPY", new BigDecimal("156.00")); // 1 USD = 156.00 JPY
        EXCHANGE_RATES_TO_USD.put("AUD", new BigDecimal("1.50")); // 1 USD = 1.50 AUD
        EXCHANGE_RATES_TO_USD.put("INR", new BigDecimal("83.50")); // 1 USD = 83.50 INR
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Industrial Training Kit Currency Converter!");

        do {
            displayMenu();
            int choice = getUserChoice(scanner);

            if (choice == 0) {
                running = false;
                System.out.println("Thank you for using the Currency Converter. Goodbye!");
                continue;
            }

            if (choice < 1 || choice > EXCHANGE_RATES_TO_USD.size()) {
                System.out.println("Invalid currency selection. Please try again.");
                continue;
            }

            String[] currencies = EXCHANGE_RATES_TO_USD.keySet().toArray(new String[0]);
            String sourceCurrencyCode = currencies[choice - 1];

            System.out.println("\nSelected source currency: " + sourceCurrencyCode);
            System.out.println("Please select the target currency:");
            displayMenu();
            int targetChoice = getUserChoice(scanner);

            if (targetChoice < 1 || targetChoice > EXCHANGE_RATES_TO_USD.size()) {
                System.out.println("Invalid target currency selection. Please try again.");
                continue;
            }
            String targetCurrencyCode = currencies[targetChoice - 1];

            if (sourceCurrencyCode.equals(targetCurrencyCode)) {
                System.out.println("Source and target currencies are the same. No conversion needed.");
                continue;
            }

            BigDecimal amount = getAmountToConvert(scanner);
            if (amount == null) {
                continue; // Error message already printed by getAmountToConvert
            }

            BigDecimal convertedAmount = convertCurrency(amount, sourceCurrencyCode, targetCurrencyCode);
            System.out.printf("\n%.2f %s is equal to %.2f %s\n", amount, sourceCurrencyCode, convertedAmount, targetCurrencyCode);

        } while (running);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nAvailable Currencies:");
        int i = 1;
        for (String currency : EXCHANGE_RATES_TO_USD.keySet()) {
            System.out.println(i++ + ". " + currency);
        }
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from scanner
                System.out.print("Enter your choice: ");
            }
        }
    }

    private static BigDecimal getAmountToConvert(Scanner scanner) {
        while (true) {
            System.out.print("Enter the amount to convert: ");
            try {
                BigDecimal amount = scanner.nextBigDecimal();
                scanner.nextLine(); // Consume newline left-over
                if (amount.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Amount cannot be negative. Please enter a positive value.");
                } else {
                    return amount;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric amount.");
                scanner.nextLine(); // Clear the invalid input from scanner
            }
        }
    }

    private static BigDecimal convertCurrency(BigDecimal amount, String sourceCurrencyCode, String targetCurrencyCode) {
        BigDecimal sourceRate = EXCHANGE_RATES_TO_USD.get(sourceCurrencyCode);
        BigDecimal targetRate = EXCHANGE_RATES_TO_USD.get(targetCurrencyCode);

        if (sourceRate == null || targetRate == null) {
            System.err.println("Error: One of the currency codes is not recognized.");
            return BigDecimal.ZERO; // Should not happen with current input validation
        }

        // Convert source amount to USD first (pivot currency)
        BigDecimal amountInUSD = amount.divide(sourceRate, 10, RoundingMode.HALF_EVEN); // Use a higher precision for intermediate calculation

        // Convert from USD to target currency
        BigDecimal convertedAmount = amountInUSD.multiply(targetRate);

        // Round the final result to 2 decimal places using HALF_EVEN (Banker's Rounding)
        return convertedAmount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
