package projectcj.swing.coding;

import javax.swing.*;
import javax.swing.event.*;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.JParameterBlockBase;
import projectcj.swing.coding.block.builtin.io.JRead;
import projectcj.swing.coding.block.builtin.io.JWrite;
import projectcj.swing.coding.block.builtin.keyword.JIf;
import projectcj.swing.coding.block.scope.function.JStartBlock;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.special.JParameter;
import projectcj.swing.coding.block.testblocks.JBlankBlock;
import projectcj.swing.coding.block.testblocks.JBlankParamBlock;
import projectcj.swing.coding.block.testblocks.JBlankScopeBlock;
import projectcj.swing.coding.block.testblocks.JHelloWorldBlock;
import projectcj.swing.coding.otherui.JBlockSelection;
import projectcj.swing.coding.otherui.JConsole;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class Display extends JFrame {
    // Container which includes main container, overlay container, block container
    public Container upperc;

    // Main container, includes console and blockSelection.
    public static JPanel c;

    // Overlay container, when block is being moved by user, blocks are in here.
    public JLayeredPane overlayContainer;

    // Block container
    public JLayeredPane blockContainer;

    private JConsole console;
    private JBlockSelection blockSelection;

    // Mouse click event variables
    public BlockContainerMouseAdapter mouseAdapter;
    public boolean isClicked = false;
    public JBlockBase clickedBlock = null;

    // public ArrayList<JBlockBase> blocks = new ArrayList<>();

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
        upperc = getContentPane();
        upperc.setLayout(null);

        c = new JPanel();
        c.setLayout(new BorderLayout());

        // Because layout manager is null, specify size manually.
        // https://stackoverflow.com/a/8792423/24828578
        c.setBounds(0, 0, 1600, 800);
        c.setBackground(Color.WHITE);
        c.setOpaque(false);

        // Init overlay container
        overlayContainer = new JLayeredPane();
        overlayContainer.setBounds(0, 0, 1600, 800);
        overlayContainer.setOpaque(false);

        // Init blockContainer
        // https://stackoverflow.com/a/18155818/24828578
        blockContainer = new JLayeredPane();
        blockContainer.setLayout(null);
        blockContainer.setBounds(0, 0, 1600, 800);

        // Event
        mouseAdapter = new BlockContainerMouseAdapter(this);

        // Records mouse pos
        blockContainer.addMouseMotionListener(mouseAdapter);

        // Find clicks, and send to proper block
        blockContainer.addMouseListener(mouseAdapter);

        upperc.add(blockContainer, 0);
        upperc.add(c, 0);
        upperc.add(overlayContainer, 0);

        console = new JConsole(this);
        blockSelection = new JBlockSelection(this);

        setSize(1600, 900);
        setVisible(true);

        repaint();
        revalidate();
    }

    public void init() {
        blockSelection.init();

        // For test
        ArrayList<JBlockBase> blocks = new ArrayList<>();

        JStartBlock startBlock = new JStartBlock(this);
        startBlock.movePropagation(new Point(0, 300));
        blocks.add(startBlock);

        JRead jread = new JRead(this);
        jread.movePropagation(new Point(500, 0));
        blocks.add(jread);

        JWrite jwrite = new JWrite(this);
        jwrite.movePropagation(new Point(300, 0));
        blocks.add(jwrite);

        for (int i = 0; i < 5; i++) {
            JIf asdf = new JIf(this);
            asdf.movePropagation(new Point(100, 500 + 100 * i));
            blocks.add(asdf);
        }

        for (int i = 0; i < 5; i++) {
            JHelloWorldBlock helloWorldBlock = new JHelloWorldBlock(this, "Hello world!" + i);
            helloWorldBlock.movePropagation(new Point(50, 500));
            blocks.add(helloWorldBlock);
        }

        for (int i = 0; i < 10; i++) {
            JBlankParamBlock block = new JBlankParamBlock(this, 120 * i, 60 * i, 100, 50,
                    new Color(i * 20, i * 20, i * 20));
            block.polygons.elementAt(0).stretchHorizontaly(i * 20);
            blocks.add(block);
        }

        for (JBlockBase blk : blocks) {
            blockContainer.add(blk, -1);
        }

        // c.add(blockContainer, BorderLayout.CENTER, 0);
        c.add(console, BorderLayout.EAST, -1);
        c.add(blockSelection, BorderLayout.WEST, -1);

        repaint();
        revalidate();
    }

    private boolean checkGluePoint(JBlockBase me, Point mePoint, GluePoint gluePoint) {
        // Type checking
        if ((me.getType() & gluePoint.getType()) == 0)
            return false;

        double distance = gluePoint.getPoint().distance(mePoint);
        // System.out.println(distance);

        if (distance < 10) {
            return true;
        }
        return false;
    }

    /**
     * When me is moving, this method returns glue point where me should be glued.
     * 
     * @param me
     *            target
     * @param mePoint
     *            pos of me
     * @return GluePoint object which refers to glue point
     */
    public GluePoint getGlueObject(JBlockBase me, Point mePoint) {
        // Point mePoint = new Point(me.getX(), me.getY());
        // System.out.printf("My: %d, %d : %d\n", (int) mePoint.getX(), (int)
        // mePoint.getY(), me.getType());

        for (Component obj : blockContainer.getComponents()) {
            if (!(obj instanceof JBlockBase))
                continue;
            JBlockBase block = (JBlockBase) obj;

            if (me == block)
                continue;

            // Param gluePoints
            // it's rvalue, single
            if ((me.getType() & GluePoint.PARAMETER_CHECK) != 0 && ((JNormalBlockBase) me).lowerBlock == null
                    && block instanceof JParameterBlockBase) {
                Vector<JParameter> params = ((JParameterBlockBase) block).parameters;

                for (JParameter param : params) {
                    // System.out.printf("%d, %d\n", (int) param.gluePoint.getPoint().getX(),
                    // (int) param.gluePoint.getPoint().getY());
                    if (checkGluePoint(me, mePoint, param.gluePoint)) {
                        return param.gluePoint;
                    }
                }
            }

            // Normal gluePoints
            Vector<GluePoint> gluePoints = block.getGluePoints();
            for (GluePoint gluePoint : gluePoints) {
                // System.out.printf("%d, %d\n", (int) gluePoint.getPoint().getX(), (int)
                // gluePoint.getPoint().getY());

                // If this block is being used as parameter
                if (gluePoint.getParent() instanceof JNormalBlockBase)
                    if (((JNormalBlockBase) gluePoint.getParent()).upperParameter != null)
                        continue;

                if (checkGluePoint(me, mePoint, gluePoint)) {
                    return gluePoint;
                }
            }
        }

        return null;
    }
}
