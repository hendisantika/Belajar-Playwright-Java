package id.co.pkp.retry;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-playwright
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/4/23
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public class PopUp {
    private Browser browser;
    private Page page;

    @BeforeTest
    private void setUp() throws InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            page = browser.newPage();
            page.navigate("https://www.programsbuzz.com");
        }
    }

    @AfterTest
    private void tearDown() {
        browser.close();
    }

    @Test(retryAnalyzer = Retry.class)
    public void popUp() throws Throwable {
        page.locator("//i[@class='fas fa-search']").click();
        page.locator("//input[@id='edit-keys']").type("Playwright");
        page.locator("//input[@id='edit-submit']").click();
        page.goBack();
        page.goForward();
    }

}
