package view;

import controller.Controller;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    private final Controller controller;
    private DrawGameElements drawPanel;

    public GameWindow(Model gameModel, Controller controller) {
        this.controller = controller;
        this.drawPanel = new DrawGameElements(gameModel);

        this.controller.setGameWindow(this);

        JFrame frame = new JFrame("Новое окно");
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
