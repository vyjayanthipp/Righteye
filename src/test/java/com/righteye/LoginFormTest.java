package com.righteye;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginFormTest {
	public WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/vyju/tools/geckodriver/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/right-eye/");
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.close();
	}

	
	public void loadLoginPage() {
		driver.get("http://localhost:8080/right-eye/");
	}
	
	
	@Test
	public void testSuccessfulLogin()  {
		loadLoginPage();
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("coding@project.com");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Coding@123");
		WebElement loginButton = driver.findElement(By.id("loginButton"));
		loginButton.click();	
		WebElement divLoginSuccess = driver.findElement(By.id("LoginSuccess"));
		assertTrue(divLoginSuccess.isDisplayed());		
		
	}
	
	@Test
	public void testIncorrectEmail()  {
		loadLoginPage();
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("coding@project");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Coding@123");
		WebElement loginButton = driver.findElement(By.id("loginButton"));
		loginButton.click();		
		WebElement divIncorrectEmail = driver.findElement(By.id("IncorrectEmail"));
		assertTrue(divIncorrectEmail.isDisplayed());
		
	}
	@Test
	public void testIncorrectPassword()  {
		loadLoginPage();
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("coding@project.com");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Coding@");
		WebElement loginButton = driver.findElement(By.id("loginButton"));
		loginButton.click();		
		WebElement divIncorrectPassword = driver.findElement(By.id("IncorrectPassword"));
		assertTrue(divIncorrectPassword.isDisplayed());
		
	}
	@Test
	public void test3IncorrectAttempts()  {
		loadLoginPage();
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("coding@project.com");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Coding@");
		WebElement loginButton = driver.findElement(By.id("loginButton"));
		loginButton.click();	
		loginButton.click();
		loginButton.click();
		WebElement divIncorrectLogin3attempt = driver.findElement(By.id("IncorrectLogin3attempt"));
		assertTrue(divIncorrectLogin3attempt.isDisplayed());
		
	}
	@Test
	public void test4AttemptsLocked()  {
		loadLoginPage();
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("coding@project.com");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Coding@");
		WebElement loginButton = driver.findElement(By.id("loginButton"));
		loginButton.click();	
		loginButton.click();
		loginButton.click();
		loginButton.click();
		WebElement divInvalidpassword4attempt = driver.findElement(By.id("Invalidpassword4attempt"));
		assertTrue(divInvalidpassword4attempt.isDisplayed());
		
	}
	@Test
	public void testNeedhelpLink()  {
		loadLoginPage();
		WebElement NeedHelp = driver.findElement(By.cssSelector("body > div:nth-child(8) > a"));
		NeedHelp.click();
		//body > h2
		WebElement FaqHeader = driver.findElement(By.cssSelector("body > h2"));
	    assertEquals("FAQS:", FaqHeader.getText());
		
	}
}
