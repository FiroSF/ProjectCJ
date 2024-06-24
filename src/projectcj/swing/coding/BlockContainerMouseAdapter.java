package projectcj.swing.coding;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.special.BlockPolygon;

import java.awt.*;
import java.awt.event.*;

public class BlockContainerMouseAdapter extends MouseAdapter {
    Display display;

    public BlockContainerMouseAdapter(Display display) {
        this.display = display;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        display.mouseX = e.getX();// - display.posx;
        display.mouseY = e.getY();// - display.posy;
        // System.out.printf("%d %d\n", mouseX, mouseY);
    }

    /**
     * Make move
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
        if (!display.isClicked)
            return;

        if (display.clickedBlock == null) {
            // Move screen
            display.posx += display.getMouseX() - display.xoffset;
            display.posy += display.getMouseY() - display.yoffset;

            display.blockContainer.setLocation(display.posx, display.posy);
            display.overlayContainer.setLocation(display.posx, display.posy);
            display.bg.setLocation(display.posx, display.posy);

        } else {
            // Move block

            display.clickedBlock.posx = display.getMouseX() - display.clickedBlock.xoffset;
            display.clickedBlock.posy = display.getMouseY() - display.clickedBlock.yoffset;

            display.clickedBlock.handleMove(e);

        }
    }

    /**
     * Find clicked block
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Clicked...");
        System.out.printf("%d, %d\n", display.getMouseX(), display.getMouseY());

        display.isClicked = true;
        display.xoffset = display.getMouseX();
        display.yoffset = display.getMouseY();

        // While iterating all of component's polygons, find appropriate block.
        for (Component obj : display.blockContainer.getComponents()) {
            if (!(obj instanceof JBlockBase))
                continue;
            JBlockBase block = (JBlockBase) obj;

            Point mousePoint =
                    new Point(display.getMouseX() - block.posx, display.getMouseY() - block.posy);
            // System.out.printf("%d, %d\n", mousePoint.x, mousePoint.y);

            for (BlockPolygon bolygon : block.polygons) {
                Polygon polygon = bolygon.getPolygon();

                if (polygon.contains(mousePoint)) {
                    block.xoffset = mousePoint.x;
                    block.yoffset = mousePoint.y;

                    // Set zindex
                    block.changeZIndex(0);

                    display.clickedBlock = block;

                    System.out.println("Block found!");
                    return;
                }
            }
        }

        // No blocks
        display.clickedBlock = null;
    }

    /**
     * After moving, refresh posx and posy
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!display.isClicked || display.clickedBlock == null)
            return;

        display.isClicked = false;
        // display.clickedBlock.posx = display.clickedBlock.getX();
        // display.clickedBlock.posy = display.clickedBlock.getY();

        display.clickedBlock.releaseMouse();
    }
}
