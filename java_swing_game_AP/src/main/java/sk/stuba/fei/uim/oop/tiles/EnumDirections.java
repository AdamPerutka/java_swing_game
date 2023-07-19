package sk.stuba.fei.uim.oop.tiles;

public enum EnumDirections {
    NORTH(0, -1),
    SOUT(0, 1),
    WEST(-1, 0),
    EAST(1, 0);

    private int x;
    private int y;

    private EnumDirections(int x, int y) {
        this.x = x;
        this.y = y;
    }

}