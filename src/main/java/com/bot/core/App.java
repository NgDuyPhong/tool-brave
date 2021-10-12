package com.bot.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private static float sumChange = 0;

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
			"Profile 15",
			"Profile 16",
			"Profile 17",
			"Profile 18",
			"Profile 19",
			"Profile 20",
			"Profile 21",
			"Profile 22",
			"Profile 23",
			"Profile 24",
			"Profile 25",
			"Profile 26",
			"Profile 27",
			"Profile 28",
			"Profile 29",
			"Profile 30"
	};

	public static void main(String[] args) {
		System.out.println("Tool start");
		try {
    		tool(); // run tool
//			printSum(true); // count Coin
//			printSum(false); // count USD
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Please close all browser Brave and try again!");
		}
		System.out.println("done!");
	}

	public static void tool() {
		int i = 0;
		while (i != 100) {
			System.out.println("---------------------------------------------");
			System.out.println("Run number: " + ++i);
			for (String profile : listProfileName) {
				runTool(profile);
			}
		}
	}

	public static void printSum(boolean isCoin) {
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);
		float sum = 0;
		float count = 0;
		String strUnit = isCoin ? "BAT" : "USD";
		// sum
		for (String profile : listProfileName) {
			count = countCoin(profile, isCoin);
			sum += count;
			System.out.println(profile + ": " + count);
			System.out.println("Sum " + strUnit + ": " + sum + " (" + strUnit + ")");
		}
		System.out.println("---------------------------------------------");
		System.out.println("Sum " + strUnit + ": " + sum + " (" + strUnit + ")");
	}

	public static float countCoin(String profileName, boolean isCoin) {
		ChromeOptions options = new ChromeOptions();
		options.setBinary(BRAVE_EXE);
		options.addArguments("--user-data-dir=" + USER_DATA_DIR_BRAVE);
		options.addArguments("--profile-directory=" + profileName);
		options.addArguments("--window-position=-1000,-1000");
		options.addArguments("--window-size=0,0");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver(options);
		WebElement el;
		if (isCoin) {
			el = driver.findElement(By.className("Amount-sc-ejzzb7"));
		} else el = driver.findElement(By.className("AmountUSD-sc-1qi3gv"));
		
		String amount = el.getAttribute("innerHTML").replace(" USD", "");
		float FlAmount = Float.parseFloat(amount);
		driver.quit();
		return FlAmount;
	}

	public static void runTool(String profileName) {
		try {
			Logger logger = Logger.getLogger("");
			logger.setLevel(Level.OFF);
			Thread.sleep(500);
			// Initializing ChromeOptions Object
			ChromeOptions options = new ChromeOptions();
			int flagForBeff = 0;
			String amountBeff = "0";
			String amount = "0";
			String strFrom = "";
			String strTo = "";
			for (int i = 0; i < 60; i++) {
				System.out.println("");
				System.out.println("Run Brave - " + profileName + ": " + (i + 1));

				Thread.sleep(500);
				File dirFrom;
				File dirTo;
				
				// Copy file
				for (String item : ARRAY_FILE) {
					strFrom = PATH_FILE_FROM + item;
					strTo = PATH_FILE_TO + profileName + ADS_SERVICE + item;
					dirFrom = new File(strFrom);
					dirTo = new File(strTo);
					copyFile(dirFrom, dirTo);
				}

				Thread.sleep(500);
				// Setting Binary Path of Brave Browser in options object.
				options.setBinary(BRAVE_EXE);
				options.addArguments("--user-data-dir=" + USER_DATA_DIR_BRAVE);
				options.addArguments("--profile-directory=" + profileName);
				options.addArguments("--window-position=-10000,-1000");
				options.addArguments("--window-size=0,0");

				options.setCapability("requireWindowFocus", false);
				System.setProperty("webdriver.chrome.silentOutput", "true");
				// Initializing Chrome Browser Instance
				WebDriver driver = new ChromeDriver(options);

				WebElement el = driver.findElement(By.className("Amount-sc-ejzzb7"));
				amountBeff = el.getAttribute("innerHTML");
				if (Float.parseFloat(amountBeff) < Float.parseFloat(amount)) {
					amountBeff = amount;
				}
				amount = amountBeff;
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
						System.out.println("All coin change: " + sumChange);
						break;
					}
				} else {
					sumChange += Float.parseFloat(amount) - Float.parseFloat(amountBeff);
					flagForBeff = 0;
				}
				System.out.println("All coin change: " + sumChange);

				// close browser brave
				driver.quit();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copyFile(File from, File to) {
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
