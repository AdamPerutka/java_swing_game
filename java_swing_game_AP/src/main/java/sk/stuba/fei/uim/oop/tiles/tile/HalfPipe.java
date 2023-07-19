package sk.stuba.fei.uim.oop.tiles.tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Random;
import javax.swing.BorderFactory;
import sk.stuba.fei.uim.oop.tiles.EnumDirections;

public class HalfPipe extends Tile {
    public HalfPipe(HashMap<EnumDirections, Boolean> connector, Random randomGenerator) {
        this.validated = false;
        this.playable = true;
        this.highlight = false;
        this.connection = connector;
        this.rotateClockwise(randomGenerator.nextInt(4));
        this.near = new HashMap();
        this.setBackground(Color.orange);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Dimension dim = this.getSize();
        this.paintPipe(g2D, dim);
        if(this.validated)
        {
            this.paintCorrectPipes(g2D, dim);
        }


    }
}
