package config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FlashWebDriver extends ChromeConfig {

private final String flashObjectId;

public FlashWebDriver(final WebDriver driver, final String flashObjectId) {
this.driver = driver;
this.flashObjectId = flashObjectId;
}

public String call(final String functionName, final String... args) {
final Object result =
((JavascriptExecutor)driver).executeScript(
makeJsFunction(functionName, args),
new Object[0]);

return result != null ? result.toString() : null;
}

private String makeJsFunction(final String functionName, final String... args) {
final StringBuffer functionArgs = new StringBuffer();


return String.format(
"return document.%1$s.%2$s(%3$s);",
flashObjectId,
functionName,
functionArgs);
}
}