package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	
	public By getBy(String locatorType, String locatorValue) {
		
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;	
		case "CSS":
			locator = By.cssSelector(locatorValue);
			break;
		case "Linktext":
			locator = By.linkText(locatorValue);
			break;	
		case "Partial Linktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "Tag name":
			locator = By.tagName(locatorValue);
			break;	
		default:
			break;
		}
		return locator;
	
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
		
	}
	
	public boolean doIsDisplayed(By locator ) {
		return getElement(locator).isDisplayed();
	}
	
	public String getAttributeValue (By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doSendkeys(String locatorType, String locatorValue, String value) {
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}
	
	
	public  void doSendkeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear(); 	
		ele.sendKeys(value);
	}
	
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator){
		return driver.findElements(locator);
	}
	
	public int getElementCount (By locator) {
		return getElements(locator).size();
	}
	
	
	public List<String> getElementAttributeList(By locator, String attrName) {
		List<WebElement> eleList =  getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for(WebElement e: eleList) {
			String attrVal = e.getAttribute(attrName);
			eleAttrList.add(attrVal);
		}
		return eleAttrList;		 
	}
	
	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList =  getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e: eleList) {
			String eleText = e.getText();
			System.out.println(eleText);
					eleTextList.add(eleText);
		}
		return eleTextList;		 
	}
	
	public  void  selectSuggestion(By locator, String searchkey, String suggVal) throws InterruptedException {
		driver.findElement(By.name("q")).sendKeys(searchkey);
		List<WebElement> suggList = driver.findElements(locator);
		for (WebElement e: suggList) {
			String text= (e.getText());
			System.out.println(text);
			if(text.contains(suggVal)) {
				e.click();
				break;
			}
		}
	}
	
	
	public  List<String>  getSugglist(By locator, String searchkey) throws InterruptedException {
		driver.findElement(By.name("q")).sendKeys(searchkey);
		List<WebElement> suggList = driver.findElements(locator);
		List<String> suggValList = new ArrayList<String>();
		for (WebElement e: suggList) {
			String text= (e.getText());
			System.out.println(text);
			suggValList.add(text);
			
		}
		return suggValList;
	}
	
	public boolean isElementDisplayed(By locator) {
		WebElement ele = getElement(locator);
		List<WebElement> elelist = getElements(locator);
		if(elelist.size()>0){
			if(ele.isDisplayed())
				return true;
		}
		return false;
	}
//**********************************************DRop down utils**********************************
	public void selectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public  void selectDropDownByVisible(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByValue(visibleText);
	}
	
	public void selectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void dropDownSelectValuewithGetOptions(By locator, String value) {
		
		Select select = new Select(getElement(locator));
		List<WebElement> optionList= select.getOptions();
		
		for(WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
		
	}	
	public void DropDownValueUsingLocator (By locator, String value) {
		List<WebElement> list = getElements(locator);
		for (WebElement e : list) {
			String text = e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
		}	
	
	
/**********************************************Action class utils************************************/
	
	public void leveltwoMenuHandling(By parentMenu, By childMenu) throws InterruptedException {
			Actions act = new Actions(driver);
			act.moveToElement(getElement(parentMenu)).perform();
			Thread.sleep(1500);
			getElement(childMenu).click();
	}
	
	public void MultiLevelMenuHandling(By parentMenu, String l1,String l2, String l3,String l4) throws InterruptedException {
		
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		
		Thread.sleep(1500);
		
		act.moveToElement(driver.findElement(By.linkText(l1))).perform();
		Thread.sleep(1500);
		
		act.moveToElement(driver.findElement(By.linkText(l2))).perform();
		Thread.sleep(1500);
		
		act.moveToElement(driver.findElement(By.linkText(l3))).perform();
		Thread.sleep(1500);
		
		driver.findElement(By.linkText(l4)).click();
		}
	
	public void selectRightClickMenu(By rightClickElementLocator, By rightMenuItems) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClickElementLocator)).perform();
		getElement(rightMenuItems).click();
	}
	
	public List<String> getRightClickMenuList(By rightClickElementLocator, By rightMenuItems) {
		List<String> rightMenuList = new ArrayList<String>();
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClickElementLocator)).perform();
		List<WebElement> menuItems = driver.findElements(rightMenuItems);

		for (WebElement e : menuItems) {
			String text = e.getText();
			rightMenuList.add(text);
		}
		return rightMenuList;
	}
		
	public void dragAndDropAction(By source, By target) {
		Actions act = new Actions(driver);
		act.clickAndHold(getElement(source)).moveToElement(getElement(target)).release().build().perform();
	}

	public void doActionSendkeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
/***********************************Wait utils****************************************************/
	
	// **********************************Wait Utils *************************//

		/**
		 * An expectation for checking that the title contains a case-sensitive
		 * substring
		 * 
		 * @param titleFraction
		 * @param timeOut
		 * @return
		 */
		public String waitForTitleContains(String titleFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
			return null;
		}

		/**
		 * An expectation for checking the title of a page.
		 * 
		 * @param title
		 * @param timeOut
		 * @return
		 */
		public String waitForTitleIs(String title, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
			return null;
		}

		public String waitForUrlContains(String urlFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
			return null;
		}

		public String waitForUrlIs(String url, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.urlToBe(url))) {
				return driver.getCurrentUrl();
			}
			return null;
		}

		public void acceptAlert(int timeOut) {
			waitForAlert(timeOut).accept();
		}

		public void dismissAlert(int timeOut) {
			waitForAlert(timeOut).dismiss();
		}

		public void alertSendKeys(int timeOut, String value) {
			waitForAlert(timeOut).sendKeys(value);
		}

		public String doGetAlertText(int timeOut) {
			return waitForAlert(timeOut).getText();
		}

		public Alert waitForAlert(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.alertIsPresent()); // wait for alert and then switch to alert

		}

		public void waitForFrameByLocator(By frameLocator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}

		public void waitForFrameByIndex(int frameIndex, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}

		public void waitForFrameByElement(WebElement frameElement, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeOut
		 */
		public void clickElementWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}

		public void waitForElementToBeClickableWithPolling(By locator, int timeOut, int pollingTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

		}

		/**
		 * An expectation for checking that all elements present on the web page that
		 * match the locator are visible. Visibility means that the elements are not
		 * only displayed but also have a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		/**
		 * An expectation for checking that there is at least one element present on a
		 * web page. Default Polling Time = 500ms
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}

		/**
		 * An expectation for checking that there is at least one element present on a
		 * web page. Default Polling Time = customized
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeOut, int pollingTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}

		public void waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime, String message) {

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofMillis(pollingTime))
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class).withMessage(message);

			wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
		}

		///
		public WebElement retryingElement(By locator, int timeOut, int pollingTime) {

			WebElement element = null;

			int attempts = 0;
			while (attempts < timeOut) {
				try {
					element = driver.findElement(locator);
					break;
				} catch (NoSuchElementException e) {
					System.out.println("element is not found in attempt : " + attempts + " for : " + locator);
					try {
						Thread.sleep(pollingTime);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				attempts++;
			}

			if (element == null) {
				try {
					throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
				} catch (Exception e) {
					System.out.println("element is not found exception....tried for : " + timeOut + " secs"
							+ " with the interval of : " + pollingTime + " ms ");
				}
			}

			return element;

		}

		public void waitForPageLoad(int timeOut) {

			long endTime = System.currentTimeMillis() + timeOut;

			while (System.currentTimeMillis() < endTime) {

				JavascriptExecutor js = (JavascriptExecutor) driver;
				String state = js.executeScript("return document.readyState").toString();
				System.out.println("page is : " + state);
				if (state.equals("complete")) {
					System.out.println("page is fully loaded now....");
					break;
				}

			}

		}
	
	
	
}

	
	
