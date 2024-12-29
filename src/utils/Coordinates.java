package main;

import java.util.Objects;


public class Coordinates {
    public final Integer POSITION_OF_HEIGHT;
    public final Integer POSITION_OF_WIDE;

    public Coordinates(Integer heightPosition, Integer widthPosition) {
        this.POSITION_OF_HEIGHT = heightPosition;
        this.POSITION_OF_WIDE = widthPosition;
    }

    public Integer getPOSITION_OF_HEIGHT() {
        return POSITION_OF_HEIGHT;
    }

    public Integer getPOSITION_OF_WIDE() {
        return POSITION_OF_WIDE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(POSITION_OF_HEIGHT, that.POSITION_OF_HEIGHT) && Objects.equals(POSITION_OF_WIDE, that.POSITION_OF_WIDE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(POSITION_OF_HEIGHT, POSITION_OF_WIDE);
    }
}