package projectcj.swing.coding.otherui.blockSelections;

import java.awt.event.*;

import javax.swing.JTextField;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.function.*;
import projectcj.swing.coding.otherui.JBlockSelection;

public class JT3BlockSelection extends JSingleBlockSelection {

    public JT3BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Functions");
    }

    @Override
    public void init() {
        blks.add(new JReturnBlock(display));

        for (JBlockBase block : blks) {
            blocksPane.add(block);
        }

        JFunctionBlock functionBlock = new JFunctionBlock(display);
        JTextField functionNameTextField = new JTextField();
        functionNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                functionBlock.setInnerText(functionNameTextField.getText());
            }
        });
        blocksPane.add(functionNameTextField);
        blocksPane.add(functionBlock);
        blks.add(functionBlock);

        for (JBlockBase blk : blks) {
            blockSelection.blks.add(blk);
        }
    }

    /**
     * Add custom function from this selection
     * 
     * @param runnerBlock
     */
    public void addFunction(JFunctionRunnerBlock runnerBlock) {
        blocksPane.add(runnerBlock);
        blks.add(runnerBlock);
    }

    /**
     * Remove custom function from this selection
     * 
     * @param runnerBlock
     */
    public void removeFunction(JFunctionRunnerBlock runnerBlock) {
        blocksPane.remove(runnerBlock);
    }
}
