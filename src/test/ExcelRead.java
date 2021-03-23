package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExcelRead {

	WebDriver driver;
	SoftAssert assertobj = new SoftAssert();
	ExtentReports extent;
	ExtentTest test;
	
	XSSFWorkbook Wb;
	XSSFSheet sheet;
	
	
	
@BeforeMethod	
public void setup() throws IOException {
		
		
		System.setProperty("webdriver.chrome.driver","/home/sanjanacstecnot/Downloads/chromedriver");
		
		System.setProperty("webdriver.gecko.driver","/home/sanjanacstecnot/Downloads/geckodriver");
		
		extent = new ExtentReports("ExtentReports.html",true);
		
		driver = new ChromeDriver();
		
		driver.get("https://www.simplilearn.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
		FileInputStream file = new FileInputStream("Testdata.xlsx");
		
		Wb = new XSSFWorkbook(file);
		sheet = Wb.getSheet("datasheet");		
	}


	@Test	
	public void testcase1(){
	    
	test =extent.startTest("Negative Login Test");
	
	WebElement lnkLogin = driver.findElement(By.linkText("Log in"));
	lnkLogin.click();
	test.log(LogStatus.PASS,"Clicked on Login Login Button");
	
	WebElement editUsername = driver.findElement(By.name("user_login"));   
	
	String UserName = sheet.getRow(1).getCell(0).getStringCellValue();
	editUsername.sendKeys(UserName);
	test.log(LogStatus.PASS,"Entered the username");
	
	WebElement editPwd = driver.findElement(By.name("user_pwd"));  
	
	String Password = sheet.getRow(1).getCell(1).getStringCellValue();
	editPwd.sendKeys(Password);
	test.log(LogStatus.PASS,"Entered the pwd");
	
	WebElement chkBox = driver.findElement(By.className("rememberMe"));    
	chkBox.click();
	test.log(LogStatus.PASS,"rememberMe");

	WebElement login = driver.findElement(By.xpath("//input[@name='btn_login']"));
	login.click();
	test.log(LogStatus.PASS,"btn_login");

	
	}
}
