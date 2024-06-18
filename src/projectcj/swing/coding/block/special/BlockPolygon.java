package projectcj.swing.coding.block.special;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;

import jdk.incubator.vector.Vector;
import projectcj.swing.coding.block.JBlockBase;

public class BlockPolygon {
    Polygon polygon = null;
    JBlockBase parent = null;
    Color color = Color.BLACK;
    public int xOffset = 0;
    public int yOffset = 0;
    // if dx and dy is negative, ignore this value.
    int dx = 0, dy = 0;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public BlockPolygon(JBlockBase parent, Vector<Integer> x, Vector<Integer> y) {
        this(parent, x.toIntArray(), y.toIntArray());
    }

    public BlockPolygon(JBlockBase parent, Vector<Integer> x, Vector<Integer> y, Color col) {
        this(parent, x.toIntArray(), y.toIntArray(), col);
    }

    public BlockPolygon(JBlockBase parent, int[] x, int[] y) {
        this(parent, new Polygon(x, y, x.length));
    }

    public BlockPolygon(JBlockBase parent, int[] x, int[] y, Color col) {
        this(parent, new Polygon(x, y, x.length), col);
    }

    public BlockPolygon(JBlockBase parent, Polygon poly, Color col) {
        this(parent, poly);
        color = col;
    }

    public BlockPolygon(JBlockBase parent, Polygon poly) {
        polygon = poly;
        this.parent = parent;
    }

    public int stretchHorizontaly(int dx) {
        // diff = delta
        int diff = Math.max(this.dx, 0);

        this.dx += dx;
        diff = Math.max(this.dx, 0) - diff;

        parent.updateSize();
        return diff;
    }

    public int stretchVertically(int dy) {
        int diff = Math.max(this.dy, 0);

        this.dy += dy;
        diff = Math.max(this.dy, 0) - diff;

        parent.updateSize();
        return diff;
    }

    public Polygon getPolygon() {
        Polygon newPolygon = new Polygon();
        Rectangle bounds = polygon.getBounds();

        int currWidth = (int) polygon.getBounds().getWidth();
        int currHeight = (int) polygon.getBounds().getHeight();
        int midWidth = currWidth / 2 + bounds.x, midHeight = currHeight / 2 + bounds.y;

        for (int i = 0; i < polygon.xpoints.length; i++) {
            int x = polygon.xpoints[i], y = polygon.ypoints[i];

            if (x > midWidth)
                x += Math.max(0, dx);
            x += xOffset;

            if (y > midHeight)
                y += Math.max(0, dy);
            y += yOffset;

            newPolygon.addPoint(x, y);
        }

        return newPolygon;
    }

    public void moveDelta(int dx, int dy) {
        xOffset += dx;
        yOffset += dy;
    }
}
