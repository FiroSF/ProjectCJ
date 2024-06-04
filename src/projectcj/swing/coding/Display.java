package projectcj.swing.coding;

import javax.swing.*;
import javax.swing.event.*;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.preload.io.JWrite;
import projectcj.swing.coding.block.scope.function.JStartBlock;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.testblocks.JBlankBlock;
import projectcj.swing.coding.block.testblocks.JHelloWorldBlock;
import projectcj.swing.coding.otherui.JConsole;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class Display extends JFrame {
    private Container c;
    public Container blockContainer;
    private Container console;

    // Mouse click event variables
    public boolean isClicked = false;
    public JBlockBase clickedBlock = null;

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

        // setContentPane(new JLayeredPane());
        c = getContentPane();
        // c.setLayout(new BorderLayout());

        // https://stackoverflow.com/a/18155818/24828578
        blockContainer = new JLayeredPane();
        blockContainer.setLayout(null);

        console = new JConsole(this);

        // Records mouse pos
        blockContainer.addMouseMotionListener(new DisplayMouseAdapter(this));

        // Find clicks, and send to proper block
        blockContainer.addMouseListener(new DisplayMouseAdapter(this));

        // For test
        JStartBlock startBlock = new JStartBlock(this);
        blocks.add(startBlock);

        JWrite jwrite = new JWrite(this);
        blocks.add(jwrite);

        for (int i = 0; i < 5; i++) {
            JHelloWorldBlock helloWorldBlock = new JHelloWorldBlock(this, "Hello world!" + i);
            blocks.add(helloWorldBlock);
        }

        for (int i = 0; i < 10; i++) {
            JBlankBlock block = new JBlankBlock(this, 120 * i, 60 * i, 100, 50,
                    new Color(i * 20, i * 20, i * 20));
            block.polygons.elementAt(0).stretchHorizontaly(i * 20);
            blocks.add(block);
        }

        for (JBlockBase blk : blocks) {
            blockContainer.add(blk, -1);
        }

        c.add(blockContainer, BorderLayout.CENTER);
        c.add(console, BorderLayout.EAST);
        setSize(1280, 720);
        setVisible(true);

        repaint();
        revalidate();
    }

    /**
     * When me is moving, this method returns glue point where me should be glued.
     * 
     * @param me
     *            target
     * @param mePoint
     *            pos of me
     * @return JBlockBase object which refers to glue point
     */
    public GluePoint getGlueObject(JBlockBase me, Point mePoint) {
        // Point mePoint = new Point(me.getX(), me.getY());
        // System.out.printf("My: %d, %d : %d\n", (int) mePoint.getX(), (int)
        // mePoint.getY(), me.getType());

        for (JBlockBase block : blocks) {
            if (me == block)
                continue;

            Vector<GluePoint> gluePoints = block.getGluePoints();
            for (GluePoint gluePoint : gluePoints) {
                // System.out.printf("%d, %d\n", (int) gluePoint.getPoint().getX(), (int)
                // gluePoint.getPoint().getY());

                // Type checking
                if ((me.getType() & gluePoint.getType()) == 0)
                    continue;

                double distance = gluePoint.getPoint().distance(mePoint);
                // System.out.println(distance);

                if (distance < 10) {
                    return gluePoint;
                }
            }
        }

        return null;
    }
}
