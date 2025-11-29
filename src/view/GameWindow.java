package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    private final MainWindow mainWindow;

    public GameWindow(int width, int height, int difficulty, MainWindow mainWindow, Model gameModel) {
        this.mainWindow = mainWindow;                            //Получаем главное окно

        JFrame frame = new JFrame("Новое окно");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрытие только этого окна
        frame.setSize(width, height);

        frame.addWindowListener(new WindowAdapter() {               //Добавляем нового слушателя стандартных кнопок (закрыть свернуть развернуть) этого окна
            @Override
            public void windowClosed(WindowEvent e) {               //при нажатии кнопки закрыть закрываем текущее окно
                if (GameWindow.this.mainWindow != null) {           //проверяем что MainWindow не закрыто
                    GameWindow.this.mainWindow.showWindow();        //когда текущее окно полностью закрыто по нажатию кнопки закрыть,
                                                                    //мы отображаем родительское (главное) окно
                }
            }
        });
        gameModel.newGame(difficulty);

        DrawGameElements gamePanel = new DrawGameElements(width, height, difficulty, gameModel, frame);

        frame.setLocationRelativeTo(null);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
