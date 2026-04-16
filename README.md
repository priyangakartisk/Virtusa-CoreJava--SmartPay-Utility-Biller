# Virtusa-CoreJava--SmartPay-Utility-Biller

SmartPay is a **console-based Java program** that acts like a digital electricity billing counter.

You walk up to it, type your name and meter readings, and it prints out a receipt showing exactly how much you owe — including taxes and which pricing slab you fall under.

# How to Run It

```bash
# Step 1 — Compile (converts Java code into something your computer understands)
javac SmartPay.java

# Step 2 — Run
java SmartPay
```

---

## Program Structure — The Big Picture

The file has **3 building blocks**:

```
SmartPay.java
│
├── interface Billable          ← A CONTRACT (rules every bill must follow)
│
├── class CustomerBill          ← The BRAIN (does all the math)
│   ├── calculateBaseCharge()   ← Applies slab rates
│   ├── calculateTotal()        ← Adds tax on top
│   └── printReceipt()          ← Prints the formatted bill
│
└── class SmartPay (main)       ← The ENTRY POINT (talks to the user)
    ├── main()                  ← Runs the loop
    └── readInt()               ← Safely reads numbers
```
You can look into "Screenshot.png" for outputs 
