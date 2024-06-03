package projectcj.core.coding;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import projectcj.swing.coding.otherui.JConsole;

/**
 * Custom console writer.
 * 
 * https://stackoverflow.com/a/12945678
 * https://velog.io/@focusonmx/Java-%EB%B9%A0%EB%A5%B8-%EC%9E%85%EC%B6%9C%EB%A0%A5
 */
public class ConsoleWriter extends OutputStreamWriter {
    public ConsoleWriter(JConsole console) {
        super(new ConsoleOutputStream(console));
    }
}

class ConsoleOutputStream extends OutputStream {
    JConsole console;

    ConsoleOutputStream(JConsole console) {
        this.console = console;
    }

    /**
     * Write single char, very slow
     */
    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        String value = Character.toString(c);
        console.write(value);
    }

    /**
     * Write multiple char, less slow but not fast I think
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append((char) b[off + i]);
        }
        console.write(sb.toString());
    }
}
