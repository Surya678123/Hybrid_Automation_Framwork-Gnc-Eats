package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    
    
    
    
    
    
  
    
            By logoutbtn=By.xpath("//button[@id='logoutBtn']");
    
            By Bhojan=By.xpath("//h2[normalize-space()='Bhojan']");
            By AMRIT=By.xpath("//h2[normalize-space()='AMRIT Cafe']");
            By Kappikudi=By.xpath("//h2[normalize-space()='KaappiKudi']");
            By SaiCanteen=By.xpath("//h2[normalize-space()='Sai Sri']");
            By cartBadge = By.xpath("//button[@id='cartCheckout']");
            
            
            
    public boolean head() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Verify logout button exists — it only exists when logged in!
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutbtn)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clicklogout() {
    	driver.findElement(logoutbtn).click();
    	
    }
    public void BhojanClick() {
    	driver.findElement(Bhojan).click();
    }
    
    public void AmritClick() {
    	driver.findElement(AMRIT).click();
    }
    
    public void KappiKudiClick() {
    	driver.findElement(Kappikudi).click();
    }
    public void SaiCanteenClick() {
    	driver.findElement(SaiCanteen).click();
    }
 // Method to click 'Add +' for ANY dish dynamically
    public void addDishToCart(String dishName) {
    	String dish=dishName.trim();
        String xpath = "//p[contains(@class, 'item-name') and contains(., '" + dish + "')]"
                     + "/ancestor::div[contains(@class, 'menu-item')]//button[contains(., 'Add')]";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        addButton.click();
    }


    public boolean isCartUpdated() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void CheckOutBtn() {
    	driver.findElement(cartBadge).click();
    }
    
    
    
    
    
    
}