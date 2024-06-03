package projectcj.swing.coding.block;

import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.special.GluePoint;

import java.awt.*;
import java.awt.event.*;

/**
 * This class represents normal blocks.
 * 
 * Normal block has two points: upper glue point, lower glue point. and Normal
 * blocks are connected
 * to another blocks, up and down.
 */
public abstract class JNormalBlockBase extends JBlockBase {
    // Block connected to lower part.
    // This block object will be JNormalBlockBase object or null.
    public JNormalBlockBase lowerBlock = null;

    // Block connected to upper part.
    // This block object will be JNormalBlockBase object or null.
    public JNormalBlockBase upperBlock = null;

    // When block is in another block's parameter, this represents that block
    public JBlockBase upperCaller = null;

    public JNormalBlockBase(Display display) {
        super(display);
        TYPE = 1;

        // Set default gluePoint (bottom of block)
        Point pointOfGluePoint = new Point(0, DEFAULT_HEIGHT + additionalHeight);
        GluePoint gluePoint = new GluePoint(this, pointOfGluePoint, GluePoint.NORMAL_LOWER_CHECK);
        gluePoints.addElement(gluePoint);

    }

    /**
     * Get target glue object(where this object should be glued).
     * 
     * @param pos
     *            current pos
     * @return GluePoint object which refers to glue point
     */
    public GluePoint getTargetGlueObject(Point pos) {
        return display.getGlueObject(this, pos);
    }

    /**
     * When object is moved by upper blocks, this method moves object properly.
     * 
     * @param pos
     */
    public int movePropagation(Point pos) {
        // System.out.printf("ID: %d\n", blockID);
        setLocation(pos);
        posx = pos.x;
        posy = pos.y;

        if (lowerBlock != null) {
            Point newpos = new Point(pos.x, pos.y + getHeight());
            return lowerBlock.movePropagation(newpos) + getHeight();
        }
        return getHeight();
    }

    /**
     * Disconnects properly.
     * 
     * @param pos
     *            Mouse position
     */
    public void disconnect(Point pos) {
        if (this.upperBlock != null) {
            upperBlock.lowerBlock = null;
            this.upperBlock = null;
        }

        int movSize = -movePropagation(pos);

        if (this.upperScope != null) {
            if (this.upperScope.getInnerBlock() == this) {
                upperScope.setInnerBlock(null);
            }

            this.upperScope.updateScopeHeight(movSize);

            // Change lower block's scope
            JNormalBlockBase now = this;
            while (now != null) {
                now.upperScope = null;
                now = now.lowerBlock;
            }
        }
    }

    /**
     * When this block is attached to another block, this method connects blocks
     * properly.
     * 
     * This works like linked list.
     * 
     * @param targetGluePoint
     *            Target's glue point object
     * 
     */
    public void connectTo(GluePoint targetGluePoint) {
        JBlockBase tmpBlock = targetGluePoint.getParent();

        // Move first
        int movSize = movePropagation(targetGluePoint.getPoint());

        // Connects
        if ((targetGluePoint.getType() & GluePoint.NORMAL_LOWER) != 0) {
            // Attatching at lower part
            // In this case, anotherBlock should be instance of JNormalBlockBase
            JNormalBlockBase anotherBlock = (JNormalBlockBase) tmpBlock;

            if (anotherBlock.lowerBlock == this)
                return;

            // Moved from another scope
            if (anotherBlock.upperScope != null && anotherBlock.upperScope != this.upperScope) {
                anotherBlock.upperScope.updateScopeHeight(movSize);
            }

            // Connect
            // Moves originaly attached blocks
            // Move scope to upperBlock's scope
            this.upperScope = anotherBlock.upperScope;
            JNormalBlockBase lowestBlock = this;

            while (lowestBlock.lowerBlock != null) {
                lowestBlock = lowestBlock.lowerBlock;
                lowestBlock.upperScope = anotherBlock.upperScope;
            }

            // Change scope of trailing blocks
            if (anotherBlock.lowerBlock != null) {
                lowestBlock.lowerBlock = anotherBlock.lowerBlock;
                anotherBlock.lowerBlock.upperBlock = lowestBlock;
            }

            this.upperBlock = (JNormalBlockBase) anotherBlock;
            anotherBlock.lowerBlock = this;

        } else if ((targetGluePoint.getType() & GluePoint.SCOPE_INNER) != 0) {
            // Attatching at inner scope
            // In this case, anotherBlock should be instance of JScopableBlock
            JScopableBlock anotherBlock = (JScopableBlock) tmpBlock;
            if (anotherBlock == this.upperScope && anotherBlock.getInnerBlock() == this)
                return;

            // Connect
            // Move scope to target block's scope
            this.upperScope = anotherBlock;
            JNormalBlockBase lowestBlock = this;

            // Change lower block's scopeBlock
            while (lowestBlock.lowerBlock != null) {
                lowestBlock = lowestBlock.lowerBlock;
                lowestBlock.upperScope = anotherBlock;
            }

            // Add additional lower block
            JNormalBlockBase formerInnerBlock = anotherBlock.getInnerBlock();
            lowestBlock.lowerBlock = formerInnerBlock;
            if (formerInnerBlock != null)
                formerInnerBlock.upperBlock = lowestBlock;

            // Update scopeBlock's inner block
            anotherBlock.setInnerBlock(this);

            // Resize upper scope
            anotherBlock.updateScopeHeight(movSize);

        } else if ((targetGluePoint.getType() & GluePoint.PARAMETER) != 0) {
            // Attatching at parameter part
            // TODO parameter attach implement

        }
    }

    @Override
    public void updateHeight(int dh) {
        super.updateHeight(dh);

        if (upperCaller != null) {
            upperCaller.updateHeight(dh);
        }
    }

    /**
     * This method handles movement
     * 
     * @param e
     *            MouseEvent object from mouseDragged event
     */
    @Override
    public void handleMove(MouseEvent e) {
        Point pos = new Point(posx, posy);

        GluePoint targetGluePoint = getTargetGlueObject(pos);
        // When there is glue point around this block
        if (targetGluePoint != null) {
            connectTo(targetGluePoint);
            return;
        }

        // No glue point
        disconnect(pos);

        // repaint();
    }
}
