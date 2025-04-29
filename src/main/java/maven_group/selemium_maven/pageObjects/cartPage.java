package maven_group.selemium_maven.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;

import maven_group.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents {
	
	WebDriver driver;

	
	public cartPage(WebDriver driver) 
	{   super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css= ".totalRow button")
	WebElement buttonele;
	By button = By.cssSelector(".totalRow button");
	
		
	public boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	//List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	public CheckoutPage gotoCheckoutPage() throws InterruptedException
	{
		Thread.sleep(4000);
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        //Scroll down till the bottom of the page
		 js.executeScript("window.scrollBy({ top: document.body.scrollHeight, behavior: 'smooth' });");
		 Thread.sleep(2000);
	        waitForElementToAppear(button);
		buttonele.click();
		Thread.sleep(3000);
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
		

		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));

		
	}
	
	
	
	
	
	



	
	
	
	
	
}
