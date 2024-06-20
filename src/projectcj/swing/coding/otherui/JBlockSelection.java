package projectcj.swing.coding.otherui;

import javax.swing.*;
import javax.swing.event.*;

import projectcj.core.coding.CodeCompiler;
import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.ConsoleReader;
import projectcj.core.coding.ConsoleWriter;
import projectcj.swing.coding.BlockContainerMouseAdapter;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.builtin.io.JRead;
import projectcj.swing.coding.block.builtin.io.JWrite;
import projectcj.swing.coding.block.builtin.keyword.JIf;
import projectcj.swing.coding.block.scope.function.JStartBlock;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Vector;

public class JBlockSelection extends JPanel {
    Display display;
    JBlockBase blks[];
    BlockSelectionMouseAdapter mouseAdapter;

    JLayeredPane blocks = new JLayeredPane();
    JPanel blockPanel = new JPanel();
    JPanel bottom = new JPanel();
    JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

    public JBlockSelection(Display display) {
        this.display = display;

        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);

        // Event
        mouseAdapter = new BlockSelectionMouseAdapter(this);
        tab.addMouseMotionListener(mouseAdapter);
        tab.addMouseListener(mouseAdapter);

        // https://stackoverflow.com/a/13511696/24828578
        blocks.setLayout(new BoxLayout(blocks, BoxLayout.Y_AXIS));
        blockPanel.add(blocks, BorderLayout.CENTER);

        // setPreferredSize(new Dimension(500, 500));
        // blocks.setPreferredSize(new Dimension(500, 500));

        tab.addTab("t1", blockPanel);
        tab.addTab("t2", bottom);

        add(tab, BorderLayout.CENTER);
    }

    public void init() {
        blks = new JBlockBase[] {
                new JRead(display),
                new JWrite(display),
                new JIf(display),
                new JStartBlock(display),
        };

        for (JBlockBase block : blks) {
            blocks.add(block);
        }
    }

}
