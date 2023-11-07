package org.coursesjava.services;

import org.coursesjava.Logger;
import org.coursesjava.Word;
import org.coursesjava.model.ServerConfiguration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class ApplicationMenuService {
    private final ConfigService config = new ConfigService();
    private final ServerConfiguration cfg = config.read();
    private BufferedWriter writer;
    private BufferedReader reader;
    public void start() {

        try (ServerSocket server = new ServerSocket(cfg.getPort(), cfg.getMaxConcurrentConnections())) {
            System.out.println("==============");
            System.out.println("Server started on port " + cfg.getPort());

            Socket client = server.accept();
            try (client) {
                System.out.println("The client has connected: " + client.getPort());
                writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writer.write("Привіт, я сервер!:)\n");
                writer.flush();

                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                List<Character> RU_letters = List.of('ё', 'ъ', 'ы', 'э');
                String receivedData = reader.readLine();

                String currentDate = DateTimeFormatter.ofPattern("MMM dd, yyyy рік, hh:mm:ss")
                        .format(LocalDateTime.now());

                boolean isRuLetter = new Word(receivedData)
                        .forbidden(RU_letters);

                if (isRuLetter) {
                    writer.write("Що таке паляниця?: ");
                    writer.flush();
                    String isLoaf = reader.readLine();
                    if (isLoaf.equals("Хліб")) {
                        writer.write(currentDate + "\n");
                        writer.write("Бувай здоровий :)");
                        writer.flush();
                    } else {
                        writer.write("Москалику я закриваю з'єднання...");
                        writer.flush();
                    }
                } else {
                    writer.write(currentDate + "\n");
                    writer.write("Бувай здоровий :)");
                    writer.flush();
                }
            } finally {
                writer.close();
                reader.close();
            }

            System.out.println("Connection interrupted!");
            System.out.println("==============");
        } catch (Exception ex) {
            System.err.println("Socket error! More in \"moskalSocket.log\"");
            Logger.write("Socket error!", ex);
        }
    }

    public void showConfig() {
        System.out.println("+=================+");
        System.out.println("Your server configuration: ");
        System.out.println("Port: " + cfg.getPort());
        System.out.println("Restriction: " + cfg.getMaxConcurrentConnections());
        System.out.println("+=================+");
    }

    public boolean exit() {
        return true;
    }
}
