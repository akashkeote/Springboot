# Spring Boot ConfigClass Demo

A demonstration project showcasing Spring Framework's Java-based configuration using `@Configuration` and `@ComponentScan` annotations with Dependency Injection.

## 📋 Overview

This project demonstrates the use of Spring's annotation-based configuration as an alternative to XML configuration. It shows how to use Java classes to configure the Spring IoC Container and implement dependency injection through component scanning.

## 🎯 Key Concepts Demonstrated

- **Java-based Configuration**: Using `@Configuration` annotation to define configuration classes
- **Component Scanning**: Automatic bean discovery using `@ComponentScan`
- **Dependency Injection**: 
  - Constructor Injection
  - Setter Injection (using `@Autowired`)
- **IoC Container**: Spring's Inversion of Control container management
- **AnnotationConfigApplicationContext**: Java-based application context

## 🏗️ Project Structure

```
src/main/java/com/fourth/configclass/
├── App.java                          # Main application entry point
└── concepts/
    ├── ConfigClass.java              # Spring configuration class
    ├── Engine.java                   # Engine component
    └── Car.java                      # Car component with DI
```

## 🔧 Technologies Used

- **Java**: Version 25
- **Spring Boot**: Version 4.0.3
- **Spring Framework**: Web MVC and Web Services starters
- **Maven**: Build and dependency management

## 📦 Components

### ConfigClass
```java
@Configuration
@ComponentScan(basePackages = {"com.fourth.configclass.concepts"})
```
- Marks the class as a source of bean definitions
- Scans the specified package for Spring-managed components

### Engine
```java
@Component("engine1")
```
- Simple component representing an engine
- Registered with Spring container with bean name "engine1"

### Car
```java
@Component("car")
```
- Depends on Engine component
- Demonstrates both constructor and setter injection
- Uses `@Autowired` for automatic dependency resolution

## 🚀 How to Run

1. **Navigate to project directory**:
   ```bash
   cd 4.ConfigClass/demo
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn exec:java -Dexec.mainClass="com.fourth.configclass.App"
   ```
   
   Or using Java directly:
   ```bash
   java -cp target/classes com.fourth.configclass.App
   ```

## 📝 Expected Output

```
project started
Starting point
engine started...
------------
setting engine:setter injection
engine started...
car started
```

## 💡 Learning Points

1. **@Configuration vs XML**: This project demonstrates how to replace XML-based configuration with Java-based configuration

2. **Component Scanning**: The `@ComponentScan` annotation automatically detects classes annotated with `@Component`, `@Service`, `@Repository`, or `@Controller`

3. **Dependency Injection**: 
   - The Car class depends on Engine
   - Spring automatically injects the Engine bean into Car using setter injection
   - Demonstrates loose coupling between objects

4. **IoC Container**: Spring manages the lifecycle and dependencies of objects, removing the need for manual object creation

## 🔍 Code Highlights

### Creating Application Context
```java
ApplicationContext container = new AnnotationConfigApplicationContext(ConfigClass.class);
```

### Retrieving Beans
```java
Engine engine1 = container.getBean("engine1", Engine.class);
Car car = container.getBean("car", Car.class);
```

## 📚 Related Projects

This is part of a series of Spring learning projects:
- `1.firstprojectSpring` - Basic Spring project
- `2.untitled` - Spring DI with XML configuration
- `3.episode2` - Advanced dependency injection patterns
- `4.ConfigClass` - **Current project** - Java-based configuration

## 🎓 Author Notes

> "The control of creating and managing objects is transferred to a container (Spring IoC Container). It allows loose coupling between objects."

This project demonstrates the fundamental principle of Spring IoC, where object creation and lifecycle management is delegated to the Spring container, promoting better design and maintainability.

## 📄 License

This is a learning/demo project.

---

**Happy Learning! 🌱**
