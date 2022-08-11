package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		//login Nykaa
		driver.get("https://www.nykaa.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		// Mouseover on Brands and Search L'Oreal Paris
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		Thread.sleep(2000);
		builder.moveToElement(brands).perform();
		
		//Click L'Oreal Paris using searching box and Check the title contains L'Oreal Paris(Hint-GetTitle)
		WebElement brandSearchBox = driver.findElement(By.id("brandSearchBox"));
		Thread.sleep(3000);
		brandSearchBox.sendKeys("L'Oreal Paris", Keys.ENTER);
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		System.out.println("The title of the page is :" + driver.getTitle());

		//Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		// Click Category 
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(2000);
		System.out.println("Category is clicked");
		
		//click hair
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(2000);
		System.out.println("Hair is clicked");
		
		//Hair care is clicked
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(3000);
		System.out.println("Hair Care is clicked");
		
		//Shampoo is clicked
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		System.out.println("Shampoo is clicked");
		Thread.sleep(3000);
		
		//Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		System.out.println("Concern is clicked");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();	
		System.out.println("Color Protection is clicked");
		

			
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.className("css-xrzmfa")).click();

		//window handles
		//String firstWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		java.util.List<String> allWindows = new ArrayList<String>(windowHandles);
		String secWindow = allWindows.get(1);

		//switching to sec window and select size as 175ml
		driver.switchTo().window(secWindow);
		WebElement sizeOfShampoo = driver.findElement(By.className("css-2t5nwu"));
		Select sec = new Select(sizeOfShampoo);
		sec.selectByIndex(1);
		
		//Print the MRP of the product
		WebElement priceOfShampoo = driver.findElement(By.xpath("//span[@class='css-u05rr']/following::span"));
		System.out.println("The price of shampoo is : " + priceOfShampoo.getText());

		// add to bag
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();

		// shopping bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();

		// Iframe
		driver.switchTo().frame(0);

		//Print the Grand Total amount
		String grandTotal = driver.findElement(By.xpath("(//span[text()='Grand Total']/following::div)[1]")).getText();
		System.out.println("Grand total of the product is : " + grandTotal);


		// proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

		// continue as guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();

		// final grand total and converting web element to string
		WebElement finalGrandTotal = driver.findElement(By.xpath("(//div[text()='Grand Total']/following::div)[1]"));
		System.out.println("Final Grand total of the product is : " + finalGrandTotal.getText());
		String finalGrandTotal1 = finalGrandTotal.getText();		
		
		if (grandTotal.contains(finalGrandTotal1))

		{
			System.out.println("The Grand total and the final Grand total price is same ");
		} else {
			System.out.println("Price is Not Same ");
		}

		System.out.println("Title of the page is " + driver.getTitle());
		Thread.sleep(5000);
		driver.quit();		
		
	}

}
