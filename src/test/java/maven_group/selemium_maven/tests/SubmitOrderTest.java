package maven_group.selemium_maven.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import maven_group.selemium_maven.pageObjects.CheckoutPage;
import maven_group.selemium_maven.pageObjects.LandingPage;
import maven_group.selemium_maven.pageObjects.ProductCatalogue;
import maven_group.selemium_maven.pageObjects.ThankyouPage;
import maven_group.selemium_maven.pageObjects.cartPage;
import maven_group.selemium_maven.pageObjects.orderPage;
import maven_group.selemium_maven.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "IPHONE 13 PRO";

	@Test(dataProvider="getdata", groups ="purchase")
	public void SubmitOrder(HashMap<String,String> input ) throws IOException, InterruptedException
	{
		
		
		
	//	LandingPage landingPage = launchApplication();
		
		ProductCatalogue productcatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5) );
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		productcatalogue.addProductToCart(input.get("product"));
		
		cartPage cartpage = productcatalogue.gotoCartPage();
		Boolean match =cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		
		CheckoutPage checkoutpage = cartpage.gotoCheckoutPage();
		checkoutpage.Address("India");
		ThankyouPage thankyoupage = checkoutpage.submit();
			Assert.assertEquals(true,thankyoupage.message().equalsIgnoreCase("Thankyou for the order."));
		//driver.close();
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		
		ProductCatalogue productcatalogue = landingPage.loginApplication("shama@yopmail.com", "Tester@123");
		orderPage orderpage = productcatalogue.gotoOrders();
		Assert.assertTrue(orderpage.verifyOrdersDisplay(productName));
		System.out.println(orderpage.verifyOrdersDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException
	{
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//maven_group//selemium_maven//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	//	HashMap<String,String> map = new HashMap<String,String>();
	//	map.put("email", "shama@yopmail.com");
	//	map.put("password", "Tester@123");
	//	map.put("product", "IPHONE 13 PRO");
		
	//	HashMap<String,String> map1 = new HashMap<String,String>();
	//	map1.put("email", "maryam@gmail.com");
	//	map1.put("password", "Tester@123");
	//	map1.put("product", "ZARA COAT 3");
		
	//	return new Object[][] {{map},{map1}};
		
		
	}
	
	
}