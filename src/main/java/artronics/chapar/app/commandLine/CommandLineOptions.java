package artronics.chapar.app.commandLine;

import artronics.chapar.app.logger.Log;
import org.apache.commons.cli.*;


public class CommandLineOptions
{
    private static String[] args;
    private static CommandLine commandLine = null;
    private static Options options = new Options();

    public CommandLineOptions(String[] argsOptns)
    {
        args = argsOptns;
        setOptions();
        setCommandLine();

    }

    /*
        Here you can add all options required for app
        after adding options put your logic for managing
        that option inside CommandLineOptionsManager class
     */
    private static void setOptions()
    {
        options.addOption("h", "help", false, "Print this message");
        options.addOption("v", "version", false, "Print version of app");
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


    public CommandLine getCommandLine()
    {
        return commandLine;
    }

    public Options getOptions()
    {
        return options;
    }
}
