package projectcj.swing.coding.otherui.blockSelections;

import java.awt.event.*;

import javax.swing.Box;
import javax.swing.JTextField;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.function.*;
import projectcj.swing.coding.otherui.JBlockSelection;
import java.awt.Color;

public class JT3BlockSelection extends JSingleBlockSelection {

    public JT3BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Functions");
    }

    @Override
    public void init() {
        blks.add(new JReturnBlock(display));

        for (JBlockBase block : blks) {
            blocksBoxPane.add(block);
            blocksBoxPane.add(Box.createVerticalStrut(10));
        }

        JFunctionBlock functionBlock = new JFunctionBlock(display);
        JTextField functionNameTextField = new JTextField();
        functionBlock.setInnerText("function");

        String functionPlaceholderText = "Input your custom function name.";
        functionNameTextField.setForeground(Color.GRAY);
        functionNameTextField.setText(functionPlaceholderText);
        functionNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (functionNameTextField.getText().equals(functionPlaceholderText)) {
                    functionNameTextField.setText("");
                    functionNameTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (functionNameTextField.getText().isEmpty()) {
                    functionNameTextField.setText(functionPlaceholderText);
                    functionNameTextField.setForeground(Color.GRAY);
                    functionBlock.setInnerText("function");
                }
            }
        });

        functionNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                functionBlock.setInnerText(functionNameTextField.getText());
                blocksBoxPane.repaint();
                blocksBoxPane.revalidate();
            }
        });
        blocksBoxPane.add(functionNameTextField);
        blocksBoxPane.add(functionBlock);
        blks.add(functionBlock);
        blocksBoxPane.add(Box.createVerticalStrut(10));

        for (JBlockBase blk : blks) {
            blockSelection.blks.add(blk);
        }
        super.init();
    }

    /**
     * Add custom function from this selection
     * 
     * @param runnerBlock
     */
    public void addFunction(JFunctionRunnerBlock runnerBlock) {
        blocksBoxPane.add(runnerBlock);
        blks.add(runnerBlock);
    }

    /**
     * Remove custom function from this selection
     * 
     * @param runnerBlock
     */
    public void removeFunction(JFunctionRunnerBlock runnerBlock) {
        blocksBoxPane.remove(runnerBlock);
    }
}
