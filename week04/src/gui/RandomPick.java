package gui;

import java.awt.*;

public class RandomPick {

    private final Frame f = new Frame("Random Pick");
    private final TextField nameInput = new TextField("코코아 멤버의 이름을 입력하세요.", 50);
    private final Button addBtn = new Button("Add");
    private final TextArea membersView = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);

    public void init() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        f.setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 100);
        f.setSize(500, 500);
        f.setResizable(false);
        f.setLayout(new FlowLayout());
        f.addWindowListener(new EventHandler());

        f.add(nameInput);

        f.add(addBtn);

        membersView.setEditable(false);
        f.add(membersView);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new RandomPick().init();
    }
}
