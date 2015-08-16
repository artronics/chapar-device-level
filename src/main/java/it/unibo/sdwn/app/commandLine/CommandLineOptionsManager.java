package it.unibo.sdwn.app.commandLine;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public final class CommandLineOptionsManager
{
    private static CommandLine commandLine;
    private static Options options;

    public CommandLineOptionsManager(String[] argsOptns)
    {
        CommandLineOptions commandLineOptions = new CommandLineOptions(argsOptns);
        commandLine = commandLineOptions.getCommandLine();
        options = commandLineOptions.getOptions();
    }

    private static boolean askedForHelp()
    {
        if (commandLine.hasOption("help"))
            return true;

        return false;
    }

    private static boolean askedForVersion()
    {
        if (commandLine.hasOption("version"))
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

    /*
        First add your option on CommandLineOptions class
        then here, create appropriate private methods for managing
        them. Final add your entry method on start method.
     */
    public void start()
    {
        requiresProgExit();
    }

    private void requiresProgExit()
    {
        if (askedForHelp()) {
            printHelp();
            System.exit(0);
        }
        if (askedForVersion()) {
            System.out.println("0.0");
            System.exit(0);
        }
    }

}
