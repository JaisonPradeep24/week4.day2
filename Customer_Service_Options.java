package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Customer_Service_Options {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		// Sales force URL
		driver.get("https://login.salesforce.com");

		// Maximise
		driver.manage().window().maximize();

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// login
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']"))
				.sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).sendKeys("Password$123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// clicking on LearnMore from Mob Publisher
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

		// clicking on the products
		Shadow dom = new Shadow(driver);
		dom.findElementByXPath("//span[text()='Products']").click();

		// MouseHover actions on Service
		WebElement service = dom.findElementByXPath("//span[text()='Service']");
		Actions builder = new Actions(driver);
		Thread.sleep(2000);
		builder.moveToElement(service).perform();

		WebElement CustService = dom.findElementByXPath("//a[text()='Customer Service']");
		builder.scrollToElement(CustService).perform();
		Thread.sleep(2000);
		CustService.click();

		// to find the list of services available
		List<WebElement> listOfServices = driver.findElements(By.xpath("//h2[@data-equalize='heading1']"));
		//java.util.List<Character> namesOfService = new ArrayList<Character>();
		
		//Take snapshot
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("snap1.png");
		FileUtils.copyFile(source, dest);		
		
		
		/*
		 * for (int i = 0; i < listOfServices.size(); i++) {
		 * listOfServices.get(i).getText(); }
		 */

		System.out.println("Title of the page is :" + driver.getTitle());

	}

}
