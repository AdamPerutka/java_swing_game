//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sk.stuba.fei.uim.oop.logic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import sk.stuba.fei.uim.oop.tiles.Tiles;
import sk.stuba.fei.uim.oop.tiles.tile.Tile;
import sk.stuba.fei.uim.oop.design.Game;
import sk.stuba.fei.uim.oop.design.GameWin;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private Game gameFrame;
    private JLabel sizeLabel;
    private int size = INITIAL_BOARD_SIZE;
    private Tiles currentTiles;
    private JLabel levelLabel;
    private int currentLevel;

    public GameLogic(Game game) {
        this.gameFrame = game;
        this.initializeBoard(this.size);
        this.gameFrame.setBoard(this.currentTiles);
        this.sizeLabel = new JLabel();
        this.updateBoardSizeLabel();
        this.currentLevel = 1;
        this.levelLabel = new JLabel();
        this.updateCurrentLevelLabel();
    }
    private void initializeBoard(int boardSize) {
        this.currentTiles = new Tiles(boardSize);
        this.currentTiles.addMouseMotionListener(this);
        this.currentTiles.addMouseListener(this);
    }
    private void updateBoardSizeLabel() {
        this.sizeLabel.setText("Size: " + this.size + "x" + this.size);
    }
    private void updateCurrentLevelLabel() {
        this.levelLabel.setText("Current level: " + this.currentLevel);
    }
    private void gameReset() {
        this.initializeBoard(this.size);
        this.currentLevel = 1;
        this.updateCurrentLevelLabel();
        this.gameFrame.setBoard(this.currentTiles);
        this.gameFrame.repaint();
    }
    private void gameCheckWin() {
        if (this.currentTiles.checkWin()) {
            this.addLevel();
            this.initializeBoard(this.size);
            this.gameFrame.setBoard(this.currentTiles);
        }
        if(currentLevel >= 3)
        {
            new GameWin();
        }

        this.gameFrame.repaint();
    }
    private void addLevel() {
        this.currentLevel += 1;
        this.updateCurrentLevelLabel();
    }
    public void keyPressed(KeyEvent e) {
        gameFrame.requestFocusInWindow();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.gameCheckWin();
                break;
            case KeyEvent.VK_R:
                this.gameReset();
                break;
            case KeyEvent.VK_ESCAPE:
                this.gameFrame.dispose();
                break;
            default:
                System.out.println("Unknown key! " + e.getKeyChar() + " is not defined.");

        }

    }
    public void actionPerformed(ActionEvent e) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();

        switch (e.getActionCommand()) {
            case "VALIDATE":
                this.gameCheckWin();
                break;
            case "RESET":
                this.gameReset();
                break;
            default:
                System.out.println("Unknown button ! " + e.getActionCommand());
                break;

        }
    }
    public void mousePressed(MouseEvent e)
    {
        Component current = this.currentTiles.getComponentAt(e.getX(), e.getY());
        if(current instanceof Tile)
        {
            if (((Tile)current).isPlayable()) {
                ((Tile) current).setHighlight(true);
                switch (e.getButton()) {
                    case 1: {
                        ((Tile) current).rotateCounterClockwise(1);
                        this.currentTiles.repaint();
                        break;
                    }
                    case 3: {
                        ((Tile) current).rotateClockwise(1);
                        this.currentTiles.repaint();
                        break;
                    }
                }
            }
        }
    }

    public void mouseExited(MouseEvent e) {
        this.currentTiles.repaint();

    }
    public void mouseMoved(MouseEvent e) {
        Component current = this.currentTiles.getComponentAt(e.getX(), e.getY());
        if (current instanceof Tile) {
            ((Tile)current).setHighlight(true);
            this.currentTiles.repaint();
        }
    }
    public void stateChanged(ChangeEvent e) {
        JSlider check = (JSlider)e.getSource();
        if (!check.getValueIsAdjusting()) {
            this.size = check.getValue();
            this.updateBoardSizeLabel();
            this.gameReset();
        }
    }



    public JLabel getSizeLabel() {
        return this.sizeLabel;}

    public JLabel getLevelLabel() {
        return this.levelLabel;
    }
}
