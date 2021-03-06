package gui;

import gui.component.Inventory;
import gui.component.RandomPickFrame;
import gui.component.ResultDialog;
import gui.data.ItemList;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class RandomPick {

    private final RandomPickFrame randomPickFrame;
    private final TextField input = new TextField("", 50);
    private final Button addBtn = new Button("Add");
    private final Inventory inventory;
    private final Button resetBtn = new Button("Reset");
    private final Button pickBtn = new Button("Pick");
    private final Button lunchBtn = new Button("점메추");
    private final Button dinnerBtn = new Button("저메추");
    private final Button memberBtn = new Button("BE 1조");

    public RandomPick() {
        randomPickFrame = new RandomPickFrame();
        inventory = new Inventory("");
    }

    public void init() {
        randomPickFrame.add(input);
        input.addActionListener(e -> addItem());

        randomPickFrame.add(addBtn);
        addBtn.addActionListener(e -> addItem());

        randomPickFrame.add(inventory.getTextArea());

        randomPickFrame.add(resetBtn);
        resetBtn.addActionListener(e -> inventory.clear());

        randomPickFrame.add(pickBtn);
        pickBtn.addActionListener(e -> showResult());

        randomPickFrame.add(lunchBtn);
        lunchBtn.addActionListener(e -> addItemList(ItemList.LUNCH));

        randomPickFrame.add(dinnerBtn);
        dinnerBtn.addActionListener(e -> addItemList(ItemList.DINNER));

        randomPickFrame.add(memberBtn);
        memberBtn.addActionListener(e -> addItemList(ItemList.MEMBER));

        randomPickFrame.run();
    }

    private String pickItem() {
        String[] members = inventory.getItems();
        return members[new Random().nextInt(members.length)];
    }

    private void addItem() {
        String name = input.getText().trim();
        if (name.equals("")) {
            return;
        }
        inventory.add(name);
        input.setText("");
    }

    private void showResult() {
        Frame frame = randomPickFrame.getFrame();
        String result = pickItem();
        new ResultDialog(frame, result).show();
    }

    private void addItemList(List<String> items) {
        inventory.clear();
        items.forEach(inventory::add);
    }

    public static void main(String[] args) {
        new RandomPick().init();
    }
}
