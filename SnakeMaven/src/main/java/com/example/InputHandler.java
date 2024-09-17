import java.security.Key;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class InputHandler {

    private Terminal terminal;
    private LineReader linereader;

    public InputHandler() throws Exception {
        this.terminal = TerminalBuilder.terminal();
        terminal.enterRawMode();
        this.linereader = LineReaderBuilder.builder().terminal(terminal).build();
    }

    public char readInput() throws Exception{
        int input = terminal.reader().read(); // reads character input
        return mapToKey(input);

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
    public Terminal getTerminal() {
        return terminal;
    }

    public LineReader getLineReader() {
        return linereader;
    }

    public void cleanup() {
        try {
            terminal.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}