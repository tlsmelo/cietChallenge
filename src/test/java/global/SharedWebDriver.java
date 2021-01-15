package global;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;
import java.util.List;

public class SharedWebDriver {
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private static List<WebDriver> storedDrivers = new ArrayList<>();

    private static final Thread CLOSE_THREAD = new Thread(() -> storedDrivers.forEach(WebDriver::quit));

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public static WebDriver getDriver() {
        WebDriver threadLocalDriver = drivers.get();
        if(threadLocalDriver == null) {
            WebDriver webDriver = WebDriverFactory.create(System.getProperty("webdriver"));
            drivers.set(webDriver);
            storedDrivers.add(drivers.get());
        }
        return drivers.get();
    }

    @Before
    public void before() {
        getDriver().manage().deleteAllCookies();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
