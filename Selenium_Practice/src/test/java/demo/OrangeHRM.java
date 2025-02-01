package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRM {
	public String baseUrl ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;
	
	@BeforeTest
public void setup() {
	System.out.println("Before Test");
	driver= new ChromeDriver();
	//maximise window
	driver.manage().window().maximize(); 
	
	// open url
	driver.get(baseUrl);
	//time out till 60sec
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	
}

@Test(priority =1, enabled=false)
public void dologinwithinvalid()throws InterruptedException  {
	//Username
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		//password
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("12345");
		////login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		
		String message_exp ="Invalid Credentials";
		String message_actual = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		Assert.assertTrue(message_actual.contains(message_exp));
		Thread.sleep(1500);
		
}
@Test(priority=2)
public void loginTestwithvalid() {
	//Username
	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
	//password
	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
	////login button click
	driver.findElement(By.xpath("//button[@type='submit']")).submit();
	
	//verify if login was successful
	String pageTitle = driver.getTitle();
	Assert.assertEquals("OrangeHRM",pageTitle);
}
@Test(priority =3)
public void addEmployee() throws InterruptedException {
	//click on PIM
	driver.findElement(By.xpath("//span[text()='PIM']")).click();
	//click on add employee
	driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
	Thread.sleep(5000);
	//first name
	driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-firstname']")).sendKeys("Srishti");
	//middle name
	driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-middlename']")).sendKeys("Sandesh");
	//last name
	driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-lastname']")).sendKeys("Patil");
	//click save
	driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
	Thread.sleep(5000);
// verify if employee is added successfully
	String confirmmsg = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
	Assert.assertEquals("Personal Details", confirmmsg);
	
}
//Search employee by name
@Test(priority =4)
public  void search_employee() throws InterruptedException {
	//click on PIM
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		//add employee name
		//click on  employee list
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
		//search via employee name
		driver.findElements(By.tagName("input")).get(1).sendKeys("srishti");
		//click on serch button
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
		Thread.sleep(5000);
	List<WebElement> element = driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));
	String exp_msg = "Records Found";
	String msg_actual=element.get(0).getText();
	System.out.println(msg_actual);
	Assert.assertTrue(msg_actual.contains(exp_msg));
}
//search employee by id
@Test(priority =5)
public void Search_employee_by_id() throws InterruptedException {
	String empID ="0295";
	String actual_msg ="";
	//click on PIM
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
			//click on  employee list
			driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
			//add employee id
			driver.findElements(By.tagName("input")).get(2).sendKeys(empID);
			//click on serch button
			driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
			Thread.sleep(5000);
			//to scroll down
			JavascriptExecutor executer =(JavascriptExecutor) driver;
			executer.executeScript("window,scrollBy(0,"+500+")");
			
			List<WebElement> rows =driver.findElements(By.xpath("//div[@role='row']"));
			if(rows.size()>1) {
			actual_msg=	driver.findElement(By.xpath("((//div[@role ='row'])[2]/div[@role='cell'])[2]")).getText();//xpath handling table
			}
			Assert.assertEquals(empID, actual_msg);
}


public void logout() {
	driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
	driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	
}

@AfterTest
public void tearDown() throws InterruptedException {
	logout();
	Thread.sleep(5000);
	
	driver.close();
	driver.quit();
}
}
