package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);

            String city = in.readLine();
            System.out.println("📥 Request from client: " + city);

            String weather = WeatherService.getWeather(city);
            out.println(weather);

            clientSocket.close();

        } catch (IOException e) {
            System.out.println("⚠ Client disconnected");
        }
    }
}
