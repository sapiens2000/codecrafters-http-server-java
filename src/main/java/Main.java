import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Logs from your program will appear here!");


        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(4221);
            serverSocket.setReuseAddress(true);
            clientSocket = serverSocket.accept();


            InputStream clientInput = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientInput));
            OutputStream output = clientSocket.getOutputStream();


            String input = reader.readLine();
            String[] httpRequest = input.split(" ");

            for(String arr : httpRequest){
                System.out.println(arr);
            }

            if(httpRequest[1].contains("/echo")){
                String[] url = httpRequest[1].split("/");
                output.write(("HTTP/1.1 200 OK\r\n\r\nContent-Type: text/plain\r\n\r\nbContent-Length: 3\r\n\r\n" + url[1]).getBytes());

            } else if (httpRequest[1].equals("/")) {
                output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
            } else{
                output.write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}


