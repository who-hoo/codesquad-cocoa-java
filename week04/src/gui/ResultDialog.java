package gui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.LayoutManager;

public class ResultDialog {

    private static final String TITLE = "Random Pick Result";
    private static final boolean IS_MODAL = true;
    private static final int WIDTH = 140;
    private static final int HEIGHT = 90;
    private static final int LOCATION_X = 50;
    private static final int LOCATION_Y = 50;
    private static final LayoutManager LAYOUT_STYLE = new FlowLayout();

    private final Dialog dialog;

    public ResultDialog(Frame frame, String result) {
        dialog = new Dialog(frame);
        dialog.setTitle(TITLE);
        dialog.setModal(IS_MODAL);
        dialog.setSize(WIDTH, HEIGHT);
        dialog.setLocation(LOCATION_X, LOCATION_Y);
        dialog.setLayout(LAYOUT_STYLE);

        Label msg = new Label();
        msg.setText(result);
        msg.setAlignment(Label.CENTER);
        dialog.add(msg);

        Button okBtn = new Button("OK");
        dialog.add(okBtn);
        okBtn.addActionListener(e -> {
            dialog.setVisible(false);
            dialog.dispose();
        });
    }

    public void show() {
        dialog.setVisible(true);
    }
}
