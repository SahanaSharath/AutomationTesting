package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

		//Declaration
	
		@FindBy(id="username")
		private WebElement unTB;
		
		@FindBy(name="pwd")
		private WebElement pwTB;
		
		@FindBy(xpath="//div[text()='Login ']")
		private WebElement loginBTN;
		
		//private WebDriver driver;
		//If u need to use multiple times "driver" the declare a global variable
		//If not, directly pass it to the class constructor
		
		//Initialization
		
		public Login(WebDriver driver){
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		//Utilization
		
		public void setUserName(String un){
			unTB.sendKeys(un);
		}
		
		public void setPwdName(String pw){
			pwTB.sendKeys(pw);
		}
			
		/*
		  public HomePage clickLogin(){
			loginBTN.click();
			return new HomePage(driver);
		}*/
		
		public void clickLoginBtn() {
			loginBTN.click();
		}	
}
