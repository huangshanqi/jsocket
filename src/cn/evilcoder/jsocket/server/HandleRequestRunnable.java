package cn.evilcoder.jsocket.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * User: Huangshanqi
 * Date: 2016/3/13
 * Time: 14:03
 */
public class HandleRequestRunnable implements Runnable {
    final static String response = "HTTP/1.0 200 OK \r\n" + "Content-type:text/plain\r\n" +"\r\n"+"Hello word\r\n";

    private final Log logger = LogFactory.getLog(HandleRequestRunnable.class);
    final Socket socket;
    public HandleRequestRunnable(Socket s){
        this.socket=s;
    }
    @Override
    public void run() {
        try {
            handleRequest(socket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleRequest(Socket socket) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            logger.info("get from client="+reader.readLine());
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        }finally {
            socket.close();
        }
    }
}
