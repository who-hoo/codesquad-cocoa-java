package hoosh;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {

    public String[] cmds;
    public Path currentPath;
    public boolean isRunning;

    public Shell() {
        this.currentPath = Paths.get("").toAbsolutePath();
        this.isRunning = true;
    }

    private String getCommand() {
        String command = Input.getString("hoosh > ");
        cmds = command.split(" ");
        return cmds[0];
    }

    public void execCommand() {
        switch (getCommand()) {
            case "q":
                quit();
                break;
            default:
                System.out.println("존재하지 않는 명령");
                execCommand();
                break;
        }
    }

    private void quit() {
        isRunning = false;
    }
}
