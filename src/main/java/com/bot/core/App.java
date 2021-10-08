package com.bot.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Tool auto step brave
 *
 */
public class App 
{
	

    public static void main( String[] args )
    {
//        System.out.println( "Tool start" );
//        try {
//    	    Process p = Runtime.getRuntime().exec("C:/Program Files (x86)/BraveSoftware/Brave-Browser/Application/brave.exe --profile-directory=\"Default\"");
////    	    p.waitFor();
//    	    System.out.println("Brave launched!");
//    	    WebDriver driver = new FirefoxDriver();
////    	    JavascriptExecutor jse = (JavascriptExecutor)driver;
////    	    jse.executeScript("location.reload(true);");
//    	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    	    Actions action = new Actions(driver);
//    	    action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
////    	    JavascriptExecutor jse = (JavascriptExecutor)driver;
////    	    jse.executeScript("location.reload(true);");
//    	} catch (Exception e) {
//    	    e.printStackTrace();
//    	}
    	
//    	System.setProperty("webdriver.chrome.driver","D:\\Workspace\\JmeterWebdriverProject\\src\\lib\\chromedriver.exe");
//    	ChromeOptions chromeOptions = new ChromeOptions();
//    	chromeOptions.addArguments("--start-maximized");
//    	WebDriver driver = new ChromeDriver(chromeOptions);
//    	driver.get("https://www.google.co.in/");
    	
//    	ChromeOptions options = <strong>new</strong> ChromeOptions();
//    	options.setBinary("/path/to/other/chrome/binary");
//    	WebDriver driver = new ChromeDriver(options);
    	
    	
//        System.setProperty("webdriver.chrome.driver", "D:\\Code\\Java\\tool-brave\\chromedriver.exe");
        
        //Initializing ChromeOptions Object
        ChromeOptions options = new ChromeOptions();
     
        //Setting Binary Path of Brave Browser in options object.
        options.setBinary("C:/Program Files (x86)/BraveSoftware/Brave-Browser/Application/brave.exe");
        options.addArguments("--user-data-dir=C:/Users/nguye/AppData/Local/BraveSoftware/Brave-Browser/User Data/");
        options.addArguments("--profile-directory=Default");
        //Initializing Chrome Browser Instance
        WebDriver driver = new ChromeDriver(options);
     
        //Maximizing Browser
     
//        driver.manage().window().maximize();

        //Launching https://abodeqa.com
//        driver.get("https://www.google.com");
     
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Actions action = new Actions(driver);
	    action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
     
//        driver.quit();
    	System.out.println("done!");
    	
    }
}
