# Amazon
Amazon project consist flow from search product to proceed to buy functionality.
Here i used automation tool Selenium with java and TestNG.
buy_Product is a package which contains Buy_Dress class.
In selenium script at begining launch Edge browse, Maximize browser window using manage().window() methods in driver context.
Launch web page using "get()" method in driver context.
WebElements are located by using "findElement" method and "xpath" locator is used.
after finding element pergorm operation on that element.
for handling dropdown elment "Select" class is used, this class has several methods as selectByVisibleText(),selectByValue()....
for send string  value into element use sendKeys() method and for click on element use click() method.
To perform synch in between script execution and web element loading time Synchronization concept is used in that implicit wait, explicit(WebDriverWait) is used.
For validation purpose Assertion concept use in that SoftAssert use it has several methods.
For scrolling web page JavascriptExecutor interface used in that executeScript is method present.
At last for taking screenshot getScreenshotAs method in context of TakesScreenshot interface is used, which capture screenshot and store in file format to store this on our required destination create Screenshot folder and store in it buy using FileHandler.copy Method
TestNG
Execution report is genereted using TestNG eg- pass fail skip 
Able to write test cases in class
use Different Annotations like @BeforeClass @Test @AfteClass etc.

