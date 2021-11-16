import hoosh.Shell;

public class Main {

    public static void main(String[] args) {
        Shell hoosh = new Shell();
        while (hoosh.isRunnable()) {
            hoosh.execCommand();
        }
    }
}
