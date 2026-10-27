package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.Baseclass;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.PaymentPage;

public class TC006_Payment  extends Baseclass{

	
	TC006_Payment(){
		super();
	}
	
	
	@Test
	public void verifyPayment() {
		 logger.info("***** Starting TC006_Payament *****");
		LoginPage lp =new LoginPage(driver);
		lp.setRegisterNo("80838");
		lp.setpass("koji0987");
		lp.LoginBtn();
		
		
		
		MainPage mac=new MainPage(driver);
		
		mac.BhojanClick();
		mac.addDishToCart("Samosa");
		mac.CheckOutBtn();
	
    	CheckoutPage ck=new CheckoutPage(driver);
		
    	ck.selectCounterTab("SFS 1");
    	ck.selectPickupTime("11:00 to 11:30");
    	ck.clickContinueToCheckout();
    	
    	logger.info("*********Payment btn validation*******");
    	PaymentPage pay=new PaymentPage(driver);
         pay.PaymentClick();
         boolean id=pay.orderIddisplayed();
         
         Assert.assertEquals(id, true);
    	
         logger.info("*****Finish TC006_payment*****");
    	
	}
}
