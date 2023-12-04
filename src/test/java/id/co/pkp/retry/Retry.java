package id.co.pkp.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-playwright
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/4/23
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class Retry implements IRetryAnalyzer {

    int retryCount = 0;
    int maxRetryCount = 4;

    @Override
    public boolean retry(ITestResult result) {

        if (!result.isSuccess()) {

            if (retryCount < maxRetryCount) {
                System.out.println(
                        "Retrying Test : Re-running " + result.getName() + " for " + (retryCount + 1) + " time(s).");

                retryCount++; // Increase the maxRetryCount by 1

                result.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);

        }

        return false;
    }
}
