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
        f.setLayout(new FlowLayout());

        TextField nameInput = new TextField("코코아 멤버의 이름을 입력하세요.", 50);
        f.add(nameInput);

        Button addBtn = new Button("Add");
        f.add(addBtn);

        TextArea membersView = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
        membersView.setEditable(false);
        f.add(membersView);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new RandomPick().init();
    }
}
