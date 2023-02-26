package com.myntra.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.myntra.baseclass.BaseClass;

public class ProductList extends BaseClass {
	public static int productPrice;
	public static List<WebElement> Product;
	public static List<Integer> ProductPriceList = new ArrayList<Integer>();

	private static void launchBrowser() throws Exception {

		browserlaunch("chrome");
		geturl("https://www.myntra.com/");
	}
	private static void moveToKids() throws InterruptedException {

		mousehover(driver.findElement(By.xpath("//a[@class='desktop-main' and contains(text(),'Kids')]")));
		Thread.sleep(2000);
		clickelement(driver.findElement(By.xpath("(//a[contains(text(),'T-Shirts')])[3]")));
	}
	private static void getProductCount() {

		Product = driver.findElements(By.xpath("//div[@class='product-price']"));
		System.out.println("Total Number Of Product:" + Product.size());
	}
	private static String getPrice(String nameOfList) {
		System.out.println(nameOfList + " :");
		for (int i = 1; i <= Product.size(); i++) {
			String path = null;
			WebElement PriceElement;

			if (nameOfList == "discountPrice") {
				path = "//li[@class='product-base'][" + i + "]//ancestor::span[@class='product-discountedPrice']";
			} else if (nameOfList == "StrikePrice") {
				path = "//li[@class='product-base'][" + i + "]//ancestor::span[@class='product-strike']";
			} else if (nameOfList == "AllPrice") {
				path = "//li[@class='product-base'][" + i + "]//ancestor::span[@class='product-discountedPrice']";
			}
			try {
				PriceElement = driver.findElement(By.xpath(path));
			} catch (Exception e) {
				path = "//li[@class='product-base'][" + i + "]//ancestor::div[@class='product-price']";
				PriceElement = driver.findElement(By.xpath(path));
			}

			String getPrice = PriceElement.getText().replaceAll("[^0-9]", "");
			int Price = Integer.parseInt(getPrice);
			ProductPriceList.add(Price);
		}
		return nameOfList;
	}
	private static void getProductPrice(String input) {
		int productPrice; 
		if (input == "min") {
			productPrice = Collections.min(ProductPriceList);
			System.out.println("Minimum product Price:" + productPrice);
		} else {
			productPrice = Collections.max(ProductPriceList);
			System.out.println("Maximum product Price:" + productPrice);
		}
		List<WebElement> ProductList = driver.findElements(By.xpath(
				"//li[@class='product-base']//ancestor::div[@class='product-price' and contains(normalize-space(), '"
						+ productPrice + "')]"));
		String brandPath;
		String productPath;
		System.out.println("List of Product:");
		for (int i = 1; i <= ProductList.size(); i++) {
			int defainType = Product.get(i).getText().split("Rs.").length;
			defainType = defainType - 1;
			if (defainType == 1) {
				brandPath = "(//li[@class='product-base']//ancestor::div[@class='product-price' and contains(normalize-space(), '"
						+ productPrice + "')])[" + i + "]//ancestor::div[@class='product-productMetaInfo']/h3";
				productPath = "(//li[@class='product-base']//ancestor::div[@class='product-price' and contains(normalize-space(), '"
						+ productPrice + "')])[" + i + "]//ancestor::div[@class='product-productMetaInfo']/h4";

			} else {
				brandPath = "(//li[@class='product-base']//ancestor::div[@class='product-price' and contains(normalize-space(), '"
						+ productPrice + "')])[" + i
						+ "]//descendant::span[@class='product-discountedPrice']//ancestor::div[@class='product-productMetaInfo']/h3";
				productPath = "(//li[@class='product-base']//ancestor::div[@class='product-price' and contains(normalize-space(), '"
						+ productPrice + "')])[" + i
						+ "]//descendant::span[@class='product-discountedPrice']//ancestor::div[@class='product-productMetaInfo']/h4";
			}

			System.out.println("product Name: " + i + ". " + driver.findElement(By.xpath(productPath)).getText());
			System.out.println("product brand Name: " + driver.findElement(By.xpath(brandPath)).getText());
			System.out.println("product Price: " + productPrice);
		}

	}
	public static void main(String[] args) throws Exception {

		launchBrowser();
		moveToKids();
		getProductCount();


		

		getPrice("discountPrice");
		getProductPrice("min");

		

	}
}
