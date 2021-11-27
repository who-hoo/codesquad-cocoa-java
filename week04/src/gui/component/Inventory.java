package gui.component;

import java.awt.TextArea;

public class Inventory {

    private static final int ROWS = 0;
    private static final int COLS = 0;
    private static final int SCROLL_TYPE = TextArea.SCROLLBARS_VERTICAL_ONLY;

    private final TextArea textArea;

    public Inventory(String initialState) {
        textArea = new TextArea(initialState, ROWS, COLS, SCROLL_TYPE);
        textArea.setEditable(false);
    }

    public void clear() {
        textArea.setText("");
    }

    public String[] getItems() {
        return textArea.getText().split("\n");
    }

    public void add(String item) {
        textArea.append(item + "\n");
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
