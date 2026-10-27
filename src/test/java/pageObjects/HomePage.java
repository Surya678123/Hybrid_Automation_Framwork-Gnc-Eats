package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
	//constructor
	public HomePage(WebDriver driver){
		
		super(driver);
		
	}
	
	
	//locator
	By CreateAccount=By.xpath("//a[@id='createLink']");
	

	
	
	
	//Action methods
	
	
	public void CreateBtn() {
		
		driver.findElement(CreateAccount).click();
		
	}
	
	
	
	
	
	
}
