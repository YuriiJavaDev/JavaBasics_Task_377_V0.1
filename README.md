# Web Registration: Static Interface Methods (JavaBasics_Task_377_V0.1)

## 📖 Description
Utility functions often clutter project structures when placed in separate "Utils" classes. This project demonstrates the use of **Static Methods in Interfaces** to keep related helper logic encapsulated. By defining `isEmpty` as a static method within the `StringChecker` interface, we provide a globally accessible validation tool that doesn't require object instantiation. This approach follows the principle of high cohesion, ensuring that the interface provides both the contract and the necessary tools to work with it.

## 📋 Requirements Compliance
- **Instance-Free Access**: Implemented a `static` method accessible directly via `StringChecker.isEmpty()`.
- **Null-Safe Validation**: Designed the logic to handle both empty and `null` string inputs.
- **Utility Abstraction**: Encapsulated common validation logic within the interface itself.
- **Functional Testing**: Verified the logic using multiple test cases (empty string vs. populated string).

## 🚀 Architectural Stack
- Java 8+ (Static Interface Methods, String API)

## 🏗️ Implementation Details
- **StringChecker**: The interface acting as a functional utility container.
- **ValidationLauncherApp**: The entry point for testing global string checks.

## 📋 Expected result
```text
true
false
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_377/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── ValidationLauncherApp.java
    │                 └── validation/
    │                     └── contracts/
    │                         └── StringChecker.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.validation.contracts.StringChecker;

public class ValidationLauncherApp {

    public static void main(String[] args) {

        boolean result1 = StringChecker.isEmpty("");
        boolean result2 = StringChecker.isEmpty("Java");

        System.out.println(result1);
        System.out.println(result2);
    }
}
```
```java
package com.yurii.pavlenko.validation.contracts;

public interface StringChecker {

    static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
