package global;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;

public final class WebDriverFactory {
    public static WebDriver create() {
        return create(null);
    }

    public static WebDriver create(String driverName) {
        String webDriverProperty = getProperty("webdriver");

        if (StringUtils.isEmpty(webDriverProperty)) {
            throw new IllegalStateException("The webdriver system property must be set");
        }

        try {
            return Drivers.valueOf(webDriverProperty.toUpperCase()).newDriver(driverName);
        } catch (IllegalArgumentException e) {
            String msg = String.format("The webdriver system property '%s' did not match any " + "existing browser or the browser was not supported on your operating system. " + "Valid values are %s",
                    webDriverProperty, Arrays.stream(Drivers.values())
                                                     .map(Enum::name)
                                            .map(String::toLowerCase)
                                        .collect(Collectors.toList()));

            throw new IllegalStateException(msg, e);
        }
    }

    private enum Drivers {
        CHROME {
            @Override
            public WebDriver newDriver(String driverName) {
                Map<String, Object> prefs = new HashMap<>();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.setExperimentalOption("useAutomationExtension", false);

                return new ChromeDriver(options);
            }
        }, FIREFOX {
            public WebDriver newDriver(String driverName) {
                FirefoxOptions options = new FirefoxOptions();
                return new FirefoxDriver(options);
            }
        }, EDGE {
            public WebDriver newDriver(String driverName) {
                EdgeOptions options = new EdgeOptions();
                return new EdgeDriver(options);
            }
        };

        public abstract WebDriver newDriver(String driverName);
    }
}
