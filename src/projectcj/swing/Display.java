package projectcj.swing;

import javax.swing.*;
import javax.swing.event.*;
import projectcj.swing.block.JBlankBlock;
import projectcj.swing.block.JBlockBase;
import projectcj.swing.block.scope.JStartBlock;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Display extends JFrame {
    private Container c;
    public ArrayList<JBlockBase> blocks;

    int mouseX = 0;
    int mouseY = 0;

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public Display() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = getContentPane();
        c.setLayout(null);

        // Records mouse pos
        c.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                // System.out.printf("%d %d\n", mouseX, mouseY);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }
        });

        JBlankBlock block = new JBlankBlock(this);
        // block.setSize(10, 10);
        // block.setLocation(100, 100);
        c.add(block);


        setSize(300, 300);
        setVisible(true);
    }
}
