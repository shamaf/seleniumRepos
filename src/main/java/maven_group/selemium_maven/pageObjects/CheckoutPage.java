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

import maven_group.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;

	
	public CheckoutPage(WebDriver driver) 
	{   super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= "[placeholder = 'Select Country']")
	WebElement selectCountry;
	
	@FindBy(css= ".ta-item:nth-of-type(2)")
	WebElement country;

	@FindBy(css= ".action__submit")
	WebElement action_button;
	
	
	By taresults = By.cssSelector(".ta-results");
	By actionbutton = By.cssSelector(".action__submit");
	

	
		
	
	
	//List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	public void Address(String countryname) throws InterruptedException
	{
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry, countryname).build().perform();
		
		waitForElementToAppear(taresults);
		//driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
       Thread.sleep(3000);
		country.click();
		Thread.sleep(3000);

		
	}
	
	public ThankyouPage submit() throws InterruptedException
	{
		
		waitForElementToAppear(actionbutton);
		//Thread.sleep(3000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        //Scroll down till the bottom of the page
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	       Thread.sleep(3000);
	        action_button.click();
	        ThankyouPage thankyoupage = new ThankyouPage(driver);
	        return thankyoupage;
	}
	
	
	
	
	
	
	



	
	
	
	
	
}
