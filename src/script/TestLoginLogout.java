package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.Login;

public class TestLoginLogout extends BaseTest{
	
	@Test(priority=1,groups= {"login","smoke"})
	public void testA() {
		Reporter.log("testA",true);
		
		String data = Utility.getXLData(INPUT_PATH, "TestLoginLogout", 0, 0);
		Reporter.log("Data:"+data,true);
		
		int r = Utility.getXLRowCount(INPUT_PATH, "TestLoginLogout");
		Reporter.log("Row: "+r,true);
		
		Login l = new Login(driver);
		
		System.out.println("Please Login...");
		
		String usrName = Utility.getXLData(INPUT_PATH, "sheet1", 1, 0);
		//System.out.println(usrName);
		l.setUserName(usrName);
		
		String pwd = Utility.getXLData(INPUT_PATH, "TestLoginLogout", 1, 1);
		//System.out.println(pwd);
		l.setPwdName(pwd);
		
		l.clickLoginBtn();
		System.out.println("Login Successfull !!!");
		
		System.out.println("Please Logout...");
		
		HomePage h = new HomePage(driver);
		//when the software has too many scripts we can handle this by create a reference variable in starting page
		//HomePage h= new HomePage(driver);
		//HomePage h = l.clickLoginBtn();
		h.clickLogoutBtn();
		System.out.println("Logout Successfull !!!");
		
		//Assert.fail();
		//Once above stmt is encountered, It will go to Result class' onTestFailure()
		//String p = Utility.getPhoto(driver, PHOTO_PATH);
		//Reporter.log("Photo: "+p,true);
	}
}
