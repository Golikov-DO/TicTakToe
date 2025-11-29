package controller;

import model.Model;
import view.GameWindow;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record Controller (MainWindow mainWindow, Model gameModel) implements ActionListener {
    public Controller(MainWindow mainWindow, Model gameModel){
        this.mainWindow = mainWindow;
        this.gameModel = gameModel;
        this.mainWindow.addOpenWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Логика обработки события
        if (e.getActionCommand().equals("Начать игру")) {
            // Контроллер решает, что должно произойти
            openNewWindow();
        }
    }

    private void openNewWindow() {
        mainWindow.hideWindow();
        int width = mainWindow.getNewWindowWidth();
        int height = mainWindow.getNewWindowHeight();
        int difficulty = mainWindow.getDifficultyLevel();
        // Создание и отображение нового окна.
        new GameWindow(width, height, difficulty, mainWindow, gameModel);
        mainWindow.hideWindow();
    }
}
