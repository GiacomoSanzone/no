
import java.io.IOException;

import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class Try {





    public static void main(String[] args) throws IOException {
        try{connection();}catch (IOException e){System.out.println(e.getMessage());
        }

    }


    public static void connection() throws IOException {


        ServerSocket sc = new ServerSocket(1234);
        Scanner scanner;
        PrintWriter out;

        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        while(nets.hasMoreElements())
        {
            NetworkInterface networkInterface =  nets.nextElement();
            Enumeration<InetAddress> enumeration = networkInterface.getInetAddresses();
            while (enumeration.hasMoreElements())
            {
                InetAddress ip =  enumeration.nextElement();
                if(ip.isSiteLocalAddress())
                    System.out.println(ip.getHostAddress());
            }
        }



        Socket client= sc.accept();
        System.out.println(client.getInetAddress().getHostAddress());
        scanner = new Scanner(client.getInputStream());
        out = new PrintWriter(client.getOutputStream());



        while(!scanner.nextLine().equalsIgnoreCase("quit")){
            out.println("Hello");
            out.flush();
        }
        scanner.close();
        out.close();
        client.close();
        sc.close();
    }

}
