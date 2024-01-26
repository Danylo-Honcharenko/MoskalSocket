package org.coursesjava.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coursesjava.exceptions.ConfigFileFieldsEmptyException;
import org.coursesjava.interfaces.ConfigMapper;
import org.coursesjava.model.ServerConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ConfigXmlService implements ConfigMapper {
    private final ObjectMapper mapper = new XmlMapper();
    private final File config = new File("src/main/resources/config.xml");
    private static final Logger log = LogManager.getLogger(ConfigXmlService.class);

    @Override
    public void create() {
        try {
            mapper.writeValue(config, new ServerConfiguration());
        } catch (IOException IOex) {
            log.error("File write error!", IOex);
        }
    }

    @Override
    public Optional<ServerConfiguration> read() throws IOException, ConfigFileFieldsEmptyException {
        ServerConfiguration config = mapper.readValue(this.config, ServerConfiguration.class);
        if (config.getPort() == 0 || config.getMaxConcurrentConnections() == 0) throw new ConfigFileFieldsEmptyException();
        return Optional.of(config);
    }
}
