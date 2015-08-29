package artronics.chapar.core;

import artronics.chapar.core.configuration.Config;

public class Chapar
{
    public static void start(){
        System.out.println("kir");
        int kir = Config.get().getInt("kir");
    }
}
