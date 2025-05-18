import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Clients {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",1234);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Model model = new Model("Giacomo",10);
        Scanner in = new Scanner(System.in);
        while(true){
          try{int i=  Integer.parseInt(in.nextLine());
          model.setAge(i);}catch (Exception e){continue;}
           oos.writeObject(model);
           oos.flush();
           Object model1;
          try{model1 = ois.readObject();
              if(model1 instanceof Model){
                  model = (Model)model1;
                  System.out.println(model.getNickname()+": "+model.getAge());
              }
          }catch(ClassNotFoundException e){
              System.out.println(e);
              socket.close();
          }
        }
    }
}
