package buy_product;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class BuyDress {

	public static void main(String[] args) throws InterruptedException, IOException {
		//Initializing browser and web page
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in/");

		String parentID = driver.getWindowHandle();
	
		// Search Product(dress)
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBox.sendKeys("dress");
		searchBox.submit();

		// select dress on search page
		WebElement dress = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-base a-text-center'])[1] "));
		dress.click();

		//switch web-page focus on new opened Tab using windowHandle
		Set<String> allTabID = driver.getWindowHandles();
		for (String tabID : allTabID) {
			if (!tabID.contains(parentID)) {
				driver.switchTo().window(tabID);
               
				 //find drop-down element and scroll up to that element
				WebElement sizedrop = driver.findElement(By.xpath("//select[@name='dropdown_selected_size_name']"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", sizedrop);
				//select Size in Drop-down-list using select class`	
				Select sel = new Select(sizedrop);
				sel.selectByVisibleText("M");

				// click on add to cart button
				WebElement addcartbtn = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
				addcartbtn.click();
				//implicit wait
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				WebElement gotocart = driver.findElement(By.xpath("//span[@id='sw-gtc']/span"));
				gotocart.click();

				// validate quantity using assertion and print 
				WebElement qtyDrop = driver
						.findElement(By.xpath("//span[@class='a-button a-button-dropdown quantity']"));
				String quantity = qtyDrop.getText();
				System.out.println("Selected dress quantity:" + quantity);
				SoftAssert sa = new SoftAssert();
				sa.assertTrue(qtyDrop.isDisplayed());

				// validate prise using assertion and print 
				WebElement priseElement = driver.findElement(By.xpath(
						"//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"));
				String prise = priseElement.getText();
				System.out.println("Product prise is:" + prise);
				sa.assertNotNull(priseElement, "prise should not be null");
				sa.assertAll();

				// proceed to buy
				WebElement buyButton = driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
				buyButton.click();
				//explicit wait
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.titleIs("Amazon Sign In"));
				// Take Screnshot of sign up page
				String path = System.getProperty("user.dir") + "/Screenshot/screenshot1.png";
				File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				File destinationFile = new File(path);
				FileHandler.copy(sourceFile, destinationFile);
				System.out.println("Screenshot Captured...!");

			}

		}
	}
}
