package com.bot.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Tool auto step brave
 *
 */
public class App 
{
//	C:\Program Files (x86)\BraveSoftware\Brave-Browser\Application\brave.exe\BraveSoftware\Brave-Browser\Application\brave.exe --window-position=50,50 --window-size=300,300
	private static String USER_DATA_DIR_BRAVE = "C:/Users/nguye/AppData/Local/BraveSoftware/Brave-Browser/User Data/";
	private static String PATH_FILE_FROM = "D:/Code/Freelancer/brave-tool/FullCombo/5555/";
	private static String PATH_FILE_TO = "C:/Users/nguye/AppData/Local/BraveSoftware/Brave-Browser/User Data/";
	private static String BRAVE_EXE = "C:/Program Files (x86)/BraveSoftware/Brave-Browser/Application/brave.exe";

	private static String ADS_SERVICE = "/ads_service/";
	private static String[] ARRAY_FILE = {"client.json", "database.sqlite", "database.sqlite-journal", "notifications.json"};
	
	private static String[] listProfileName = {
			"Default",
			"Profile 1",
			"Profile 2",
			"Profile 3",
			"Profile 4",
			"Profile 5",
			"Profile 6",
			"Profile 7",
			"Profile 8",
			"Profile 9",
			"Profile 10",
			"Profile 11",
			"Profile 12",
			"Profile 13",
			"Profile 14",
			"Profile 15"
	};

    public static void main( String[] args ) {
    	System.out.println( "Tool start" );
    	try {
    		tool();
//        	printSum();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Please close all browser Brave and try again!");
		}
    	
    	System.out.println("done!");
    }
    
    public static void tool() {
    	int i = 0;
    	while(i != 100) {
    		System.out.println("Run number: " + ++i);
    		for (String profile : listProfileName) {
        		runTool(profile);
    		}
    		i++;
    	}
    }
    
    public static void printSum() {
    	float sum = 0;
    	// sum coin had
    	for (String profile : listProfileName) {
    		sum += countCoin(profile);
    		System.out.println(profile + ": " + sum);
		}
    	System.out.println("Sum coin: " + sum);
    }
    
    public static float countCoin(String profileName) {
    	ChromeOptions options = new ChromeOptions();
    	options.setBinary(BRAVE_EXE);
        options.addArguments("--user-data-dir=" + USER_DATA_DIR_BRAVE);
        options.addArguments("--profile-directory=" + profileName);
        options.addArguments("--window-position=-10000,-1000");
        options.addArguments("--window-size=0,0");
        WebDriver driver = new ChromeDriver(options);
        
        WebElement el = driver.findElement(By.className("Amount-sc-ejzzb7"));
        String amount = el.getAttribute("innerHTML");
        float FlAmount = Float.parseFloat(amount);
        driver.quit();
        return FlAmount;
    }
    
    public static void runTool(String profileName) {
    	try {
    		float sumChange = 0;
	    	Thread.sleep(500);
	        //Initializing ChromeOptions Object
	        ChromeOptions options = new ChromeOptions();
	        int flagForBeff = 0;
	        for (int i = 0; i < 60; i++) {
	    		System.out.println("Run Brave - " + profileName + ": " + (i + 1));

	        	Thread.sleep(500);
	        	File dirFrom;
	        	File dirTo;

	        	Thread.sleep(500);
		        //Setting Binary Path of Brave Browser in options object.
		        options.setBinary(BRAVE_EXE);
		        options.addArguments("--user-data-dir=" + USER_DATA_DIR_BRAVE);
		        options.addArguments("--profile-directory=" + profileName);
		        options.addArguments("--window-position=-10000,-1000");
		        options.addArguments("--window-size=0,0");
		         
		        options.setCapability("requireWindowFocus", false);
		        options.addArguments("--start-minimized");

		        //Initializing Chrome Browser Instance
		        WebDriver driver = new ChromeDriver(options);
		        
		        // Copy file
	        	for (String item : ARRAY_FILE) {
	        		String strFrom = PATH_FILE_FROM + item;
	            	String strTo = PATH_FILE_TO + profileName + ADS_SERVICE + item;
	            	dirFrom = new File(strFrom);
	            	dirTo = new File(strTo);
	            	copyFile(dirFrom, dirTo);
				}

		        WebElement el = driver.findElement(By.className("Amount-sc-ejzzb7"));
		        String amountBeff = el.getAttribute("innerHTML");
		        String amount = amountBeff;
		        System.out.println("Amount start: " + amountBeff);
		        Thread.sleep(500);
		        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		        for (int j = 0; j < 23; j++) {
		        	Thread.sleep(250);
		        	driver.navigate().refresh();
		        	if (j == 22) {
		        		el = driver.findElement(By.className("Amount-sc-ejzzb7"));
			        	amount = el.getAttribute("innerHTML");
		        	}
		        }
		        System.out.println("Amount end: " + amount);
		        if (Float.parseFloat(amountBeff) == Float.parseFloat(amount)) {
		        	if (flagForBeff == 0) {
			        	flagForBeff = 1;
			        } else {
			        	flagForBeff = 0;
			        	driver.quit();
			        	break;
			        }
		        } else {
		        	sumChange += Float.parseFloat(amount) - Float.parseFloat(amountBeff);
		        	flagForBeff = 0;
		        }
		        System.out.println("All coin change same: " + sumChange);

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
    		}
    		Files.copy(from.toPath(), to.toPath());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    } 
}
