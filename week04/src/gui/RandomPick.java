package gui;

import java.awt.*;

public class RandomPick {

    public void init() {
        Frame f = new Frame("Random Pick");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        f.setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 100);
        f.setSize(500, 500);
        f.setResizable(false);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new RandomPick().init();
    }
}
