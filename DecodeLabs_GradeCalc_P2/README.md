# DecodeLabs Student Grade Calculator

This project is a Java-based console application that calculates a student's total marks, average percentage, and assigns a grade based on the results. It is designed with defensive programming to handle invalid inputs and uses high-precision calculations.

## Features

- **Scalable Input:** Allows the user to specify the number of subjects.

- **Defensive Programming:** Validates that marks are within the range [0] [100] and re-prompts the user if an invalid value or non-integer is entered.

- **Arithmetic Precision:** Uses `double` precision and explicit type casting to ensure the average percentage is calculated accurately (e.g., preserving decimal places).

- **Logic Ladder:** Uses a structured conditional ladder to assign grades (A, B, C, D, E, F) based on the average percentage.

- **Professional Output:** Uses `printf` formatting to display a clean and readable student report.

## Project Structure

- `DecodeLabs_GradeCalc_P2.java`: The main source code file.

- `DecodeLabs_GradeCalc_P2.class`: The compiled Java bytecode.

## How to Run

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Compilation

Compile the source code using the following command:

```bash
javac DecodeLabs_GradeCalc_P2.java
```

### Execution

Run the compiled program:

```bash
java DecodeLabs_GradeCalc_P2
```

## Grading System

| Average Percentage | Grade | Status |
| --- | --- | --- |
| >= 90% | A | PASS |
| >= 80% | B | PASS |
| >= 70% | C | PASS |
| >= 60% | D | PASS |
| >= 50% | E | PASS |
| < 50% | F | FAIL |