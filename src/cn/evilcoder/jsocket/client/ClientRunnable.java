package cn.evilcoder.jsocket.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.Socket;

/**
 * User: Huangshanqi
 * Date: 2016/3/13
 * Time: 14:18
 */
public class ClientRunnable implements Runnable{
    private final Log logger = LogFactory.getLog(ClientRunnable.class);
    final Socket socket;
    final int index;
    public ClientRunnable(Socket s,int i){
        this.socket = s;
        this.index = i;
    }
    @Override
    public void run() {
        try {
            sendMsg(socket,index);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Socket socket,int i) throws IOException, InterruptedException {
        logger.info("start client with =="+socket);
        // …Ë÷√IOæ‰±˙
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        out.println("Hello Server,I am " + i);
        String str = in.readLine();
//        logger.info(index + " client get from server = "+str);
        out.println("byebye");
        Thread.sleep(1000*100L);
    }
}
