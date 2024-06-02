package projectcj.swing.coding.console;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class JConsole extends JPanel {
    JPanel center = new JPanel();
    JPanel bottom = new JPanel();

    JButton enterButton = new JButton("enter");
    JLabel consoleMainText = new JLabel("Console output here");
    JTextField consoleInput = new JTextField("test2", 30);

    public JConsole() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        // setOpaque(false);

        consoleMainText.setSize(getWidth() - 20, getHeight() - 100);
        consoleMainText.setBackground(Color.BLACK);
        consoleMainText.setForeground(Color.WHITE);
        consoleMainText.setOpaque(true);

        center.add(consoleMainText);
        bottom.add(consoleInput);
        bottom.add(enterButton);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
