package maven_group.selemium_maven.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import maven_group.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;

	
	public LandingPage(WebDriver driver) 
	
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
		
	}
	
	public void goTo() throws InterruptedException
	{
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		Thread.sleep(1000);
	
	}
	
	public String getErrorMessage()
	{
		waitForwebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	
}
