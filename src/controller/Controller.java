package controller;

import model.Model;
import view.GameWindow;

public class Controller {
    private Model gameModel;
    private GameWindow gameWindow;

    public Controller(Model gameModel){
        this.gameModel = gameModel;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void handleMouseClick(int x, int y) {
       gameModel.setValue(x, y);
        // После обновления модели нужно перерисовать игровое окно
        if (gameWindow != null) {
            gameWindow.redraw();
        }
    }
}
