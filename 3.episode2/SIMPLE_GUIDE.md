# Simple Dependency Injection Guide

## 3 Types - Super Simple! 🎯

### 1️⃣ **Main1ConstructorInjection** with `config1.xml`
```
Run: mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main1ConstructorInjection"
```
✅ **BEST PRACTICE** - Dependency through constructor
- Use this by default!
- Immutable (final keyword)
- Fail-fast if dependency missing

---

### 2️⃣ **Main2SetterInjection** with `config2.xml`
```
Run: mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main2SetterInjection"
```
⚠️ **For Optional Dependencies** - Dependency through setter
- Use only when dependency is optional
- Can change after creation
- More flexible than constructor

---

### 3️⃣ **Main3FieldInjection** with `config3.xml`
```
Run: mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main3FieldInjection"
```
❌ **Avoid in Production** - Dependency directly to field
- Hard to test
- Hidden dependencies
- Only for learning/tutorials

---

## Config Files Structure

```
src/main/resources/
├── config1.xml    → Scans constructor package
├── config2.xml    → Scans setter package
└── config3.xml    → Scans field package
```

Each config file has its own component-scan:

**config1.xml:**
```xml
<context:component-scan base-package="com.episode.second.episode2.constructor" />
```

**config2.xml:**
```xml
<context:component-scan base-package="com.episode.second.episode2.setter" />
```

**config3.xml:**
```xml
<context:component-scan base-package="com.episode.second.episode2.field" />
```

---

## Package Structure

```
episode2/
└── src/main/java/com/episode/second/episode2/
    ├── Main1ConstructorInjection.java  → Run constructor example
    ├── Main2SetterInjection.java       → Run setter example
    ├── Main3FieldInjection.java        → Run field example
    │
    ├── constructor/
    │   ├── CarConstructorInjection.java
    │   └── EngineConstructorInjection.java
    │
    ├── setter/
    │   └── CarSetterInjection.java
    │       (Uses Engine from classes package)
    │
    └── field/
        ├── CarFieldInjection.java
        └── EngineFieldInjection.java
```

---

## Quick Start

1. **Constructor Injection (Best):**
   - Open `Main1ConstructorInjection.java`
   - Run it → See: Engine Type: V8 Turbo, HP: 500

2. **Setter Injection (Optional):**
   - Open `Main2SetterInjection.java`
   - Run it → See: Engine Type: V8, HP: 300

3. **Field Injection (Learn only):**
   - Open `Main3FieldInjection.java`
   - Run it → See: Engine Type: V6, HP: 300

---

## Summary Table

| Type | How | When | Best? |
|------|-----|------|-------|
| Constructor | Via constructor param | Required deps | ✅ YES |
| Setter | Via setter method | Optional deps | ⚠️ Sometimes |
| Field | Direct to field | Learning only | ❌ NO |

---

*All three examples work independently with their own config files.*

Boom! Simple aur clean! 🚀
