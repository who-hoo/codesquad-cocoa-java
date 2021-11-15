package hoosh;

import java.io.File;
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
            case "pwd":
                pwd();
                break;
            case "ls":
                ls();
                break;
            case "q":
                quit();
                break;
            default:
                System.out.println("존재하지 않는 명령");
                execCommand();
                break;
        }
    }

    private void pwd() {
        System.out.println(currentPath);
    }

    private void ls() {
        File f = new File(currentPath.toString());
        String[] files = f.list();
        if (files == null) {
            return;
        }
        for (String file : files) {
            System.out.println(file);
        }
    }

    private void quit() {
        isRunning = false;
    }
}
