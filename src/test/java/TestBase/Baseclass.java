package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Baseclass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    public Baseclass() {
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String br) throws IOException {
        // Load properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        // Initialize Logger
        logger = LogManager.getLogger(this.getClass());

        // Browser selection
        switch (br.toLowerCase()) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                logger.error("Invalid browser specified: " + br);
                throw new IllegalArgumentException("Unsupported browser: " + br);
        }

        // Configure browser settings
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Launch application URL (reads key 'appURL' from config.properties)
        String url = p.getProperty("appURL");
        if (url == null || url.trim().isEmpty()) {
            url = "http://localhost:4000/";
        }
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        
        // Ensure the screenshots directory exists
        String dirPath = System.getProperty("user.dir") + File.separator + "screenshots";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        String targetFilePath = dirPath + File.separator + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        
        // Robust copy across different filesystems/drives
        java.nio.file.Files.copy(sourceFile.toPath(), targetFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        
        return targetFilePath;
    }
}