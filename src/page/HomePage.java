package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	//Declaration
	@FindBy(id="logoutLink")
	private WebElement logoutBTN;
	
	//private WebDriver driver;
	
	//Initialization
	public HomePage(WebDriver driver){
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickLogoutBtn() {
		logoutBTN.click();
	}
}
