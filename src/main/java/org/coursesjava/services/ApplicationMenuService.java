package org.coursesjava.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coursesjava.exceptions.ConfigFileFieldsEmptyException;
import org.coursesjava.interfaces.ConfigMapper;
import org.coursesjava.model.ServerConfiguration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

public class ApplicationMenuService {
    private final ConfigMapper config = new ConfigXmlService();
    private ServerConfiguration cfg;
    private static final Logger log = LogManager.getLogger(ApplicationMenuService.class);
    public void start() {
        try {
            cfg = config.read().orElseThrow();

            int port = cfg.getPort();
            int maxConnection = cfg.getMaxConcurrentConnections();

            try (ServerSocket server = new ServerSocket(port, maxConnection)) {
                log.info("Server started on port: {}", cfg.getPort());

                try (Socket client = server.accept();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                    log.info("The client has connected: {}", client.getPort());
                    log.info("Server send message: Hello, I am server!:)");
                    writer.write("Hello, I am server!:)\n");
                    writer.flush();

                    String clientMessage = reader.readLine();
                    log.info("Client send message: {}", clientMessage);
                    String currentDate = LocalDate.now()
                            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    log.info("Server send date: {}", currentDate);
                    writer.write("\n" + currentDate);
                    writer.flush();
                }
                log.info("Connection closed!");
            } catch (Exception ex) {
                log.error("Socket", ex);
            }
        } catch (FileNotFoundException fileNotFound) {
            log.warn("File \"config.xml\" not found! We will create it automatically, exit from program!");
            config.create();
        } catch (IOException IOex) {
            log.error("File read error!", IOex);
        } catch (ConfigFileFieldsEmptyException emptyFileFields) {
            log.error("The configuration values are empty!");
        } catch (NoSuchElementException noElemEx) {
            log.error("The configuration file instance is empty!");
        }
    }

    public void showConfig() {
        System.out.println("+=================+");
        System.out.println("Your server configuration: ");
        System.out.println("Port: " + cfg.getPort());
        System.out.println("Max connection: " + cfg.getMaxConcurrentConnections());
        System.out.println("+=================+");
    }
}
