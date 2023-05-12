package org.example;

import java.util.concurrent.TimeUnit;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.*;
import java.net.Socket;

public class start {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("开始记录");
        while (true) {
            out();
            push();
            TimeUnit.SECONDS.sleep(10);
        }
    }
    static void push() throws IOException
    {
        Socket socket = new Socket("127.0.0.1",4040);

        FileInputStream fs = new FileInputStream("./out.txt");

        BufferedInputStream bis = new BufferedInputStream(fs);



        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);


        int data;
        while ((data = bis.read()) != -1){
            bos.write(data);
        }
        bos.flush();


        socket.shutdownOutput();

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str;
        while ((str = br.readLine()) != null){
            System.out.println(str);
        }



        socket.close();
        os.close();
        bis.close();


    }
    static void out() throws FileNotFoundException {
        var startout = new Main();
        PrintStream ps = new PrintStream("./out.txt");
        System.setOut(ps);
        startout.hostinfo();
        ps.close();
    }
}
