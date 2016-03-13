package cn.evilcoder.jsocket.client;

import cn.evilcoder.jsocket.comment.JConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.Socket;

/**
 * User: Huangshanqi
 * Date: 2016/3/13
 * Time: 11:52
 */
public class JClient {
    public static final Log logger = LogFactory.getLog(JClient.class);
    public static void main(String[] args) throws IOException,InterruptedException{
        for (int i=0;i<10;i++){
            Socket socket = new Socket("127.0.0.1", JConstant.DEFAULT_SERVER_PORT);
            try {
                sendMsg(socket,i);
            }finally {
                socket.close();
            }
        }


    }

    public static void sendMsg(Socket socket,int i) throws IOException, InterruptedException {
        logger.info("start client with =="+socket);
        // ÉèÖÃIO¾ä±ú
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        out.println("Hello Server,I am " + i);
        String str = in.readLine();
        out.println("byebye");
        Thread.sleep(1000*10L);
        logger.info("stop client with =="+socket);
    }

}
