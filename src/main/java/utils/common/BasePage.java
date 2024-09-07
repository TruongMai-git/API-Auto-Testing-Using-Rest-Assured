package utils.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePage {
    private long longTimeout = 30L;
    private long shortTimeout = 5L;
    private JavascriptExecutor jsExecutor;
    private Actions action;

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver, String loginPageUrl) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertAccept(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, this.longTimeout);
        return (Alert) explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        this.waitForAlertAccept(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        this.waitForAlertAccept(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return this.waitForAlertAccept(driver).getText();
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        Iterator var4 = allWindowIDs.iterator();

        while (var4.hasNext()) {
            String id = (String) var4.next();
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }

    }

    public void switchToWindowByTitles(WebDriver driver, String tabTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        Iterator var4 = allWindowIDs.iterator();

        while (var4.hasNext()) {
            String id = (String) var4.next();
            if (!id.equals(tabTitle)) {
                driver.switchTo().window(id);
                break;
            }
        }

    }

    public void clsoeAllTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (Iterator var4 = allWindowIDs.iterator(); var4.hasNext(); driver.switchTo().window(parentID)) {
            String id = (String) var4.next();
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }

    }

    public By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }
    public By getByXpath(String xpathLocator, String... dynamicValues) {
        return By.xpath(xpathLocator);
    }

    public WebElement getWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(this.getByXpath(xpathLocator));
    }
    public WebElement getWebElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
        return driver.findElement(this.getByXpath(xpathLocator, dynamicValues));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElements(this.getByXpath(xpathLocator));
    }

    public void clickToElement(WebDriver driver, String xpathLocator) {
        this.getWebElement(driver, xpathLocator).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues)).click();
    }

    public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
        WebElement element = this.getWebElement(driver, xpathLocator);
        element.clear();
        element.sendKeys(textValue);
    }
    public void sendKeyToElementNoClear(WebDriver driver, String xpathLocator, String textValue) {
        WebElement element = this.getWebElement(driver, xpathLocator);
        element.sendKeys(textValue);
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
        WebElement element = this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    public void clickItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
        Select select = new Select(this.getWebElement(driver, xpathLocator));
        select.selectByValue(textItem);
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
        Select select = new Select(this.getWebElement(driver, xpathLocator));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
        Select select = new Select(this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues)));
        return select.getFirstSelectedOption().getText();
    }

    public Boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
        Select select = new Select(this.getWebElement(driver, xpathLocator));
        return select.isMultiple();
    }
    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath,
                                           String childXpath, String expedtedItem) {
        this.getWebElement(driver, parentXpath).click();
        this.sleepInSecond(1);
        WebDriverWait explicitWait = new WebDriverWait(driver, this.longTimeout);
        List<WebElement> allItems = (List) explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(this.getByXpath(childXpath)));
        Iterator var = allItems.iterator();

        while (var.hasNext()) {
            WebElement item = (WebElement) var.next();
            if (item.getText().trim().equals(expedtedItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", new Object[]{item});
                this.sleepInSecond(2);
                item.click();
                break;
            }
        }

    }
    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
                                           String expedtedItem, String... dynamicValues) {
        this.getWebElement(driver, parentXpath).click();
        this.sleepInSecond(1);
        WebDriverWait explicitWait = new WebDriverWait(driver, this.longTimeout);
        List<WebElement> allItems = (List) explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(this.getByXpath(childXpath, childXpath)));
        Iterator var = allItems.iterator();

        while (var.hasNext()) {
            WebElement item = (WebElement) var.next();
            if (item.getText().trim().equals(expedtedItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", new Object[]{item});
                this.sleepInSecond(2);
                item.click();
                break;
            }
        }

    }

    public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
        return this.getWebElement(driver, xpathLocator).getAttribute(attributeName);
    }

    public String getElementCssValues(WebDriver driver, String xpathLocator, String propertyName) {
        return this.getWebElement(driver, xpathLocator).getCssValue(propertyName);
    }
    public String getElementCssValues(WebDriver driver, String xpathLocator, String propertyName, String... dynamicValues) {
        return this.getWebElement(driver, xpathLocator, dynamicValues).getCssValue(propertyName);
    }

    public String getElementText(WebDriver driver, String xpathLocator) {
        return this.getWebElement(driver, xpathLocator).getText();
    }

    public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
        return this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues)).getText();
    }

    public List<String> getTextOfListElement(WebDriver driver, String xpathLocator) {
        List<WebElement> listOfLabels = getListWebElement(driver, xpathLocator);
        List<String> listOfText = new ArrayList();
        for (WebElement i : listOfLabels) {
            String a = i.getText();
            listOfText.add(a);
        }
        return listOfText;
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String xpathLocator) {
        return this.getListWebElement(driver, xpathLocator).size();
    }


    public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
        return this.getListWebElement(driver, getByDynamicXpath(locatorType, dynamicValues)).size();
    }

    public void cherckToDefaultCheckBoxRadioButton(WebDriver driver, String xpathLocator) {
        WebElement element = this.getWebElement(driver, xpathLocator);
        if (!element.isSelected() && element.isEnabled()) {
            element.click();
        }
    }
    public void cherckToDefaultCheckBoxRadioButtonByWebElement(WebDriver driver,  WebElement element) {
        if (!element.isSelected() && element.isEnabled()) {
            element.click();
        }
    }

    public void cherckToDefaultCheckBoxRadioButton(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues));
        if (!element.isSelected() && element.isEnabled()) {
            element.click();
        }
    }

    public void uncherckToDefaultCheckBoxRadioButton(WebDriver driver, String xpathLocator) {
        WebElement element = this.getWebElement(driver, xpathLocator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncherckToDefaultCheckBoxRadioButton(WebDriver driver,
                                                     String locatorType, String... dynamicValues) {
        WebElement element = this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues));
        if (element.isSelected()) {
            element.click();
        }
    }

    public Boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
        return this.getWebElement(driver, xpathLocator).isDisplayed();
    }

    public Boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        return this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues)).isDisplayed();
    }

    public Boolean isElementSelected(WebDriver driver, String xpathLocator) {
        return this.getWebElement(driver, xpathLocator).isSelected();
    }

    public Boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
        return this.getWebElement(driver, getByDynamicXpath(locatorType, dynamicValues)).isSelected();
    }

    public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(this.getWebElement(driver, xpathLocator));
    }

    public void switchtoDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseTo(WebDriver driver, String xpathLocator) {
        Actions action = new Actions(driver);
        action.moveToElement(this.getWebElement(driver, xpathLocator)).perform();
    }

    public void hoverMouseTo(WebDriver driver, String xpathLocator, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.moveToElement(this.getWebElement(driver, xpathLocator)).perform();
    }

    public void scroolToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)", new Object[0]);
    }

    public void scrollDownBy(WebDriver driver) {
        System.out.println("Scrolled");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,50000)", " ");
    }

    public void zoomTo(WebDriver driver, int level) {
        System.out.println("Scrolled");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + level + "%'");
    }

    public void highlightElement(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = this.getWebElement(driver, xpathLocator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", new Object[]{element, "style", "border: 2px solid red"});
        this.sleepInSecond(1L);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", new Object[]{element, "style", originalStyle});
    }

    public void clickToElementByJS(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("argument[0].click();", new Object[]{this.getWebElement(driver, xpathLocator)});
    }

    public void scrollToElementByJS(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("argument[0].scrollIntoView(true);",
                new Object[]{this.getWebElement(driver, xpathLocator)});
    }

    public void remvoeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("argument[0].remvoeAttribute('" + attributeRemove + "');",
                new Object[]{this.getWebElement(driver, xpathLocator)});
    }

    public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (Boolean) jsExecutor.executeScript(
                "return argument[0].complete && typeof argument[0].naturalWidth > 0", new Object[]{xpathLocator});
        return status;
    }

    public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return argument[0].valiadtionMessage;",
                new Object[]{this.getWebElement(driver, xpathLocator)});
    }

    public boolean isPageLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jqueryLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return (Long) jsExecutor.executeScript("returrn jQuery.active", new Object[0]) == 0L;
                } catch (Exception var3) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState", new Object[0])
                        .toString().equals("complete");
            }
        };
        return (Boolean) explicitwait.until(jqueryLoad) && (Boolean) explicitwait.until(jsLoad);
    }

    public void waitForElementVisiable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfElementLocated(this.getByXpath(xpathLocator)));
    }

    public void waitForElementVisiable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfElementLocated(
                this.getByXpath(getByDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementsVisiable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfAllElements(this.getListWebElement(driver, xpathLocator)));
    }

    public void waitForAllElementsVisiable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfAllElements(
                this.getListWebElement(driver, getByDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementInVisiable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(this.getByXpath(xpathLocator)));
    }

    public void waitForElementInVisiable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(
                this.getByXpath(getByDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementsInVisiable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfAllElements(this.getListWebElement(driver, xpathLocator)));
    }

    public void waitForAllElementsInVisiable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.visibilityOfAllElements(
                this.getListWebElement(driver, getByDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementClickable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.elementToBeClickable(this.getByXpath(xpathLocator)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitwait = new WebDriverWait(driver, this.longTimeout);
        explicitwait.until(ExpectedConditions.elementToBeClickable(
                this.getByXpath(getByDynamicXpath(locatorType, dynamicValues))));
    }

    private String getByDynamicXpath(String locatorType, String... dynamicValues) {
        locatorType = String.format(locatorType, (Object[]) dynamicValues);
        return locatorType;
    }

    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        }
    }
}

