package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckOutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.OrderPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.gotToCheckout();
		checkOutPage.selectCountry("Eg");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getconfirmMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("mahmoudeid1840@gmail.com", "Hamburg3035#");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
		// IPHONE 13 PRO not executed and choice ZARA COAT 3
	}
}

// we use Object not string or Int..etc as object generic any data type which
// accepts any kind of data type
// as we don't know which date we will receive
/*
 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
 * "mahmoudeid1840@gmail.com"); map.put("password", "Hamburg3035#");
 * map.put("product", "ZARA COAT 3");
 * 
 * HashMap<String, String> map1 = new HashMap<String, String>();
 * map1.put("email", "shetty@gmail.com"); map1.put("password", "Iamking@000");
 * map1.put("product", "IPHONE 13 PRO");
 */