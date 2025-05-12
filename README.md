Here is the complete **directory structure**, **file explanations**, and **setup instructions** for **Task 18: Create a Selenium Test Suite for Login Form Validation**, formatted similarly to Tasks 12 & 17:

---

## ✅ Project Folder Structure: `SeleniumLoginTest`

```
SeleniumLoginTest/
├── lib/                            # Folder for external JARs (JUnit, Selenium, etc.)
│   ├── junit-4.13.2.jar
│   ├── hamcrest-core-1.3.jar
│   └── selenium-java-x.xx.x.jar
├── src/
│   └── LoginTest.java              # Java Selenium test suite
├── web/
│   └── login.html                  # HTML form to test
├── node_modules/                  # (If using Node.js for static hosting)
├── package.json                   # (If http-server is installed locally)
└── README.md                      # (Optional documentation)
```

---

## 🔧 Step-by-Step Setup Guide

### 1. Create the Project Folder

```bash
mkdir SeleniumLoginTest
cd SeleniumLoginTest
```

---

### 2. Create and Host the Login Form

#### 📄 File: `web/login.html`

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
</head>
<body>
    <h1>Login</h1>
    <form id="loginForm">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
    <p id="result"></p>
    <script>
        document.getElementById('loginForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const result = document.getElementById('result');
            if (username === 'admin' && password === 'password123') {
                result.textContent = 'Login successful!';
            } else {
                result.textContent = 'Invalid username or password.';
            }
        });
    </script>
</body>
</html>
```

#### 🌐 Host the Form (using Node.js `http-server`)

```bash
npm install -g http-server
cd web
http-server -p 8080
```

✔ Visit [http://localhost:8080/login.html](http://localhost:8080/login.html) to test.

---

### 3. Create the Selenium Test Suite

#### 📄 File: `src/LoginTest.java`

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe"); // Update this path
        driver = new ChromeDriver();
    }

    @Test
    public void testSuccessfulLogin() {
        driver.get("http://localhost:8080/login.html");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        String result = driver.findElement(By.id("result")).getText();
        assertEquals("Login successful!", result);
    }

    @Test
    public void testInvalidUsername() {
        driver.get("http://localhost:8080/login.html");
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        String result = driver.findElement(By.id("result")).getText();
        assertEquals("Invalid username or password.", result);
    }

    @Test
    public void testInvalidPassword() {
        driver.get("http://localhost:8080/login.html");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        String result = driver.findElement(By.id("result")).getText();
        assertEquals("Invalid username or password.", result);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

---

### 4. Add Required JARs (in `lib/`)

* Download and place the following in the `lib/` folder:

  * `junit-4.13.2.jar`
  * `hamcrest-core-1.3.jar`
  * `selenium-java-x.xx.x.jar` (Use the one matching your Selenium version)
* Add them to IntelliJ project dependencies:

  ```
  File > Project Structure > Libraries > + > Java > Select all JARs in /lib
  ```

---

### 5. Compile and Run Tests (if using terminal)

```bash
cd src
javac -cp "../lib/*" LoginTest.java
java -cp ".:../lib/*" org.junit.runner.JUnitCore LoginTest
```

🖥 **Expected Output:**

```
Time: 1.135
OK (3 tests)
```

---

## 📄 Summary of Components

| File/Folder        | Purpose                                                                    |
| ------------------ | -------------------------------------------------------------------------- |
| `login.html`       | A simple login form for manual and automated testing.                      |
| `LoginTest.java`   | Selenium test suite with 3 cases: valid login, invalid username, password. |
| `http-server`      | Hosts `login.html` on `http://localhost:8080`.                             |
| `lib/`             | Contains required external JARs.                                           |
| `chromedriver.exe` | Launches automated Chrome instance (path set in Java).                     |
| `node_modules/`    | If installed http-server locally using npm.                                |

---

Would you like me to provide a downloadable ZIP structure or IntelliJ project setup screenshots as well?
