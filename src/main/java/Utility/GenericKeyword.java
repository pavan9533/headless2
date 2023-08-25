package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ExtentReports.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericKeyword {
	public static WebDriver driver;
	public Properties prop;
	public Properties envprop;
	public  ExtentReports report;
	public  static ExtentTest test;
	public SoftAssert softAssert;
	public Select select;
	public Alert alert;
	public String date;
	public Actions action;
	public void launchBrowser(String browser) {
			
		log( "Launching Browser  "+ browser);
		
		 if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*");
			ops.addArguments("--incognito");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(ChromeOptions.CAPABILITY, ops);
			ops.merge(cap);
			driver = new ChromeDriver(ops);
		}else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		 
		logPass(browser +"Browser Launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void launchBrowser(List<String> browsers) {
	    log("Launching Browser: " + browsers);
	    String browser = browsers.get(0); // Assuming only one browser will be passed in the list

	    if (browser.equals("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*");
			ops.addArguments("--incognito");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(ChromeOptions.CAPABILITY, ops);
			ops.merge(cap);
	        driver = new ChromeDriver(ops);
	    } else if (browser.equals("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
	    } else if (browser.equals("edge")) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
	    }

	    logPass(browser + " Browser Launched");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void navigate(String url) {
		log("Navigating to the browser");
		driver.get(prop.getProperty(url));
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
		wait(3);
		log("Page Refreshed");
	}
	
	
	public void click(String Locator) {
		log("Clicking an element");
		getElement(Locator).click();
	}
	
	public void clickEnter(String locator) {
		getElement(locator).sendKeys(Keys.ENTER);
		
	}
	
	public void type(String locator,String data) {
		log("Locator  "+locator+"    "+"Data     "+data);
		getElement(locator).sendKeys((prop.getProperty(data)));		
	}
	
	
	
	
	public void type(String locator, List<String> data) {
	    log("Locator  " + locator + "    " + "Data     " + data);
	    WebElement element = getElement(locator);

	    for (String value : data) {
	        element.sendKeys(value);
	       
	    }
	}
	
	public void typeAfterSwitch(String locator, List<String> data) {
	    log("Locator  " + locator + "    " + "Data     " + data);

	    // Switch to the appropriate frame or iframe if necessary
	    driver.switchTo().window(locator);

	    WebElement element = getElement(locator);

	    for (String value : data) {
	        element.sendKeys(value);
	    }
	}

	
	
	

	
	public void select(String locator, String data) {
		
	}
	
	public void getText(String locatorKey) {
		WebElement e = driver.findElement(getLocator(locatorKey));
		if(e.isDisplayed()) {
		String text=e.getText();
		test.log(Status.PASS, text);
		}else {
			test.log(Status.INFO, "Text is not present");
		}
	}
	
	public String getStringText(String locatorKey) {
		WebElement e = driver.findElement(getLocator(locatorKey));
		String text=e.getText();
		if(e.isDisplayed()) {
		
		test.log(Status.PASS, text);
		}else {
			test.log(Status.INFO, "Text is not present");
		}
		return text;
	}
	public WebElement getText1(String locatorKey) {
		WebElement e = driver.findElement(getLocator(locatorKey));
		String text=e.getText();
		if(e.isDisplayed()) {
		
		test.log(Status.PASS, text);
		}else {
			test.log(Status.INFO, "Text is not present");
		}
		return e;
	}

	
	public boolean visibilityOfElement(String locator) {
		WebDriverWait  wait = new  WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
		
		return true;
	}
	
	public boolean presenceOfElement(String locator) {
		WebDriverWait  wait = new  WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locator)));
		
		return true;
	}
	
	
	
	public void compareText(String locatorKey1, String locatorKey2) {
	    WebElement e1 = driver.findElement(getLocator(locatorKey1));
	    WebElement e2 = driver.findElement(getLocator(locatorKey2));
	    
	    String text1 = e1.getText();
	    String text2 = e2.getText();
	    
	    if (text1.equals(text2)) {
	        test.log(Status.PASS, "Text match: " + text1);
	    } else {
	        test.log(Status.FAIL, "Text does not match. Expected: " + text1 + ", Actual: " + text2);
	        generateScreenshots();
	    }
	}
	
	public void selectColorFromExcel(String option1Locator, String option2Locator, String columnName, String sheetName) {
	    List<String> colours = readExcelData(columnName, sheetName);
	    if (colours != null && !colours.isEmpty()) {
	        WebElement option1 = driver.findElement(getLocator(option1Locator));
	        WebElement option2 = driver.findElement(getLocator(option2Locator));

	        for (String colour : colours) {
	            if (colour.equalsIgnoreCase("Brown")) {
	                option1.click();
	                break;
	            } else if (colour.equalsIgnoreCase("White")) {
	                option2.click();
	                break;
	            }
	        }
	    } else {
	        test.log(Status.INFO, "No colour data found in the Excel sheet");
	    }
	}




	
	public WebElement getElement(String locator) {
		log("Getting WebElement  "+locator);
		//Check the presence
		if(!presenceOfElement(locator)) {
			log("Element not present  "+locator);
		}
		
		//Check the visibility
		if(!visibilityOfElement(locator)) {
			log("Element not visible  "+locator);
		}
		
		
		WebElement e = driver.findElement(getLocator(locator));
		
		return e;
	}
	
	public List<WebElement> getElements(String locator) {
	    log("Getting WebElements: " + locator);

	    // Check the presence
	    if (!presenceOfElement(locator)) {
	        log("Element not present: " + locator);
	        return new ArrayList<>(); // Return an empty list if element not present
	    }

	    // Check the visibility
	    if (!visibilityOfElement(locator)) {
	        log("Element not visible: " + locator);
	        return new ArrayList<>(); // Return an empty list if element not visible
	    }

	    List<WebElement> elements = driver.findElements(getLocator(locator));
	    return elements;
	}
	
	public int numberOfSubClassesInClass(Class<?> parentLocatorClass) {
	    int subClassCount = 0;
	    Class<?>[] classes = parentLocatorClass.getDeclaredClasses();

	    for (Class<?> clazz : classes) {
	        if (parentLocatorClass.isAssignableFrom(clazz)) {
	            subClassCount++;
	        }
	    }
	    
	    log("Number of subclasses in class: " + subClassCount);
	    return subClassCount;
	}
	
	public WebElement moveTo(String locator) {
		
		WebElement e = driver.findElement(getLocator(locator));
		Actions act = new Actions(driver);
		act.moveToElement(e).click().perform();
		return e;
	}
	
	public void scrollTo(String locator) {
        WebElement e = driver.findElement(getLocator(locator));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", e);
        
    }
	


	public void scrollByValue(String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String script = "window.scrollBy(0, " + value + ");";
        jsExecutor.executeScript(script);
    }
	
	 
	public By getLocator(String locator) {
	
		By by = null;
		if(locator.endsWith("_id")) {
			by = By.id(prop.getProperty(locator));
		}else if(locator.endsWith("_name")) {
			by = By.name(prop.getProperty(locator));
		}else if(locator.endsWith("_css")) {
			by = By.cssSelector(prop.getProperty(locator));
		}else if(locator.endsWith("_xpath")) {
			by = By.xpath(prop.getProperty(locator));
		}else if(locator.endsWith("_class")) {
			by = By.className(prop.getProperty(locator));
		}
		return by;
	}
	public String getAttributeValue(String element, String attributeName) {
		WebElement ele = driver.findElement(getLocator(element));
	    return ele.getAttribute(attributeName);
	}


	
	public void log(String message) {
		test.log(Status.INFO, message);
	}
	
	public void validateAddToCartPopup(String locator) {
		String popupText = null;
		try {
	        WebElement popup = driver.findElement(getLocator(locator));
	        popupText = popup.getText();	
		} catch (NoAlertPresentException e) {
	        test.log(Status.FAIL, "No alert found");
	        // Handle the case when no alert is present
	    }
	       String addToCartText = "Item successfully Added to Cart.";
	       String selectVariantText = "Please Select All Variants";
	       
	    if(popupText.equals(addToCartText)) {
	    	test.log(Status.PASS, popupText+" is displayed");
	    	generateScreenshots();
	    }else {
	    	test.log(Status.FAIL, popupText+" is displayed");
	    	generateScreenshots();
	    }
	}
	
	public void validateItemNotAvailablePopup(String locator) {
		String popupText = null;
		try {
	        WebElement popup = driver.findElement(getLocator(locator));
	        popupText = popup.getText();	
		} catch (NoAlertPresentException e) {
	        test.log(Status.FAIL, "No alert found");
	        // Handle the case when no alert is present
	    }
	       String itemNotAvailableText = "Delivery not available to this location, Please check for other";
	       
	    if(popupText.equals(itemNotAvailableText)) {
	    	test.log(Status.PASS, popupText+" is displayed");
	    	generateScreenshots();
	    }else {
	    	test.log(Status.FAIL, popupText+" is displayed");
	    	generateScreenshots();
	    }
	}
	
	public void validateSelectVariantPopup(String locator) {
		String popupText = null;
		try {
	        WebElement popup = driver.findElement(getLocator(locator));
	        popupText = popup.getText();	
		} catch (NoAlertPresentException e) {
	        test.log(Status.FAIL, "No alert found");
	        // Handle the case when no alert is present
	    }
	       String selectVariantText = "Please Select All Variants";
	       
	    if(popupText.equals(selectVariantText)) {
	    	test.log(Status.PASS, popupText+" is displayed");
	    	generateScreenshots();
	    }else {
	    	test.log(Status.FAIL, popupText+" is displayed");
	    	generateScreenshots();
	    }
	}
	
	public void logPass(String message) {
		test.log(Status.PASS, message);
	}
	
	public void reportFailure( String failMsg, boolean stopOnFailure) {
		test.log(Status.FAIL, failMsg);
		generateScreenshots();
		softAssert.fail(failMsg);
		if(stopOnFailure) {
			Reporter.getCurrentTestResult().setAttribute("criticalfailure", "Y");
			assertAll();
		}	
	}
	
	public void assertAll() {
		Reporter.getCurrentTestResult().setAttribute("critical", "Y");
		softAssert.assertAll();
	}
	
	public  static String  generateScreenshots()  {

		Date d = new Date();
		String filelocation = d.toString().replaceAll(":", "-").replaceAll(" ","_")+".jpg";
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String path = ExtentManager.reports+"//"+filelocation;
		File des = new File(path);
		
		try {
			FileUtils.copyFile(src, des);
			test.log(Status.INFO, "Screenshot--> "+test.addScreenCaptureFromPath(path));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	return path;

	}
	
	public void clear(String locator) {
		getElement(locator).clear();
	}
	public void clearText(String locator) {
	    WebElement element = driver.findElement(getLocator(locator));
	    element.clear();
	}
	
//	public void waitForPageLoad() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		int i =0;
//		while(i!=10) {
//			String ajaxStatus = (String) js.executeScript("return document.readyState;");
//			if(ajaxStatus.equals("complete")) {
//				break;
//			}else {
//				wait(2);
//			}
//			i++;
//		}
//		i=0;
//		while(i!=10) {
//			Long jQueryStatus = (Long) js.executeScript("return jQuery.active;");
//			if(jQueryStatus.longValue()==0) {
//				break;
//			}else {
//				wait(2);
//			}
//			i++;
//		}
//		
//	}
//	
	

	 public void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	        wait.until(pageLoadCondition);
	    }


	
	public void wait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	public void waitForElement(int time, By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, time);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	
	public void selectFromDown(String locator, String option) {
		
		select = new Select(getElement(locator));
		select.selectByVisibleText(prop.getProperty(option));
		
		
	}
	
//	public void selectFromDown(String locator, String columnName, String sheetName) {
//	    List<String> options = readExcelData(columnName, sheetName);
//	    if (options != null && !options.isEmpty()) {
//	        WebElement element = getElement(locator);
//	        Select select = new Select(element);
//
//	        boolean optionSelected = false; // Flag to track if any option is selected
//
//	        for (String option : options) {
//	            List<WebElement> matchingOptions = select.getOptions().stream()
//	                    .filter(opt -> opt.getText().equalsIgnoreCase(option))
//	                    .collect(Collectors.toList());
//
//	            if (!matchingOptions.isEmpty()) {
//	                select.selectByVisibleText(option);
//	                optionSelected = true;
//	                break;
//	            }
//	        }
//
//	        if (!optionSelected) {
//	            test.log(Status.INFO, "No matching option found in the dropdown");
//	        }
//	    } else {
//	        test.log(Status.INFO, "No option data found in the Excel sheet");
//	    }
//	}
	public void selectFromDown(String locator, String columnName, String sheetName) {
	    List<String> options = readExcelData(columnName, sheetName);
	    if (options != null && !options.isEmpty()) {
	        WebElement element = getElement(locator);
	        Select select = new Select(element);

	        boolean optionSelected = false; // Flag to track if any option is selected

	        for (final String option : options) {
	            List<Object> matchingOptions = select.getOptions().stream()
	                    .filter(new Predicate<WebElement>() {
							@Override
							public boolean test(WebElement opt) {
								return opt.getText().equalsIgnoreCase(option);
							}
						})
	                    .collect(Collectors.toList());

	            if (!matchingOptions.isEmpty()) {
	                select.selectByVisibleText(option);
	                optionSelected = true;
	                break;
	            }
	        }

	        if (!optionSelected) {
	            test.log(Status.INFO, "No matching option found in the dropdown");
	        }
	    } else {
	        test.log(Status.INFO, "No option data found in the Excel sheet");
	    }
	}

	
	public void selectdValueInDropDown(String locator , List<String> option) {
		select = new Select(getElement(locator));
		select.getFirstSelectedOption().getText();
	}
	
	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();
	
	}
	
	public void selectDateFromCalendar(String date) {
		Date d = new Date();
		try {
			Date sdf = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			String day = new SimpleDateFormat("dd").format(sdf);
			String month = new SimpleDateFormat("MMMM").format(sdf);
			String year = new SimpleDateFormat("yyyy").format(sdf);
			String monthyear = month+" "+year;
			String displayedMonth = getElement("monthyear_css").getText();
			while(!monthyear.equals(displayedMonth)) {
				click("forward_xpath");
				displayedMonth= getElement("monthyear_css").getText();
			}
			WebElement e =  driver.findElement(By.xpath("//td[text()='"+day+"']"));
			wait(3);
			e.click();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	    
	
	public int selectDataFromTable(String locator, String data) {
		
		WebElement table = driver.findElement(getLocator(locator));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i=0;i<rows.size();i++) {
			System.out.println("Value before loop   "+i);
			WebElement row = rows.get(i);
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(int col=0; col<cells.size(); col++) {
				WebElement cell= cells.get(col);
				System.out.println(cell.getText());
				if(!cell.getText().trim().equals(" ")) 
					if(data.startsWith(cell.getText())) 
				
					 return (i+1);
			}
		}
		return -1;
		
	}
	
	public String countRowsInTableAsString(String locator) {
	    WebElement table = driver.findElement(getLocator(locator));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));
	    int rowCount = rows.size();
	    return String.valueOf(rowCount);
	}


	
	public int countColumnsInTable(String locator) {
	    WebElement table = driver.findElement(getLocator(locator));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));
	    
	    if (rows.size() > 0) {
	        List<WebElement> cells = rows.get(0).findElements(By.tagName("td"));
	        return cells.size();
	    }
	    
	    return 0;
	}


	

	@SuppressWarnings("deprecation")
	public List<String> readExcelData(String columnName, String nameOfSheet) {
	    String filePath = System.getProperty("user.dir") + "//Excel//data.xlsx";
	    String sheetName = nameOfSheet;
	    int startingRowIndex = 1; // Assuming the data starts from the second row (row index 1)

	    List<String> data = new ArrayList<>();

	    try (FileInputStream fileInputStream = new FileInputStream(filePath);
	         Workbook workbook = new XSSFWorkbook(fileInputStream)) {

	        Sheet sheet = workbook.getSheet(sheetName);

	        // Find the column index for the specified column name
	        Row headerRow = sheet.getRow(0);
	        int columnIndex = -1;
	        for (Cell cell : headerRow) {
	            if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
	                columnIndex = cell.getColumnIndex();
	                break;
	            }
	        }

	        // Retrieve the data from the specified column
	        int lastRowIndex = sheet.getLastRowNum();
	        for (int rowIndex = startingRowIndex; rowIndex <= lastRowIndex; rowIndex++) {
	            Row dataRow = sheet.getRow(rowIndex);
	            if (dataRow != null) {
	                Cell cell = dataRow.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	                if (cell != null) {
	                    cell.setCellType(CellType.STRING);
	                    data.add(cell.getStringCellValue());
	                } else {
	                    data.add(""); // Add an empty string for blank cells
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }

	    return data;
	}
	
	public String readExcelDataAsString(String columnName, String nameOfSheet) {
	    List<String> data = readExcelData(columnName, nameOfSheet);
	    if (data == null || data.isEmpty()) {
	        test.log(Status.FAIL, "Data is not present in the excel");
	        return ""; // Return an empty string if the data retrieval failed or if there is no data
	    }

	    StringBuilder stringBuilder = new StringBuilder();
	    for (String value : data) {
	        stringBuilder.append(value).append("\n"); // Append each value to the string with a new line separator
	    }

	    return stringBuilder.toString();
	}

	public List<String> gettextFromExcel(String columnName, String nameOfSheet) {
		List<String> data = readExcelData(columnName, nameOfSheet);
		 if (data == null || data.isEmpty()) {
		        test.log(Status.FAIL, "Data is not present in the excel");
		    }else {
		    	test.log(Status.PASS, "text is "+data);
		    }
		return data;
	}


	
	public void getTextFromExcel(String columnName , String sheetname) {
	    List<String> data = readExcelData(columnName, sheetname);
	    if (data != null && !data.isEmpty()) {
	        for (String text : data) {
	            if (!text.isEmpty()) {
	                test.log(Status.PASS, text);
	            } else {
	                test.log(Status.INFO, "Text is not present");
	            }
	        }
	    } else {
	        test.log(Status.INFO, "No data found in the Excel sheet");
	    }
	}
	public List<String> getTextsFromExcel(String locatorKey, String sheetName) {
	    List<String> data = readExcelData(locatorKey, sheetName);
	    List<String> extractedTexts = new ArrayList<>();

	    if (data != null && !data.isEmpty()) {
	        for (String text : data) {
	            if (!text.isEmpty()) {
	                extractedTexts.add(text);
	            }
	        }
	    }

	    return extractedTexts;
	}


	
	
	
	
	public void navigate(String columnName, String nameOfSheet) {
		log("Navigating to the browser");

		 List<String> excelData = readExcelData(columnName, nameOfSheet);

		    if (excelData != null && !excelData.isEmpty()) {
		        String url = excelData.get(0); // Assuming the URL is at index 0 of the excelData list
		        driver.get(url);
		    } else {
		        log("Excel data is empty or null. Unable to navigate.");
		    }
	}
	
	public void clearFilter(String locatorKey) {
		WebElement button = driver.findElement(getLocator(locatorKey));
		if(button.isDisplayed()) {
			button.click();
		}else {
			log("Clear All button not visible");
		}
	}
	
	public void selectCategoryFromExcel(String columnName, String nameOfSheet, String fridgeLocator, String washingLocator, String AcLocator, String MicrowaveLocator, String dishLocator, String Purifier) {
	  
	    WebElement fridge = getText1(fridgeLocator);
	    WebElement washing = getText1(washingLocator);
	    WebElement AC = getText1(AcLocator);
	    WebElement micro = getText1(MicrowaveLocator);
	    WebElement dishwasher = getText1(dishLocator);
	    WebElement purifiers = getText1(Purifier);
	    
	    
	    String fridgeText = fridge.getText();
	    String washingText = washing.getText();
	    String acText = AC.getText();
	    String microwaveText = micro.getText();
	    String dishWasherText = dishwasher.getText();
	    String purifierText = purifiers.getText();
	    
	    
	    log("Reading data from Excel...");
	    List<String> categories = readExcelData(columnName, nameOfSheet);
	    if (categories == null) {
	        test.log(Status.FAIL, "Failed to read data from Excel.");
	        generateScreenshots();
	        return;
	    }

	    for (String category : categories) {
	        log("Selected category is " + category);

	        if (category.toLowerCase().equals(fridgeText.toLowerCase())) {
	        	WebElement refrigerator = driver.findElement(getLocator(fridgeLocator));
	            if (refrigerator.isDisplayed()) {
	                refrigerator.click();
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, "Refrigerator is not visible");
	                generateScreenshots();
	            }
	        } else if (category.toLowerCase().equals(washingText.toLowerCase())) {
	        	 WebElement washingMachine = driver.findElement(getLocator(washingLocator));
	            if (washingMachine.isDisplayed()) {
	                washingMachine.click();
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, "Washing Machine is not visible");
	                generateScreenshots();
	            }
	        } else if (category.toLowerCase().equals(acText.toLowerCase())) {
	        	WebElement ac = driver.findElement(getLocator(AcLocator));
	            if (ac.isDisplayed()) {
	                ac.click();
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, "Air Conditioner is not visible");
	                generateScreenshots();
	            }
	        } else if (category.toLowerCase().equals(microwaveText.toLowerCase())) {
	        	WebElement microwave = driver.findElement(getLocator(MicrowaveLocator));
	            if (microwave.isDisplayed()) {
	                microwave.click();
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, "Microwaves is not visible");
	                generateScreenshots();
	            }
	        } else if (category.toLowerCase().equals(purifierText.toLowerCase())) {
	        	WebElement purifier = driver.findElement(getLocator(Purifier));
	            if (purifier.isDisplayed()) {
	                purifier.click();
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, "Purifier is not visible");
	                generateScreenshots();
	            }
	        } else if (category.toLowerCase().equals(dishWasherText.toLowerCase())) {
	        	WebElement dishWasher = driver.findElement(getLocator(dishLocator));
	            if (dishWasher.isDisplayed()) {
	                dishWasher.click();
	                generateScreenshots();
	            } else {
	                test.log(Status.FAIL, "Dishwasher is not visible");
	                generateScreenshots();
	            }
	        }
	    }
	}
	
	public Object[][] getLoginData(String sheetname) throws IOException {
        String filePath =  System.getProperty("user.dir") + "//Excel//data.xlsx";
        String sheetName = sheetname;

        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                data[i][j] = cell.toString();
            }
        }

        workbook.close();
        fileInputStream.close();

        return data;
    }
	
	
	
	


	
	 public void quit() {
	        if(driver != null) {
	            driver.quit();
	        }
	 }

	
}
