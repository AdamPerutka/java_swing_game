
package sk.stuba.fei.uim.oop.tiles.tile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import javax.swing.JPanel;
import sk.stuba.fei.uim.oop.tiles.EnumDirections;

public abstract class Tile extends JPanel {
    protected boolean playable;
    protected boolean highlight;
    protected boolean validated;
    protected HashMap<EnumDirections, Boolean> connection;
    protected HashMap<EnumDirections, Tile> near;

    public Tile() {
    }

    protected void setConnection() {
        this.connection = new HashMap();
        this.connection.put(EnumDirections.NORTH, false);
        this.connection.put(EnumDirections.WEST, false);
        this.connection.put(EnumDirections.SOUT, false);
        this.connection.put(EnumDirections.EAST, false);
    }

    public void setConnection(Tile tile) {
        Iterator pipe = this.near.keySet().iterator();

        while(pipe.hasNext()) {
            EnumDirections dir = (EnumDirections)pipe.next();
            if (this.near.get(dir).equals(tile)) {
                this.connection.put(dir, true);
            }
        }

    }

    public boolean pipeRotation(Tile prevTile) {
        Iterator pipe = this.near.keySet().iterator();

        EnumDirections dir;
        do {
            if (!pipe.hasNext()) {
                return false;
            }

            dir = (EnumDirections)pipe.next();
        } while(!this.near.get(dir).equals(prevTile));

        return this.connection.get(dir);
    }

    public void addNear(EnumDirections dir, Tile tile) {
        this.near.put(dir, tile);
    }

    public Tile getConnectedNeighbour(Stack<Tile> checkedTiles) {
        Tile outTile = null;
        Iterator pipe = this.connection.keySet().iterator();

        while(pipe.hasNext()) {
            EnumDirections dir = (EnumDirections)pipe.next();
            if (this.connection.get(dir)) {
                if (checkedTiles.isEmpty()) {
                    outTile = this.near.get(dir);
                } else if (!checkedTiles.contains(this.near.get(dir))) {
                    outTile = this.near.get(dir);
                }
            }
        }

        return outTile;
    }

    public ArrayList<Tile> getEmptyTiles(Stack<Tile> visitedTiles) {
        ArrayList<Tile> neighboursList = new ArrayList(this.near.values());
        ArrayList<Tile> emptytilesList = new ArrayList();
        Iterator pipe = neighboursList.iterator();

        while(pipe.hasNext()) {
            Tile tile = (Tile)pipe.next();
            if (!visitedTiles.contains(tile)) {
                emptytilesList.add(tile);
            }
        }

        return emptytilesList;
    }

    public void rotateClockwise(int amount) {
        for(int i = 0; i < amount; ++i) {
            HashMap<EnumDirections, Boolean> connectionOld = new HashMap(this.connection);
            this.connection.put(EnumDirections.NORTH, connectionOld.get(EnumDirections.WEST));
            this.connection.put(EnumDirections.WEST, connectionOld.get(EnumDirections.SOUT));
            this.connection.put(EnumDirections.SOUT, connectionOld.get(EnumDirections.EAST));
            this.connection.put(EnumDirections.EAST, connectionOld.get(EnumDirections.NORTH));
        }

    }

    public void rotateCounterClockwise(int amount) {
        for(int i = 0; i < amount; ++i) {
            HashMap<EnumDirections, Boolean> connectionOld = new HashMap(this.connection);
            this.connection.put(EnumDirections.NORTH, connectionOld.get(EnumDirections.EAST));
            this.connection.put(EnumDirections.WEST, connectionOld.get(EnumDirections.NORTH));
            this.connection.put(EnumDirections.SOUT, connectionOld.get(EnumDirections.WEST));
            this.connection.put(EnumDirections.EAST, connectionOld.get(EnumDirections.SOUT));
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        Dimension dim = this.getSize();
        if (this.highlight) {
            g2D.setColor(Color.pink);
            g2D.setStroke(new BasicStroke(200));
            g2D.drawRect(0, 0, dim.width, dim.height);
            this.highlight = false;
        }

    }

    protected void paintPipe(Graphics2D g2D, Dimension dim) {
        BasicStroke thick = new BasicStroke((dim.width)*0.2f);
        float middleX = (float)dim.width * 0.5f;
        float middleY = (float)dim.height * 0.5f;
        g2D.setColor(Color.darkGray);
        g2D.setStroke(thick);
        if (this.connection.get(EnumDirections.NORTH)) {
            g2D.draw(new Line2D.Float(middleX, 0, middleX, middleY));
        }
        if (this.connection.get(EnumDirections.EAST)) {
            g2D.draw(new Line2D.Float((float)dim.width, middleY, middleX, middleY));
        }
        if (this.connection.get(EnumDirections.SOUT)) {
            g2D.draw(new Line2D.Float(middleX, (float)dim.height, middleX, middleY));
        }
        if (this.connection.get(EnumDirections.WEST)) {
            g2D.draw(new Line2D.Float(0, middleY, middleX, middleY));
        }
    }
    protected void paintCorrectPipes(Graphics2D g2D, Dimension dim) {
        BasicStroke thick = new BasicStroke((dim.width)*0.2f);
        float middleX = (float)dim.width * 0.5f;
        float middleY = (float)dim.height * 0.5f;
        g2D.setColor(Color.green);
        g2D.setStroke(thick);
        if (this.connection.get(EnumDirections.NORTH)) {
            g2D.draw(new Line2D.Float(middleX, 0, middleX, middleY));
        }
        if (this.connection.get(EnumDirections.EAST)) {
            g2D.draw(new Line2D.Float((float)dim.width, middleY, middleX, middleY));
        }
        if (this.connection.get(EnumDirections.SOUT)) {
            g2D.draw(new Line2D.Float(middleX, (float)dim.height, middleX, middleY));
        }
        if (this.connection.get(EnumDirections.WEST)) {
            g2D.draw(new Line2D.Float(0, middleY, middleX, middleY));
        }
    }



    protected void paintPipeEnd(Graphics2D g2D, Dimension dim) {
        g2D.setColor(Color.BLACK);
        g2D.fillRect((int) (dim.width*0.2), (int) (dim.width*0.1), (int) (dim.width*0.7), (int) (dim.width*0.8));
    }

    public boolean isPlayable() {
        return this.playable;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }


    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public HashMap<EnumDirections, Boolean> getConnection() {
        return this.connection;
    }

}
