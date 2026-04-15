# 🌿 GreenFlavors Manager - Desktop ERP System

A robust inventory management system designed for food warehouses, focusing on storage zone separation (Standard vs. Cold Storage) and automated sales processing.

## 🚀 Key Features
* **Secure Authentication System**: User access control based on credentials.
* **Full CRUD Management**: Create, Read, Update, and Delete products with persistent storage.
* **Smart Storage Logic**: Automated routing of products to specific zones based on temperature requirements (Standard or Cold Storage).
* **Sales Simulation**: Real-time stock deduction upon transaction.
* **Critical Stock Alerts**: Visual console notifications when product levels drop below the minimum threshold.

## 🛠️ Tech Stack
* **Java Core (JDK 17+)**: Advanced OOP concepts (Interfaces, Abstract Classes, Polymorphism).
* **MySQL**: Relational database for persistent inventory and user data.
* **JDBC**: Secure connectivity between Java and SQL database.
* **Git & GitHub**: Version control and source code management.

## 📁 Project Architecture
* `src/model`: Data entities and abstractions (Product, Storable, WarehouseArea).
* `src/service`: Business logic layer (InventoryManager, AuthService).
* `src/ui`: User interaction (Console-based Menu system).
* `src/exception`: Custom error handling (StorageException).

## 💻 Setup and Installation
1. Clone the repository: `git clone https://github.com/GabrielFlorin997/GreenFlavors-Manager.git`
2. Configure the MySQL database using the provided schema.
3. Add the MySQL Connector J driver to the `lib/` folder.
4. Run `Main.java` to start the application.

---
*Developed by **Brânzea Gabriel-Florin** as part of a Java Backend career transition.*