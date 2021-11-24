package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;

public class RandomPickFrame {

    private static final String TITLE = "Random Pick";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private static final LayoutManager LAYOUT_STYLE = new FlowLayout();

    private final Frame frame = new Frame();

    public RandomPickFrame() {
        frame.setTitle(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLayout(LAYOUT_STYLE);
        frame.addWindowListener(new WindowEventHandler());
    }

    public void add(Component component) {
        frame.add(component);
    }

    public void run() {
        frame.setVisible(true);
    }

    public Frame getFrame() {
        return frame;
    }
}
