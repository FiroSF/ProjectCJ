package projectcj.swing.coding.block;

import projectcj.swing.coding.Display;

public class JBlankBlock extends JNormalBlockBase {
    public JBlankBlock(Display display, int x, int y, int w, int h) {
        super(display);
        width = w;
        height = h;
        xs.add(0);
        xs.add(width);
        xs.add(width);
        xs.add(0);
        ys.add(0);
        ys.add(0);
        ys.add(height);
        ys.add(height);


        // Default pos
        posx = x;
        posy = y;
        setSize(width, height);
        setLocation(posx, posy);
    }
}
