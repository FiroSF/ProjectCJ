package projectcj.core.coding;

import java.io.InputStreamReader;

/**
 * Custom console writer.
 * 
 * https://stackoverflow.com/a/12945678
 * https://velog.io/@focusonmx/Java-%EB%B9%A0%EB%A5%B8-%EC%9E%85%EC%B6%9C%EB%A0%A5
 */
public class ConsoleReader extends InputStreamReader {
    ConsoleInputStream cis;

    public void supplyData(String s) {
        cis.supplyData(s);
    }

    public ConsoleReader(ConsoleInputStream cis) {
        super(cis);
        this.cis = cis;
    }
}

