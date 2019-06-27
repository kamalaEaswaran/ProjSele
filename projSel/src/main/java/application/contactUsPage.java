package application;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class contactUsPage {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger();
	
	public contactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	/*
	 * There are three ways of initializing elements. 
	 * One by using By method, next by using webelement class , next by using page factory
	 */
		
	By linkContactUs = By.xpath("//a[@title='Contact Us']");
	//WebElement linkContactUs; // if we declare the element here, it will throw null pointer expection.
	
	By SubHeading = By.id("id_contact");	 
		
	@FindBy(id="email")
	public WebElement email; // this is taken care by the Page factory init elemnts methods	
	
	//WebElement eleOrderOfRef = driver.findElement(By.xpath("id_order")); -- we cannot declare like this as it will throw null pointer excpetion
	WebElement eleOrderOfRef;
	
	@FindBy(id="message")
	public WebElement txtboxMsg;
		
	public WebElement contact() { 
		 // linkContactUs=  driver.findElement(By.xpath("//a[@title='Contact Us']")); 
		  return driver.findElement(linkContactUs); 
	 }	 	
	
	public void editEmail(String emailval) {
		//email.sendKeys("test@gmail.com");
		email.sendKeys(emailval);
	}
	
	public void selectType() {
		Select sel = new Select(driver.findElement(SubHeading));
		sel.selectByIndex(1);
	}	
	
	public WebElement emailele() {
		return email;
	}
	
	public void orderOfRef(String orVal) {
		eleOrderOfRef = driver.findElement(By.id("id_order"));
		log.info("entering order value ");
		eleOrderOfRef.sendKeys("order");
		eleOrderOfRef.sendKeys(orVal);
	}
}
