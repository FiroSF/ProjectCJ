package projectcj.swing.coding.block.special;

import java.awt.Color;
import java.awt.Font;

import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;

public class BlockText {
    String text = "";
    int width;
    JBlockBase parent = null;
    Color color = Color.WHITE;
    Font font = new Font("Pretendard", Font.BOLD, 30);

    int x = 0;
    int y = 0;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public BlockText(JBlockBase parent, String text, int x, int y, Color col) {
        this(parent, text, x, y);
        color = col;
    }

    public BlockText(JBlockBase parent, String text, int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        setText(text);
    }

    public void setText(String text) {
        this.text = text;

        Display.c.getGraphics().setFont(font);
        // https://stackoverflow.com/a/258499
        width = Display.c.getGraphics().getFontMetrics(font).stringWidth(text);
    }

    public String getText() {
        return text;
    }

    public int getWidth() {
        return width;
    }

}
