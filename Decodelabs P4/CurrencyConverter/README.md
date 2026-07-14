# Industrial Training Kit: Currency Converter

A professional, high-precision Java application for currency conversion, developed as part of the DecodeLabs Industrial Training series. This project demonstrates core Java competencies in financial arithmetic, defensive programming, and menu-driven architecture.

## Key Features

- Financial Precision: Utilizes `java.math.BigDecimal` to eliminate the "double crisis" (floating-point inaccuracies) in monetary calculations.

- Banker's Rounding: Implements `RoundingMode.HALF_EVEN` for final outputs to ensure unbiased financial results.

- Multi-Currency Support: Supports USD, EUR, GBP, JPY, AUD, and INR with a scalable cross-rate routing engine.

- Robust Error Handling:
  - Survives non-numeric input typos via `InputMismatchException` handling.
  - Blocks negative monetary amounts.
  - Validates menu selections to prevent logic errors.

- User-Friendly Interface: Interactive console-based menu with a persistent `do-while` loop for multiple conversions in one session.

## Technical Architecture

The application follows the **IPO (Input-Processing-Output)** model:

1. Input: Captured via the `Scanner` class with explicit buffer management.

1. Processing:
  - Rates are stored relative to a **USD pivot**.
  - Conversion Flow: `Source Currency` ➔ `USD (10-decimal precision)` ➔ `Target Currency`.

1. Output: Formatted using `System.out.printf` to ensure a clean, two-decimal financial presentation.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your system.

### Installation

1. Download the `CurrencyConverter.java` file.

1. Open your terminal or command prompt.

1. Navigate to the directory containing the file.

### Compilation

Compile the source code using the Java compiler:

```bash
javac CurrencyConverter.java
```

### Running the Application

Execute the compiled bytecode:

```bash
java CurrencyConverter
```

## Example Usage

```
Welcome to the Industrial Training Kit Currency Converter!

Available Currencies:
1. USD
2. EUR
3. GBP
4. JPY
5. AUD
6. INR
0. Exit
Enter your choice: 2

Selected source currency: EUR
Please select the target currency:
...
Enter your choice: 6
Enter the amount to convert: 100

100.00 EUR is equal to 9076.09 INR
```

## Gatekeeper Checklist Compliance

This implementation successfully passes the following certification requirements:

- [x] Multi-Currency Support: Handles at least 3 currencies and routes cross-rates accurately.

- [x] Input Integrity: Blocks negative numbers and survives non-numeric input.

- [x] Financial Accuracy: Uses `BigDecimal` to avoid floating-point errors.

- [x] Formatting Precision: Uniformly formats output to exactly two decimal places.

---

Developed by Manus AI for the DecodeLabs Industrial Training Program.