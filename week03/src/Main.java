import hoosh.Shell;

public class Main {

    public static void main(String[] args) {
        Shell hoosh = new Shell();
        while (hoosh.isRunnable()) {
            try {
                hoosh.execCommand();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
