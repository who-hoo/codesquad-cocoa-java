package hoosh;

import hangul.Clock;
import java.io.*;
import java.nio.file.*;

public class Shell {

    private static final Path HOME = Paths.get("/Users/parkjunghoo");

    private String[] cmds;
    private Path currentPath;
    private boolean isRunning;

    public Shell() {
        this.currentPath = Paths.get("").toAbsolutePath();
        this.isRunning = true;
    }

    public boolean isRunnable() {
        return isRunning;
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
            case "mkdir":
                mkdir(cmds[1]);
                break;
            case "hclock":
                // TODO: 스레드를 사용해서 구현
                new Clock().print();
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
            if (file.isDirectory() && !file.isHidden()) {
                System.out.println(file.getName() + "/");
            } else if (file.isFile() && !file.isHidden()) {
                System.out.println(file.getName());
            }
        }
    }

    private void cd(String target) {
        switch (target) {
            case "~":
                this.currentPath = HOME;
                System.out.println(currentPath);
                break;
            case "/":
                this.currentPath = Path.of("/");
                System.out.println(currentPath);
                break;
            case "..":
                this.currentPath = currentPath.getParent();
                System.out.println(currentPath);
                break;
            default:
                Path targetPath;
                if (target.charAt(0) == '/') {
                    targetPath = Path.of(target);
                } else {
                    targetPath = Path.of(currentPath.toString(), target);
                }

                if (".".equals(target)) {
                    break;
                }

                if (!Files.exists(targetPath)) {
                    System.out.println("no such file or directory: " + target);
                    break;
                }

                if (!Files.isDirectory(targetPath)) {
                    System.out.println("not a directory: " + target);
                    break;
                }

                if (Files.isDirectory(targetPath)) {
                    this.currentPath = targetPath;
                    System.out.println(currentPath);
                    break;
                }
        }
    }

    private void mkdir(String dirName) {
        Path newPath = Paths.get(currentPath.toString(), dirName);
        try {
            Files.createDirectory(newPath);
            System.out.println("success");
        } catch (FileAlreadyExistsException e) {
            System.out.println(dirName + " is already exists.");
        } catch (IOException e) {
            System.out.println("fail");
        }
    }

    private void quit() {
        isRunning = false;
    }
}
