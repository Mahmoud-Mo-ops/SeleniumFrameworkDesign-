package rahulshettyacademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponent.AbstarctComponent;

public class ConfirmationPage extends AbstarctComponent {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;

	public String getconfirmMessage() {
		String Message=confirmMessage.getText();
		return Message;
	}
}


//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

