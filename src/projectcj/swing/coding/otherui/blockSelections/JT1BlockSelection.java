package projectcj.swing.coding.otherui.blockSelections;

import javax.swing.*;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.builtin.*;
import projectcj.swing.coding.block.builtin.calc.assign.JAssignBlock;
import projectcj.swing.coding.block.builtin.keyword.*;
import projectcj.swing.coding.block.builtin.variable.*;
import projectcj.swing.coding.block.scope.function.JStartBlock;
import projectcj.swing.coding.otherui.JBlockSelection;
import java.awt.event.*;

import java.awt.Color;

public class JT1BlockSelection extends JSingleBlockSelection {

    public JT1BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Basics");
    }

    @Override
    public void init() {
        blks.add(new JStartBlock(display));
        blks.add(new JIfBlock(display));
        blks.add(new JElseBlock(display));
        blks.add(new JWhileBlock(display));
        blks.add(new JBreakBlock(display));
        blks.add(new JAssignBlock(display));

        for (JBlockBase block : blks) {
            blocksBoxPane.add(block);
            blocksBoxPane.add(Box.createVerticalStrut(10));
        }

        JLiteralBlock literalBlock = new JLiteralBlock(display);
        JTextField blockNameTextField = new JTextField();

        literalBlock.setInnerText("\"Sample Text\"");
        literalBlock.updateSize();

        String literalPlaceholderText = "Input a string value.";
        blockNameTextField.setForeground(Color.GRAY);
        blockNameTextField.setText(literalPlaceholderText);

        blockNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (blockNameTextField.getText().equals(literalPlaceholderText)) {
                    blockNameTextField.setText("");
                    blockNameTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (blockNameTextField.getText().isEmpty()) {
                    blockNameTextField.setText(literalPlaceholderText);
                    blockNameTextField.setForeground(Color.GRAY);
                    // literalBlock.setInnerText("\"Sample Text\"");
                    literalBlock.updateSize();
                }
            }
        });

        blockNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                literalBlock.setInnerText("\"" + blockNameTextField.getText() + "\"");
                literalBlock.updateSize();
            }
        });
        blocksBoxPane.add(blockNameTextField);
        blocksBoxPane.add(literalBlock);
        blks.add(literalBlock);
        blocksBoxPane.add(Box.createVerticalStrut(10));

        // JVariableDeclareBlock variableDeclareBlock = new
        // JVariableDeclareBlock(display);
        // blocksBoxPane.add(variableDeclareBlock);
        // blks.add(variableDeclareBlock);

        JVariableNameBlock variableNameBlock = new JVariableNameBlock(display);
        JTextField variableNamTextField = new JTextField();

        variableNameBlock.setInnerText("x");
        blocksBoxPane.repaint();
        blocksBoxPane.revalidate();

        String variablePlaceholderText = "Input a variable name or a decimal number.";
        variableNamTextField.setForeground(Color.GRAY);
        variableNamTextField.setText(variablePlaceholderText);
        variableNamTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (variableNamTextField.getText().equals(variablePlaceholderText)) {
                    variableNamTextField.setText("");
                    variableNamTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (variableNamTextField.getText().isEmpty()) {
                    variableNamTextField.setText(variablePlaceholderText);
                    variableNamTextField.setForeground(Color.GRAY);
                    variableNameBlock.setInnerText("x");
                    blocksBoxPane.repaint();
                    blocksBoxPane.revalidate();
                }
            }
        });

        variableNamTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                variableNameBlock.setInnerText(variableNamTextField.getText());
                blocksBoxPane.repaint();
                blocksBoxPane.revalidate();
            }
        });
        blocksBoxPane.add(variableNamTextField);
        blocksBoxPane.add(variableNameBlock);
        blks.add(variableNameBlock);
        blocksBoxPane.add(Box.createVerticalStrut(10));

        for (JBlockBase blk : blks) {
            blockSelection.blks.add(blk);
            blocksBoxPane.repaint();
            blocksBoxPane.revalidate();
        }

        super.init();
    }

}
