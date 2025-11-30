import controller.Controller;
import model.Model;
import view.GameWindow;
import view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            MainWindow mainWindow = new MainWindow(model);

            mainWindow.getStartButton().addActionListener(e -> {
                // Это действие выполняется после того, как MainWindow закроется
                // и режим будет установлен в Model.

                // Создаем контроллер и передаем ему модель
                Controller controller = new Controller(model);

                // Создаем игровое окно и передаем ему модель и контроллер
                new GameWindow(model, controller);
            });
        });
    }
}