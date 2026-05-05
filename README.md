# GreenFlavors Manager - Desktop ERP System

## Overview
GreenFlavors Manager is a professional inventory management system designed for food warehouses. The application manages complex storage requirements and automates essential business processes while ensuring data consistency between the user interface and a MySQL database.

## Core Functionality
* **Authentication**: Secure login system that validates user credentials against a database to control system access.
* **Storage Management**: Implements logical validation to separate products based on temperature requirements (Standard vs. Cold Storage), preventing improper handling of goods.
* **Persistent CRUD**: Complete management of the product lifecycle, including creation, retrieval, updates, and logical deletion with permanent storage.
* **Sales and Transaction Module**: Handles real-time sales by calculating subtotals, VAT (19%), and generating standardized receipts in the console.
* **Stock Integrity**: Uses atomic SQL operations to update quantities, ensuring that stock levels remain synchronized during transactions.
* **Inventory Monitoring**: System-wide alerts for products that fall below the critical threshold of 10 units.

## Technical Stack
* **Language**: Java 23.
* **Database**: MySQL with JDBC for persistent storage and secure connectivity.
* **Architecture**: Object-Oriented Design focusing on the "Separation of Concerns" principle (Model-Service-UI layers).
* **Version Control**: Git and GitHub.

## System Architecture
* src/model: Domain entities and storage abstractions (Product, WarehouseArea).
* src/service: Business logic layer handling database transactions and inventory management.
* src/ui: Interface layer managing user input and console output.
* src/exception: Specialized error handling for storage and capacity constraints.

## Installation
1. Clone the repository to your local machine.
2. Configure the MySQL environment using the provided database schema.
3. Set the DB_PASSWORD environment variable for database authentication.
4. Execute Main.java to start the application.

---
*Developed by Brânzea Gabriel-Florin as part of a Java Backend career transition.*