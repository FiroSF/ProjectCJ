package projectcj.swing.coding.otherui;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.JParameterBlockBase;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.JParameter;

import java.awt.*;
import java.awt.event.*;

public class BlockSelectionMouseAdapter extends MouseAdapter {
    JBlockSelection blockSelection;

    public BlockSelectionMouseAdapter(JBlockSelection blockSelection) {
        this.blockSelection = blockSelection;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        blockSelection.display.mouseAdapter.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        blockSelection.display.mouseAdapter.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
            Point mousePoint = new Point(e.getX() - block.posx, e.getY() - block.posy);

            for (BlockPolygon bolygon : block.polygons) {
                Polygon polygon = bolygon.getPolygon();

                if (polygon.contains(mousePoint)) {
                    System.out.println("Block FOUND!");
                    // Copy block

                    try {
                        JBlockBase newBlock = (JBlockBase) block.clone();
                        newBlock.xoffset = mousePoint.x;
                        newBlock.yoffset = mousePoint.y;

                        // Set zindex
                        newBlock.changeZIndex(0);

                        blockSelection.display.isClicked = true;
                        blockSelection.display.clickedBlock = newBlock;

                        System.out.println("Block spawn!");
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
