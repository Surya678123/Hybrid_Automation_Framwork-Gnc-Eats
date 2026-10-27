package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;
import TestBase.Baseclass;
import pageObjects.HomePage;
import pageObjects.registrationPage;

public class TC001_AccountRegistrationTest extends Baseclass {

    // Explicit no-argument constructor for TestNG instantiation
    public TC001_AccountRegistrationTest() {
        super();
    }

    @Test(priority = 1)
   
    public void RegisterAccount_Validation() {
        logger.info("----------Register account validation start----------");

        try {
            HomePage hp = new HomePage(driver);
            logger.info("Creating a button");
            hp.CreateBtn();

            registrationPage rp = new registrationPage(driver);
            logger.info("Customer details");
            rp.setFirstname("koji09");
            rp.setRollNo("89023");
            rp.setPassword("surya0013");
            rp.signbtn();

        } catch (Exception e) {
            logger.error("Exception encountered: ", e);
            Assert.fail();
        }
        logger.info("---------------Finished---------------");
        
    }
}