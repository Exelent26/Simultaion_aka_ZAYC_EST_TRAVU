package main.exeptions;

public class NoFreeCoordinatesException extends RuntimeException {
    public NoFreeCoordinatesException(String message) {
        super(message);
    }
}
