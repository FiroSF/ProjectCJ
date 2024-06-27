package projectcj.swing.coding.otherui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JConsoleTop extends JPanel {
    JConsole console;
    JButton compileStartButton = new JButton("Compile & Start");
    JButton compileButton = new JButton("Compile");
    JButton startButton = new JButton("Start");
    JButton stopButton = new JButton("Stop");

    public JConsoleTop(JConsole console) {
        this.console = console;
        setLayout(new GridLayout(1, 10));

        compileStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (console.executor != null && console.executor.isRunning) {
                    console.codeIsRunning();
                    return;
                }
                console.compile();

                if (console.executor.startBlock == null) {
                    console.noStartBlock();
                    console.executor = null;
                    return;
                }
                console.start();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (console.executor != null && console.executor.isRunning) {
                    console.codeIsRunning();
                    return;
                }
                if (console.executor == null) {
                    console.notCompiled();
                    return;
                }
                if (console.executor.startBlock == null) {
                    console.noStartBlock();
                    console.executor = null;
                    return;
                }
                console.start();
            }
        });

        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (console.executor != null && console.executor.isRunning) {
                    console.codeIsRunning();
                    return;
                }
                console.compile();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (console.executor == null || !console.executor.isRunning) {
                    console.codeIsNotRunning();
                    return;
                }
                console.stop();
            }
        });

        add(compileStartButton);
        add(compileButton);
        add(startButton);
        add(stopButton);
    }
}
