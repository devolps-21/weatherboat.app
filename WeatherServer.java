package server;

import java.net.ServerSocket;
import java.net.Socket;

public class WeatherServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket =
                 new ServerSocket(Config.PORT)) {

            System.out.println("🌐 Multithreaded Weather Server Started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("📡 New client connected");

                ClientHandler clientHandler =
                    new ClientHandler(clientSocket);
                clientHandler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
