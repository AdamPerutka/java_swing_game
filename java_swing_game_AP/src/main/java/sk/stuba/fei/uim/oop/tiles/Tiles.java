
package sk.stuba.fei.uim.oop.tiles;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import sk.stuba.fei.uim.oop.tiles.tile.NoTile;
import sk.stuba.fei.uim.oop.tiles.tile.StartEndPipe;
import sk.stuba.fei.uim.oop.tiles.tile.HalfPipe;
import sk.stuba.fei.uim.oop.tiles.tile.Tile;

public class Tiles extends JPanel {
    private Random randomGenerator;
    private Tile[][] board;
    private Tile startTile;
    private Tile endTile;

    public Tiles(int size) {
        this.setLayout(new GridLayout(size, size));
        this.randomGenerator = new Random();
        this.initializeBoard(size);
        this.generateStartEndTile(size);
        this.setNearPipes(size);
        this.setPipes(size, this.generatePath());
        this.setNearPipes(size);
        this.setBoard(size);
        this.setBackground(Color.GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    }

    private void initializeBoard(int size) {
        this.board = new Tile[size][size];
        for(int y = 0; y < size; ++y) {
            for(int x = 0; x < size; ++x) {
                this.board[x][y] = new NoTile();
            }
        }

    }

    private void generateStartEndTile(int size) {
        ArrayList<EnumDirections> edges = new ArrayList();
        edges.add(EnumDirections.NORTH);
        edges.add(EnumDirections.EAST);
        edges.add(EnumDirections.SOUT);
        edges.add(EnumDirections.WEST);
        Collections.shuffle(edges);
        StartEndPipe startendPipe = new StartEndPipe();
        switch (edges.get(0)) {
            case NORTH:
                this.endTile = this.board[this.randomGenerator.nextInt(size)][size - 1] = new StartEndPipe();
                this.startTile = this.board[this.randomGenerator.nextInt(size)][0] = startendPipe;
                break;
            case EAST:
                this.startTile = this.board[size - 1][this.randomGenerator.nextInt(size)] = startendPipe;
                this.endTile = this.board[0][this.randomGenerator.nextInt(size)] = new StartEndPipe();
                break;
            case SOUT:
                this.startTile = this.board[this.randomGenerator.nextInt(size)][size - 1] = startendPipe;
                this.endTile = this.board[this.randomGenerator.nextInt(size)][0] = new StartEndPipe();
                break;
            case WEST:
                this.startTile = this.board[0][this.randomGenerator.nextInt(size)] = startendPipe;
                this.endTile = this.board[size - 1][this.randomGenerator.nextInt(size)] = new StartEndPipe();
        }

    }

    private Stack<Tile> generatePath() {
        Stack<Tile> path = new Stack();
        path.add(this.startTile);
        Stack<Tile> visitedTiles = new Stack();
        visitedTiles.add(this.startTile);
        Tile currentTile = this.startTile;

        while(true) {
            while(currentTile != this.endTile) {
                ArrayList<Tile> noNear = currentTile.getEmptyTiles(visitedTiles);
                if (!noNear.isEmpty()) {
                    Collections.shuffle(noNear);
                    Tile nextTile = noNear.get(0);
                    path.add(nextTile);
                    visitedTiles.add(nextTile);
                    currentTile = nextTile;
                } else {
                    for(int i = path.size() - 1; i > 0; i--) {
                        noNear = path.get(i).getEmptyTiles(visitedTiles);
                        if (!noNear.isEmpty()) {
                            currentTile = path.get(i);
                            break;
                        }

                        path.remove(i);
                    }
                }
            }

            return path;
        }
    }

    private void setPipes(int size, Stack<Tile> path) {
        int y;
        for(y = 1; y < path.size(); y++) {
            path.get(y - 1).setConnection(path.get(y));
            path.get(y).setConnection(path.get(y - 1));
        }

        for(y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if (path.contains(this.board[x][y]) && this.board[x][y] instanceof NoTile) {
                    this.board[x][y] = new HalfPipe(this.board[x][y].getConnection(), this.randomGenerator);
                }
            }
        }

    }

    private void setBoard(int size) {
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                this.add(this.board[x][y]);
            }
        }

    }

    private void setNearPipes(int size) {
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                if (x != 0) {
                    this.board[x][y].addNear(EnumDirections.WEST, this.board[x - 1][y]);
                }

                if (x != size - 1) {
                    this.board[x][y].addNear(EnumDirections.EAST, this.board[x + 1][y]);
                }

                if (y != 0) {
                    this.board[x][y].addNear(EnumDirections.NORTH, this.board[x][y - 1]);
                }

                if (y != size - 1) {
                    this.board[x][y].addNear(EnumDirections.SOUT, this.board[x][y + 1]);
                }
            }
        }

    }

    public boolean checkWin() {
        Stack<Tile> checkedTiles = new Stack();
        Tile currentTile = this.startTile;

        while(currentTile != null) {
            Tile nextTile = currentTile.getConnectedNeighbour(checkedTiles);
            if (nextTile != null && !(nextTile instanceof NoTile)) {
                if (nextTile.pipeRotation(currentTile)) {
                    currentTile.setValidated(true);
                    checkedTiles.add(currentTile);
                    currentTile = nextTile;
                    continue;
                }

                currentTile.setValidated(true);
                checkedTiles.add(currentTile);
                break;
            }

            currentTile.setValidated(true);
            checkedTiles.add(currentTile);
            break;
        }

        return checkedTiles.contains(this.endTile);
    }

}
