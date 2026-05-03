package com.yurii.pavlenko.app;

import com.yurii.pavlenko.validation.contracts.StringChecker;

/**
 * Main application demonstrating static method calls from an interface.
 */
public class ValidationLauncherApp {

    public static void main(String[] args) {
        // Calling static methods directly using the interface name
        boolean result1 = StringChecker.isEmpty("");
        boolean result2 = StringChecker.isEmpty("Java");

        System.out.println(result1);
        System.out.println(result2);
    }
}