package rahulshettyacademy.pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstarctComponent;

public class OrderPage extends AbstarctComponent {
	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNames.stream()
				.anyMatch(Product -> Product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage gotToCheckout() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)");
		Thread.sleep(1000);
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
}
