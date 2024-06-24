package projectcj.swing.coding.otherui;

import javax.swing.*;
import projectcj.core.coding.CodeCompiler;
import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.ConsoleInputStream;
import projectcj.core.coding.ConsoleReader;
import projectcj.core.coding.ConsoleWriter;
import projectcj.swing.coding.Display;

import java.awt.*;
import java.awt.event.*;
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
    public ConsoleReader consoleReader;
    public ConsoleInputStream tmp;

    JPanel top = new JConsoleTop(this);
    JScrollPane center;
    JPanel bottom = new JPanel();

    JButton enterButton = new JButton("enter");
    JTextArea consoleMainText = new JTextArea("");
    JTextField consoleInput = new JTextField("test2", 30);

    public JConsole(Display display) {
        this.display = display;

        tmp = new ConsoleInputStream(this);
        consoleReader = new ConsoleReader(tmp);
        ins = new BufferedReader(consoleReader);
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

        bottom.add(consoleInput);


        // User input
        ActionListener enterActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = consoleInput.getText();
                consoleInput.setText("");

                consoleReader.supplyData(inp + "\n");
            }
        };
        enterButton.addActionListener(enterActionListener);
        consoleInput.addActionListener(enterActionListener);

        bottom.add(enterButton);



        center = new JScrollPane(consoleMainText);
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        // add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    // public int read() {
    // return 'a';
    // }

    /**
     * This will be executed by ConsoleWriter
     * 
     * @param s
     */
    public void write(String s) {
        // https://stackoverflow.com/questions/33659532/how-do-you-add-text-to-a-jtextarea
        consoleMainText.append(s);
        // String prevText = consoleMainText.getText();
        // consoleMainText.setText(prevText + s);
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
            // System.out.println("Executing...");
            executor.run();
            // System.out.println("Executed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop program
     */
    public void stop() {
        executor.stop();
    }

}
