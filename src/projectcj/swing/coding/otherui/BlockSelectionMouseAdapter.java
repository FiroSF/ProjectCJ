package projectcj.swing.coding.otherui;

import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.function.JFunctionBlock;
import projectcj.swing.coding.block.scope.function.JStartBlock;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.otherui.blockSelections.JSingleBlockSelection;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class BlockSelectionMouseAdapter extends MouseAdapter {
    JSingleBlockSelection blockSelection;
    JPanel tab;
    Display display;
    static int TAB_OFFSET = 25;

    public BlockSelectionMouseAdapter(JSingleBlockSelection blockSelection, JPanel tab) {
        this.blockSelection = blockSelection;
        this.tab = tab;
        display = blockSelection.display;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int scrollSize = blockSelection.blocksScrollWrapper.getVerticalScrollBar().getValue();
        e.translatePoint(-display.posx + tab.getX(), -display.posy + tab.getY() - scrollSize + TAB_OFFSET);
        blockSelection.display.mouseAdapter.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Don't move screen
        if (display.clickedBlock == null)
            return;

        int scrollSize = blockSelection.blocksScrollWrapper.getVerticalScrollBar().getValue();
        e.translatePoint(-display.posx + tab.getX(), -display.posy + tab.getY() - scrollSize + TAB_OFFSET);
        blockSelection.display.mouseAdapter.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int scrollSize = blockSelection.blocksScrollWrapper.getVerticalScrollBar().getValue();
        e.translatePoint(-display.posx + tab.getX(), -display.posy + tab.getY() - scrollSize + TAB_OFFSET);
        blockSelection.display.mouseAdapter.mouseReleased(e);
    }

    /**
     * Copy block
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Clicked...");

        // While iterating all of component's polygons, find appropriate block.
        for (JBlockBase block : blockSelection.blks) {
            Point mousePoint = new Point(e.getX() - block.getX(), e.getY() - block.getY());

            for (BlockPolygon bolygon : block.polygons) {
                Polygon polygon = bolygon.getPolygon();

                if (polygon.contains(mousePoint)) {
                    System.out.println("Block FOUND!");

                    // Start block checking
                    if (block instanceof JStartBlock) {
                        if (display.hasStartBlock) {
                            return;
                        } else {
                            display.hasStartBlock = true;
                        }
                    }

                    // Same name function checking
                    if (block instanceof JFunctionBlock
                            && display.getFunction(((JFunctionBlock) block).blockName) != null) {
                        return;
                    }

                    // Copy block
                    try {
                        JBlockBase newBlock = (JBlockBase) block.clone();
                        newBlock.xoffset = mousePoint.x;// - block.getX();
                        newBlock.yoffset = mousePoint.y;// - block.getY();
                        // newBlock.xoffset = blockSelection.display.getMouseX() - block.posx;
                        // newBlock.yoffset = blockSelection.display.getMouseY() - block.posy;

                        // Set zindex
                        newBlock.changeZIndex(0);

                        blockSelection.display.isClicked = true;
                        blockSelection.display.clickedBlock = newBlock;

                        // System.out.println("Block spawn!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("CLONE ERROR!!!!!!!!");
                    }

                    return;
                }
            }
        }
    }
}
