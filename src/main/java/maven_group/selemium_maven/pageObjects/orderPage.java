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

public class orderPage extends AbstractComponents {
	
	WebDriver driver;

	
	public orderPage(WebDriver driver) 
	{   super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= "tr td:nth-child(3)")
	List<WebElement> orderedProduct;
	
			
	public boolean verifyOrdersDisplay(String productName)
	{
		Boolean match = orderedProduct.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	
	
	
	
	
	



	
	
	
	
	
}
