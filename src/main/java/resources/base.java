package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;

public class base extends ExtentReporterNG {
	
	public static WebDriver driver;
	protected Properties props = new Properties();;
	String filepath= System.getProperty("user.dir")+"/src/main/java/resources/config.properties";
	public browserENUM browser;
	String path =new File("").getAbsolutePath();
	String filename = path+"\\driverJars\\";
	
	public void getBrowser() throws IOException {
		FileInputStream file = new FileInputStream(filepath);
		props.load(file);
		
		String browsername = props.getProperty("browser");
		
		switch (browsername.toLowerCase()) {
		case "chrome":
			browser= browserENUM.Chrome;
			break;
		case "internetexplorer":
			browser= browserENUM.InternetExplorer;
			break;
		}
	}
	public WebDriver initializeDriver() throws Exception {
		getBrowser();
		switch (browser) { 
			case Chrome :
				System.setProperty("webdriver.chrome.driver", filename+"chromedriver.exe");
				driver = new ChromeDriver();
				//test.log(LogStatus.PASS, "initiliased chrome driver");
				break;
			case InternetExplorer :
				System.setProperty("webdriver.ie.driver", filename+"IEDriverServer");
				driver= new InternetExplorerDriver();
				break;
			case Firefox:
				System.setProperty("webdriver.firefox.driver", "D:\\driverJars");
				driver = new FirefoxDriver();
				break;
			default :
				throw new Exception("browsername not recognised");
		}		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		return driver;
	}
	
	/*
	 * the below class FileUtils is available in the jar org.apache.commons.io
	 * teh below class Takesscreenshot is available in the jar org.openqa.selenium
	 */
	public void getScreenshot(String testname) throws IOException {
		File srcfile = new File(System.getProperty("user.dir")+"/Screenshot/"+testname+"-Screenshot.png.");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, srcfile);
	}

}
