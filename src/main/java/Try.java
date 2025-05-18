
import java.io.IOException;

import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Try {




    public static void main(String[] args) throws IOException {
        try{connection();}catch (IOException e){System.out.println(e.getMessage());
        }

    }


    public static void connection() throws IOException {
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

        ServerSocket sc;
        try{   sc = new ServerSocket(1234);} catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        while(true){
            System.out.println("Waiting for connection");
            try{
                Socket socket= sc.accept();
                System.out.println(socket.getInetAddress().getHostAddress());
                executor.submit(new TryMultiThreaded(socket));
            }catch (IOException e){
                break;
            }
        }
        executor.shutdown();
    }

}
