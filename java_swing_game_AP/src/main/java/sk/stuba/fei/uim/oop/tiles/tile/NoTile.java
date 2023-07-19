package sk.stuba.fei.uim.oop.tiles.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.BorderFactory;


public class NoTile extends Tile {
    public NoTile() {
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
    }
}
