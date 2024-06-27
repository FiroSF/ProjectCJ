package projectcj.swing.coding.otherui;

import javax.swing.*;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.function.JFunctionBlock;
import projectcj.swing.coding.otherui.blockSelections.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class JBlockSelection extends JPanel {
    public Display display;

    // All blocks
    public Vector<JBlockBase> blks = new Vector<>();

    // Single block selections
    Vector<JSingleBlockSelection> blockSelections;

    // Custom functions
    HashMap<String, FunctionData> functions = new HashMap<>();

    // Function selection
    JT3BlockSelection functionSelection;
    // BlockSelectionMouseAdapter mouseAdapter;

    // https://stackoverflow.com/a/10346673/24828578
    // JScrollPane blocksWrapper = new JScrollPane();
    // JLayeredPane blocks = new JLayeredPane();
    // JPanel blockPanel = new JPanel();
    JPanel bottom = new JPanel();
    JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

    public JBlockSelection(Display display) {
        this.display = display;

        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);

        blockSelections = new Vector<>();
        blockSelections.add(new JT1BlockSelection(this));
        blockSelections.add(new JT2BlockSelection(this));
        functionSelection = new JT3BlockSelection(this);
        blockSelections.add(functionSelection);
        blockSelections.add(new JT4BlockSelection(this));
        blockSelections.add(new JT5BlockSelection(this));

        for (JSingleBlockSelection selection : blockSelections) {
            tab.addTab(selection.tabname, selection.getContainer());
        }

        // tab.addTab("t1", blockSelections.get(0).getContainer());
        // tab.addTab("t2", bottom);

        add(tab, BorderLayout.CENTER);
    }

    public void init() {
        for (JSingleBlockSelection selection : blockSelections) {
            selection.init();
        }
    }

    /**
     * Add custom function
     * 
     * @param functionBlock
     */
    public void addFunction(JFunctionBlock functionBlock) {
        FunctionData functionData = new FunctionData(functionBlock);
        functions.put(functionBlock.blockName, functionData);

        functionSelection.addFunction(functionData.originalRunner);
        functionBlock.setFunctionData(functionData);
        blks.add(functionData.originalRunner);
    }

    /**
     * Remove custom function
     * 
     * @param functionBlock
     */
    public void removeFunction(JFunctionBlock functionBlock) {
        // TODO: Implement remove block first
        // functionSelection.removeFunction(functionData.originalRunner);

        FunctionData fdata = functions.get(functionBlock.blockName);

        functionSelection.removeFunction(fdata.originalRunner);
        blks.remove(fdata.originalRunner);
        functions.remove(functionBlock.blockName);
    }

    public FunctionData getFunction(String blockName) {
        return functions.get(blockName);
    }

}
