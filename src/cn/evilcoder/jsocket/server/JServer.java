package cn.evilcoder.jsocket.server;

import cn.evilcoder.jsocket.comment.JConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: Huangshanqi
 * Date: 2016/3/13
 * Time: 11:51
 */
public class JServer {
    public static final Log logger = LogFactory.getLog(JServer.class);
    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(JConstant.DEFAULT_SERVER_PORT);
        ExecutorService executor = Executors.newFixedThreadPool(500);
        logger.info("server start.......");
        try {
            while (true){
                Socket socket  = listener.accept();
                try {
                    executor.submit(new Thread(new HandleRequestRunnable(socket)));
                }catch (Exception e){
                    logger.info("e:", e);
                }
            }
        }finally {
            listener.close();
        }

    }

}
