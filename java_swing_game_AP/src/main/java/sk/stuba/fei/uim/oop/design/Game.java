//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sk.stuba.fei.uim.oop.design;

import java.awt.*;
import javax.swing.*;

import sk.stuba.fei.uim.oop.tiles.Tiles;
import sk.stuba.fei.uim.oop.logic.GameLogic;

public class Game extends JFrame {
    private Tiles currentTiles;
    private GridBagConstraints grid;

    public Game() {
        super("Zadanie 2 - waterpipes _ Adam Perutka 111414");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setSize(800, 800);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setBackground(Color.orange);
        this.setLayout(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.grid.fill = 1;
        GameLogic logic = new GameLogic(this);
        this.addKeyListener(logic);
        GameNav currentMenu = new GameNav(logic);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.weightx = 1.0;
        this.grid.weighty = 0.0;
        this.add(currentMenu, this.grid);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setBoard(Tiles tiles) {
        if (this.currentTiles != null) {
            this.remove(this.currentTiles);
        }

        this.currentTiles = tiles;
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.weightx = 1.0;
        this.grid.weighty = 1.0;
        this.add(tiles, this.grid);
        this.repaint();
    }

    public void repaint() {
        this.revalidate();
        super.repaint();
    }
//

}
