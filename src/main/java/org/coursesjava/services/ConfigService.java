package org.coursesjava.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.coursesjava.Logger;
import org.coursesjava.Path;
import org.coursesjava.model.ServerConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigService {
    private final ObjectMapper mapper = new XmlMapper();
    private final File config = new File(Path.DEFAULT.getPath() + "config.xml");
    public void create() {
        try {
            mapper.writeValue(config, new ServerConfiguration());
        } catch (IOException ex) {
            System.err.println("File write error! More in \"moskalSocket.log\": " + ex.getMessage());
            Logger.write("File write error!", ex);
        }
    }

    public ServerConfiguration read() {
        ServerConfiguration config = null;
        try {
            config = mapper.readValue(this.config, ServerConfiguration.class);
        } catch (FileNotFoundException fileNotFound) {
            System.err.println("File \"config.xml\" not found! We will create it automatically, exit from program!");
            Logger.write("File \"config.xml\" not found but was created automatically", fileNotFound);
            create();
        } catch (IOException ex) {
            System.err.println("File read error! More in \"moskalSocket.log\"");
            Logger.write("File read error!", ex);
        }
        return config;
    }
}
