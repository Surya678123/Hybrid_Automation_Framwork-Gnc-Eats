package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.Baseclass;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class TC005_CheckOut extends Baseclass {

public TC005_CheckOut() {
		
        super();
        
    }
    @Test
    public void checkout() {
    	
    	 logger.info("***** Starting TC005_checkout *****");
		LoginPage lp =new LoginPage(driver);
		lp.setRegisterNo("80838");
		lp.setpass("koji0987");
		lp.LoginBtn();
		
		
		
		MainPage mac=new MainPage(driver);
		
		mac.BhojanClick();
		mac.addDishToCart("Samosa");
		mac.CheckOutBtn();
	
    	CheckoutPage ck=new CheckoutPage(driver);
		logger.info("******TAB and PicupTIme*******");
    	ck.selectCounterTab("SFS 1");
    	ck.selectPickupTime("11:00 to 11:30");
    	ck.clickContinueToCheckout();
    	
	    boolean Order=	ck.orderbtn();
	    
	    logger.info("Checkout page validation ");
		Assert.assertEquals(Order, true);
		
		 logger.info("***** Finish TC005_checkout *****");
    }















}
