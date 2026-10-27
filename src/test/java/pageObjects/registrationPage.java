package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class registrationPage extends BasePage{
	
	//constructor
	public registrationPage(WebDriver driver) {
		
		super(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}


	
	//locator

	By signupName=By.xpath("//input[@id='signupName']");
	
	By signupRoll=By.xpath("//input[@id='signupIdentifier']");
	
	By signupPassword=By.xpath("//input[@id='signupPassword']");
	
	By signupbtn=By.xpath("//form[@id='signupForm']//button[@type='submit']");
	
	By AccountDis=By.xpath("//p[@class='subtitle']");
	//Action methods
	
	
	public void setFirstname(String firstname)
	{
		driver.findElement(signupName).sendKeys(firstname);
	}
	
	
	public void setPassword(String pass) 
	{
		driver.findElement(signupPassword).sendKeys(pass);
		
	}

	
	public void setRollNo(String rollno) {
	
		driver.findElement(signupRoll).sendKeys(rollno);
	}

	
	public void signbtn() {
		driver.findElement(signupbtn).click();
	}
}
	


