# 🏥 Smart Hospital Queue Management System

## 📌 Overview
The Smart Hospital Queue Management System is a Java-based desktop application designed to optimize patient handling in hospitals. It replaces traditional first-come-first-served queues with a priority-based system using real-world health parameters.

## 🎯 Objectives
- Reduce patient waiting time  
- Prioritize critical cases effectively  
- Improve hospital workflow  
- Provide a simple GUI-based system  

## ⚙️ Features
- Priority-based scheduling using disease, age, and BMI  
- Disease categories: accident, brain, heart, fracture, fever  
- BMI-based risk analysis using height and weight  
- Real-time queue display  
- Current serving panel with color indication  
- Clean GUI using Java Swing & AWT  

## 🧠 Technologies Used
- Java (Core Java)  
- Swing & AWT  
- PriorityQueue (Heap)  
- Object-Oriented Programming  

## 🧩 OOP Concepts Used
- Encapsulation  
- Abstraction  
- Inheritance  
- Polymorphism  
- Composition  

## 🔢 Priority Logic
- Accident → highest priority  
- Brain / Heart → high priority  
- Fracture → medium  
- Fever → low  
- Age > 60 → +1 priority  
- BMI > 30 → +2 priority  
- BMI > 25 → +1 priority  

## 🧮 BMI Formula
BMI = weight (kg) / (height (m))²

## 🚀 How to Run
javac MainApp.java  
java MainApp  

## 📂 Project Structure
- MainApp.java → GUI + logic  
- Person → patient data  
- Token → priority calculation  
- QueueManager → priority queue handling  

## 🖥️ Workflow
1. Enter patient details  
2. Click Add  
3. System assigns priority  
4. Click Serve  
5. Current patient updates  

## 📈 Advantages
- Efficient queue handling  
- Real-world applicability  
- Simple and intuitive  
- Scalable  

## 🔮 Future Enhancements
- Database integration  
- Multi-counter system  
- Waiting time prediction  
- Web/mobile version  

## 📌 Conclusion
This system demonstrates how OOP and data structures can be applied to solve real-world problems efficiently.

