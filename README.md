# 🏦 Digital Bank Management System

A full-stack **Digital Bank Management System** built with **Java (Spring Boot)**, **Spring Data JPA**, and **MySQL**, with a lightweight HTML/JS dashboard on top. The system digitizes core banking operations — customer onboarding, account creation, deposits/withdrawals, transaction reconciliation, and reporting — through a clean REST API.

---

## 📌 About the Project

This project simulates how a bank manages its customers and accounts internally. Instead of paper registers, every operation — from opening an account to processing a transaction — is handled through REST endpoints backed by a MySQL database, following a layered **Controller → Service → Repository → Entity** architecture.

It's a great reference project for understanding:
- Spring Boot REST API design
- Spring Data JPA / Hibernate with MySQL
- Layered architecture (Controller, Service, Repository, Entity)
- Transactional operations (`@Transactional`) for safe money transfers
- Building a simple frontend that talks to a Spring Boot backend

---

## ✨ Features

### 👤 Customer Management
- Create new customers with full KYC-style details (name, address, mobile, gender, email, Aadhar No., PAN No.)
- Track customer status
- Fetch all customers or a specific customer by ID

### 🏦 Account Management
- Open new bank accounts linked to a customer
- Define **Account Types** (account type, account nature, branch)
- Fetch account details by account number
- Auto-tracked opening date, closing date, and last transaction date

### 💰 Transactions (Recon Module)
- Deposit (credit) and withdraw (debit) money on any account
- Supports multiple transaction modes — **Cash, Transfer, Online**
- Real-time balance validation (prevents withdrawal if balance is insufficient)
- Every transaction is atomic and safe using `@Transactional`
- Automatic transaction timestamp and status (`DONE`) logging

### 📊 Reports
- Generate **all-accounts** transaction reports for a given date range
- Generate **individual account** statements for a given date range
- Verify whether an account number exists in the system

### 🖥️ Frontend
- A built-in single-page dashboard (`index.html`) to interact with the APIs directly from the browser — no separate frontend setup required

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Data Layer | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Build Tool | Maven (with Maven Wrapper `mvnw`) |
| Boilerplate | Lombok |
| Frontend | HTML, CSS, JavaScript (static, served by Spring Boot) |

---

## 🏗️ Project Architecture

```
com.example.Bank
│
├── Controller/          # REST API endpoints
│   ├── AccountController.java
│   ├── CustomerController.java
│   ├── BankController.java
│   ├── ReconController.java
│   └── ReportController.java
│
├── Service/             # Business logic (interfaces + implementations)
│   ├── CreateAccountService / Imp
│   ├── CreateCustomerService / Imp
│   ├── BankService / Imp
│   ├── ReconService / Imp
│   └── ReportService / Imp
│
├── Repositry/           # Spring Data JPA Repositories
│   ├── AccountRepository.java
│   ├── CustomerRepository.java
│   ├── AccountTypeRepository.java
│   └── ReconRepository.java
│
├── Entity/               # Database Entities (tables)
│   ├── AccountMaster.java
│   ├── AccountTypeMaster.java
│   ├── Customerid_detail.java
│   └── Recon.java
│
└── BankApplication.java  # Spring Boot main class
```

---

## 🔌 API Endpoints

### Customer APIs — `/api/customers`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/customers` | Create a new customer |
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |

### Account APIs — `/api/accounts`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/accounts` | Create a new account |
| GET | `/api/accounts/{accNo}` | Get account by account number |

### Bank APIs — `/bank`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/bank/save` | Save account data |
| GET | `/bank/all` | Get all accounts |
| POST | `/bank/type` | Add a new account type |
| GET | `/bank/get-all-types` | Get all account types |
| POST | `/bank/create-customer` | Create a customer |
| GET | `/bank/get-all-customers` | Get all customers |
| POST | `/bank/path` | Open a new account |

### Transaction APIs — `/api/transactions`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/transactions/recon` | Perform a deposit/withdrawal transaction |

### Report APIs — `/api/reports`
| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/reports/all?fromDate=&toDate=` | Get all transactions in a date range |
| GET | `/api/reports/individual?fromDate=&toDate=&accNo=` | Get a single account's statement |
| GET | `/api/reports/verify-account?accNo=` | Check if an account number exists |

---

## ⚙️ Setup & Installation

### Prerequisites
- Java JDK (as configured in `pom.xml`)
- Maven (or use the bundled `mvnw` wrapper)
- MySQL Server running locally

### 1. Clone the repository
```bash
git clone https://github.com/your-username/digital-bank-management-system.git
cd digital-bank-management-system
```

### 2. Create the database
```sql
CREATE DATABASE bankdb;
```

### 3. Configure database credentials
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=root
spring.datasource.password=your_password
```
> ⚠️ Tables are auto-created/updated via `spring.jpa.hibernate.ddl-auto=update` — no manual schema setup needed.

### 4. Run the application
```bash
./mvnw spring-boot:run
```

### 5. Open the app
Visit **`http://localhost:8080`** in your browser to use the built-in dashboard, or hit the REST APIs directly using Postman/cURL.

---

## 🚀 Future Enhancements

- [ ] JWT-based authentication & role-based access (Admin / Teller / Customer)
- [ ] Interest calculation for savings accounts
- [ ] Loan/EMI management module
- [ ] Email/SMS notifications on transactions
- [ ] Downloadable PDF statements
- [ ] Dockerize the application for easy deployment

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork this repo, create a feature branch, and submit a pull request.

```bash
git checkout -b feature/your-feature-name
git commit -m "Add your feature"
git push origin feature/your-feature-name
```

---

## 📄 License

This project is open-source and available for learning purposes. Add a license of your choice (MIT recommended for personal/academic projects).

---

## 👨‍💻 Author

**[priyam tripathi ]**
📧 priyamtripathi6262@gmail.com
🔗 [LinkedIn]=https://www.linkedin.com/in/priyam-tripathi-5107663a9?utm_source=share_via&utm_content=profile&utm_medium=member_android** | [GitHub](#)
