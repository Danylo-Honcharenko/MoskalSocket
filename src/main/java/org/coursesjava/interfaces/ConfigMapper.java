package org.coursesjava.interfaces;

import org.coursesjava.exceptions.ConfigFileFieldsEmptyException;
import org.coursesjava.model.ServerConfiguration;

import java.io.IOException;
import java.util.Optional;

public interface ConfigMapper {
    void create();
    Optional<ServerConfiguration> read() throws IOException, ConfigFileFieldsEmptyException;
}
