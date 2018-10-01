package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

//In the back ground Result.java class will be executing
@Listeners(Result.class)
abstract public class BaseTest implements IAutoConst {
	public WebDriver driver;
	
	public String url = Utility.getPropertyValue(CONFIG_PATH, "URL");
	//public String url = URL_PATH; --->???
	String ITO = Utility.getPropertyValue(CONFIG_PATH, "ITO");
	public long duration = Long.parseLong(ITO);
	//public long duration = 10;
	
	static {
		System.setProperty(CHROME_KEY,CHROME_VAL);
		System.setProperty(GECKO_KEY, GECKO_VAL);
	}

	@Parameters({"ip","browser"})
	@BeforeMethod
	public void openApplication(@Optional("localhost")String ip, @Optional("chrome")String browser) {
		//driver = new ChromeDriver();
		//driver=Utility.openBrowser(driver, "localhost", "chrome");
		driver=Utility.openBrowser(driver, ip, browser);
		driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
		driver.get(url);
		Reporter.log("open Application",true);
	}
	
	@AfterMethod
	public void closeApplication(ITestResult result) {
		String name = result.getName();
		int status = result.getStatus();
		if (status == 2) {
			Utility.getPhoto(driver, PHOTO_PATH);
			Reporter.log("Test Method: "+name+" is FAILED & photo is "+PHOTO_PATH,true);
		}
		else {
			Reporter.log("Test Method: "+name+" is PASSED & NO photo",true);
		}
		//driver.quit();
	}
}

//Here we haven't used System.setProperty (We can also use) - 
//Coz we have set the WebDriver path in Environmental variables itself
//Else we get "IllegalStateException"-
//So we use Static block inside we use System.SetProperty
//2nd arg - path of a .exe file of a WebDriver 
//So to give the path , we need to create a folder "driver" where we copy chrome & gecko driver exe
//If WebDriver is a "Static" - selenium Grid doesn't work 
//i.e, we cannot run a script on multiple browser parallely

//Remaining variables are "public static final" 
//Hence best place to place them is interface
//As Interface has only "public static final" variables

//DO not make "URL" static & final coz different builds may have diff Url
//But it's preferable to take it from excel sheet 
//coz Manual test engineer shud also able to run the Automation script 
//by reading a data from excel file which is readable by Manual Test Engineer