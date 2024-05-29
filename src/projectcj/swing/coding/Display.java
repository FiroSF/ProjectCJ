package projectcj.swing.coding;

import javax.swing.*;
import javax.swing.event.*;
import projectcj.swing.coding.block.JBlankBlock;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.JStartBlock;
import projectcj.swing.coding.console.JConsole;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Display extends JFrame {
    private Container c;
    private Container blockContainer;
    private Container consoleContainer;
    public ArrayList<JBlockBase> blocks = new ArrayList<>();

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
        // c.setLayout(null);

        blockContainer = new Container();
        blockContainer.setLayout(null);

        consoleContainer = new JConsole();

        // Records mouse pos
        blockContainer.addMouseMotionListener(new MouseAdapter() {
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

        // For test
        for (int i = 0; i < 5; i++) {
            JBlankBlock block = new JBlankBlock(this, 120 * i, 60 * i, 100, 50);
            block.color = new Color(i * 50, i * 50, i * 50);
            blocks.add(block);
            blockContainer.add(block);
        }

        c.add(blockContainer, BorderLayout.CENTER);
        c.add(consoleContainer, BorderLayout.EAST);
        setSize(1280, 720);
        setVisible(true);
    }

    /**
     * When me is moving, this method returns glue point where me should be glued.
     * 
     * @param me target
     * @param mePoint pos of me
     * @return JBlockBase object which refers to glue point
     */
    public JBlockBase getGlueObject(JBlockBase me, Point mePoint) {
        // Point mePoint = new Point(me.getX(), me.getY());

        for (JBlockBase block : blocks) {
            if (me == block)
                continue;

            Point blockPoint = block.getGluePoint();
            double distance = blockPoint.distance(mePoint);
            System.out.println(distance);

            if (distance < 10) {
                return block;
            }
        }

        return null;
    }
}
