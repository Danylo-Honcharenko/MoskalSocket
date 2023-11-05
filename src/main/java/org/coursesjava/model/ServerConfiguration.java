package org.coursesjava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.InetAddress;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerConfiguration {
    private int port = 8080;
    private int restriction = 1;
}
