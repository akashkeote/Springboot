# 🚗 First Spring Project - Dependency Injection Learning

## 📖 About This Project
This is my first Spring Framework learning project that demonstrates the fundamental concepts of **Tight Coupling** vs **Loose Coupling** and **Dependency Injection** using a simple Car-Engine analogy.

## 🎯 Learning Objectives
- Understanding Tight Coupling and its limitations
- Understanding Loose Coupling and its benefits
- Introduction to Dependency Injection (Constructor Injection)
- Basics of Spring Framework Core concepts

## 📂 Project Structure
```
firstproejctSpring/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── springexample/
│   │               ├── App.java          # Main application entry point
│   │               ├── part1/            # Tight Coupling Example
│   │               │   ├── Car1.java
│   │               │   └── Engine1.java
│   │               └── part2/            # Loose Coupling Example
│   │                   ├── Car2.java
│   │                   └── Engine2.java
│   └── test/
│       └── java/
├── pom.xml                                # Maven configuration
└── README.md
```

## 🔍 Concepts Demonstrated

### Part 1: Tight Coupling ❌
**Location:** `part1/` package

**What happens:**
- `Car1` directly creates an instance of `Engine1` inside its method
- Car1 is **tightly coupled** with Engine1
- If we want to change the engine, we must modify the Car1 source code

**Problem:**
```java
public class Car1 {
    public void startCar(){
        Engine1 e = new Engine1();  // Hard dependency!
        e.startEngine();
        System.out.println("Car 1 Started");
    }
}
```
- Not flexible
- Hard to test
- Difficult to maintain
- Cannot easily swap engines

### Part 2: Loose Coupling ✅ (Dependency Injection)
**Location:** `part2/` package

**What happens:**
- `Car2` receives `Engine2` through constructor (Constructor Injection)
- Car2 doesn't create the engine itself
- More flexible than Part 1, but still requires manual dependency management

**Improvement:**
```java
public class Car2 {
    Engine2 e;
    
    public Car2(Engine2 eng){  // Engine injected via constructor
        this.e = eng;
    }
    
    public void startCar(){
        System.out.println("Car 2 Started");
    }
}
```
- More flexible than tight coupling
- Dependencies are injected from outside
- Better separation of concerns

**Note:** This is manual dependency injection. Spring Framework automates this process using IoC (Inversion of Control) container.

## 🛠️ Technologies Used
- **Java** (JDK 8 or higher)
- **Maven** (Build Tool)
- **Spring Core** 7.0.4
- **JUnit** 3.8.1 (Testing)

## 📋 Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven 3.x
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 How to Build and Run

### Build the Project
```bash
mvn clean compile
```

### Run the Application
```bash
mvn exec:java -Dexec.mainClass="org.springexample.App"
```

Or run directly from your IDE by executing the `App.java` main method.

### Expected Output
```
Engine 1 started
Car 1 Started
Engine 2 started
Car 2 Started
```

## 💡 Key Takeaways
1. **Tight Coupling**: Objects create their own dependencies → Hard to change, test, and maintain
2. **Loose Coupling**: Dependencies are provided from outside → More flexible and testable
3. **Dependency Injection**: A design pattern that implements loose coupling
4. **Spring Framework**: Provides IoC container to manage dependencies automatically

## 🔜 Next Steps
- Learn about Spring IoC Container
- Implement Dependency Injection using Spring annotations (`@Component`, `@Autowired`)
- Explore XML-based Spring configuration
- Learn about different types of Dependency Injection (Constructor, Setter, Field)

## 📚 Useful Resources
- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
- [Dependency Injection explained](https://www.martinfowler.com/articles/injection.html)

## 👨‍💻 Author
Created while learning Spring Framework fundamentals

---
**Note:** This is a learning project demonstrating core concepts. Future versions will incorporate actual Spring IoC container and annotations.
