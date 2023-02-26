package com.myntra.practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.myntra.baseclass.BaseClass;

public class Temp extends BaseClass {
	public static List<WebElement> Product;

	public static void main(String[] args) throws Exception {
		
		String property = System.getProperty("user.dir");
		System.out.println(property);
		driver.getPageSource();
		
		
		
		
		
		// taskskil/ im chromedriver.exe /f
//		browserlaunch("chrome", "57965");
//		// geturl("https://www.myntra.com/kids?f=Categories%3ATshirts%3A%3AGender%3Aboys%2Cboys%20girls");
//		Product = driver.findElements(By.xpath("//div[@class='product-price']"));
//		// getBrowserPort();
//		System.out.println(Product.size());
//
//		for (int i = 0; i <= Product.size(); i++) {
//			try {
//				int length = Product.get(i).getText().split("Rs.").length;
//
//				System.out.println(length - 1);
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//		}
	}

}
