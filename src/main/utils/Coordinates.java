package main.utils;

public class Coordinates {
    public final int lines;
    public final int columns;

    public Coordinates(int x, int y) {
        this.lines = x;
        this.columns = y;
    }

    public Coordinates shift(int dx, int dy) {
        return new Coordinates(lines + dx, columns + dy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates that = (Coordinates) obj;
        return lines == that.lines && columns == that.columns;
    }

    @Override
    public int hashCode() {
        return 31 * lines + columns;
    }

    @Override
    public String toString() {
        return "(" + lines + ", " + columns + ")";
    }
}