import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TryMultiThreaded implements Runnable {

    Socket socket;


   public TryMultiThreaded(Socket socket) {
       this.socket = socket;
   }


    @Override
    public void run() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while(true){
                Object message = ois.readObject();
                if(Model.class.isAssignableFrom(message.getClass())){
                    try{
                        Model m = (Model) message;
                        System.out.println(m.getNickname() +": "+ m.getAge());
                        m.setAge(m.getAge()+1);
                        if(m.getAge()==20)
                            break;
                        oos.writeObject(m);
                        oos.flush();

                    }catch(Exception ignored){}
                }
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println(exception);
            return;
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

