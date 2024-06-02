package projectcj.swing.coding.block.special;

import java.awt.Color;
import java.awt.Font;

import projectcj.swing.coding.block.JBlockBase;

public class BlockText {
    String text = "";
    JBlockBase parent = null;
    Color color = Color.BLACK;
    Font font = new Font("Pretendard", Font.BOLD, 30);

    int x = 0;
    int y = 0;

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
        this.text = text;
        this.parent = parent;
    }

    public String getText() {
        return text;
    }
}
