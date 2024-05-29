package projectcj.swing.coding.block;

import javax.swing.*;
import javax.swing.event.*;
import projectcj.swing.coding.Display;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents normal blocks.
 * 
 * Normal block has two points: upper glue point, lower glue point. and Normal blocks are connected
 * to another blocks, up and down.
 */
public class JNormalBlockBase extends JBlockBase {

    // Block connected to upper part.
    // This block object will be JBlockBase object or null.
    JBlockBase upperBlock = null;

    public JNormalBlockBase(Display display) {
        super(display);
    }


    /**
     * Get target glue object(where this object should be glued).
     * 
     * @param pos current pos
     * @return JBlockBase object which refers to glue point
     */
    public JBlockBase getTargetGlueObject(Point pos) {
        return display.getGlueObject(this, pos);
    }

    /**
     * When object is moved by upper blocks, this method moves object properly.
     * 
     * @param pos
     */
    public void movePropagation(Point pos) {
        System.out.printf("ID: %d\n", blockID);
        setLocation(pos);
        posx = pos.x;
        posy = pos.y;

        if (lowerBlock != null) {
            lowerBlock.movePropagation(new Point(pos.x, pos.y + height));
        }
    }

    /**
     * When this block is attached to another block's lower part, this method connects blocks
     * properly.
     * 
     * This works like linked list.
     * 
     * @param anotherBlock upper block
     */
    public void connectTo(JBlockBase anotherBlock) {
        if (anotherBlock.lowerBlock == this)
            return;

        // Moves originaly attached blocks
        if (anotherBlock.lowerBlock != null) {
            JBlockBase lowestBlock = this;

            while (lowestBlock.lowerBlock != null) {
                lowestBlock = lowestBlock.lowerBlock;
            }

            lowestBlock.lowerBlock = anotherBlock.lowerBlock;
            anotherBlock.lowerBlock.upperBlock = lowestBlock;
        }

        this.upperBlock = anotherBlock;
        anotherBlock.lowerBlock = this;
    }

    public void disconnect() {
        if (this.upperBlock != null) {
            upperBlock.lowerBlock = null;
            this.upperBlock = null;
        }
    }

    /**
     * This method handles movement
     * 
     * @param e MouseEvent object from mouseDragged event
     */
    @Override
    public void handleMove(MouseEvent e) {
        Point pos = new Point(posx, posy);

        JBlockBase anotherBlock = getTargetGlueObject(pos);
        // when there is glue point around this block
        if (anotherBlock != null) {
            if (anotherBlock != upperBlock) {
                disconnect();
            }
            connectTo(anotherBlock);
            movePropagation(anotherBlock.getGluePoint());
            return;
        }

        if (upperBlock != null) {
            disconnect();
        }

        movePropagation(pos);

        // repaint();
    }
}
