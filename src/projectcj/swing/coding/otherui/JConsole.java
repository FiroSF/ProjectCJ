package projectcj.swing.coding.otherui;

import javax.swing.*;
import javax.swing.event.*;

import projectcj.core.coding.CodeCompiler;
import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.ConsoleReader;
import projectcj.core.coding.ConsoleWriter;
import projectcj.swing.coding.Display;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class JConsole extends JPanel {
    Display display;

    // Compiler and Executor
    CodeCompiler compiler;
    CodeExecutor executor = null;

    // Console input and output streams
    public BufferedReader ins;
    public BufferedWriter outs;

    JPanel top = new JConsoleTop(this);
    JScrollPane center;
    JPanel bottom = new JPanel();

    JButton enterButton = new JButton("enter");
    JTextArea consoleMainText = new JTextArea("");
    JTextField consoleInput = new JTextField("test2", 30);

    public JConsole(Display display) {
        this.display = display;
        ins = new BufferedReader(new ConsoleReader(this));
        outs = new BufferedWriter(new ConsoleWriter(this));

        // Compiler is singleton
        compiler = new CodeCompiler(ins, outs);

        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);
        // setOpaque(false);

        consoleMainText.setBackground(Color.BLACK);
        consoleMainText.setForeground(Color.WHITE);
        consoleMainText.setOpaque(true);
        // https://stackoverflow.com/a/49971887
        consoleMainText.setEditable(false);

        center = new JScrollPane(consoleMainText);
        bottom.add(consoleInput);
        bottom.add(enterButton);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        // add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public int read() {
        // TODO implement read...
        return 'a';
    }

    /**
     * This will be executed by ConsoleWriter
     * 
     * @param s
     */
    public void write(String s) {
        String prevText = consoleMainText.getText();
        consoleMainText.setText(prevText + s);
    }

    /**
     * When compile button is clicked, this method runs.
     */
    public void compile() {
        System.out.println("Compiling...");
        executor = compiler.compile(display.blockContainer.getComponents());
        System.out.println("Compiled");
    }

    /**
     * When start button is clicked, this method runs.
     */
    public void start() {
        try {
            System.out.println("Executing...");
            executor.run();
            System.out.println("Executed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
