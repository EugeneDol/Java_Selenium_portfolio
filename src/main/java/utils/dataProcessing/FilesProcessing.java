package utils.dataProcessing;

import Pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesProcessing {
    static String report_folder = Paths.get("").toAbsolutePath() + "/src/main/resources/testReports/";

    public static Path takeScreenshot(WebDriver driver, String name) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filename = name + "screen.png";
        try {
            FileUtils.copyFile(file, new File(report_folder + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Paths.get(file.getAbsolutePath());
    }

    public static Path takeScreenshot() {
        File file = ((TakesScreenshot) BasePage.getWebDriver()).getScreenshotAs(OutputType.FILE);

        return Paths.get(file.getAbsolutePath());
    }
}
