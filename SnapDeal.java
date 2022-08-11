package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		// login
		driver.get("https://www.snapdeal.com");
		System.out.println("SNAP DEAL PAGE IS OPENED");
		// Maximize
		driver.manage().window().maximize();
		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Go to Mens Fashion
		WebElement mensFashion = driver
				.findElement(By.xpath("(//a[@class='menuLinks leftCategoriesProduct ']//span)[2]"));

		// Mouse over Action
		Actions builder = new Actions(driver);
		builder.moveToElement(mensFashion).perform();

		// Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		System.out.println("SPORTS SHOE IS CLICKED");
		String countOfTheSportsShoe = driver
				.findElement(By.xpath("(//a[@class='child-cat-node dp-widget-link hashAdded']//div)[2]")).getText();
		System.out.println("Count of the sports shoes are : " + countOfTheSportsShoe);

		// Clicking the training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		System.out.println("TRAINING SHOE IS CLICKED");

		// sorting the filer by LOW to HIGH
		System.out.println("SORTING THE FILTER BY LOW and HIGH");
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		driver.findElement(By.xpath("(//div[contains(text(),'Popularity')]/following::li)[2]")).click();

		// price range from 900 to 1200
		System.out.println("PRICE RANGE IS DONE ");
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("519");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("2199");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();

		Thread.sleep(3000);

		// filter by Navy
		
		driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label[1]")).click();
		System.out.println("FILTER BY NAVY IS SELECTED ");

		Thread.sleep(3000);
		WebElement firstResult = driver.findElement(By.xpath("//p[@class='product-title']"));
		builder.moveToElement(firstResult).perform();

		// click QuickView button
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();

		// print the cost and the discounted percentage
		Thread.sleep(3000);
		WebElement originalPrice = driver.findElement(By.xpath("//div[@class='product-desc-price pdp-e-i-PAY-r ']/span"));
		String cost = originalPrice.getText();
		WebElement discountPrice = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span"));
		String discount = discountPrice.getText();
		System.out.println("Cost of the product :  " + cost + " and the discounted price is : " + discount);

		// close the current window
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();

	}

}
