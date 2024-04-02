package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	//,retryAnalyzer=Retry.class
	@Test(groups = { "ErrorHandling" })
	public void ErrorValidationError() throws IOException, InterruptedException {
		landingPage.loginApplication("mahmoud@gmail.com", "Hambur#");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("mahmoudeid1840@gmail.com", "Hamburg3035#");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}