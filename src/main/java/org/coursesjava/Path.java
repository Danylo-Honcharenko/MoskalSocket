package org.coursesjava;

import lombok.Getter;

@Getter
public enum Path {
    DEFAULT("src/main/resources/");
    private final String path;
    Path(String path) {
        this.path = path;
    }
}
