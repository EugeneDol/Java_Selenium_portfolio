package utils.reports;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import utils.dataProcessing.FilesProcessing;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class AllureHandler {
    public static void attachScreenshot() {
        try (InputStream is = Files.newInputStream(FilesProcessing.takeScreenshot())) {
            Allure.addAttachment("Screenshot", is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("{message}")
    public static void logStep(String message) {
    }
}
