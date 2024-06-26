package projectcj.swing.coding.otherui.blockSelections;

import javax.swing.*;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.otherui.BlockSelectionMouseAdapter;
import projectcj.swing.coding.otherui.JBlockSelection;
import java.awt.*;
import java.util.Vector;

public abstract class JSingleBlockSelection {
    public Vector<JBlockBase> blks = new Vector<>();

    JBlockSelection blockSelection;
    public Display display;

    public String tabname;

    // Swings
    public JPanel blocksBoxPane = new JPanel();
    public JPanel blocksWrapper = new JPanel();
    // https://stackoverflow.com/a/10346673/24828578
    public JScrollPane blocksScrollWrapper = new JScrollPane();

    JSingleBlockSelection(JBlockSelection blockSelection, String tabname) {
        this.blockSelection = blockSelection;
        display = blockSelection.display;

        // Event
        BlockSelectionMouseAdapter mouseAdapter = new BlockSelectionMouseAdapter(this, blocksBoxPane);
        blocksWrapper.addMouseMotionListener(mouseAdapter);
        blocksWrapper.addMouseListener(mouseAdapter);

        // https://stackoverflow.com/a/13511696/24828578
        blocksBoxPane.setLayout(new BoxLayout(blocksBoxPane, BoxLayout.Y_AXIS));

        // JLabel sizeHolder = new JLabel(
        // " ");

        // // lb.setSize(400, 0);
        // blocksBoxPane.add(sizeHolder);
        // blockPanel.setLayout(null);

        blocksWrapper.add(blocksBoxPane);
        blocksScrollWrapper.setViewportView(blocksWrapper);
        blocksScrollWrapper.setPreferredSize(new Dimension(400, 1000));
        this.tabname = tabname;
    }

    /**
     * Tab init function.
     */
    public void init() {
        JLabel sizeHolder = new JLabel(
                "                                                                                       ");

        // lb.setSize(400, 0);
        blocksBoxPane.add(sizeHolder);
        // https://stackoverflow.com/a/5583571/24828578
        blocksScrollWrapper.getVerticalScrollBar().setUnitIncrement(16);
        blocksScrollWrapper.getHorizontalScrollBar().setUnitIncrement(16);
    };

    public Container getContainer() {
        return blocksScrollWrapper;
    }
}
