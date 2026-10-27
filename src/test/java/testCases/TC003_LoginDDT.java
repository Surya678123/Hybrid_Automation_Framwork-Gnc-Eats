package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.Baseclass;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends Baseclass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verify_loginDDT(String RegisterNo, String pwd, String expResult) {
        logger.info("***** Starting TC003_LoginDDT for user: " + RegisterNo + " *****");
      
        String   expResult1=expResult.trim();
        
        try {
            LoginPage lp = new LoginPage(driver);
          
            lp.setRegisterNo(RegisterNo);
            lp.setpass(pwd);
            lp.LoginBtn();
        
            MainPage macc = new MainPage(driver);
            
            boolean targetPageExists = macc.head();

            System.out.println("User:"+ RegisterNo + " Expected:" +expResult1+" Logged In Successfully?"+targetPageExists);

            if (expResult1.equalsIgnoreCase("Valid")) {
                if (targetPageExists) {
                    macc.clicklogout(); 
                    Assert.assertTrue(true);
                } else {
                    Assert.fail("Expected Valid login, but account page was not found.");
                }
            }

            if (expResult1.equalsIgnoreCase("Invalid")) {
                if (targetPageExists) {
                    macc.clicklogout(); 
                    Assert.fail("Expected Invalid login, but login succeeded.");
                } else {
                    Assert.assertTrue(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred during execution: " + e.getMessage());
        }

        logger.info("***** Finished TC003_LoginDDT *****");
    }
}