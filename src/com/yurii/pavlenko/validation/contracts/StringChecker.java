package com.yurii.pavlenko.validation.contracts;

/**
 * Interface providing utility methods for string validation.
 */
public interface StringChecker {

    /**
     * Static utility method to check if a string is null or empty.
     * Can be called without creating an instance of the interface.
     *
     * @param str The string to check.
     * @return true if string is null or empty, false otherwise.
     */
    static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}