package sk.stuba.fei.uim.oop.tiles.tile;

import java.awt.*;
import java.util.HashMap;
import javax.swing.BorderFactory;

public class StartEndPipe extends Tile {
    public StartEndPipe() {
        this.validated = false;
        this.playable = false;
        this.highlight = false;
        this.setConnection();
        this.near = new HashMap();
        this.setBackground(Color.ORANGE);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Dimension dim = this.getSize();
        this.paintPipeEnd(g2D, dim);
        this.paintPipe(g2D, dim);
    }
}
