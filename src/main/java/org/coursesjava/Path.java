package org.coursesjava;

public enum Path {
    DEFAULT("src/main/resources/");
    private final String path;
    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
