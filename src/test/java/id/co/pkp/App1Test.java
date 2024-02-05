package id.co.pkp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-playwright
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/30/23
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public class App1Test {
    @Test
    @DisplayName("Test Web PKP")
    public void test1() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://www.google.co.id/");
        System.out.println("Page Title nya adalah: " + page.title());
    }

    @Test
    @DisplayName("Check URL or Check HTTPS")
    public void testCheckHTTPS() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://10.8.11.1:8761/");
        String currentUrl = page.url();
        String expectedUrl = "https://10.8.11.1:8761/";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("URL is correct: " + currentUrl);
        } else {
            System.out.println("URL is incorrect. Expected: " + expectedUrl + ", but got: " + currentUrl);
        }
        // System.out.println(currentUrl);
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Check Place Holder")
    public void checkPlaceHolder() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://testkru.com/Elements/TextFields");

        Locator textLocator = page.locator("#lastNameWithPlaceholder");

        String placeholder = textLocator.getAttribute("placeholder");
        // using getAttribute() method to get the placeholder text
        System.out.println("Placeholder text: " + placeholder);
        String expectedText = "Enter your last name...";
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(expectedText, placeholder, "MATCHED");

        if (placeholder.contains("Enter your last name...")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL! No such texts");
        }

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Assert Checkbox")
    public void assertCheckBox() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/dropdown1/");
        Locator locator = page.locator("select.custom-select option >> nth=-2");
        String attributeV = locator.getAttribute("value");

        if (attributeV.equals("item3")) {
            System.out.println("Attribute value is correct!");
        } else {
            System.out.println("Attribute value is incorrect.");
        }
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Tooltip Check Test")
    public void tooltipCheckTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();

        Page page = browser.newPage();
        page.navigate("https://jqueryui.com/tooltip/");
        FrameLocator frameOne = page.frameLocator(".demo-frame");
        Locator ageBox = frameOne.locator("#age");
        Locator toolTipText = frameOne.locator(".ui-tooltip-content");
        ageBox.hover();
        String textContent = toolTipText.textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Soft Assertion Test")
    public void softAssertionTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/user/login");
        page.locator("#edit-name").type("yuji");
        page.locator("#edit-pass").type("yuji");
        page.locator("(//input[@type='submit'])[2]").click();
        String actualText = page.locator("//a[normalize-space()='Forgot your password?']").textContent();
        System.out.println(actualText);
        String expectedText = "Forgot your password?";
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(actualText, expectedText, "Matched");

        System.out.println("This part is executed");
        soft.assertAll();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Assert Title Test")
    public void assertTitleTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("http://www.programsbuzz.com");
        String title = page.title();
        String expectedTitle = "ProgramsBuzz - Online Technical Courses";
        if (title.equalsIgnoreCase(expectedTitle)) {
            System.out.println("Title Match Verified");
        } else {
            System.out.println("Not a match!!");
        }

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Assert Text On Web Page Test")
    public void assertTextOnWebPageTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("http://www.programsbuzz.com");
        Locator body = page.locator("body");
        String bodyText = body.textContent();
        Assert.assertFalse(bodyText.contains("Spam Message"), "Spam Text Not Found!!");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Get Current URL Test")
    public void getCurrentURL() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("http://www.programsbuzz.com/user/login");
        page.locator("#edit-name").type("Naruto");
        page.locator("#edit-pass").type("uzumaki");

        String currentUrl = page.url();
        System.out.println(currentUrl);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Click Browser Back and Forward Button Test Case")
    public void clickBrowserBackNForwardButtonTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com");
        page.locator("#edit-submit--3").click();
        page.locator("//input[@id='edit-keys']").type("Playwright");
        page.locator("//input[@id='edit-submit']").click();
        page.goBack();
        page.goForward();
        page.close();
        browser.close();
        playwright.close();
        // To do
    }

    @Test
    @DisplayName("Navigate to URL")
    public void navigateToURLTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/upload1/");
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("How to Refresh Page")
    public void howtoRefreshPageTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/popup/");
        page.reload();
        page.locator("//a[normalize-space()='JQuery Popup Model']").click();
        String textContent = page.locator("//p[normalize-space()='This is Sample Popup.']").textContent();
        System.out.println(textContent);
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Maximize Browser")
    public void maximizeBrowserTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

//        browser.newContext(new Browser.NewContextOptions().setViewportSize(1800, 880));
        browser.newContext(new Browser.NewContextOptions().setViewportSize(800, 600));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

//        int width2 = (int) screenSize.getWidth();
//        int height2 = (int) screenSize.getHeight();
//        BrowserContext newContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width2, height2));
//        Page page = newContext.newPage();
        Page page = browser.newPage();
        page.navigate("https://www.google.co.id");
        page.navigate("https://www.pkp.co.id");
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Get First and Last Element in Playwright Java")
    public void getFirstAndLastElementTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle = page.locator("//h3[@class='search-result__title']");
//        Find the First Element using the first method
        listEle.first().click();

//        Find the First Element using Nth Method
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle2 = page.locator("//h3[@class='search-result__title']");
        listEle2.nth(0).click();

//        Find the Last Element using the last method
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle3 = page.locator("//h3[@class='search-result__title']");
        listEle3.last().click();

//        Find the Last Element using Nth Method
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle4 = page.locator("//h3[@class='search-result__title']");
        listEle4.nth(-1).click();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Get List of Elements in Playwright Java")
    public void getListOfElements() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");

        Locator listEle = page.locator("//h3[@class='search-result__title']");

        int count = listEle.count();
        Assert.assertEquals(count, 10);

//        Nth content filter
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        String textContent = listEle.nth(1).textContent();
        System.out.println(textContent);

//        Display a list of texts
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        List<String> allTextContents = listEle.allTextContents();
        System.out.println(allTextContents);
        allTextContents.forEach(System.out::println);

        for (int i = 0; i < allTextContents.size(); i++) {
            System.out.println(i + 1 + " -->" + allTextContents.get(i));
        }

        page.close();
        browser.close();
        playwright.close();

    }

    @Test
    @DisplayName("Using XPath")
    public void usingPathTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/user/login");
        page.locator("//input[@id= 'edit-name']").type("Naruto");
//        page.locator("xpath=//input[@id= 'edit-name']").type("Naruto");
        page.locator("//input[@id= 'edit-pass']").type("Sasuke");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Using ComboBox")
    public void usingComboboxTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/dropdown1/");
        page.selectOption(".custom-select", "item2");

        // Dynamic DropDown
        page.navigate("http://autopract.com/selenium/dropdown4/");
        page.locator("//span[@class='caret']").click();
        Locator countries = page.locator("//div[@role='combobox']");
        List<String> allInnerTexts = countries.allInnerTexts();

        allInnerTexts.forEach(System.out::println);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Auto Complete Test")
    public void autoCompleteTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        String expectedText = "Indonesia";

        page.navigate("https://demo.automationtesting.in/AutoComplete.html");
        Locator autoC = page
                .locator("//div[@class='ui-autocomplete-multiselect ui-state-default ui-widget ui-state-active']");
        int autoCcount = autoC.count();
        System.out.println("autoCcount: " + autoCcount);

        page.pause();

        for (int i = 0; i < autoCcount; i++) {
            String autoCText = autoC.nth(i).textContent();
            System.out.println("Yg dicari: " + autoCText);
            if (autoCText == expectedText) {
                autoC.nth(i).click();
                break;
            }
        }

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Check and Uncheck Checkbox in Playwright Java")
    public void checkNUncheckCheckboxTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        Using Click
        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='two']").click();

//        Using Check
//        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='four']").check();

//        Using Uncheck
//        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='four']").uncheck();

//        page.navigate("http://autopract.com/selenium/form5/");
        page.locator("input[value='CA']").click();
        page.locator("input[value='mac']").check();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Frame in Playwright Java")
    @Disabled()
    @Ignore("Ignore Handle Frame in Playwright Java")
    public void handleFrameTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
//        page.navigate("http://www.maths.surrey.ac.uk/explore/nigelspages/frame2.htm");
        page.navigate("https://testkru.com/Interactions/Frames");

//        FrameLocator middleFrame = page.frameLocator("//frame[@src='message.htm']");
        FrameLocator middleFrame = page.frameLocator("#frame1");

//        middleFrame.locator("//input[@name='name']").type("Naruto Uzumaki");
//        middleFrame.locator("//textarea[@name='suggestions']").type("I Am Inside The Frame");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Nested Frames in Playwright Java")
    public void handleNestedFramesTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/nested_frames");
        FrameLocator parentFrame = page.frameLocator("//frame[@name='frame-top']");
        FrameLocator middleFrame = parentFrame.frameLocator("//frame[@name='frame-middle']");
        String textContent = middleFrame.locator("body").textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Multiple Tabs in Playwright Java")
    @Disabled
    @Ignore("Ignore Handle Multiple Tabs in Playwright Java")
    public void HandleMultipleTabsTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://www.programsbuzz.com/");

        page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(p -> p.context().pages().size() == 2), () -> {
            page.locator("a[href='https://www.ivagus.com']").click();
        });

        List<Page> pages = page.context().pages();

        for (Page tabs : pages) {
            tabs.waitForLoadState();
            System.out.println(tabs.url());
        }

        Page pbPage = pages.get(0);
        Page ivagusPage = pages.get(1);

        System.out.println(pbPage.url());
        System.out.println(ivagusPage.url());

        page.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Alert Button in Playwright Java")
    public void handleAlertTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
        Page page = newContext.newPage();

        page.navigate("http://autopract.com/selenium/alert5/");
        page.onDialog(Dialog::accept);
        page.locator("#alert-button").click();

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Alert Confirm in Playwright Java")
    public void handleAlertConfirmTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
        Page page = newContext.newPage();

        page.navigate("http://autopract.com/selenium/alert5/");
        page.onDialog(Dialog::dismiss);
        page.locator("#confirm-button").click();

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Alert Prompt in Playwright Java")
    public void handleAlertPromptTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
        Page page = newContext.newPage();

        page.navigate("http://autopract.com/selenium/alert5/");
        page.onDialog(dialog -> {
            dialog.accept("20");
        });
        page.locator("#prompt-button").click();

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Popup in Playwright Java")
    public void handlePopup() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://autopract.com/selenium/popup/");

        Page popUp = page.waitForPopup(() -> {
            page.locator("//a[normalize-space()='Open Link in Popup']").click();
        });
        popUp.waitForLoadState();
        System.out.println(popUp.title());

        page.navigate("http://autopract.com/selenium/popup/");
        page.locator("//a[normalize-space()='JQuery Popup Model']").click();
        String textContent = page.locator("//p[normalize-space()='This is Sample Popup.']").textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Take Screenshot in Playwright Java")
    public void takeScreenshotTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/upload1/");
//        page.setInputFiles("//input[@type='file']",
//                Paths.get("C:\\Users\\naruto\\git\\Assignments\\Selenium\\target\\CRED.xlsx"));
//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("C:\\Users\\naruto\\git\\Assignments\\Selenium\\target\\uploadPage.png")));

        //get final screenshot
//        Path screenshotPath = Paths.get("TEST1_" + System.currentTimeMillis() + ".jpg");
        LocalDateTime dateTime = LocalDateTime.now();
//        System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
        String tanggal = dateTime.format(DateTimeFormatter.ofPattern("ddMMuuuu_HHmmss"));
        System.out.println(tanggal);
        Path screenshotPath = Paths.get("TEST1_" + tanggal + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));

        Path screenshotPath2 = Paths.get("TEST2_" + tanggal + ".jpg");
//        page.locator(".class").screenshot(new Locator.ScreenshotOptions().setPath(screenshotPath2));
//        page.locator(".class").screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("/Users/hendisantika/IdeaProjects/fincore/demo-playwright/uploadPage.png")));

//        Buffer Capture
        byte[] buffer = page.screenshot();
        System.out.println(Base64.getEncoder().encode(buffer));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Record Video in Playwright Java")
    public void recordVideoTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(
                        Paths.get("Videos/")).setRecordVideoSize(1280, 720));
        Page page = newContext.newPage();

        page.navigate("https://www.programsbuzz.com/user/login");

        page.locator("#edit-name").type("Naruto");
        page.locator("#edit-pass").type("Madara");

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Download File in Playwright Java")
    public void downloadFileTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://demo.automationtesting.in/FileDownload.html");
//        Download waitForDownload = page.waitForDownload(() -> page.locator("//a[@type='button']").click());
        Download waitForDownload = page.waitForDownload(page.locator("a.btn.btn-primary")::click);
//        Download waitForDownload = page.waitForDownload(page.locator("//html/body/section/div[1]/div/div/div[1]/a")::click);
        // Wait for the download to start
        // Perform the action that initiates download
// Wait for the download process to complete and save the downloaded file somewhere
        waitForDownload.saveAs(Paths.get("Downloads/", waitForDownload.suggestedFilename()));

        System.out.println(waitForDownload.url());
        System.out.println(waitForDownload.page().title());
        System.out.println(waitForDownload.path().toString());
        page.close();
        browser.close();
        playwright.close();

        /*
        waitForDownload.saveAs() - Use this to save the files to our required path.
        waitForDownload.cancel() - This will cancel the download when clicked.
        waitForDownload.failure() - Returns download error.
        waitForDownload.delete() - This will delete the downloaded file.*/
    }

    @Test
    @DisplayName("Download File in Playwright Java 2")
    public void downloadFileTest2() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setDownloadsPath(Paths.get("Downloads/")).setHeadless(false).setSlowMo(200));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));
        Page page = context.newPage();
        //https://playwright.dev/java/docs/api/class-keyboard
        page.navigate("https://the-internet.herokuapp.com/download");
        Download download = page.waitForDownload(() -> {
            // Perform the action that initiates download
            page.click("[href=\"download/dummy.pdf\"]");
        });
// Wait for the download process to complete
        Path path = download.path();
        System.out.println(path.toString());
        page.pause();
        page.close();
        browser.close();
        playwright.close();

    }

    @Test
    @DisplayName("Upload File in Playwright Java")
    public void uploadFileTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/upload1/");
        page.setInputFiles("//input[@type='file']",
                Paths.get("/Users/hendisantika/IdeaProjects/fincore/demo-playwright/Uploads/ayana.png"));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Upload File in Playwright Java 2")
    public void uploadFileTest2() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));
        Page page = context.newPage();
        //https://playwright.dev/java/docs/api/class-keyboard
        page.navigate("https://the-internet.herokuapp.com/upload");
        FileChooser fileChooser = page.waitForFileChooser(() -> page.click("#file-upload"));
        fileChooser.setFiles(Paths.get("/Users/hendisantika/IdeaProjects/fincore/demo-playwright/Uploads/ayana.png"));
        page.click("input:has-text(\"Upload\")");
        page.waitForLoadState();
        System.out.println(page.locator("#uploaded-files").textContent());
        page.pause();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Mouse Hover Element in Playwright Java")
    public void mouseHoverElementTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.w3schools.com/howto/howto_css_dropdown.asp");
        Locator hover = page.locator(".dropbtn");
        hover.hover();
        page.locator("//*[@id=\"main\"]/div[3]/div/a[1]").click();
        System.out.println(page.title());
        browser.close();
        playwright.close();
        // To do
    }

    @Test
    @DisplayName("Double Click in Playwright Java")
    public void doubleClickTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://testkru.com/Elements/Buttons");
        Locator textClick = page.locator("#doubleClick");
        textClick.dblclick();
        String textContent = page.locator("#doubleClick").textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Reusing Signed in State in Playwright Java")
    public void reusingSignedInStateTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();

        Page page = browser.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");

        page.locator("//input[@id='username']").type("student");
        page.locator("//input[@id='password']").type("Password123");

        page.locator("//button[@id='submit']").click();
        String textContent = page
                .locator("//strong[contains(text(),'Congratulations student. You successfully logged i')]")
                .textContent();
        System.out.println(textContent);

        browser.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Get API Request using Playwright Java")
    public void getAPIRequestTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        Response response = page.navigate("https://reqres.in/api/users?page=2");
        int status = response.status();
        System.out.println(status);
        assertEquals(status, 200);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Post API Request using Playwright Java")
    public void postAPIRequestTest() {
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        HashMap<String, String> data = new HashMap<>();

        data.put("name", "Naruto");
        data.put("job", "Ninja");

        String response = request.post("https://reqres.in/api/users", RequestOptions.create().setData(data)).text();

        System.out.println(response);

        JsonObject j = new Gson().fromJson(response, JsonObject.class);
        System.out.println(j.get("name"));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Put API Request using Playwright Java")
    public void putAPIRequestTest() {
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        HashMap<String, String> data = new HashMap<>();

        data.put("name", "Sasuke");
        data.put("job", "Uchiha");

        String response = request.put("https://reqres.in/api/users/2", RequestOptions.create().setData(data)).text();
        System.out.println(response);

        JsonObject j = new Gson().fromJson(response, JsonObject.class);
        System.out.println(j.get("name"));
        System.out.println(j.get("job"));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Put API Request using Playwright Java")
    public void patchAPIRequestTest() {
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        HashMap<String, String> data = new HashMap<>();

        data.put("name", "Sasuke");
        data.put("job", "Uchiha");

        String response = request.patch("https://reqres.in/api/users/2", RequestOptions.create().setData(data)).text();
        System.out.println(response);

        JsonObject j = new Gson().fromJson(response, JsonObject.class);
        System.out.println(j.get("name"));
        System.out.println(j.get("job"));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Delete API Request using Playwright Java")
    public void deleteAPIRequestTest() {
        Playwright playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        HashMap<String, String> data = new HashMap<>();

        data.put("name", "Sasuke");
        data.put("job", "Uchiha");

        String response = request.delete("https://reqres.in/api/users/2", RequestOptions.create().setData(data)).text();
        System.out.println(response);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Check Frame Test")
    public void checkFrameTest() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //  This line creates a new page object within the browser instance. The page object represents a single web page that can be navigated, interacted with, and tested.(It's kind of tab but exactly a tab.)
        Page page = browser.newPage();
        //  This code instructs the page object to navigate to the specified URL
        page.navigate("https://the-internet.herokuapp.com/");
        System.out.println(page.title());
        assertThat(page).hasTitle("The Internet");
        // Wait for the element to be available Wait are optional.
        page.waitForSelector("//a[contains(@href, '/frames')]");
        // Click the element
        page.click("//a[contains(@href, '/frames')]");
        // How to handle a single frame and interact with an element within it.
        // Go to Iframe example
        page.click("a[href='/iframe']");
        // get all the frames in the page to a list.
        List<Frame> frames = page.frames();
        System.out.println("No.of frames available: " + frames.size());

        // In Playwright we don't have to switch to the frame. we just have to locate the frame.
        FrameLocator myFrame = page.frameLocator("//iframe[@id='mce_0_ifr']");
        // Then related to the frame write the locator of element that I want to interact.
        Locator textFieldBody = myFrame.locator("//body[@id='tinymce']");
        textFieldBody.click();
        textFieldBody.clear();
        textFieldBody.type("Typed inside of the text field of Imframe");

        // Or you can  define the Locator inside of the frame using frame in one go with just one Locator variable.
        Locator textFieldBodyDirect = page.frameLocator("//iframe[@id='mce_0_ifr']").locator("//body[@id='tinymce']");
        textFieldBodyDirect.click();
        textFieldBodyDirect.clear();
        textFieldBodyDirect.type("Handle the element in the frame in one go");

        // How to handle Nested frames, frame within a frame.
        page.navigate("https://letcode.in/frame");

        // There first name and last name are inside of just 1 frame.
        // Let's get the First frame to a locator,
        FrameLocator firstFrame = page.frameLocator("//iframe[@id='firstFr']");
        // Below first name element is inside the frame
        Locator firstName = firstFrame.locator("//input[@name='fname']");
        firstName.type("Hello First name");
        Locator lastName = firstFrame.locator("//input[@name='lname']");
        lastName.type("Hello Last name");

        // Email field is inside of the above firstFrame
        FrameLocator nestedSecondFrame = firstFrame.frameLocator("//iframe[@class='has-background-white']");
        // Now let's enter the values at Enter email is in Email fild.
        Locator emailInsideOfTwoFrames = nestedSecondFrame.locator("//input[@name='email']");
        emailInsideOfTwoFrames.type("johndoe@yahoo.co.uk");

        // optional codes to get an idea about frame content of the page.
        List<Frame> multipleFrames = page.frames();
        System.out.println("No.of frames available: " + multipleFrames.size());
        // Print all the URLS of frame
        multipleFrames.forEach(frame -> System.out.println(frame.url()));
       /* every iframe element has a unique URL. The URL of an iframe element is the URL of the page that is embedded in the iframe.
        Example <iframe src="https://www.google.com"></iframe>  in Facebook page we can add a link to google.
         url means src value of iframe
        The src value is the attribute that specifies the URL
        <iframe _ngcontent-serverapp-c66="" src="frameUI" id="firstFr" name="firstFr"></iframe>
        */

        Thread.sleep(24000);
        //  Frame frame is old way don't use it   Frame frame = page.frame("//iframe[@id='mce_0_ifr']");
        page.close();
        // Closes the browser window(s) associated with the browser instance.
        browser.close();
        // playwright.close() command is used to close the page that is currently open in the Chromium browse
        playwright.close();
    }

    @Test
    @DisplayName("Check Frame Test 2")
    public void checkFrameTest2() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // creating a BrowserContext
        BrowserContext browserContext = browser.newContext();

        Page page = browserContext.newPage();

        // Navigating to the URL
        page.navigate("https://testkru.com/Interactions/Frames");

        Frame frame = page.frame("singleFrame");

        Locator locator = frame.locator("h1");
        System.out.println("Text written inside the frame: " + locator.innerText());

        // Navigating to the URL
        page.navigate("https://testkru.com/Interactions/Frames");
        // switching to the child frame
        Frame childFrame = page.frame("singleFrame");

        FrameLocator frameLocator = page.frameLocator("#frame1").first();

        Locator locator2 = frameLocator.locator("h1");
        System.out.println("Text written inside the frame: " + locator2.innerText());

        // switching back to parent frame
        Frame parentFrame = childFrame.parentFrame();

        // closing the instances
        browser.close();
        playwright.close();

    }

    @Test
    @DisplayName("Tabs & Windows Test")
    public void tabsAndWindowsTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // creating a BrowserContext
        BrowserContext browserContext = browser.newContext();

        Page page = browserContext.newPage();

        // Navigating to the URL
        page.navigate("https://testkru.com/Elements/TextFields");
        System.out.println("Title of current window: " + page.title());

        // Creating a new BrowserContext
        BrowserContext browserContext2 = browser.newContext();

        // Navigating to the new URL
        Page page2 = browserContext2.newPage();
        page2.navigate("https://testkru.com/Elements/Buttons");

        System.out.println("Title of new window: " + page2.title());

        // closing the instances
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Open a new tab using Playwright")
    public void openANewTabTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // creating a BrowserContext
        BrowserContext browserContext = browser.newContext();

        // creating a page
        Page page1 = browserContext.newPage();

        // Navigating to the URL
        page1.navigate("https://testkru.com/Elements/TextFields");
        System.out.println("Current tab title: " + page1.title());

        // Opening second tab
        Page page2 = browserContext.newPage();
        page2.navigate("https://testkru.com/Elements/Buttons");
        System.out.println("New tab title: " + page2.title());

        // closing the instances
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Switching between tabs")
    public void switchingBetweenTabsTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // creating a BrowserContext
        BrowserContext browserContext = browser.newContext();

        // creating a page
        Page page1 = browserContext.newPage();

        // Navigating to the URL
        page1.navigate("https://testkru.com/Elements/TextFields");

        // Opening second tab
        Page page2 = browserContext.newPage();
        page2.navigate("https://testkru.com/Elements/Buttons");

        // get all pages
        List<Page> pages = browserContext.pages();

        // iterate over pages
        for (Page singlePage : pages) {
            System.out.println("Title of the page: " + singlePage.title());
        }

        // closing the instances
        browser.close();
        playwright.close();

    }

}
