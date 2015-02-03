package client;


import java.awt.EventQueue;
import java.util.Timer;


//i run from eclipse, eclipse will pass in default port and host of
//<property name="host" value="localhost"/>
//<property name="port" value="8081"/>
//<property name="useMockServer" value="false"/>
public class ClientLauncher {

  //arg0 = host, arg1 = port
    
   public static void main(String[] args) {
        final String port = args[1];
        final String host = args[0];
        final boolean useMockServer = Boolean.parseBoolean(args[2]);
        EventQueue.invokeLater(new Runnable(){      
            public void run() {
                
            }
        });
    }//end main
    

}
