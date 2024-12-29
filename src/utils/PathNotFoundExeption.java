package main;

public class PathNotFoundExeption extends RuntimeException{
    public PathNotFoundExeption(String message) {
        super(message);
    }
}
