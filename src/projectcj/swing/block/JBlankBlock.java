package projectcj.swing.block;

import projectcj.swing.Display;

public class JBlankBlock extends JBlockBase {
    public JBlankBlock(Display display) {
        super(display);
        width = 100;
        height = 100;
        xs.add(0);
        xs.add(100);
        xs.add(100);
        xs.add(0);
        ys.add(0);
        ys.add(0);
        ys.add(100);
        ys.add(100);


        // Default pos
        posx = 100;
        posy = 100;
        setSize(100, 100);
        setLocation(posx, posy);
    }
}
