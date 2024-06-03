package projectcj.swing.coding;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.special.BlockPolygon;

import java.awt.*;
import java.awt.event.*;

public class DisplayMouseAdapter extends MouseAdapter {
    Display display;

    public DisplayMouseAdapter(Display display) {
        this.display = display;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        display.mouseX = e.getX();
        display.mouseY = e.getY();
        // System.out.printf("%d %d\n", mouseX, mouseY);
    }

    /**
     * Make move
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
        if (!display.isClicked || display.clickedBlock == null)
            return;

        display.clickedBlock.posx = display.getMouseX() - display.clickedBlock.xoffset;
        display.clickedBlock.posy = display.getMouseY() - display.clickedBlock.yoffset;

        display.clickedBlock.handleMove(e);
    }

    /**
     * Find clicked block
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Clicked...");

        // While iterating all of component's polygons, find appropriate block.
        for (JBlockBase block : display.blocks) {
            Point mousePoint = new Point(e.getX() - block.posx, e.getY() - block.posy);

            for (BlockPolygon bolygon : block.polygons) {
                Polygon polygon = bolygon.getPolygon();

                if (polygon.contains(mousePoint)) {
                    block.xoffset = mousePoint.x;
                    block.yoffset = mousePoint.y;

                    // Set zindex
                    display.blockContainer.setComponentZOrder(block, 0);

                    // Manage lower block's zindex
                    if (block instanceof JNormalBlockBase) {
                        JNormalBlockBase now = (JNormalBlockBase) block;
                        while (now.lowerBlock != null) {
                            now = now.lowerBlock;
                            display.blockContainer.setComponentZOrder(now, 0);
                        }
                    }

                    // Manage inner block's zindex
                    if (block instanceof JScopableBlock) {
                        JNormalBlockBase now = ((JScopableBlock) block).getInnerBlock();
                        while (now != null) {
                            display.blockContainer.setComponentZOrder(now, 0);
                            now = now.lowerBlock;
                        }
                    }

                    display.isClicked = true;
                    display.clickedBlock = block;

                    System.out.println("Block found!");
                    return;
                }
            }
        }
    }

    /**
     * After moving, refresh posx and posy
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!display.isClicked || display.clickedBlock == null)
            return;

        display.isClicked = false;
        display.clickedBlock.posx = display.clickedBlock.getX();
        display.clickedBlock.posy = display.clickedBlock.getY();
    }
}
