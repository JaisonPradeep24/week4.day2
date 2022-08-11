package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArchitectCertifications {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		// Login to URL
		driver.get("https://login.salesforce.com");

		// Maximise
		driver.manage().window().maximize();

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Loging in with credentials
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']"))
				.sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).sendKeys("Password$123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// 3. Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		// Window handles
		String FirstWindow = driver.getWindowHandle();
		System.out.println("The First Window title is : " + FirstWindow);
		Set<String> windowHandles = driver.getWindowHandles();
		java.util.List<String> allWindows = new ArrayList<String>(windowHandles);
		String secWindow = allWindows.get(1);
		System.out.println("Total no of windows are : " + windowHandles.size());

		// Switching to secondWindow
		driver.switchTo().window(secWindow);

		// confirm to redirect
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		System.out.println("Title of the page is : " + driver.getTitle());

		// clicking on the resources
		driver.findElement(By.linkText("Resources")).click();
		System.out.println("Resources link is clicked");

		Shadow dom = new Shadow(driver);
		// clicking on the Learning
		Thread.sleep(3000);
		WebElement learningXpath = dom.findElementByXPath("//span[text()='Learning']");
		learningXpath.click();
		System.out.println("Learning is clicked");

		WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		Thread.sleep(3000);
		Actions builder = new Actions(driver);

		builder.moveToElement(trailHead).perform();
		System.out.println("Moved to trail head");

		WebElement salesForceCrt = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		builder.scrollToElement(salesForceCrt).perform();
		Thread.sleep(2000);
		salesForceCrt.click();
		// click on adminstrator certification
		driver.findElement(By.xpath("//a[text()='Administrator']")).click();
		System.out.println("The title of the window is : " + driver.getTitle());

		List<WebElement> listOfCertifications = driver.findElements(
				By.xpath("//div[text()='Certification']//following::div/a[contains(@href,'/en/credentials')]"));

		for (int i = 0; i < listOfCertifications.size(); i++) {

			System.out.println("List of certifications in Architect are : " + listOfCertifications.get(i).getText());

		}

	}

}
