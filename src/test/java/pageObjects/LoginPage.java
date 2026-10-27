package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}
	
	
	
	By Logregister=By.xpath("//input[@id='loginIdentifier']");
	
	By Logpass=By.xpath("//input[@id='loginPassword']");
	
	By LoginBtn=By.xpath("//form[@id='loginForm']//button[@type='submit']");
	
	
	
	public void setRegisterNo(String reg) {
		driver.findElement(Logregister).clear();
		driver.findElement(Logregister).sendKeys(reg);
		
	}
	
	public void setpass(String pass) {
		driver.findElement(Logpass).clear();
		driver.findElement(Logpass).sendKeys(pass);
	}
	
	public void LoginBtn() {
		driver.findElement(LoginBtn).click();
	}
	

}
