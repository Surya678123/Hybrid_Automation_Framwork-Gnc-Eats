package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckoutPage extends BasePage {

	
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		
	}
	
	
	
	
	
	
	By continueTocheckoutBtn=By.xpath("//button[@id='confirmTimeslotBtn']");
	
	
	By orderText=By.xpath("//h1[normalize-space()='Your order']");
	
	
  
	public void selectCounterTab(String tabName) {
	    String xpath = "//*[normalize-space()='" + tabName.trim() + "']";
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	}
	public void selectPickupTime(String timeSlot) {
	
	    String xpath = "//*[contains(text(), '" + timeSlot.trim() + "')]";
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	}

    public void clickContinueToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(continueTocheckoutBtn)).click();
    }
    public boolean orderbtn() {
    	   try {
               WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
               return wait.until(ExpectedConditions.visibilityOfElementLocated(orderText)).isDisplayed();
           } catch (Exception e) {
               return false;
           }
    }
    
    
    
    
    
}
