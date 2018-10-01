package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Utility {

	public static String getPropertyValue(String path, String key) {
		String v="";
		try {
			Properties p = new Properties();
			p.load(new FileInputStream(path));
			v = p.getProperty(key);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return v;
	}
	
	public static void writeResultToXL(String path, String sheet, int pass, int fail) {
		try {
			Workbook w = WorkbookFactory.create(new FileInputStream(path));
			w.getSheet(sheet).getRow(1).getCell(0).setCellValue(pass);
			w.getSheet(sheet).getRow(1).getCell(1).setCellValue(fail);
			w.write(new FileOutputStream(path));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getXLData(String path, String sheet, int r, int c) {
		
		String v="";
		try {
			Workbook w = WorkbookFactory.create(new FileInputStream(path));
			v = w.getSheet(sheet).getRow(r).getCell(c).toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public static int getXLRowCount(String path, String sheet) {
		int count = 0;
		try {
			Workbook w = WorkbookFactory.create(new FileInputStream(path));
			count=w.getSheet(sheet).getLastRowNum();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static String getPhoto(WebDriver driver, String folder) {
		
		Date d = new Date();
		String dateTime = d.toString().replaceAll(":", "_");
		String path = folder+"/"+dateTime+".png";
		try {
			TakesScreenshot t = (TakesScreenshot)driver;
			File srcFile = t.getScreenshotAs(OutputType.FILE);
			File destFile = new File(path);
			//Below line did not get any error
			//If we get it means jar file has not got imported
			//if Dependencies doesn't work, download the jar from below link & copy - paste it
			//http://www.java32.com/Code/Jar/c/Downloadcommonsio32jar.html
			//Some cases even if have Selenium standalone jar file imported 
			FileUtils.copyFile(srcFile, destFile);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static WebDriver openBrowser(WebDriver driver, String ip, String browser) {
		if(ip.equals("localhost")) {
			if(browser.equals("chrome")) {
				driver = new ChromeDriver();
			}
			else
				driver = new FirefoxDriver();
		}
		else {
			try {
				URL u = new URL("http://"+ip+":4444/wd/hub");
				DesiredCapabilities d = new DesiredCapabilities();
				d.setBrowserName(browser);
				driver = new RemoteWebDriver(u,d);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		return driver;
	}
}
