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
	private static String USER_DATA_DIR_BRAVE = "C:/Users/nguye/AppData/Local/BraveSoftware/Brave-Browser/User Data/";
	private static String BRAVE_EXE = "C:/Program Files (x86)/BraveSoftware/Brave-Browser/Application/brave.exe";
	private static String PROFILE = "Default";
    public static void main( String[] args ) throws InterruptedException
    {
    	System.out.println( "Tool start" );
//        System.setProperty("webdriver.chrome.driver", "D:\\Code\\Java\\tool-brave\\chromedriver.exe");
 
        //Initializing ChromeOptions Object
        ChromeOptions options = new ChromeOptions();
     
        //Setting Binary Path of Brave Browser in options object.
        options.setBinary(BRAVE_EXE);
        options.addArguments("--user-data-dir=" + USER_DATA_DIR_BRAVE);
        options.addArguments("--profile-directory=" + PROFILE);

        //Initializing Chrome Browser Instance
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Actions action = new Actions(driver);

	    action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // close browser brave
        driver.quit();
    	System.out.println("done!");
    	
    }
}
