package unibo.it;


import artronics.chapar.core.Chapar;
import unibo.it.controller.SdwnController;

/**
 * Hello world!
 *
 */
public class Sdwn
{
    public static void main( String[] args )
    {
        Chapar.setController(SdwnController.class);
        Chapar.start();
    }
}
