package maven_group.selemium_maven.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import maven_group.selemium_maven.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initialiseDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\maven_group\\selemium_maven\\resources\\GlobalData.properties");
		prop.load(fis);
		//Browser name can be picked up at run time via maven command line using ternary operators through system property level.
		//String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//For maven/jenkins run, comment below line and uncomment above line
		String browserName = prop.getProperty("browser");
		if (browserName.contains("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			
			
			
			if (browserName.contains("headless"))
			{
				options.addArguments("headless");
				
			}
					
			driver = new ChromeDriver(options);
			//driver = new ChromeDriver();
			//to avoid flaky tests where element is not visible
			driver.manage().window().setSize(new Dimension(1440,900)); //full screen
	 			}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			
			/* System.setProperty("webdriver.gecko.driver", "C:\\Users\\Shama_Firoz\\drivers\\geckodriver.exe");
			
			FirefoxProfile profile = new FirefoxProfile();
		    FirefoxOptions options = new FirefoxOptions();
		    profile.setPreference("javascript.enabled", "False");
		    options.setProfile(profile);

		    driver = new FirefoxDriver(); */
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public  List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		
		//read JSON to string
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
	
	//String to Hashmap Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException, InterruptedException
	{
		
		driver= initialiseDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException
	{
		driver.quit();
		//System.exit(0);
	//	Thread.sleep(1000);
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" +testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" +testCaseName+".png";
		
		
	}

}
