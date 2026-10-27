package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.Baseclass;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class TC004_AddToCart extends Baseclass{

public TC004_AddToCart() {
		
        super();
        
    }


    @Test
	public void verify_addTocart() {
		
    	 logger.info("***** Starting TC004_AddtoCart *****");
		LoginPage lp =new LoginPage(driver);
		lp.setRegisterNo("80838");
		lp.setpass("koji0987");
		lp.LoginBtn();
		
		
		logger.info("*********Add to cart validation********");
		MainPage mac=new MainPage(driver);
		
		mac.BhojanClick();
		mac.addDishToCart("Samosa");
	
		
		boolean Cart=mac.isCartUpdated();
		Assert.assertEquals(Cart, true);
		 logger.info("***** Finish TC004_AddtoCart *****");
	}

	
	
	
	
	
	
}
