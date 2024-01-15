package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testng.annotations.DataProvider;
import utils.config.Config;

import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;


public class DriverProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    static String chromePath = Paths.get("").toAbsolutePath() + "/drivers";
    private static String downloadFilepath = Paths.get("").toAbsolutePath() + "\\target\\";
    public static ThreadLocal<WebDriver> instance = new ThreadLocal<WebDriver>();
    private static WebDriver outputDriver;

    public static BrowserWebDriverContainer<?> chromeDocker;

    public static WebDriver getDriver(String driverSource) throws MalformedURLException {
        switch (driverSource){
            case "local":
                outputDriver = getDriver_Local();
                break;
            case "Remote":
                outputDriver = getDriver_Remote();
                break;

            default:
                outputDriver = getDriver_Remote();

        }

        return outputDriver;
    }

    public ThreadLocal<WebDriver> getWebDriverInstance(){
        return this.instance;
    }


    public static ChromeDriver getChrome_Remote(boolean hdls){
        String chromeDriverVersionFromConfig = Config.getConfigProperty("ChromeDriverVersion");

        synchronized (DataProvider.class) {
            if (chromeDriverVersionFromConfig.isEmpty()) {
                WebDriverManager.chromedriver().cachePath(chromePath).avoidOutputTree().setup();
            } else {
                WebDriverManager
                        .chromedriver()
                        .driverVersion(Config.getConfigProperty("ChromeDriverVersion"))
                        .cachePath(chromePath)
                        .avoidOutputTree()
                        .forceDownload()
                        .setup();
            }
        }

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable("browser", Level.OFF);
        Map<String, Object> preferences = new Hashtable();
        preferences.put("download.default_directory", downloadFilepath);
        preferences.put("download.prompt_for_download", false);
        ChromeOptions caps = new ChromeOptions();
        caps.setCapability("loggingPrefs", logPrefs);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", preferences);
        chromeOptions.addArguments(new String[]{"--no-sandbox"});
        chromeOptions.addArguments(new String[]{"--no-cache"});
        chromeOptions.addArguments(new String[]{"--lang=en"});
        chromeOptions.addArguments(new String[]{"--disable-gpu"});
        chromeOptions.addArguments(new String[]{"--remote-allow-origins=*"});
        if (Config.getConfigProperty("WindowSize") == null) {
            chromeOptions.addArguments(new String[]{"--start-maximized"});
        } else {
            chromeOptions.addArguments(new String[]{"--window-size=" + Config.getConfigProperty("WindowSize")});
        }

        if (hdls) {
            chromeOptions.addArguments(new String[]{"--headless"});
            chromeOptions.addArguments(new String[]{"--window-size=1920,1080"});
        }

        chromeOptions.merge(caps);
        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver getChrome_Local(boolean hdls, String driverExe){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(new String[]{"--remote-allow-origins=*"});
        if (hdls) {
            chromeOptions.addArguments(new String[]{"--headless"});
            chromeOptions.addArguments(new String[]{"--window-size=1920,1080"});
        }

        System.setProperty("webdriver.chrome.driver", Paths.get("").toAbsolutePath() + "/drivers/"+driverExe);

        // Create a new instance of the ChromeDriver
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        return webDriver;
    }
    public static WebDriver getDriver_Remote() throws MalformedURLException {

            switch (getBrowserConfiguration()) {
                case "chrome_headless":
                    instance.set(getChrome_Remote(true));
                    LOGGER.info("Init Chrome driver in headless mode");
                    break;

                default:
                    LOGGER.info("Init Chrome driver");
                    instance.set(getChrome_Remote(false));

        }

        maximizeWindow(instance);
        return (WebDriver)instance.get();
    }

    public static WebDriver getDriver_Local() throws MalformedURLException {
        LOGGER.info("Local chrome driver chosen");
        switch (getBrowserConfiguration()) {
            case "chrome_headless":
                instance.set(getChrome_Local(true, "chromedriver.exe"));
                LOGGER.info("Chrome driver set in headless mode");
                break;

            default:
                instance.set(getChrome_Local(false, "chromedriver.exe"));
                LOGGER.info("Chrome driver set in visible mode");

        }

        //maximizeWindow(instance);
        return getChrome_Local(false, "chromedriver.exe");
    }

    static public RemoteWebDriver getWebDriverDocker_Chrome(){
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.OFF);

        Map<String, Object> preferences = new Hashtable<>();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeDocker = new BrowserWebDriverContainer<>()
                .withExposedPorts(8081, 8082)
                .withCapabilities(chromeOptions);

        chromeDocker.start();

        return chromeDocker.getWebDriver();

    }

    private static String getBrowserConfiguration() {
        return Config.getConfigProperty("browser");
    }

    private static void maximizeWindow(ThreadLocal<WebDriver> instance) {
        ((WebDriver)instance.get()).manage().window().maximize();
    }

    public static void closeDriver() {
        instance.get().quit();
        instance.set(null);
    }

}
