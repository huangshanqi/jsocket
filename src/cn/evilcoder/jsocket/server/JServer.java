package cn.evilcoder.jsocket.server;

import cn.evilcoder.jsocket.comment.JConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * User: Huangshanqi
 * Date: 2016/3/13
 * Time: 11:51
 */
public class JServer {
    public static final Log logger = LogFactory.getLog(JServer.class);
    final static String response = "HTTP/1.0 200 OK \r\n" + "Content-type:text/plain\r\n" +"\r\n"+"Hello word\r\n";
    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(JConstant.DEFAULT_SERVER_PORT);
        logger.info("server start.......");
        try {
            while (true){
                Socket socket  = listener.accept();
                try {
                    handleRequest(socket);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }finally {
            listener.close();
        }

    }

    public static void handleRequest(Socket socket) throws IOException{
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            logger.info(reader.readLine());
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        }finally {
            socket.close();
        }
    }
}
