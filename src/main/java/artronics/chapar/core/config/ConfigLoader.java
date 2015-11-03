package artronics.chapar.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

public class ConfigLoader
{
    private Properties properties;

    public ConfigLoader(String fileName)
    {
        loadConfigFile(fileName);
    }

    public Properties getProperties()
    {
        return properties;
    }

    private void loadConfigFile(String fileName)
    {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            properties = prop;

        }catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
