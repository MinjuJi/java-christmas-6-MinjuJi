package controller;

import view.InputView;

public class Controller {
    private final InputView inputView = new InputView();

    public void run() {
        reserve();
    }

    private void reserve() {
        inputView.readDay();
        inputView.readMenu();
    }

}
