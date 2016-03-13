package cn.evilcoder.jsocket.client;

import cn.evilcoder.jsocket.comment.JConstant;

import java.io.IOException;
import java.net.Socket;

/**
 * User: Huangshanqi
 * Date: 2016/3/13
 * Time: 11:52
 */
public class JClient {
    public static void main(String[] args) throws IOException,InterruptedException{
        for (int i=0;i<100000;i++){
            Socket socket = new Socket("127.0.0.1", JConstant.DEFAULT_SERVER_PORT);
            new Thread(new ClientRunnable(socket,i)).start();
        }
    }


}
