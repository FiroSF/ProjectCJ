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

    public void stretchHorizontaly(int dx) {
        this.dx += dx;
        parent.updateSize();
        // Polygon newPolygon = new Polygon();
        // int currWidth = (int) polygon.getBounds().getWidth();
        // int midPoint = currWidth / 2;

        // for (int i = 0; i < polygon.xpoints.length; i++) {
        // if (polygon.xpoints[i] > midPoint) {
        // polygon.xpoints[i] += dx;
        // System.out.println(polygon.xpoints[i]);
        // }

        // newPolygon.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
        // }

        // polygon = newPolygon;
        // parent.updateSize();
    }

    public void stretchVertically(int dy) {
        this.dy += dy;
        parent.updateSize();
        // Polygon newPolygon = new Polygon();
        // Rectangle bounds = polygon.getBounds();
        // int currHeight = (int) bounds.getHeight();
        // int midPoint = currHeight / 2 + bounds.y;

        // System.out.println(midPoint);
        // System.out.println(bounds.y);
        // for (int i = 0; i < polygon.ypoints.length; i++) {
        // System.out.printf("%d ", polygon.ypoints[i]);
        // if (polygon.ypoints[i] > midPoint) {
        // polygon.ypoints[i] += dy;
        // }

        // newPolygon.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
        // }
        // System.out.println();

        // polygon = newPolygon;
        // parent.updateSize();
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

            if (y > midHeight)
                y += Math.max(0, dy);

            newPolygon.addPoint(x, y);
        }

        return newPolygon;
    }
}
