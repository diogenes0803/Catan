package client;


import java.awt.EventQueue;
import java.util.Scanner;
import java.util.Timer;

import com.google.gson.Gson;

import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;
import shared.models.CatanModel;
import client.communication.ServerProxy;


//if run from eclipse, eclipse will pass in default port and host of
//<property name="host" value="localhost"/>
//<property name="port" value="8081"/>
//<property name="useMockServer" value="false"/>
public class ClientLauncher {

  //arg0 = host, arg1 = port
    
   public static void main(String[] args) {
        //final String host = args[0];
        //final String port = args[1];
        //final boolean useMockServer = Boolean.parseBoolean(args[2]);
        EventQueue.invokeLater(new Runnable(){      
            public void run() {
                ServerProxy server= new ServerProxy("localhost", "8081");
                UserLoginResults result = server.userLogin(new UserLoginParams("Sam","sam"));
                System.out.println("UserLogin Result: "+ result.isSuccess());
                if(!result.isSuccess())
                  return;
                JoinGameResults result2 = server.joinGame(new JoinGameParams(0, "orange"));
                System.out.println("JoinGame Result: "+ result2.isSuccess());
                if(!result2.isSuccess())
                  return;
                CatanModel model = server.getModel();
                System.out.println("Model obtained:");
                if(model != null){
                    String jsonModel = new Gson().toJson(model);
                    @SuppressWarnings("resource")
                    Scanner scan = new Scanner(jsonModel).useDelimiter(",");
                    while(scan.hasNext())
                        System.out.println(scan.next());
                }
            }
        });
    }//end main
    

}
