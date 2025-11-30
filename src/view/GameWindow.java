package view;

import controller.Controller;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow {
    private final DrawGameElements drawPanel;

    public GameWindow(Model gameModel, Controller controller) {
        gameModel.newGame();
        this.drawPanel = new DrawGameElements(gameModel);

        controller.setGameWindow(this);

        JFrame frame = new JFrame("Игра " + gameModel.getCurrentMode());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрытие только этого окна
        frame.add(drawPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Передаем координаты клика в контроллер
                controller.handleMouseClick(e.getX(), e.getY());
            }
        });
    }

    public void redraw() {
        drawPanel.repaint();
    }
}
