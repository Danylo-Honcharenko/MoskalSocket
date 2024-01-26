package org.coursesjava.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MenuItem {
    TITLE("+ Socket +"),
    START("1. Start"),
    SERVER_CONFIG("2. Show server config"),
    EXIT("3. Exit"),
    ACTION("Choose an action: "),
    NO_EXISTS_ITEM("This menu item does not exist!");

    private final String message;

    @Override
    public String toString() {
        return message;
    }
}
