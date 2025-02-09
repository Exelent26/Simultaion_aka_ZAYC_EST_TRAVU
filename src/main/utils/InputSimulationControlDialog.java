package main.utils;
import java.util.List;
import java.util.Objects;

public class InputSimulationControlDialog extends AbstractDialog<Integer>{

    public InputSimulationControlDialog(String title, String error, List<Integer> keys){
        super(title,
                error,
                Integer::valueOf,
                s -> getValidator(s,keys)
        );
    }

    private static boolean getValidator(Integer s, List<Integer> keys) {
        for (Integer key : keys) {
            if (Objects.equals(key, s)) {
                return true;
            }
        }
        return false;
    }
}
