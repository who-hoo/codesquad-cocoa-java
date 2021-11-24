package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class RandomPick {

    private final Frame f = new Frame("Random Pick");
    private final TextField nameInput = new TextField("코코아 멤버의 이름을 입력하세요.", 50);
    private final Button addBtn = new Button("Add");
    private final TextArea membersView = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
    Button pickBtn = new Button("Pick");

    public void init() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        f.setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 100);
        f.setSize(500, 300);
        f.setResizable(false);
        f.setLayout(new FlowLayout());
        f.addWindowListener(new EventHandler());

        f.add(nameInput);
        nameInput.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String name = nameInput.getText();
                    membersView.append(name + "\n");
                    nameInput.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        f.add(addBtn);
        addBtn.addActionListener(e -> {
            String name = nameInput.getText();
            membersView.append(name + "\n");
            nameInput.setText("");
        });

        membersView.setEditable(false);
        f.add(membersView);

        f.add(pickBtn);
        pickBtn.addActionListener(e -> {
            String[] members = membersView.getText().split("\n");
            String pickedMember = members[new Random().nextInt(members.length)];
            Dialog result = new Dialog(f, "Result", true);
            result.setSize(140, 90);
            result.setLocation(50, 50);
            result.setLayout(new FlowLayout());
            Label msg = new Label("랜덤 뽑기 결과 : " + pickedMember, Label.CENTER);
            Button ok = new Button("OK");
            result.add(msg);
            result.add(ok);

            ok.addActionListener(okEvent -> {
                result.setVisible(false);
                result.dispose();
            });

            result.setVisible(true);
        });

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new RandomPick().init();
    }
}
