package application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {
	public WebDriver driver;
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	By signin = By.cssSelector("a[title='Log in to your customer account']");
	
	public WebElement eleSignin() {
		return driver.findElement(signin);
	}

}
