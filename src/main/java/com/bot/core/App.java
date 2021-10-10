package com.bot.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
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
	private static String PATH_FILE_FROM = "D:/Code/Freelancer/brave-tool/FullCombo/5555/";
	private static String PATH_FILE_TO = "C:/Users/nguye/AppData/Local/BraveSoftware/Brave-Browser/User Data/";
	private static String ADS_SERVICE = "/ads_service/";
	private static String BRAVE_EXE = "C:/Program Files (x86)/BraveSoftware/Brave-Browser/Application/brave.exe";
	private static String[] ARRAY_FILE = {"client.json", "database.sqlite", "database.sqlite-journal", "notifications.json"};

    public static void main( String[] args ) {
    	System.out.println( "Tool start" );
    	String[] listProfileName = {
    			"Default",
    			"Profile 1",
    			"Profile 2",
    			"Profile 3"
    	};
    	for (String profile : listProfileName) {
    		runTool(profile);
		}
    	System.out.println("done!");
    	
    }
    
    public static void runTool(String profileName) {
    	try {
	    	Thread.sleep(2000);
	        //Initializing ChromeOptions Object
	        ChromeOptions options = new ChromeOptions();
	        for (int i = 0; i < 60; i++) {
	        	Thread.sleep(1000);
	        	File dirFrom;
	        	File dirTo;
	        	for (String item : ARRAY_FILE) {
	        		String strFrom = PATH_FILE_FROM + item;
	            	String strTo = PATH_FILE_TO + profileName + ADS_SERVICE + item;
	            	dirFrom = new File(strFrom);
	            	dirTo = new File(strTo);
	            	copyFile(dirFrom, dirTo);
				}
	        	Thread.sleep(500);
		        //Setting Binary Path of Brave Browser in options object.
		        options.setBinary(BRAVE_EXE);
		        options.addArguments("--user-data-dir=" + USER_DATA_DIR_BRAVE);
		        options.addArguments("--profile-directory=" + profileName);
		        Map<String, Object> prefs = new HashMap<String, Object>();
		        prefs.put("profile.exit_type", "Normal");
		        options.setExperimentalOption("prefs", prefs);	        
		        
		        //Initializing Chrome Browser Instance
		        WebDriver driver = new ChromeDriver(options);
		        Thread.sleep(3000);
		        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		        Actions action = new Actions(driver);
		        for (int j = 0; j < 23; j++) {
		        	action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
				}
		        // close browser brave
		        driver.quit();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void copyFile(File from, File to ) {
    	try {
    		if (Files.exists(to.toPath())) {
    			Files.delete(to.toPath());
    			Files.copy(from.toPath(), to.toPath());
    		} else {
    			Files.copy(from.toPath(), to.toPath());
    		}
//    		Files.copy(from.toPath(), to.toPath());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    } 
}
