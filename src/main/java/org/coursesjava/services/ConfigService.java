package org.coursesjava.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.coursesjava.model.ServerConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigService {
    private final ObjectMapper mapper = new XmlMapper();
    private final File config = new File("src/main/resources/config.xml");
    public void create() {
        try {
            mapper.writeValue(config, new ServerConfiguration());
        } catch (IOException ex) {
            System.err.println("File write error! " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ServerConfiguration read() {
        ServerConfiguration config = null;
        try {
            config = mapper.readValue(this.config, ServerConfiguration.class);
        } catch (FileNotFoundException fileNotFound) {
            System.err.println("File \"config.xml\" not found! We will create it automatically, exit from program!");
            create();
        } catch (IOException ex) {
            System.err.println("File read error! " + ex.getMessage());
        }
        return config;
    }
}
