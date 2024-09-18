package com.example;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class InputHandler implements Runnable {

    private Terminal terminal;
    private volatile Key currentDirection;
    private Thread inputThread;
    private boolean running;

    public InputHandler() throws Exception {
        this.terminal = TerminalBuilder.builder()
                .jna(false)
                .jansi(true)
                .system(true)
                .build();
        terminal.enterRawMode();

        this.currentDirection = Key.RIGHT;
        this.running = true;
        this.inputThread = new Thread(this);

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