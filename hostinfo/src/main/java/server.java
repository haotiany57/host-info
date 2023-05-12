import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class server {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("开始监听");
        while (true) {
        pull();
        TimeUnit.SECONDS.sleep(10);
        }
    }
    static void pull() throws IOException {


        ServerSocket ss = new ServerSocket(4040);
        Socket socket = ss.accept();

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./outpull.txt"));

        InputStream is = socket.getInputStream();

        int data;
        while ((data = is.read()) != -1){
            bos.write(data);
        }


        System.out.println("传输完成");

        bos.close();
        socket.close();
        ss.close();

    }
}
