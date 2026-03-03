# Project 6: @Qualifier - बेहद महत्वपूर्ण! 🎯

## 📚 इस प्रोजेक्ट में क्या सीखेंगे?

यह **Project 5 का continuation** है! इसमें एक बहुत ही आम problem solve होती है:

**समस्या:** एक ही type के **multiple beans** होने पर Spring confused होता है कि कौन सा inject करे?

**समाधान:** 
- **@Qualifier** - बताओ कि कौन सा bean needs करते हो
- **@Primary** - एक default bean बताओ

---

## 🔴 समस्या को समझिए पहले

### ये Error क्यों आता है?

```java
public interface ColdDrink {
    void drink();
}

@Component
public class CocaCola implements ColdDrink {
    public void drink() { System.out.println("Drinking CocaCola"); }
}

@Component
public class Pepsi implements ColdDrink {
    public void drink() { System.out.println("Drinking Pepsi"); }
}

@Component
public class Human {
    @Autowired
    ColdDrink coldDrink;  // ❌ PROBLEM! कौन सा?
    
    public void tryColdDrink() {
        coldDrink.drink();  // CocaCola या Pepsi?
    }
}
```

**Error Message:**
```
Field coldDrink in com.qualify.qualifier.Human required a single bean,
but 2 were found:
- cocaCola: CocaCola
- pepsi: Pepsi
```

Spring को ambiguity है! इसका हल दो तरीके से होता है 👇

---

## ✅ समाधान 1: @Qualifier

### Step 1: Bean को पहचान दो
```java
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
```

### Step 2: @Qualifier से बताओ कौन सा चाहिए

```java
@Component
public class Human {
    @Autowired
    @Qualifier("pepsi")  // ✅ यही चाहिए!
    ColdDrink coldDrink;
    
    public void tryColdDrink() {
        coldDrink.drink();  // Output: "Drinking Pepsi"
    }
}
```

**कैसे काम करता है:**
1. `@Autowired` देखता है → ColdDrink चाहिए
2. Multiple beans मिलते हैं → CocaCola, Pepsi
3. `@Qualifier("pepsi")` देखता है → Pepsi को select करता है
4. Pepsi inject हो जाता है ✅

---

## ✅ समाधान 2: @Primary (अलग approach)

अगर आप @Qualifier नहीं लिखना चाहते, तो **@Primary** लगाओ:

```java
@Component
@Primary  // ✅ यह default है!
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
    @Autowired  // ✅ कोई @Qualifier नहीं
    ColdDrink coldDrink;  // CocaCola inject होगा (क्योंकि @Primary है)
    
    public void tryColdDrink() {
        coldDrink.drink();  // Output: "Drinking CocaCola"
    }
}
```

**कैसे काम करता है:**
1. `@Autowired` देखता है → ColdDrink चाहिए
2. Multiple beans मिलते हैं → CocaCola, Pepsi
3. `@Primary` वाला देखता है → CocaCola है
4. CocaCola inject हो जाता है ✅

---

## 🤔 @Qualifier vs @Primary - कौन बेहतर?

| Feature | @Qualifier | @Primary |
|---------|-----------|----------|
| कहां लिखते हैं | Injection point पर | Bean definition पर |
| Control | ज्यादा flexible | कम flexible |
| Default | नहीं | हां |
| Multiple places से use करने पर | बार-बार @Qualifier लिखना पड़े | सब जगह @Primary काम करेगा |
| Best for | विभिन्न implementations select करना | Default behavior सेट करना |

**सलाह:** 
- आमतौर पर **@Primary बेहतर है** (clean code)
- अगर हर जगह different beans चाहिए, तो **@Qualifier use करो**

---

## 📂 इस प्रोजेक्ट की फाइलें

```
6.Qualifier/
├── config/
│   └── ConfigClass.java          ← @Configuration
├── student/
│   ├── Student.java              ← @Component
│   └── Teacher.java              ← @Component
├── concept/
│   ├── Car.java                  ← Setter injection (Engine)
│   └── Engine.java               ← Dependency
├── concepts/
│   ├── Car.java                  ← Duplicate folder
│   └── Engine.java
├── qualifier/
│   ├── ColdDrink.java            ← Interface
│   ├── CocaCola.java             ← Implementation 1 (@Primary)
│   ├── Pepsi.java                ← Implementation 2
│   └── Human.java                ← Uses @Autowired + @Qualifier
└── App.java                       ← Main entry point
```

---

## 🔍 Detailed Code Walkthrough

### ColdDrink.java (Interface)
```java
public interface ColdDrink {
    void drink();  // सभी drinks को यह करना चाहिए
}
```

---

### CocaCola.java
```java
@Component
@Primary  // ✅ Default choice
public class CocaCola implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking CocaCola");
    }
}
```

**@Primary का मतलब:**
- अगर ColdDrink चाहिए और कोई specify न करे, तो मुझे दो

---

### Pepsi.java
```java
@Component
// ❌ कोई @Primary नहीं
public class Pepsi implements ColdDrink {
    @Override
    public void drink() {
        System.out.println("Drinking Pepsi");
    }
}
```

---

### Human.java (सबसे महत्वपूर्ण!)
```java
@Component
public class Human {
    
    @Autowired
    // @Qualifier(value = "pepsi")  // ← यह uncomment करो अगर Pepsi चाहिए
    ColdDrink coldDrink;  // ← अभी @Primary (CocaCola) से आएगा
    
    public void tryColdDrink() {
        coldDrink.drink();  // Output: "Drinking CocaCola" (या Pepsi)
    }
}
```

**अगर बदलना हो Pepsi को:**
```java
@Autowired
@Qualifier(value = "pepsi")  // ✅ Pepsi inject होगा
ColdDrink coldDrink;
```

---

### ConfigClass.java
```java
@Configuration
@ComponentScan(basePackages = {"com.qualify.student", "com.qualify.concept"})
public class ConfigClass {
    
    @Bean(name = "teacher")
    public Teacher getTeacher() {
        return new Teacher();
    }
}
```

---

### App.java
```java
public class App {
    public static void main(String[] args) {
        ApplicationContext container = 
            new AnnotationConfigApplicationContext(ConfigClass.class);
        
        // पहले जैसे ही सब काम करेगा, लेकिन अब:
        Car car = container.getBean("car", Car.class);
        car.start();
        
        Student student = container.getBean("student", Student.class);
        student.show();
        
        Teacher teacher = container.getBean("teacher", Teacher.class);
        teacher.show();
        
        // ✅ यह नया है:
        Human akash = container.getBean("human", Human.class);
        akash.tryColdDrink();  // Output: "Drinking CocaCola"
    }
}
```

---

## 🎬 Execution Output

```
Creating Student Object
Creating Teacher Object
creating car object
creating engine object
setting engine: setter injection
creating teacher object (by @Bean)
Printing student...
I am student

I am teacher
Drinking CocaCola  ← @Primary काम कर गया!
```

---

## 💡 Real-World Example (समझिए)

```java
// Database के लिए 2 implementations हो सकते हैं:

public interface DatabaseService {
    void save(String data);
}

@Component
@Primary
public class MySQLDatabase implements DatabaseService {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

@Component("mongo")
public class MongoDBDatabase implements DatabaseService {
    public void save(String data) {
        System.out.println("Saving to MongoDB: " + data);
    }
}

// अब Customer code:
@Component
public class UserService {
    @Autowired
    DatabaseService db;  // MySQL मिलेगा (default)
    
    public void createUser(String name) {
        db.save(name);  // "Saving to MySQL: name"
    }
}

// अगर MongoDB चाहिए:
@Component
public class AdminService {
    @Autowired
    @Qualifier("mongo")
    DatabaseService db;  // MongoDB मिलेगा
    
    public void createAdmin(String name) {
        db.save(name);  // "Saving to MongoDB: name"
    }
}
```

---

## ❓ अक्सर पूछे जाने वाले सवाल

### Q1: @Qualifier में "value" क्या है?

```java
@Qualifier(value = "pepsi")  // या सिर्फ
@Qualifier("pepsi")          // दोनों same हैं

// "pepsi" क्या है? 
// यह bean का नाम है!
@Component
public class Pepsi { }  // Default name: "pepsi" (lowercase)

// या custom नाम दे सकते हो:
@Component("myPepsi")
public class Pepsi { }
```

---

### Q2: @Qualifier से multiple fields inject कर सकते हैं?

**हां!**

```java
@Component
public class Beverage {
    @Autowired
    @Qualifier("cocaCola")
    ColdDrink drink1;
    
    @Autowired
    @Qualifier("pepsi")
    ColdDrink drink2;  // दोनों अलग-अलग inject हो जाएंगे
}
```

---

### Q3: अगर @Primary भी हो और @Qualifier भी दिया हो?

**@Qualifier जीत जाता है!**

```java
@Component
@Primary
public class CocaCola implements ColdDrink { }

@Component
public class Pepsi implements ColdDrink { }

@Autowired
@Qualifier("pepsi")
ColdDrink drink;  // ✅ Pepsi आएगा (न कि CocaCola)
```

@Qualifier की priority @Primary से ज्यादा है।

---

### Q4: अगर @Qualifier दिया लेकिन वह bean नहीं है?

```java
@Autowired
@Qualifier("nonexistent")
ColdDrink drink;  // ❌ ERROR!
```

Error आएगी:
```
No bean named 'nonexistent' available
```

---

## 🚀 कैसे Run करें?

```bash
cd 6.Qualifier/qualifier

# Compile
mvn clean compile

# Run
mvn exec:java -Dexec.mainClass="com.qualify.App"

# या IDE में: Right-click App.java → Run
```

---

## 🔄 @Qualifier के साथ खेलकर देखो

1. **@Primary का असर देखो:**
   ```java
   // Human.java में
   @Autowired
   ColdDrink coldDrink;  // Output: "Drinking CocaCola"
   ```

2. **@Qualifier से बदलो:**
   ```java
   @Autowired
   @Qualifier("pepsi")
   ColdDrink coldDrink;  // Output: "Drinking Pepsi"
   ```

3. **दोनों को remove करो:**
   ```java
   @Autowired
   ColdDrink coldDrink;  // ❌ ERROR! (दोनों beans, कोई @Primary नहीं)
   ```

---

## ✅ Learning Checklist

- [ ] समझ गए कि multiple beans होने पर क्या problem आती है?
- [ ] @Qualifier का use समझ गए?
- [ ] @Primary का use समझ गए?
- [ ] दोनों में कौन सा बेहतर है, जान गए?
- [ ] Real-world example (database) को समझ गए?
- [ ] कर सकते हो अपने code में @Qualifier लगाना?

अगर सभी checkmarks हैं, तो **Spring का foundation पूरा हो गया!** 🎉

---

## 📌 अब तक का सफर

| Project | Topic | Goal |
|---------|-------|------|
| 4 | @Configuration | XML से Java को switch करना |
| 5 | @Bean vs @Component | Beans को कैसे create करें |
| 6 | @Qualifier & @Primary | **Multiple beans को resolve करना** |

**अगला क्या सीखेंगे?**
- Aspect Oriented Programming (AOP)
- Transaction Management
- Spring Boot के advanced features

لكن पहले यह 3 projects complete करो और 100% समझ जाओ! 💪

---

## 🎓 Pro Tips

1. **@Primary use करो, जब:**
   - Default behavior चाहिए
   - ज्यादातर जगहों पर एक ही bean चाहिए

2. **@Qualifier use करो, जब:**
   - विभिन्न जगहों पर अलग-अलग beans चाहिए
   - Dynamic selection करना हो

3. **Convention following:**
   ```java
   // Bean के names lowercase होने चाहिए
   @Component
   public class CocaCola { }  // bean name: cocaCola (not CocaCola)
   
   @Qualifier("cocaCola")  // यही लिखो
   ```

4. **@Component का default name:**
   ```java
   @Component              // name = "pepsi" (first letter lowercase)
   @Component("myPepsi")   // custom name = "myPepsi"
   ```

---

## 🔗 References

- Project 5 समझा हुआ? फिर यह easy है!
- अगर @Bean / @ComponentScan confused हो, Project 5 को फिर पढ़ो
- Interface-based design को समझ गए? तो बाकी सब easy है!

**Happy Learning!** 🚀
