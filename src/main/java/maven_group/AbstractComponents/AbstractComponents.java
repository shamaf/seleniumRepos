package maven_group.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import maven_group.selemium_maven.pageObjects.cartPage;
import maven_group.selemium_maven.pageObjects.orderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForwebElementToAppear(WebElement findby1)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5) );
		wait.until(ExpectedConditions.visibilityOf(findby1));
	}

	public void waitForElementToAppear(By findby)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5) );
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5) );
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public cartPage gotoCartPage()
	
	{
		cartHeader.click();
		cartPage cartpage = new cartPage(driver);
		return cartpage;
	}
	
public orderPage gotoOrders()
	
	{
		orderHeader.click();
		orderPage orderpage = new orderPage(driver);
		return orderpage;
	}
	
}
