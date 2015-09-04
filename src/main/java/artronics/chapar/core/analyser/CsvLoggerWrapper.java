package artronics.chapar.core.analyser;


import artronics.chapar.core.configuration.Config;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CsvLoggerWrapper
{
    private static final String[] csvFiles = Config.get().getStringArray("csv");

    private static final String baseDir = "logs/";

    private static boolean loggingIsInitialized = false;

    public static void log(String appenderName, Analysable analysable)
    {
        if (!loggingIsInitialized)
            initLogging();
        if (Arrays.asList(csvFiles).contains(appenderName)) {
            Logger csv = Logger.getLogger(appenderName);
            csv.info(analysable.toCsv());
            return;
        }
    }

    // log4j logging is lazily constructed; it gets initialized
    // the first time the invoking app calls a log method
    private static void initLogging()
    {
        loggingIsInitialized = true;
        String date = new SimpleDateFormat("MM-dd").format(new Date());

        for (int i = 0; i < csvFiles.length; i++) {

            PatternLayout layout = new PatternLayout("%m%n");
            String fileName = baseDir + csvFiles[i] + "/" + date + "/" + csvFiles[i] + ".csv";
//            String fileName = baseDir + csvFiles[i] + "/" + csvFiles[i] + ".csv";
            String appenderName = csvFiles[i];

            //appender and logger has the same name
            Logger csv = Logger.getLogger(appenderName);

            RollingFileAppender csvFile = null;
            try {
                csvFile = new RollingFileAppender(layout, fileName, false);
                csvFile.setMaxBackupIndex(1000);
                csvFile.setMaximumFileSize(10240);//10KB
            }catch (IOException e) {
                e.printStackTrace();
            }
            csv.addAppender(csvFile);
        }
    }
}
