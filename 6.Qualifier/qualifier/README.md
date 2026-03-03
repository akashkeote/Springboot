# Project 6: @Qualifier & @Primary - Resolving Bean Ambiguity

## 📚 Learning Objectives

This project solves a critical Spring problem:
- **Multiple beans of the same type** causing ambiguity
- **@Qualifier** annotation to specify which bean to inject
- **@Primary** annotation as an alternative approach

---

## 🔴 The Problem

When you have multiple beans implementing the same interface:

```java
public interface ColdDrink {
    void drink();
}

@Component
public class CocaCola implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking CocaCola");
    }
}

@Component
public class Pepsi implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking Pepsi");
    }
}

@Component
public class Human {
    @Autowired
    ColdDrink coldDrink;  // ❌ ERROR: Which one?
}
```

**Error:**
```
Field coldDrink in Human required a single bean, but 2 were found:
- cocaCola: CocaCola
- pepsi: Pepsi
```

Spring doesn't know which implementation to inject! 

---

## ✅ Solution 1: Use @Qualifier

Tell Spring exactly which bean you want:

```java
@Component
public class Human {
    
    @Autowired
    @Qualifier("pepsi")  // ✅ "Inject the Pepsi bean"
    ColdDrink coldDrink;
    
    public void tryColdDrink() {
        coldDrink.drink();  // Output: "Drinking Pepsi"
    }
}
```

**How it works:**
1. Spring sees `@Autowired` → needs ColdDrink
2. Finds 2 beans → CocaCola, Pepsi
3. Sees `@Qualifier("pepsi")` → selects Pepsi
4. Injects Pepsi ✅

---

## ✅ Solution 2: Use @Primary

Mark one bean as the default choice:

```java
@Component
@Primary  // ✅ "Use me by default"
public class CocaCola implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking CocaCola");
    }
}

@Component
public class Pepsi implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking Pepsi");
    }
}

@Component
public class Human {
    @Autowired  // No @Qualifier needed
    ColdDrink coldDrink;  // Gets CocaCola (has @Primary)
    
    public void tryColdDrink() {
        coldDrink.drink();  // Output: "Drinking CocaCola"
    }
}
```

**How it works:**
1. Spring sees `@Autowired` → needs ColdDrink
2. Finds 2 beans → CocaCola, Pepsi
3. Sees `@Primary` on CocaCola
4. Injects CocaCola ✅

---

## 🤔 @Qualifier vs @Primary

| Aspect | @Qualifier | @Primary |
|--------|-----------|----------|
| Where | On injection point | On bean |
| Flexibility | Choose any bean anytime | Sets one default |
| Reusability | Must repeat @Qualifier everywhere | Works everywhere automatically |
| Best for | Different beans in different places | One default with rare exceptions |
| Code clarity | Very explicit | Implicitly clear |

**Recommendation:**
- Use **@Primary** for cleaner code (most cases)
- Use **@Qualifier** when different parts need different beans

---

## 📂 Project Structure

```
6.Qualifier/qualifier/
├── config/
│   └── ConfigClass.java          ← @Configuration
├── student/
│   ├── Student.java              ← @Component
│   └── Teacher.java              ← @Component + @Bean
├── concept/
│   ├── Car.java                  ← With Engine injection
│   └── Engine.java
├── concepts/
│   ├── Car.java                  ← Duplicate
│   └── Engine.java
├── qualifier/
│   ├── ColdDrink.java            ← Interface
│   ├── CocaCola.java             ← with @Primary
│   ├── Pepsi.java                ← Alternative
│   └── Human.java                ← Uses ColdDrink
└── App.java                       ← Main entry point
```

---

## 🔍 Code Walkthrough

### ColdDrink.java
```java
public interface ColdDrink {
    void drink();
}
```

Simple interface that both implementations will follow.

---

### CocaCola.java
```java
@Component
@Primary  // ✅ Default bean
public class CocaCola implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking CocaCola");
    }
}
```

@Primary means: "If nobody specifies which drink, use me"

---

### Pepsi.java
```java
@Component  // No @Primary
public class Pepsi implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking Pepsi");
    }
}
```

---

### Human.java (The key class!)
```java
@Component
public class Human {
    
    @Autowired
    // @Qualifier(value = "pepsi")  // Uncomment to use Pepsi instead
    ColdDrink coldDrink;  // Currently gets CocaCola via @Primary
    
    public void tryColdDrink() {
        coldDrink.drink();
    }
}
```

**To switch to Pepsi, uncomment the @Qualifier:**
```java
@Autowired
@Qualifier("pepsi")  // Now gets Pepsi
ColdDrink coldDrink;
```

---

### App.java
```java
public class App {
    public static void main(String[] args) {
        ApplicationContext container = 
            new AnnotationConfigApplicationContext(ConfigClass.class);
        
        Car car = container.getBean("car", Car.class);
        car.start();
        
        Student student = container.getBean("student", Student.class);
        student.show();
        
        Teacher teacher = container.getBean("teacher", Teacher.class);
        teacher.show();
        
        // ✅ New feature:
        Human akash = container.getBean("human", Human.class);
        akash.tryColdDrink();  // Output: "Drinking CocaCola"
    }
}
```

---

## 💡 Real-World Example

Imagine you have a payment system:

```java
public interface PaymentService {
    void process(double amount);
}

@Component
@Primary  // Use Stripe for most cases
public class StripePayment implements PaymentService {
    public void process(double amount) {
        System.out.println("Processing via Stripe: $" + amount);
    }
}

@Component
public class PayPalPayment implements PaymentService {
    public void process(double amount) {
        System.out.println("Processing via PayPal: $" + amount);
    }
}

// Service 1: Uses default (Stripe)
@Component
public class RegularCheckout {
    @Autowired
    PaymentService payment;  // Gets Stripe
    
    void checkout(double amount) {
        payment.process(amount);  // "Processing via Stripe"
    }
}

// Service 2: Needs specific payment (PayPal)
@Component
public class B2BCheckout {
    @Autowired
    @Qualifier("payPalPayment")
    PaymentService payment;  // Gets PayPal
    
    void checkout(double amount) {
        payment.process(amount);  // "Processing via PayPal"
    }
}
```

---

## ❓ Common Questions

### Q: What's the @Qualifier value for my bean?

By default, Spring uses the class name with first letter lowercase:

```java
@Component
public class CocaCola { }  // name = "cocaCola"

@Component("myDrink")
public class Pepsi { }     // name = "myDrink"

// In @Qualifier:
@Qualifier("cocaCola")     // First one
@Qualifier("myDrink")      // Second one
```

---

### Q: Can I use @Qualifier on constructor?

**Yes!**

```java
@Component
public class Human {
    ColdDrink coldDrink;
    
    // Constructor Injection with @Qualifier
    @Autowired
    public Human(@Qualifier("pepsi") ColdDrink drink) {
        this.coldDrink = drink;
    }
}
```

---

### Q: What if I use both @Qualifier and @Primary?

**@Qualifier wins!**

```java
@Component
@Primary
public class CocaCola implements ColdDrink { }

@Component
public class Pepsi implements ColdDrink { }

@Autowired
@Qualifier("pepsi")
ColdDrink drink;  // ✅ Gets Pepsi (ignores @Primary)
```

@Qualifier has higher priority.

---

### Q: Can I inject multiple beans of same type?

**Yes, with a List or Map:**

```java
@Component
public class BeverageShop {
    @Autowired
    List<ColdDrink> allDrinks;  // Gets [CocaCola, Pepsi]
    
    public void serveRandomDrink() {
        ColdDrink drink = allDrinks.get(
            new Random().nextInt(allDrinks.size())
        );
        drink.drink();
    }
}
```

---

## 🚀 How to Run

```bash
cd 6.Qualifier/qualifier

# Compile
mvn clean compile

# Run
mvn exec:java -Dexec.mainClass="com.qualify.App"

# Or in IDE: Right-click App.java → Run
```

---

## 🔧 Experiment with the Code

1. **See @Primary default:**
   - Run as-is → CocaCola is used

2. **Switch to @Qualifier:**
   - Uncomment `@Qualifier("pepsi")` in Human.java
   - Re-run → Pepsi is used

3. **Remove @Primary:**
   - Comment out `@Primary` in CocaCola.java
   - Run → ERROR! (ambiguity without either decorator)

4. **Add another implementation:**
   - Create `OrangeJuice implements ColdDrink`
   - Try to inject without @Qualifier → ERROR!

---

## ✅ Knowledge Checklist

- [ ] Understand why multiple beans cause ambiguity
- [ ] Know how @Qualifier works
- [ ] Know how @Primary works
- [ ] Can choose between them for your use case
- [ ] Can identify bean names correctly
- [ ] Can implement this in your own code

---

## 📌 Spring Journey Summary

| Project | Concept | Problem Solved |
|---------|---------|----------------|
| 4 | @Configuration | XML → Java config |
| 5 | @Bean & @ComponentScan | Bean creation strategies |
| 6 | @Qualifier & @Primary | **Multiple bean ambiguity** ← YOU ARE HERE |

**Next Steps:**
- Spring AOP (Aspect-Oriented Programming)
- Transaction Management
- Spring Boot features
- Microservices

---

## 🎓 Advanced Patterns

### Pattern 1: Conditional Beans
```java
@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
public class StripePayment implements PaymentService { }
```

### Pattern 2: Factory Pattern with @Bean
```java
@Configuration
public class PaymentFactory {
    @Bean
    public PaymentService paymentService() {
        // Return different implementations based on config
        if (environment.getProperty("payment.type").equals("paypal")) {
            return new PayPalPayment();
        }
        return new StripePayment();
    }
}
```

### Pattern 3: Named vs Typed Qualifiers
```java
@Autowired
@Qualifier("pepsi")  // By bean name
PaymentService payment1;

@Autowired
@Qualifier("paypal")  // Custom qualifier
PaymentService payment2;
```

---

## 🔗 Connection to Previous Projects

**Project 5 taught you:**
- How beans are created
- How @ComponentScan finds them
- How @Bean gives you control

**Project 6 teaches you:**
- How to handle multiple beans
- How Spring resolves ambiguity
- Real-world scenarios

**This is the foundation for:**
- Building complex enterprise systems
- Creating flexible, maintainable code
- Understanding Spring Boot internals

---

## 💻 Final Exercise

Try to build a notification system:

```java
public interface NotificationService {
    void send(String message);
}

// Create implementations: EmailNotification, SMSNotification, SlackNotification
// Create a NotificationCenter that uses @Qualifier to send via different channels
// Make one @Primary for default behavior
```

**Happy Coding!** 🚀
