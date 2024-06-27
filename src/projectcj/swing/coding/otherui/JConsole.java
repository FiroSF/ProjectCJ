package projectcj.swing.coding.otherui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

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
    public CodeExecutor executor = null;

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
    JTextField consoleInput = new JTextField("", 30);

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

                if (executor == null || !executor.isRunning) {
                    return;
                }

                write("Input: " + inp + "\n");
                consoleReader.supplyData(inp + "\n");
            }
        };
        enterButton.addActionListener(enterActionListener);
        consoleInput.addActionListener(enterActionListener);

        bottom.add(enterButton);

        center = new JScrollPane(consoleMainText);
        center.setPreferredSize(new Dimension(400, 2000));

        // https://stackoverflow.com/a/2483824/24828578
        DefaultCaret caret = (DefaultCaret) consoleMainText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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
        consoleMainText.append("=== Compiling... ===\n");
        executor = compiler.compile(display.blockContainer.getComponents());
        System.out.println("Compiled");
        consoleMainText.append("=== Compiled ===\n");
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

    public void codeIsRunning() {
        System.out.println("Code is already running!");
        consoleMainText.append("=== Code is already running! ===\n");
    }

    public void codeIsNotRunning() {
        System.out.println("Code is not running!");
        consoleMainText.append("=== Code is not running! ===\n");
    }

    public void notCompiled() {
        System.out.println("Code has never been compiled!");
        consoleMainText.append("=== Code has never been compiled! ===\n");
    }

    public void noStartBlock() {
        System.out.println("Can't find start block!");
        consoleMainText.append("=== Can't find start block! ===\n");
    }

}
