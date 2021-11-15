package hoosh;

import java.io.File;
import java.nio.file.*;

public class Shell {

    public String[] cmds;
    public Path currentPath;
    public boolean isRunning;

    public Shell() {
//        this.currentPath = Paths.get("/Users/parkjunghoo");
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
            case "cd":
                cd(cmds[1]);
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
        File currentFile = new File(currentPath.toString());
        File[] files = currentFile.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("dir : " + file.getName());
            } else {
                System.out.println(file.getName());
            }
        }
    }

    private void cd(String target) {
        File currentFile = new File(currentPath.toString());
        if ("..".equals(target)) {
            String parent = currentFile.getParent();
            System.out.println(parent);
            this.currentPath = Path.of(parent);
        } else {
            File targetFile = new File(currentPath.toString() + "/" + target);

            if (!targetFile.exists()) {
                System.out.println("no such file or directory: " + target);
                return;
            }

            if (targetFile.isFile()) {
                System.out.println("not a directory: " + target);
            }

            if (targetFile.isDirectory()) {
                this.currentPath = Paths.get(currentPath.toString() + "/" + target);
                System.out.println(currentPath);
                return;
            }
        }
    }

    private void quit() {
        isRunning = false;
    }
}