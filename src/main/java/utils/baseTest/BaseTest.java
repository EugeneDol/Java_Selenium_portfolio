package utils.baseTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import utils.config.Config;

import static utils.auth.Auth.getCredByName;


public class BaseTest {
    static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    public static SoftAssert soft = new SoftAssert();
    public static String defaultPass = getCredByName("defaultPass");

}
