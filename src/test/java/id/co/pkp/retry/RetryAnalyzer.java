package id.co.pkp.retry;

import org.testng.ITestResult;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-playwright
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/4/23
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public interface RetryAnalyzer {
    /**
     * Returns true if the test method has to be retried, false otherwise.
     *
     * @param result The result of the test method that just ran.
     * @return true if the test method has to be retried, false otherwise.
     */
    boolean retry(ITestResult result);
}
