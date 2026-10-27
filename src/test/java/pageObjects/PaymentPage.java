package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class PaymentPage extends BasePage {

	public PaymentPage(WebDriver driver) {
		super(driver);
	
	}

	
	By paybtn=By.xpath("//button[@id='confirmPaidBtn']");
	
	
	By orderId=By.xpath("//p[@id='confirmOrderId']");
	
	
	

	
	public void PaymentClick() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    // Wait for the button to be present in DOM
	    WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(paybtn));
	    
	    // Scroll element directly into view
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", button);
	    
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
	    } catch (Exception e) {
	        // Fallback to JS click if normal click is blocked by modal overlay
	        js.executeScript("arguments[0].click();", button);
	    }
	}
	public boolean orderIddisplayed() {
		try {
		WebDriverWait Wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		return Wait.until(ExpectedConditions.visibilityOfElementLocated(orderId)).isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	
	
}
