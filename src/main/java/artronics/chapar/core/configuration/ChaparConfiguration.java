package artronics.chapar.core.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChaparConfiguration
{
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    public ChaparConfiguration()
    {
        config.setThrowExceptionOnMissing(true);
    }

    public PropertiesConfiguration getConfig()
    {

        File configFile = new File("chapar.config");
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
        }

        return config;
    }

    private PropertiesConfiguration loadConfigStreams()
    {
        try {
            Path chaparConfigFile = Paths.get("/config/chapar.config");
            InputStream chaparConfig = loadConfigFile(chaparConfigFile);
            config.load(chaparConfig);
            chaparConfig.close();
            config.save("chapar.config");
        }catch (ConfigurationException e) {
            e.printStackTrace();
        }catch (IOException e) {
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
