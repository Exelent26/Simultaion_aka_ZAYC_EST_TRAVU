package main.utils.InputDialog;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractDialog<T> implements Dialog<T> {

    protected final String title;
    protected final String error;
    protected final Function<String, T> mapper;
    protected final Predicate<T> validator;
    protected final Scanner scanner = new Scanner(System.in);

    public AbstractDialog(String title, String error, Function<String, T> mapper, Predicate<T> validator) {
        this.title = title;
        this.error = error;
        this.mapper = mapper;
        this.validator = validator;
    }

    protected void showTitle() {
        System.out.println(title);
    }

    protected void showError() {
        System.out.println(error);
    }

    @Override
    public T input() {

        while (true) {
            showTitle();
            String input = scanner.nextLine();
            try {
                T result = mapper.apply(input);
                if (validator.test(result)) {
                    return result;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(error);
            }
            showError();
        }
    }
}