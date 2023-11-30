package id.co.pkp;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-playwright
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/30/23
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class App1 {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("http://www.programsbuzz.com/user/login");
            page.locator("#edit-name").type("Naruto");
            page.locator("#edit-pass").type("uzumaki");
            browser.close();
            playwright.close();
        }
    }
}
