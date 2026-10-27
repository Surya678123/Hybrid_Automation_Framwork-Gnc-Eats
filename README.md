# GNC-Eats UI Automation Framework

A modular, data-driven UI test automation framework built for the **GNC-Eats** platform using **Java**, **Selenium WebDriver**, and **TestNG** based on the **Page Object Model (POM)** architectural pattern.

---

## 📌 Architecture & Design Patterns
- **Page Object Model (POM):** Decoupled web elements and page actions from test assertion logic for high maintainability.
- **Data-Driven Framework:** Externalized test inputs configured via `testdata/` to test multiple user workflows without hardcoding.
- **Robust Execution Engine:** TestNG suite management (`testng.xml`) with prioritized execution, parallel configurations, and custom listeners.
- **Reporting & Logging:** Automated test failure capture with dynamic screenshot generation (`screenshots/`), HTML execution logs (`reports/`), and structured tracing with Log4j2 (`logs/`).
- **Synchronizations:** Dynamic explicit waits (`WebDriverWait`) implemented across `BasePage` to handle asynchronous DOM updates and avoid flaky runs.

---

## 🛠️ Tech Stack & Dependencies
- **Language:** Java (JDK 1.8 / 17)
- **Automation Engine:** Selenium WebDriver
- **Test Framework:** TestNG
- **Build Tool:** Apache Maven
- **Design Pattern:** Page Object Model (POM)
- **Reporting:** ExtentReports / TestNG Default Reports
- **Logging:** Log4j2

---

## 📂 Project Structure
```text
GNC-Eats UI Automation/
├── src/test/java/              # Test suites, Page Objects, BasePage, Listeners
├── src/test/resources/         # Configuration files, environment properties
├── testdata/                   # Test data sheets & execution payloads
├── testng.xml                  # Test suite runner & configuration
├── pom.xml                     # Maven build configuration & dependencies
├── .gitignore                  # Git exclusions for test artifacts
└── README.md                   # Project documentation
