package it.unibo.sdwn.helper;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

import java.util.Scanner;

public class AskUserForInput
{

    public static int makeChoice(int maxValue, String header) throws InvalidValue
    {
        int choice = 0;
        String msg = "";
        Scanner keyboard = new Scanner(System.in);
        if (header != null) {
            msg += header + "\n";
        }
        msg += "Please enter a number between 1 and " + maxValue + ":\n" +
                "Enter 0 to exit.\n";
        System.out.println(msg);
        int input = keyboard.nextInt();
        while (input < 0 || input > maxValue) {
            System.out.println("Wrong number, try again.\n" + msg);
            input = keyboard.nextInt();
        }
        if (input == 0) {
            throw new InvalidValue();
        }

        return input;
    }
}
