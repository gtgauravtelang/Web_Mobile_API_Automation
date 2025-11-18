# Automation Suite Guide for Manual QA Testers

## 📋 What is This Automation Suite?

Think of this as a **robot assistant** that can automatically test your application in three different ways:
1. **Web Testing** - Tests websites in a browser (like Chrome)
2. **Mobile Testing** - Tests mobile apps on Android devices
3. **API Testing** - Tests the backend services without using a browser

Instead of manually clicking through the application every time, this suite does it automatically and tells you if everything works correctly.

---

## 🏗️ How is It Organized? (The Structure)

Imagine this like a **recipe book** with three sections:

```
📁 Web_Mobile_API_Automation
├── 📁 src/main/java/org/example/
│   ├── 📁 pagesWEB/          → Web page elements (like buttons, text fields)
│   ├── 📁 pagesMobile/       → Mobile app elements
│   └── 📁 pagesAPI/          → API endpoints and methods
│
├── 📁 src/test/
│   ├── 📁 resources/features/  → Test scenarios written in plain English (like recipes)
│   │   ├── WebsiteTesting/     → Web test scenarios
│   │   ├── MobileTesting/      → Mobile test scenarios
│   │   └── APITesting/         → API test scenarios
│   │
│   └── 📁 java/org/example/
│       ├── stepDefWEBlogin/    → Code that executes web test steps
│       ├── stepDefMobile/      → Code that executes mobile test steps
│       └── stepDefAPItests/    → Code that executes API test steps
```

---

## 📖 Understanding the Test Scenarios (Feature Files)

The test scenarios are written in **plain English** using a format called **Gherkin/Cucumber**. Even if you're not a programmer, you can read and understand them!

### Example: Web Login Test

**File:** `src/test/resources/features/WebsiteTesting/loginUsrPwd.feature`

```gherkin
Feature: Login from homepage using username and password

  Scenario: TC1: To verify login using incorrect username and password
    Given User is on the login page
    When User provided the invalid username and wrong password
    Then User should not be logged in and should be shown an invalid password message

  Scenario: TC2: To verify login using correct username and password
    Given User is on the login page
    When User provided the valid username and password
    Then User should be logged in successfully and should be on homepage
```

**What this means:**
- **Feature:** What feature we're testing (Login functionality)
- **Scenario:** A specific test case
- **Given:** The starting condition (user is on login page)
- **When:** The action being performed (entering credentials)
- **Then:** The expected result (what should happen)

This is exactly like writing a manual test case, but in a structured format!

---

## 🔍 The Three Types of Tests

### 1. 🌐 Web Testing (Selenium)

**What it does:** Automates browser testing - opens Chrome, navigates to websites, clicks buttons, fills forms, etc.

**Example Test:**
- Opens a login page
- Enters username and password
- Clicks submit button
- Verifies if login was successful or error message appears

**Files to know:**
- **Feature file:** `src/test/resources/features/WebsiteTesting/loginUsrPwd.feature`
- **Page Object:** `src/main/java/org/example/pagesWEB/loginUsrPwd_page.java` (contains all the page elements like buttons, text fields)
- **Step Definitions:** `src/test/java/org/example/stepDefWEBlogin/loginUsrPwd_stepDef.java` (the actual code that performs actions)

**Current Tests:**
- ✅ TC1: Test login with wrong password (should show error)
- ✅ TC2: Test login with correct credentials (should succeed)

---

### 2. 📱 Mobile Testing (Appium)

**What it does:** Tests Android mobile applications - taps buttons, enters text, navigates screens, etc.

**Example Test:**
- Opens MakeMyTrip app on Android device
- Navigates to flight booking page
- Enters source, destination, and travel date
- Verifies booking confirmation page appears

**Files to know:**
- **Feature file:** `src/test/resources/features/MobileTesting/MobileflightFinder.feature`
- **Page Object:** `src/main/java/org/example/pagesMobile/flightFinderPage.java`
- **Step Definitions:** `src/test/java/org/example/stepDefMobile/FlightFinderStepDef.java`

**Current Tests:**
- TC-1: Experiment test
- TC-2: Navigate to flights page and exit
- TC-3: Find flight with source, destination, and date

**Note:** Requires Appium server running and Android device/emulator connected.

---

### 3. 🔌 API Testing (Rest Assured)

**What it does:** Tests backend APIs directly - sends HTTP requests (GET, POST, etc.) and verifies responses.

**Example Test:**
- Sends a GET request to an API endpoint
- Verifies the response code is 200 (success)
- Validates the response data structure

**Files to know:**
- **Feature file:** `src/test/resources/features/APITesting/APItests.feature`
- **API Page:** `src/main/java/org/example/pagesAPI/APItests_page.java`
- **Step Definitions:** `src/test/java/org/example/stepDefAPItests/APItests_stepDef.java`

**Current Tests:**
- TC1_API_get200: Verify API returns 200 status code

---

## 🎯 How Tests Are Executed (The Flow)

Think of it like a **playbook**:

1. **You write the test scenario** in plain English (the feature file)
   - Example: "User is on the login page"

2. **The step definition code** reads that scenario and performs the action
   - Example: Opens Chrome browser, navigates to login URL

3. **The page object** contains all the elements needed
   - Example: Username field, Password field, Submit button

4. **The test runner** executes everything and generates reports
   - Example: `testRunnerCucWEB.java` runs all web tests

---

## 🚀 How to Run the Tests

### Prerequisites:
- ✅ Java installed
- ✅ Maven installed
- ✅ Chrome browser installed (for web tests)
- ✅ Appium server running (for mobile tests - if needed)

### Running Tests from Terminal:

#### Run ALL tests (configured in testNGCucumber.xml):
```powershell
mvn clean test
```

#### Run only WEB tests:
```powershell
mvn test -Dtest=testRunnerCucWEB
```

#### Run only API tests:
```powershell
mvn test -Dtest=testRunnerCucAPI
```

#### Run only Mobile tests:
```powershell
mvn test -Dtest=testRunnerCucMobile
```

---

## 📊 Test Reports

After running tests, you get reports in multiple formats:

### 1. **Console Output**
- Shows test progress in real-time
- Displays pass/fail status
- Shows any error messages

### 2. **HTML Cucumber Reports**
- Location: `resources/cucumber-reports/cucu-report.html`
- Beautiful visual report with:
  - Test scenarios with pass/fail status
  - Step-by-step execution details
  - Screenshots (if configured)

### 3. **Extent Reports**
- Advanced reporting with charts and graphs
- Shows test execution timeline
- Detailed test results

### 4. **Online Cucumber Report**
- Temporary link provided after test execution
- Shareable report (expires in 24 hours)

---

## 📝 Understanding the TestNG XML Configuration

**File:** `src/test/resources/testNGCucumber.xml`

This file controls **which tests run** when you execute `mvn test`:

```xml
<test name="Regression Tests - Website">
    <classes>
        <class name="runnerCucumberPkg.testRunnerCucWEB"/>
    </classes>
</test>
```

**Currently Active:**
- ✅ Website tests (WEB) - **ENABLED**
- ❌ Mobile tests - **DISABLED** (commented out)
- ❌ API tests - **DISABLED** (commented out)

**To enable/disable tests:**
- Uncomment the `<test>` section to enable
- Comment out the `<test>` section to disable

---

## 🎓 Key Concepts Explained Simply

### **Page Object Model (POM)**
- **What it is:** A design pattern that stores all page elements (buttons, text fields) in one place
- **Why it's good:** If a button ID changes, you only update it in one file, not everywhere
- **Example:** `loginUsrPwd_page.java` contains all login page elements

### **Step Definitions**
- **What it is:** The actual code that performs the actions described in feature files
- **Example:** When feature file says "User is on the login page", step definition opens the browser and navigates to login URL

### **Cucumber/Gherkin**
- **What it is:** A way to write tests in plain English that both humans and machines can understand
- **Benefit:** Non-programmers can read and write test scenarios

### **TestNG**
- **What it is:** A testing framework that runs tests and generates reports
- **Benefit:** Can run tests in parallel, group tests, generate detailed reports

---

## 🔧 Common Tasks for Manual QA

### **Adding a New Test Scenario:**

1. Open the feature file (e.g., `loginUsrPwd.feature`)
2. Add a new scenario following the format:
   ```gherkin
   Scenario: TC3: Your test description
     Given Some starting condition
     When Some action is performed
     Then Expected result should occur
   ```
3. The step definitions might already exist, or you'll need a developer to add them

### **Understanding Test Results:**

- **✅ PASSED:** Test executed successfully, expected result matched
- **❌ FAILED:** Test executed but expected result didn't match
- **⚠️ ERROR:** Test couldn't execute (e.g., element not found, connection failed)
- **⏭️ SKIPPED:** Test was skipped (e.g., prerequisite failed)

### **Reading Error Messages:**

When a test fails, look for:
- **What step failed:** "Then User should be logged in..."
- **Why it failed:** "Expected: 'Logged In Successfully' but found: 'Login Failed'"
- **Where it failed:** The line number in the step definition file

---

## 📚 Summary

This automation suite is like having a **robot tester** that:
- ✅ Runs tests automatically (no manual clicking needed)
- ✅ Tests web, mobile, and API in one place
- ✅ Generates detailed reports
- ✅ Can run tests repeatedly (regression testing)
- ✅ Saves time and catches bugs early

**As a manual QA, you can:**
- Read and understand test scenarios (feature files)
- Add new test scenarios in plain English
- Run tests and interpret results
- Review test reports
- Work with developers to add new test cases

---

## 🆘 Need Help?

- **Test not running?** Check if Chrome is installed (for web tests)
- **Mobile tests failing?** Ensure Appium server is running and device is connected
- **API tests failing?** Check if the API endpoint is accessible
- **Can't find reports?** Check `resources/cucumber-reports/` folder

---

**Remember:** Automation doesn't replace manual testing - it complements it! Use automation for repetitive tests, and manual testing for exploratory testing and user experience validation.

