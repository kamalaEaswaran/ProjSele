package application;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.base;
import resources.readExcel;

public class HomePage extends base {

	
	/*
	 * //this also works. create a constructor and create a object for the class in
	 * //the main method
	 * 
	 * public HomePage() throws Exception { 
	 * driver= super.initializeDriver();
	 * driver.get(props.getProperty("url")); 
	 * driver.manage().window().maximize(); }
	 * 
	 * public void signintoApp(){ 
	 * SignInPage sign = new SignInPage(driver);
	 * sign.eleSignin().click(); 
	 * }
	 * 
	 * public static void main(String[] args) throws Exception { 
	 * HomePage hp = new HomePage(); 
	 * hp.signintoApp();
	 * 
	 * }
	 */
	 
	public static Logger log = LogManager.getLogger();

	@BeforeTest
	public void invokeapplication() throws Exception {
		driver = initializeDriver();
		driver.get(props.getProperty("url"));
		//test.log(LogStatus.PASS, "Open the application");
		driver.manage().window().maximize();
	}

	@Test(dataProvider="getDataFromExcel")
	public void signinToApp(String email, String ordervalue) {
		SignInPage sign = new SignInPage(driver);
		log.debug("Clicking sign in link");
		//test.log(LogStatus.PASS, "Clicking sign in link");
		sign.eleSignin().click();		
		
		log.info("Clicking contact us in link");
		contactUsPage cont = new contactUsPage(driver);
		//cont.linkContactUs.click(); // Cannot use the element directly which is declared using BY
		cont.contact().click(); //using the element by calling the method that returns the element
		log.info("entering values in the fields");
		cont.selectType();
		cont.editEmail(email); // or cont.emailele().sendKeys("tets@gmail.com");;
		cont.orderOfRef(ordervalue);	
		cont.txtboxMsg.sendKeys("contact us page message");
		
	}

	@AfterTest
	public void close() {
		driver.close();
		driver.quit();
	}
	
	/*
	 * Data provider helps us to send the parameters to the method. 
	 * one way we can hard code the value in a array and pass it to the Test method
	 * Other way to read it from excel and pass it to the test method
	 * 
	 */
	
	@DataProvider
	public Object[][] getdata() {
		int arr[]= new int[1];
		int a[] = {1,2,3,4};
		System.out.println(a[0]);		
		
		//String obj[][] = new String[1][1];
		Object[][] obj = new Object[2][2];
				
		obj[0][0] = "test@gmail.com";
		obj[0][1] = "order";
		
		obj[1][0] ="test2@gmail.com";
		obj[1][1] ="order2";
		
		return obj;
	}
	
	
	@DataProvider
	public Object[][] getDataFromExcel() throws IOException {
		String[][] str = new String[2][2];
		readExcel read = new readExcel();
		str = read.getdatafromExcel();
		//System.out.println(str[0][0]+" "+str[0][1]);
		return str;
	}
	
	
}




