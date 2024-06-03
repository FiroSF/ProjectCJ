package projectcj.swing.coding.otherui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JConsoleTop extends JPanel {
    JConsole console;
    JButton compileStartButton = new JButton("Compile & Start");
    JButton compileButton = new JButton("Compile");
    JButton startButton = new JButton("Start");

    public JConsoleTop(JConsole console) {
        this.console = console;
        setLayout(new GridLayout(1, 10));

        compileStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                console.compile();
                console.start();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                console.start();
            }
        });

        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                console.compile();
            }
        });

        add(compileStartButton);
        add(compileButton);
        add(startButton);
    }
}
