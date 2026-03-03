# Episode 2 - Spring Boot Dependency Injection (XML Config)

## Overview
This is a Spring Boot tutorial project demonstrating **Dependency Injection (DI)** using **XML-based configuration** along with **component scanning** and **autowiring annotations**.

## What's Happening Here?

This project shows how Spring Framework manages object dependencies automatically. Instead of manually creating objects, Spring injects them where needed.

### Key Concepts:

1. **Component Scanning**: The `config.xml` uses `<context:component-scan>` to automatically detect and register Spring beans from the `com.episode.second.episode2.classes` package.

2. **Autowiring**: The `@Autowired` annotation in the `Car` class automatically injects an `Engine` bean into the `setEngine()` method.

3. **Bean Registration**: Classes are marked with `@Component` annotation, making them eligible for Spring to manage them.

## Project Structure

```
3.episode2/
├── src/main/
│   ├── java/
│   │   └── com/episode/second/episode2/
│   │       ├── Main.java              (Entry point)
│   │       └── classes/
│   │           ├── Car.java           (Has an Engine dependency)
│   │           └── Engine.java        (Engine component)
│   └── resources/
│       ├── config.xml                 (Spring XML configuration)
│       └── application.properties      (Spring Boot properties)
└── pom.xml                             (Maven configuration)
```

## Classes

### `Engine.java`
```java
@Component("engine")
public class Engine {
    private String type = "V8";
    
    // Getters and setters
}
```
- Registered as a Spring bean with name "engine"
- Default engine type is "V8"

### `Car.java`
```java
@Component("car")
public class Car {
    private Engine engine;
    
    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```
- Registered as a Spring bean with name "car"
- Engine dependency is autowired via setter injection

### `Main.java`
Entry point that:
1. Loads Spring context from `config.xml`
2. Retrieves Car and Engine beans from the context
3. Displays the engine type

## How It Works

1. **Main.java creates ApplicationContext** → `ClassPathXmlApplicationContext("config.xml")`
   - This loads the XML configuration file from classpath
   - ApplicationContext is the Spring container that manages all beans
   
2. **Spring loads `config.xml`** → Initializes component scanning
   - `<context:component-scan>` scans the specified package
   
3. **Component scanning finds Car.java and Engine.java** → Creates beans
   - Classes with `@Component` annotation become Spring beans
   
4. **Spring injects Engine into Car** → Autowiring happens
   - `@Autowired` annotation triggers dependency injection
   
5. **Main retrieves beans from context** → Demonstrates the injection
   - `context.getBean("car", Car.class)` retrieves the Car bean
   - `context.getBean("engine", Engine.class)` retrieves the Engine bean

## Running the Application

```bash
mvn clean install
mvn spring-boot:run
```

Or run the `Main` class directly from your IDE.

## Expected Output
```
Car's Autowired Engine: V8
Direct Engine Bean: V8
```

## Technologies Used
- **Java 25** (as per pom.xml)
- **Spring Boot 4.0.3**
- **Spring Framework** (XML-based DI)
- **Maven** (Build tool)

## Learning Points

✅ How Spring manages bean lifecycle  
✅ XML configuration for Spring  
✅ Component scanning with annotations  
✅ Setter injection using @Autowired  
✅ Accessing beans from ApplicationContext  

---

*This project is from a Spring Framework tutorial series.*
