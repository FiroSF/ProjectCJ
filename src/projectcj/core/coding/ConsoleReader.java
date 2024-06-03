package projectcj.core.coding;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import projectcj.swing.coding.otherui.JConsole;

/**
 * Custom console writer.
 * 
 * https://stackoverflow.com/a/12945678
 * https://velog.io/@focusonmx/Java-%EB%B9%A0%EB%A5%B8-%EC%9E%85%EC%B6%9C%EB%A0%A5
 */
public class ConsoleReader extends InputStreamReader {
    public ConsoleReader(JConsole console) {
        super(new ConsoleInputStream(console));
    }
}

class ConsoleInputStream extends InputStream {
    JConsole console;

    ConsoleInputStream(JConsole console) {
        this.console = console;
    }

    /**
     * Is read speed is matter?
     */
    @Override
    public int read() throws IOException {
        return console.read();
    }
}
