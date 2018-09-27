package script;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;

public class TestDemo extends BaseTest{
	
	@Test
	public void testA() {
		Reporter.log("sample",true);
		
		String data = Utility.getXLData(INPUT_PATH, "sheet1", 0, 0);
		Reporter.log("Data:"+data,true);
		
		int r = Utility.getXLRowCount(INPUT_PATH, "sheet1");
		Reporter.log("Row: "+r,true);
		
		Assert.fail();
		//Once above stmt is encountered, It will go to Result class' onTestFailure()
		//String p = Utility.getPhoto(driver, PHOTO_PATH);
		//Reporter.log("Photo: "+p,true);
	}
}
