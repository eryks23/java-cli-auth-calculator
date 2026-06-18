# SecureCalculator

> A Java CLI calculator protected by a login authentication system with account lockout.

[![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Description

SecureCalculator is a single-file Java console application that gates a four-operation arithmetic calculator behind a credential-based login system. The application locks itself out after three consecutive failed login attempts and handles division-by-zero gracefully. It is aimed at Java learners exploring CLI interaction, basic input validation, and simple hash-based authentication patterns.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Security Notes](#security-notes)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Authentication gate** – the calculator is inaccessible without valid credentials
- **Account lockout** – three consecutive failed login attempts terminate the session immediately
- **Four arithmetic operations** – addition, subtraction, multiplication, division
- **Division-by-zero guard** – displays a clear error message instead of throwing a runtime exception
- **US-locale decimal parsing** – accepts dot-separated floating-point numbers regardless of system locale
- **No external dependencies** – compiles and runs with any standard JDK, no build tool required

---

## Tech Stack

| Component | Detail |
|-----------|--------|
| Language | Java 8+ |
| Standard library | `java.util.Scanner`, `java.util.Locale` |
| Build tool | None (single-file compilation via `javac`) |
| External dependencies | None |

---

## Requirements

- **JDK 8 or higher** — [Eclipse Temurin](https://adoptium.net/) is recommended
- A terminal or command prompt with `java` and `javac` on `PATH`

Verify your installation:

```bash
java -version
javac -version
```

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/eryks23/java-cli-auth-calculator.git
cd java-cli-auth-calculator
```

2. Compile the source file:

```bash
javac SecureCalculator.java
```

This produces `SecureCalculator.class` in the working directory.

---

## Usage

### Starting the application

```bash
java SecureCalculator
```

### Login

The application prompts for credentials on launch. After three failed attempts, access is permanently denied for the current session.

```
=== System Login ===
Username: admin
Password: ••••••••

Login successful! Welcome, admin.
```

> Default credentials are hardcoded in the source. See [Security Notes](#security-notes).

### Calculator

After a successful login, the main menu is presented in a loop until the user exits:

```
=== Calculator Menu ===
1. Addition (+)
2. Subtraction (-)
3. Multiplication (*)
4. Division (/)
5. Exit
Selection: 3

Enter first number: 6.5
Enter second number: 4.0

Result: 6.5 * 4.0 = 26.0
-----------------------
```

Enter `5` at the menu prompt to close the application cleanly.

### Division by zero

```
Enter first number: 9
Enter second number: 0

Result: Error: Division by zero!
```

---

## Project Structure

```
java-cli-auth-calculator/
├── SecureCalculator.java   # Application entry point: authentication + calculator loop
├── README.md
└── LICENSE
```

`SecureCalculator.java` contains two methods:

| Method | Access | Description |
|--------|--------|-------------|
| `main(String[] args)` | `public static` | Entry point. Runs the login flow, then the calculator menu loop. |
| `simpleHash(String input)` | `private static` | Polynomial rolling hash used to verify the password. Returns `long`. |

---

## Security Notes

> **⚠️ This project is intended for educational purposes only. Do not deploy it in any production or security-sensitive context.**

| Concern | Detail |
|---------|--------|
| Hardcoded credentials | The username and password hash are compile-time constants inside `main()`. Anyone with access to the source can extract them. |
| Weak hash function | `simpleHash()` is a simple polynomial hash (`h = h * 31 + c`). It is not cryptographically secure and must not be used for real password storage. |
| No brute-force protection beyond attempt count | The three-attempt limit exists only for the current process lifetime; restarting the application resets the counter. |

**Recommended improvements for a production scenario:**
- Store credentials externally (environment variable, secrets manager, or database)
- Replace `simpleHash` with `BCrypt`, `Argon2`, or `PBKDF2`
- Add logging for failed authentication attempts

---

## Contributing

Contributions are welcome. To propose a change:

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes with a clear message:
   ```bash
   git commit -m "Add: short description of the change"
   ```
4. Push the branch:
   ```bash
   git push origin feature/your-feature-name
   ```
5. Open a Pull Request against `main` and describe what the change does and why.

Please keep pull requests focused on a single concern. Bug reports and feature suggestions can be filed as [GitHub Issues](https://github.com/eryks23/java-cli-auth-calculator/issues).

---

## Contact / Author

**eryks23** — [github.com/eryks23](https://github.com/eryks23)

Project repository: [https://github.com/eryks23/java-cli-auth-calculator](https://github.com/eryks23/java-cli-auth-calculator)

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for full terms.
