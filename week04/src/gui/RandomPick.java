package gui;

import gui.component.RandomPickFrame;
import gui.component.ResultDialog;
import java.awt.*;
import java.util.Random;

public class RandomPick {

    private final RandomPickFrame randomPickFrame;
    private final TextField nameInput = new TextField("코코아 멤버의 이름을 입력하세요.", 50);
    private final Button addBtn = new Button("Add");
    private final TextArea membersView = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
    private final Button resetBtn = new Button("Reset");
    private final Button pickBtn = new Button("Pick");

    public RandomPick() {
        randomPickFrame = new RandomPickFrame();
    }

    public void init() {
        randomPickFrame.add(nameInput);
        nameInput.addActionListener(e -> addMember());

        randomPickFrame.add(addBtn);
        addBtn.addActionListener(e -> addMember());

        membersView.setEditable(false);
        randomPickFrame.add(membersView);

        randomPickFrame.add(resetBtn);
        resetBtn.addActionListener(e -> membersView.setText(""));

        randomPickFrame.add(pickBtn);
        pickBtn.addActionListener(
            e -> new ResultDialog(randomPickFrame.getFrame(), pickRandomMember()).show());

        randomPickFrame.run();
    }

    private String pickRandomMember() {
        String[] members = membersView.getText().split("\n");
        return members[new Random().nextInt(members.length)];
    }

    private void addMember() {
        String name = nameInput.getText();
        if (name.equals("")) return;
        membersView.append(name + "\n");
        nameInput.setText("");
    }

    public static void main(String[] args) {
        new RandomPick().init();
    }
}
