package com.myntra.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.myntra.baseclass.BaseClass;

public class MinProduct extends BaseClass {
	public static int productPrice;
	public static List<WebElement> Product;
	public static List<Integer> ProductPriceList = new ArrayList<Integer>();

	@BeforeClass
	private static void launchBrowser() throws Exception {

		browserlaunch("chrome");
		geturl("https://www.myntra.com/");

	}

	@Test(priority = 1, enabled = true)
	private static void moveToKids() throws InterruptedException {

		mousehover(driver.findElement(By.xpath("//a[@class='desktop-main' and contains(text(),'Kids')]")));
		Thread.sleep(2000);
		clickelement(driver.findElement(By.xpath(
				"//a[@class='desktop-main' and contains(text(),'Kids')]//parent::div/descendant::a[text()='T-Shirts']")));
	}

	@Test(priority = 2)
	private static void getProductCount() {

		Product = driver.findElements(By.xpath("//li[@class='product-base']"));
		System.out.println("Total Number Of Product:" + Product.size());

	}

	@Test(priority = 3)
	private void ProductList() throws Exception {
		for (WebElement Each : Product) {

			WebElement EachPrice = Each.findElement(By.xpath(".//div[@class='product-price']"));
			String[] split = EachPrice.getText().split("Rs. ");

			int ProductEachPrice = Integer.parseInt(split[1]);
			ProductPriceList.add(ProductEachPrice);

		}

	}

	@Test(priority = 4)
	private void minPrice() {
		Integer min = Collections.min(ProductPriceList);
		System.out.println("MinValue: " + min);

		for (WebElement Each : Product) {
			WebElement MinPrice;
			if (Each.getAttribute("innerHTML").contains("product-discountedPrice")) {
				MinPrice = Each.findElement(By.xpath(".//span[@class='product-discountedPrice']"));
			} else {
				MinPrice = Each.findElement(By.xpath(".//div[@class='product-price']"));
			}

			String[] split = MinPrice.getText().split("Rs. ");
			if (Integer.valueOf(split[1]).equals(min)) {

				String ProductName = Each.findElement(By.xpath(".//h4[@class='product-product']")).getText();
				String BrandName = Each.findElement(By.xpath(".//h4[@class='product-product']")).getText();

				System.out.println("Product Name:" + ProductName);
				System.out.println("Product Brand Name:" + BrandName);
				break;
			}
		}
	}

	@Test(priority = 5)
	private void maxPrice() {
		Integer max = Collections.max(ProductPriceList);
		System.out.println("MaxValue: " + max);

		for (WebElement Each : Product) {
			WebElement MinPrice;
			if (Each.getAttribute("innerHTML").contains("product-discountedPrice")) {
				MinPrice = Each.findElement(By.xpath(".//span[@class='product-discountedPrice']"));
			} else {
				MinPrice = Each.findElement(By.xpath(".//div[@class='product-price']"));
			}

			String[] split = MinPrice.getText().split("Rs. ");
			if (Integer.valueOf(split[1]).equals(max)) {

				String ProductName = Each.findElement(By.xpath(".//h4[@class='product-product']")).getText();
				String BrandName = Each.findElement(By.xpath(".//h4[@class='product-product']")).getText();

				System.out.println("Product Name:" + ProductName);
				System.out.println("Product Brand Name:" + BrandName);
				break;
			}

		}

	}

}