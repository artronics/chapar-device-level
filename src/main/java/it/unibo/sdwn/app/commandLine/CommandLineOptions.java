package it.unibo.sdwn.app.commandLine;

import it.unibo.sdwn.app.logger.Log;
import org.apache.commons.cli.*;


public class CommandLineOptions
{
    private static CommandLineOptions instance = null;
    private static String[] args;
    private static CommandLine commandLine = null;
    private static Options options = new Options();

    protected CommandLineOptions()
    {
    }

    public static CommandLineOptions getInstance(String[] inputArgs)
    {
        if (instance == null) {
            instance = new CommandLineOptions();
            args = inputArgs;
            instance.setOptions();
            instance.setCommandLine();
            return instance;
        }
        Log.instantiate("Attempt to re-instantiating singleton:", instance);

        return instance;
    }

    private static void setCommandLine()
    {
        CommandLineParser parser = new DefaultParser();

        try {
            commandLine = parser.parse(options, args);

        }catch (ParseException e) {
            Log.main().error("Unable to parse command line.");
            e.printStackTrace();
        }
    }

    private static void setOptions()
    {
        options.addOption("h", "help", false, "Print this message");
        options.addOption("v", "version", false, "Print version of app");
    }

    public static boolean askedForHelp()
    {
        if (instance.getCommandLine().hasOption("help"))
            return true;

        return false;
    }

    public static boolean askedForVersion()
    {
        if (instance.getCommandLine().hasOption("version"))
            return true;

        return false;
    }

    public static void printHelp()
    {
        MessageFormatter.printHelp(options,
                                   80,
                                   "--HELP--",
                                   "\nAuthor: Seyed Jalal Hosseini (jalalhosseiny@gmail.com)",
                                   3,
                                   5,
                                   true,
                                   System.out);
    }

    public CommandLine getCommandLine()
    {
        return commandLine;
    }
}
