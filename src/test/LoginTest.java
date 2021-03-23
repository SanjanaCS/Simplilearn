package test;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	

	WebDriver driver;
	SoftAssert assertobj = new SoftAssert();
	ExtentReports extent;
	ExtentTest test;
	
	
@BeforeMethod	
public void setup() {
		
		
		System.setProperty("webdriver.chrome.driver","/home/sanjanacstecnot/Downloads/chromedriver");
		
		System.setProperty("webdriver.gecko.driver","/home/sanjanacstecnot/Downloads/geckodriver");
		
		extent = new ExtentReports("ExtentReports.html",true);
		
		driver = new ChromeDriver();
		
		driver.get("https://www.simplilearn.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
	}
	
@Parameters({"uname","password"})
@Test
public void testcase1(String UserName, String Password ){
    
test =extent.startTest("Login Test");

WebElement lnkLogin = driver.findElement(By.linkText("Log in"));
lnkLogin.click();
test.log(LogStatus.PASS,"Clicked on Login Login Button");

WebElement editUsername = driver.findElement(By.name("user_login"));    
editUsername.sendKeys("UserName");
test.log(LogStatus.PASS,"Entered the username");

WebElement editPwd = driver.findElement(By.name("user_pwd"));    
editPwd.sendKeys("Password");
test.log(LogStatus.PASS,"Entered the pwd");

WebElement chkBox = driver.findElement(By.className("rememberMe"));    
chkBox.click();
test.log(LogStatus.PASS,"rememberMe");

WebElement btnPwd =driver.findElement(By.className("btn_login"));
btnPwd.click();
test.log(LogStatus.PASS,"btn_login");

WebElement error = driver.findElement(By.id("msg_box"));
String ActError = error.getText();

String ExpError = "The email or password you have entered is invalid";

// Assert.assertEquals(ActError, ExpError);

assertobj.assertEquals(ActError, ExpError);

System.out.println("After Failiure");


}



@AfterMethod
public void teardown() {
driver.quit();
extent.endTest(test);
extent.flush();
extent.close();
//assertobj.assertAll();

}
}