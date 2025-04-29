package maven_group.selemium_maven.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import maven_group.selemium_maven.pageObjects.ProductCatalogue;
import maven_group.selemium_maven.pageObjects.cartPage;
import maven_group.selemium_maven.testComponents.BaseTest;
import maven_group.selemium_maven.testComponents.Retry;

public class ErrorValidations extends BaseTest {
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "IPHONE 13 PRO";
	//	LandingPage landingPage = launchApplication();
		
		ProductCatalogue productcatalogue = landingPage.loginApplication("shama@yopmail.com", "Tester@123");
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5) );
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		productcatalogue.addProductToCart(productName);
		
		cartPage cartpage = productcatalogue.gotoCartPage();
		//Adding incorrect productname to replicate match=false 
		Boolean match =cartpage.verifyProductDisplay("IPHONE 133 PRO");
		Assert.assertFalse(match);
		System.out.println(match);
		//driver.quit();
	}
	@Test(groups = {"Error Handling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		
		
		String productName = "IPHONE 13 PRO";
	//	LandingPage landingPage = launchApplication();
		
		landingPage.loginApplication("shama@yopmail.com", "Tester@123_wrong");
		//ng-tns-c4-13 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
		//driver.quit();
		
	//	Thread.sleep(2000);
	
	}
}