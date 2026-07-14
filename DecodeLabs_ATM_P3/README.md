# DecodeLabs ATM Interface

This project is a Java-based console application that simulates a real-world ATM interface. It is built using core Object-Oriented Programming (OOP) principles, including encapsulation, separation of concerns, and layered architecture.

## Features

- Object-Oriented Design: Decoupled architecture with separate `ATM` (UI/Controller) and `BankAccount` (Logic/Data) classes.

- Strict Encapsulation: Account balance and transaction history are kept private, accessible only through controlled public methods.

- Robust Input Handling: Uses `hasNextDouble()` and buffer management to prevent crashes from malformed or non-numeric user input.

- Financial Business Rules: Enforces rules for positive transaction amounts and sufficient balance for withdrawals.

- Transaction History: Tracks and displays all activities within the current session.

- Resilient IPO Flow: Follows the Input-Process-Output model for every transaction.

## Project Structure

- `DecodeLabs_ATM_P3.java`: The main source code file containing all required classes.

- `DecodeLabs_ATM_P3.class`: The compiled Java bytecode.

## How to Run

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Compilation

Compile the source code using the following command:

```bash
javac DecodeLabs_ATM_P3.java
```

### Execution

Run the compiled program:

```bash
java DecodeLabs_ATM_P3
```

## Architectural Layers

- Foundation: OOP Digital Twins (BankAccount).

- Vault: Encapsulated data protected by mutators.

- Lobby: Cleanly separated UI (ATM class).

- Security Gate: Robust input validation engine.

- Brain: Modular execution flow.