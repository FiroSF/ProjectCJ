package projectcj.swing.coding.block.scope.function;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.StartBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;

public class JStartBlock extends JScopeBlock {

    public JStartBlock(Display display) {
        super(display);

        // // Default pos
        // posx = 0;
        // posy = 300;
        additionalWidth = 160;
        additionalHeight = 60;
        setSize(DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT + additionalHeight);
        setLocation(posx, posy);

        // texts.addElement(new BlockText(this, "Start", 40, 0, Color.WHITE));
        polygons = makePolygon();

        setInnerText("Start");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new StartBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JStartBlock(display);
    }
}
