package utils.dataProcessing;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class DataCreation {

    public static String getStackTrace(Throwable problem) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        problem.printStackTrace(printWriter);
        return result.toString();
    }
}
