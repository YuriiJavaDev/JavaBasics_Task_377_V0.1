## Static methods in interfaces.

### 1. Introduction

Before Java 8, an interface was purely a "contract": only abstract methods, no implementation, no logic, just promises. But starting with Java 8, interfaces have become a bit more "independent": they can now contain not only default methods but also **static methods**.

**Static methods in interfaces** are methods that belong to the interface itself, not to its implementations (classes). They don't require object creation and are called directly via the interface name.

**Analogy:**

Static methods in interfaces are like a reference book or a memo posted to the wall of an office: **every employee (class) can use it, but the memo itself doesn't belong to any individual employee.**

Static methods in interfaces allow you to group helper functions related to that interface without cluttering the namespace of the implementing classes.

#### Syntax of static methods in interfaces

Static methods are declared within an interface using the **static** keyword. They contain the implementation (the code inside the curly braces) and can only be called through the interface name.

**Example:**

```java
public interface MathUtils {
    static int sum(int a, int b) {
        return a + b;
    }
    
    static double average(int a, int b) {
        return (a + b) / 2.0;
    }
}
```

**Calling a static interface method:**

```java
int result = MathUtils.sum(5, 7); // 12
double avg = MathUtils.average(10, 20); // 15.0
```

**Warning:**

You cannot call a static interface method through an implementing class or object! Only through the interface name.

### 2. How do static interface methods differ from default methods?

**Static Methods:**

- Belong to the interface itself.
- Not inherited by implementing classes.
- Cannot be called through an object of the class.
- Cannot be overridden in the implementing class.
- Can only be called through the interface name.

**Default Methods:**

- Belong to an object (instance) of the class implementing the interface.
- Can be overridden in the implementing class.
- Can be called through an object of the implementing class.
- Inherited by implementing classes.

In summary: **default** methods extend the capabilities of an object, while **static** methods extend the capabilities of the interface itself.

**Comparison example:**

```java
interface Printer {
    default void print(String text) {
        System.out.println("Default: " + text);
    }
    
    static void info() {
        System.out.println("Printer interface v1.0");
    }
}

class ConsolePrinter implements Printer {}

public class Main {
    public static void main(String[] args) {
        Printer.info(); // Call a static method through an interface
        
        ConsolePrinter cp = new ConsolePrinter();
        cp.print("Hello!"); // Call a default method through an object
        // cp.info(); // Error! A static method cannot be called through an object.
    }
}
```

### 3. Why are static methods needed in interfaces?

Before static methods were added to interfaces, if you needed to add a utility function associated with an interface, you had to create separate classes with the **Utils** or **Helper** suffix:

```java
public interface Movable {
    void move(int x, int y);
}

public class MovableUtils {
    public static void resetPosition(Movable m) {
        m.move(0, 0);
    }
}
```

Now you can do this directly in the interface:

```java
public interface Movable {
    void move(int x, int y);
    
    static void resetPosition(Movable m) {
        m.move(0, 0);
    }
}
```

This makes the code more logical and coherent: methods related to the interface now live directly within it.

**Advantages:**

- Grouping utility functions near the interface contract.
- Not "polluting" the namespace of implementing classes.
- Improves code readability and maintainability.

### 4. Limitations and Features of Static Interface Methods

**Static interface methods are not inherited by implementing classes.**

They cannot be called through an object of the implementing class or through the class name. Only through the interface name!

**Static methods in an interface always contain an implementation: they cannot be abstract or default.**

They always contain an implementation.

**Static methods cannot access non-static methods or interface variables.**

They can only access other static interface members (for example, static final constants).

**Static interface methods can be private (Java 9+).**

You can create helper private static methods for use within the interface.

### 5. Example: Static Methods for the Movable Interface

Let's look at how to add static methods to the Movable interface. Let's say we have a Movable interface implemented by different classes (e.g., robots, animals, vehicles).

**Step 1. Declare an interface with a static method:**

```java
public interface Movable {
    void move(int x, int y);
    
    static void resetPosition(Movable obj) {
        obj.move(0, 0);
    }
    
    static double distance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```

**Step 2. Implement the interface in the class:**

```java
public class Robot implements Movable {
    private int x, y;
    
    public Robot(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void move(int x, int y) {
        System.out.println("The robot is moving to point (" + x + "," + y + ")");
        this.x = x;
        this.y = y;
    }
    
    public void printPosition() {
        System.out.println("Current position: (" + x + "," + y + ")");
    }
}
```

**Step 3. Using the interface's static methods:**

```java
public class Main {
    public static void main(String[] args) {
        Robot robby = new Robot(10, 15);
        robby.printPosition();
        
        // Resetting the position via the interface's static method
        Movable.resetPosition(robby);
        robby.printPosition();
        
        // Calculating the distance between points via the interface's static method
        double dist = Movable.distance(0, 0, 10, 15);
        System.out.println("Distance: " + dist);
    }
}
```

**Result:**

```
Current position: (10,15)
Robot moves to point (0,0)
Current position: (0,0)
Distance: 18.027756377319946
```

**Note:**

We call **Movable.resetPosition(robby)**, not **robby.resetPosition()**. Static methods are convenient for operations that logically relate to the interface, but not to a specific object.

### 6. Private static methods in interfaces

Sometimes you need to use helper methods in an interface only for internal purposes (for example, to avoid duplicating code in several static or default methods). Starting with Java 9, interfaces support **private static** methods.

**Example:**

```java
public interface Logger {
    static void logInfo(String message) {
        log("INFO", message);
    }
    static void logError(String message) {
        log("ERROR", message);
    }
    private static void log(String level, String message) {
        System.out.println("[" + level + "] " + message);
    }
}
```

Now **log()** is not accessible outside the interface, but is used inside other static methods.

### 7. Where are static methods in the Java standard library?

Static methods in interfaces are widely used in the Java standard library, especially in collections and functional interfaces.

**Examples:**

- **Comparator.comparing(), Comparator.reverseOrder()** are static methods of the Comparator interface.
- **Predicate.isEqual()** is a static method of the **Predicate** interface.
- **List.of(), Set.of(), Map.of()** (Java 9+) are static methods for creating immutable collections.

**Example with Comparator:**

```java
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<String> cmp = Comparator.reverseOrder();
        int res = cmp.compare("a", "b"); // positive number because "a" > "b" in reverse order
        System.out.println(res);
    }
}
```

### 8. Common Errors When Working with Static Interface Methods

**Error №1: Attempting to call a static method through an object of the implementing class.**

This won't work! A static interface method can only be called through the interface name, for example, **Movable.resetPosition(obj)**, not **obj.resetPosition()**.

**Error №2: Attempting to override a static interface method in an implementing class.**

Static methods cannot be inherited or overridden! If you declare a static method with the same name in a class, it will be a completely different method, unrelated to the interface.

**Error №3: Forgetting that static methods cannot access non-static members.**

Static interface methods can only access static members (e.g., static final constants), but cannot access non-static methods or variables.

**Error №4: Confusion with default methods.**

Default methods are called through the object, while static methods are called only through the interface name. Don't confuse them!


