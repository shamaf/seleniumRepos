package maven_group.selemium_maven.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import maven_group.selemium_maven.pageObjects.CheckoutPage;
import maven_group.selemium_maven.pageObjects.LandingPage;
import maven_group.selemium_maven.pageObjects.ProductCatalogue;
import maven_group.selemium_maven.pageObjects.ThankyouPage;
import maven_group.selemium_maven.pageObjects.cartPage;
import maven_group.selemium_maven.testComponents.BaseTest;

public class stepDefinitionImpl extends BaseTest{
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public CheckoutPage checkoutpage;
	public cartPage cartpage;
	public ThankyouPage thankyoupage;
	
	@Given("I landed on the ecommerce page")
	public void I_landed_on_the_ecommerce_page() throws IOException, InterruptedException
	
	{
		//code
		 landingpage = launchApplication();
		 

		
	}
	
	@Given("^Loggedin with username (.+) and (.+)$")
	public void Loggedin_with_username_password(String username, String password)
	{
		productcatalogue = landingpage.loginApplication(username, password);
	}
	
	@When("^I add (.+) to cart$")
	public void I_add_product_to_cart(String productName)	
	{
		List<WebElement> products = productcatalogue.getProductsList();
		productcatalogue.addProductToCart(productName);
	}

	
	@When("^checkout (.+) to submit the order$")
	public void checkout_product_to_submit_the_order(String productName) throws InterruptedException
	{
		cartPage cartpage = productcatalogue.gotoCartPage();
		Boolean match =cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		
		checkoutpage = cartpage.gotoCheckoutPage();
		checkoutpage.Address("India");
		thankyoupage = checkoutpage.submit();
	}
	
	@Then("{string} message is displayed on the confirmation page")
	public void message_is_displayed_on_the_confirmation_page(String string) throws InterruptedException
	{
		Assert.assertEquals(true,thankyoupage.message().equalsIgnoreCase(string));	
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string1) throws InterruptedException
	{
	

		//ng-tns-c4-13 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
		Assert.assertEquals(string1, landingPage.getErrorMessage());
		driver.close();
		//driver.quit();
	}
}
