package controller;

import model.Model;
import view.GameWindow;

public class Controller {
    private final Model gameModel;
    private GameWindow gameWindow;

    public Controller(Model gameModel){
        this.gameModel = gameModel;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void handleMouseClick(int x, int y) {
        int width = gameModel.getWidth();
        int height = gameModel.getHeight();
        int side = gameModel.getSide();
        int cellWidth = width / side;
        int cellHeight = height / side;

        // Определяем строку и столбец, куда кликнули
        int col = x / cellWidth;
        int row = y / cellHeight;
        gameModel.setValue(col, row);
        // После обновления модели нужно перерисовать игровое окно
        if (gameWindow != null) {
            gameWindow.redraw();
        }
    }
}
