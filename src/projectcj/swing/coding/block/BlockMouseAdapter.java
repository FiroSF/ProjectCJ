package projectcj.swing.coding.block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlockMouseAdapter extends MouseAdapter {
    JBlockBase block;

    public BlockMouseAdapter(JBlockBase block) {
        this.block = block;
    }

    /**
     * Adjust offset
     */
    @Override
    public void mousePressed(MouseEvent e) {
        block.xoffset = block.display.getMouseX() - block.posx;
        block.yoffset = block.display.getMouseY() - block.posy;
        // System.out.println("Offset");

        // repaint();
    }

    /**
     * After moving, refresh posx and posy
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        block.posx = block.getX();
        block.posy = block.getY();
    }

    /**
     * Make move
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        block.posx = block.display.getMouseX() - block.xoffset;
        block.posy = block.display.getMouseY() - block.yoffset;
        redispatchToParent(e);

        block.handleMove(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        redispatchToParent(e);
    }

    // https://stackoverflow.com/questions/3818246/passing-events-to-parent
    private void redispatchToParent(MouseEvent e) {
        Component source = (Component) e.getSource();
        MouseEvent parentEvent = SwingUtilities.convertMouseEvent(source, e, source.getParent());
        source.getParent().dispatchEvent(parentEvent);
    }
}
