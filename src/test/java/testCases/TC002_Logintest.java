package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.Baseclass;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class TC002_Logintest extends Baseclass {

	
	public TC002_Logintest() {
		
        super();
        
    }

	@Test
	public void Login() {
		
		 logger.info("***** Starting TC002_LoginTest *****");
	LoginPage Lp=new LoginPage(driver);

    Lp.setRegisterNo("80838");
    Lp.setpass("koji0987");
    Lp.LoginBtn();
	
	
    logger.info("-------Main page headline validation-----------------");
    MainPage Mp=new MainPage(driver);
    
    Boolean Headline1=Mp.head();
    
    Assert.assertEquals( Headline1,true);
    
    logger.info("***** Finished TC002_Logintest *****");
	}
	
	
}
