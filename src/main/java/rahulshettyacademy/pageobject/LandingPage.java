package rahulshettyacademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstarctComponent;

public class LandingPage extends AbstarctComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// pageFactor will trigger all elements (findBy) using initElements and it
		// expects two arguments driver and this (refers to driver in class scope)
		PageFactory.initElements(driver, this); // this refers to current driver
	}

	// at run time this findBy will be constructed like that WebElement userMail=
	// driver.findElement(By.id("userEmail"));
	// PageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEl;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css ="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) { 
		userEmail.sendKeys(email);
		passwordEl.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public String getErrorMessage() {
		//explicit
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
