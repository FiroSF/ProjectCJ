package projectcj.swing.coding.block;

import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.special.JParameter;
import projectcj.swing.coding.block.special.ParameterGluePoint;
import projectcj.swing.coding.block.variable.JLValue;
import projectcj.swing.coding.block.variable.JRValue;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

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
    // public JBlockBase upperCaller = null;
    public JParameter upperParameter = null;

    // JLValue, JRValue offsets
    public int X_OFFSET = 0;

    public JNormalBlockBase(Display display, Color color) {
        super(display, color);
        TYPE = 1;

        if (this instanceof JLValue) {
            TYPE |= GluePoint.LVALUE_BLOCK_TYPE;
            additionalWidth += 10;
            X_OFFSET += 10;
        }

        if (this instanceof JRValue) {
            TYPE |= GluePoint.RVALUE_BLOCK_TYPE;
            additionalWidth += 10;
            X_OFFSET += 10;
        }

        // Set default gluePoint (bottom of block)
        Point pointOfGluePoint = new Point(0, getCalcedHeight());
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
     * 
     * @return Moved height
     */
    public int movePropagation(Point pos) {
        // System.out.printf("ID: %d\n", blockID);
        setLocation(pos);
        posx = pos.x;
        posy = pos.y;

        if (lowerBlock != null) {
            Point newpos = new Point(pos.x, pos.y + getCalcedHeight());
            return lowerBlock.movePropagation(newpos) + getCalcedHeight();
        }
        return getCalcedHeight();
    }

    /**
     * Disconnects properly.
     * 
     * @param pos
     *            Mouse position
     */
    public void disconnect(Point pos) {
        // If this block has gluepoint object, implementation of this method will be
        // much easier...

        if (this.upperBlock != null) {
            upperBlock.lowerBlock = null;
            this.upperBlock = null;
        }

        int movSize = -movePropagation(pos);

        if (this.upperParameter != null) {
            JParameterBlockBase anotherBlock = this.upperParameter.outerBlock;

            // Resize!
            anotherBlock.changeParameterSize(this.upperParameter.index, -this.getCalcedWidth(),
                    -this.getCalcedHeight());

            // After resize, upper scope's lower blocks should be moved
            JParameterBlockBase now = anotherBlock;
            while (now.upperParameter != null) {
                now = now.upperParameter.outerBlock;
            }
            if (now != anotherBlock)
                now.movePropagation(now.getLocation());

            // Move scope to target block's scope
            this.upperParameter.innerBlock = null;
            this.upperParameter = null;
        } else if (this.upperScope != null) {
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
        int movHeight = movePropagation(targetGluePoint.getPoint());

        // Connects
        if ((targetGluePoint.getType() & GluePoint.NORMAL_LOWER) != 0) {
            // Attatching at lower part
            // In this case, anotherBlock should be instance of JNormalBlockBase
            JNormalBlockBase anotherBlock = (JNormalBlockBase) tmpBlock;

            if (anotherBlock.lowerBlock == this)
                return;

            // Moved from another scope
            if (anotherBlock.upperScope != null && anotherBlock.upperScope != this.upperScope) {
                anotherBlock.upperScope.updateScopeHeight(movHeight);
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
            anotherBlock.updateScopeHeight(movHeight);

        } else if ((targetGluePoint.getType() & GluePoint.PARAMETER) != 0) {
            // Attatching at parameter part
            // In this case, anotherBlock should be instance of JParameterBlockBase
            ParameterGluePoint targetParamGluePoint = (ParameterGluePoint) targetGluePoint;
            JParameter targetParam = targetParamGluePoint.getParentParam();
            JParameterBlockBase anotherBlock = targetParam.outerBlock;

            if (targetParam == this.upperParameter) {
                // If it's in same pos and same upperCaller, return.
                return;
            }

            int movWidth = this.getCalcedWidth();

            // Connect
            // If there is another block already
            if (targetParam.innerBlock != null) {
                JNormalBlockBase tmp = (JNormalBlockBase) targetParam.innerBlock;
                System.out.println(111);

                // Move original block
                Point newP = targetParamGluePoint.getPoint();
                newP.translate(10, 10);
                tmp.disconnect(newP);
            }

            System.out.println(222);
            // Move scope to target block's scope
            targetParam.innerBlock = (JRValue) this;
            this.upperParameter = targetParam;

            // Resize upper scope
            anotherBlock.changeParameterSize(targetParam.index, movWidth, movHeight);

            // After resize, upper scope's lower blocks should be moved
            JParameterBlockBase now = anotherBlock;
            while (now.upperParameter != null) {
                now = now.upperParameter.outerBlock;
            }
            now.movePropagation(now.getLocation());
        }
    }

    // @Override
    // private void updateHeight(int dh) {
    // super.updateHeight(dh);

    // if (upperParameter != null) {
    // upperParameter.outerBlock.updateHeight(dh);
    // }
    // }

    /**
     * Marks JLValue, JRValue.
     */
    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();

        // Make block polygon
        int w = getCalcedWidth(), h = getCalcedHeight();
        int[] xs = { 0, w, w, 0 };
        int[] ys = { 0, 0, h, h };

        if (this instanceof JLValue && this instanceof JRValue) {
            xs = new int[] { 0, w, w, w - 10, w - 10, w, w, 0, 0, 10, 10, 0 };
            ys = new int[] { 0, 0, 10, 10, h - 10, h - 10, h, h, 10, 10, h - 10, h - 10 };

        } else if (this instanceof JLValue) {
            xs = new int[] { 0, w, w, w - 10, w - 10, w, w, 0 };
            ys = new int[] { 0, 0, 10, 10, h - 10, h - 10, h, h };

        } else if (this instanceof JRValue) {
            xs = new int[] { 0, w, w, 0, 0, 10, 10, 0 };
            ys = new int[] { 0, 0, h, h, 10, 10, h - 10, h - 10 };

        }

        v.add(new BlockPolygon(this, xs, ys, color));
        return v;
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

    @Override
    public void changeZIndex(int index) {
        super.changeZIndex(index);

        // Manage lower block's zindex
        JNormalBlockBase now = this;

        if (now.lowerBlock != null) {
            now = now.lowerBlock;
            now.changeZIndex(0);
        }
    }
}
