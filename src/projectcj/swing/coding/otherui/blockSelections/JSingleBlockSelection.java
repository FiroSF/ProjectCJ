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
    JLayeredPane blocksPane = new JLayeredPane();
    JPanel blockPanel = new JPanel();
    // https://stackoverflow.com/a/10346673/24828578
    JScrollPane blocksWrapper = new JScrollPane();

    JSingleBlockSelection(JBlockSelection blockSelection, String tabname) {
        this.blockSelection = blockSelection;
        display = blockSelection.display;

        // Event
        BlockSelectionMouseAdapter mouseAdapter = new BlockSelectionMouseAdapter(this, blockPanel);
        blockPanel.addMouseMotionListener(mouseAdapter);
        blockPanel.addMouseListener(mouseAdapter);

        // https://stackoverflow.com/a/13511696/24828578
        blocksPane.setLayout(new BoxLayout(blocksPane, BoxLayout.Y_AXIS));
        blockPanel.add(blocksPane, BorderLayout.CENTER);
        blocksWrapper.setViewportView(blockPanel);

        this.tabname = tabname;
    }

    /**
     * Tab init function.
     */
    abstract public void init();

    public Container getContainer() {
        return blocksWrapper;
    }
}
