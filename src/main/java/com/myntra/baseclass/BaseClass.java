package com.myntra.baseclass;

import java.security.PublicKey;
import java.sql.DriverManager;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static ChromeOptions options = new ChromeOptions();

	public static WebDriver browserlaunch(String browser) {

		try {
			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		return driver;
	}

	public static void getBrowserPort() {

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		Map<String, Object> myCap = cap.asMap();
		System.out.println(
				"Port:" + myCap.get("goog:chromeOptions").toString().replace("debuggerAddress=localhost:", ""));

	}

	public static WebDriver browserlaunch(String browser, String Port) {

		try {
			if (browser.equals("chrome")) {
				Port = "localhost:" + Port;

				WebDriverManager.chromedriver().setup();

				options.setExperimentalOption("debuggerAddress", Port);
				driver = new ChromeDriver(options);

			} else if (browser.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		return driver;
	}

	public static void geturl(String url) throws Exception {
		try {
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("INVALID URL");
		}

	}

	public static void mousehover(WebElement element) {
		try {
			Actions ac = new Actions(driver);
			ac.moveToElement(element).build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String currenturl() {
		try {
			String currentUrl = driver.getCurrentUrl();
			System.out.println(currentUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currenturl();
	}

	public static void scroll(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrolltoView", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean elementisdisplay(WebElement element) {
		boolean displayed = false;
		try {
			displayed = element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return displayed;
	}

	public static boolean elementisenable(WebElement element) {
		boolean enabled = false;
		try {
			enabled = element.isEnabled();
			System.out.println(enabled);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enabled;

	}

	public static boolean elementiselected(WebElement element) {
		boolean selected = false;
		try {
			selected = element.isSelected();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selected;
	}

	public static void sendkeys(WebElement element, String value) throws Throwable {
		try {
			if (elementisdisplay(element) && elementisenable(element)) {
				element.clear();
				element.sendKeys(value);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("value is not present");
		}
	}

	public static String gettitle() {
		String title = null;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}

	public static void dropdown(WebElement element, String option, String i) {
		Select s = new Select(element);
		try {
			if (option.equals("index")) {
				s.selectByIndex(Integer.parseInt(i));

			} else if (option.equals("text")) {
				s.selectByVisibleText(i);
			} else if (option.equals("value")) {
				s.selectByValue(i);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void clickelement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void navigateto(String url) {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void navigateforward() {
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void navigatebackward() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void Pagerefresh() {

		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void elementGetText(WebElement element) {

		try {
			System.out.println(element.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void elementgetattribute(WebElement element) {
		try {
			String attribute = element.getAttribute("value");
			System.out.println(attribute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void actionsClick(WebElement element) {
		Actions ac2 = new Actions(driver);
		ac2.click(element).build().perform();
	}

	public static void rightClick(WebElement element) {
		Actions ac3 = new Actions(driver);
		ac3.contextClick(element).build().perform();

	}

	public static void actionsSendkeys(WebElement element, String value) {
		Actions ac4 = new Actions(driver);
		ac4.sendKeys(element, value).build().perform();
	}

	public void dragdrop(WebElement element1, WebElement element2) {

		Actions ac = new Actions(driver);
		ac.dragAndDrop(element1, element2).build().perform();

	}

	public void iframes(WebElement elements) {
		driver.switchTo().frame(elements);

	}

	public static void waitforvisibilityofelment(WebElement element) {

		try {
			WebDriverWait wb = new WebDriverWait(driver, 30);
			wb.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// is displayed

	public static boolean elementisDisplayed(WebElement element) {
		boolean displayed = false;

		try {
			displayed = element.isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return displayed;

	}

	// is enabled

	public static boolean elementisenabled(WebElement element) {
		boolean enabled = false;

		try {
			enabled = element.isEnabled();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enabled;

	}

	// is selected

	public boolean elementisselected(WebElement element) {

		boolean selected = false;
		try {
			selected = element.isSelected();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selected;

	}

	public static WebElement waitForElementPresent(WebDriver driver, final By by, int timeOutInSeconds) {

		WebElement element;

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // Nullify implicitlyWait()

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // Reset implicitlyWait
			return element; // Return the element

		} catch (Exception e) {

		}
		return null;
	}
}
