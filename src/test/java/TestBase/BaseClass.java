package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; // Log4j ✅ Correct import


public class BaseClass {

	        public static WebDriver driver;
	        public Logger logger;  //Log4j
	        public Properties p;
	        
	        
	        @BeforeClass (groups= {"Sanity" ,"Regression", "Master" , "Datadriven"})
	        @Parameters ({"os", "browser"})
	        public void setup(String os, String br) throws IOException {
	        	//loading config.properties file
	        	//we can use fileinput stream too to read the file.
	        	FileReader file = new FileReader("./src//test//resources//config.properties");
	        	p = new Properties();
	        	p.load(file);
	        	
	        	logger = LogManager.getLogger(this.getClass());
	        	//LogManager is a class which has getlogger method
	        	// this represents to class 
	        	
	        	switch(br.toLowerCase()) {
	        	case "chrome" : driver = new ChromeDriver(); break;
	        	case "edge" : driver= new EdgeDriver(); break;
	        	case "firefox" : driver = new FirefoxDriver(); break ;
	        	default : System.out.println("Invalid browser name..."); return;
	        	}
	        	
	      	 	driver.manage().deleteAllCookies();
	      	 	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	      	 	
	      	 	driver.get(p.getProperty("appURL")); //reading url from properties file
	      	    driver.manage().window().maximize();
	      	    
	      	    logger.info("Browser launched and URL opened.");
	      }

	        @AfterClass(groups= {"Sanity" ,"Regression", "Master" , "Datadriven"})
	        public void tearDown() {
	      		driver.quit();
	      		 logger.info("Browser closed.");
	      	}
	        
	        
	        public String randomString()
	        {
	      	String generatedstring =  RandomStringUtils.randomAlphabetic(5);
	      	  return generatedstring;
	        }
	        
	        public String randomNumer() {
	      	  String generatednumber = RandomStringUtils.randomNumeric(10);
	            return generatednumber;
	        }
	        
	        public String randomAlphanumeric() {
	      	   
	      	  String generatedstring = RandomStringUtils.randomAlphabetic(3);
	            String generatednumber = RandomStringUtils.randomNumeric(3);
	            return (generatedstring +"@"+ generatednumber); 
	        }

	        public String captureScreen(String tname) throws IOException {
	            // Create a timestamp for the screenshot file name
	            String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	            // Take the screenshot
	            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	            // Define target file path
	            String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	            File targetFile = new File(targetFilePath);

	            // Move file from temp to target location
	            sourceFile.renameTo(targetFile);

	            // Return path of screenshot to use in report
	            return targetFilePath;
	        }
 
	        
	        
}
