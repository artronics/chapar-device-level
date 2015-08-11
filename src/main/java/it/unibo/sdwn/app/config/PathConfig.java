package it.unibo.sdwn.app.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathConfig
{

    public final static Path BASE = Paths.get("/");
    public final static Path CONFIG = Paths.get(BASE.toString(), "config");
    public final static Path APP_CONFIG_FILE = Paths.get(BASE.toString(), CONFIG.toString(), "App.config");

}
