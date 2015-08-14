package it.unibo.sdwn.app.config;

public class Config
{
    private static boolean hasInstanciated = false;

    private Config()
    {
    }

    public static String get(ConfigType configType)
    {
        Configuration conf = null;
        if (!hasInstanciated) {
            conf = new Configuration();
            hasInstanciated = true;
        }
        return conf.get(configType);
    }

}
