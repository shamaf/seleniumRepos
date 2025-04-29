package maven_group.selemium_maven.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.Assert;

import maven_group.AbstractComponents.AbstractComponents;

public class ThankyouPage extends AbstractComponents {
	
	WebDriver driver;

	
	public ThankyouPage(WebDriver driver) 
	{   super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	By heroprimary = By.cssSelector(".hero-primary");
	@FindBy(css= ".hero-primary")
	WebElement message;
	
		
	
	
	//List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	public String message() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the top of the page
        js.executeScript("window.scrollBy(0,-350)");
		waitForElementToAppear(heroprimary);
		Thread.sleep(2000);

		String confirmationMessage = message.getText();
		return confirmationMessage;

		
	}
	
	
	
	
	
	



	
	
	
	
	
}
