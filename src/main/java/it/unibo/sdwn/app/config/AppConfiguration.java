package it.unibo.sdwn.app.config;

import it.unibo.sdwn.app.logger.Log;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class AppConfiguration
{
    private static PropertiesConfiguration config;

    public AppConfiguration()
    {
        config = new PropertiesConfiguration();
        config.setThrowExceptionOnMissing(true);
    }

    public PropertiesConfiguration getConfig()
    {

        File configFile = new File("sdwn.config");
        if (configFile.exists() && !configFile.isDirectory())
            return loadConfigFile(configFile);
        else
            return loadConfigStreams();
    }

    private PropertiesConfiguration loadConfigFile(File configFile)
    {
        try {
            config.load(configFile);
        }catch (ConfigurationException e) {
            e.printStackTrace();
            Log.main().error("Can not open sdwn.config");
        }

        return config;
    }

    private PropertiesConfiguration loadConfigStreams()
    {
        try {
            InputStream mainConfig = loadConfigFile(PathConfig.MAIN_CONFIG_FILE);
            config.load(mainConfig);
            InputStream appConfig = loadConfigFile(PathConfig.APP_CONFIG_FILE);
            config.load(appConfig);
            try {
                mainConfig.close();
                appConfig.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }catch (ConfigurationException e) {
            e.printStackTrace();
        }
        try {
            config.save("sdwn.config");
        }catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return config;
    }

    private InputStream loadConfigFile(Path configFile)
    {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getResourceAsStream(configFile.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }

        return inputStream;
    }
}
