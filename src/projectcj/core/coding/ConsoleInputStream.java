package projectcj.core.coding;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingDeque;
import projectcj.swing.coding.otherui.JConsole;

/**
 * 
 * https://stackoverflow.com/q/36285350/24828578
 */
public class ConsoleInputStream extends InputStream {
    JConsole console;

    // User input will be stored in this
    private LinkedBlockingDeque<Integer> buffer = new LinkedBlockingDeque<>();

    public void supplyData(String s) {
        for (char ch : s.toCharArray()) {
            buffer.add((int) ch);
        }
    }

    @Override
    public int read() throws IOException {
        try {

            Integer t = buffer.take();

            // When there is lf, add -1 at deque.
            // -1 means EOF, so stream will be flushed.
            if (t == '\n') {
                buffer.addFirst(-1);
            }
            return t;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ConsoleInputStream(JConsole console) {
        this.console = console;
    }
}
