package com.example;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class InputHandler {

    private Terminal terminal;

    public InputHandler() throws Exception {
        this.terminal = TerminalBuilder.builder()
                .jna(false)
                .jansi(true)
                .system(true)
                .build();
        terminal.enterRawMode();
    }

    public Key readInput() throws Exception {
        NonBlockingReader reader = terminal.reader();
        if (reader.ready()) {
            int input = reader.read();
            return mapToKey(input);
        }
        return null;
    }

    private Key mapToKey(int inputChar) {
        switch (inputChar) {
            case 'w':
            case 'W':
                return Key.UP;
            case 'd':
            case 'D':
                return Key.RIGHT;
            case 's':
            case 'S':
                return Key.DOWN;
            case 'a':
            case 'A':
                return Key.LEFT;
            default:
                return null;
        }
    }

    /// getters and setters///

    public void cleanup() {
        try {
            terminal.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }
}