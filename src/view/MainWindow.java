package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private final JFrame frame;
    private final JButton button;
    private Model gameModel;

    public MainWindow(Model gameModel) {
        this.gameModel = gameModel;
        frame = new JFrame("Крестики Нолики");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JPanel difficultyLevelPanel = new JPanel();
        JPanel playersOptionPanel = new JPanel();
        difficultyLevelPanel.setLayout(new GridLayout(0, 1));       // Создаём панель для сложности один столбец
        playersOptionPanel.setLayout(new GridLayout(0, 1));         // Создаём панель для выбора игрока один столбец

        TitledBorder difficultyBorder = new TitledBorder("Уровень сложности"); // Создаём рамку с названием для сложности
        difficultyLevelPanel.setBounds(20, 20, 140, 90);
        difficultyLevelPanel.setBorder(difficultyBorder);

        TitledBorder playerBorder = new TitledBorder("Выбор игроков");          // Создаём рамку с названием для выбора игрока
        playersOptionPanel.setBounds(170, 20, 150, 130);
        playersOptionPanel.setBorder(playerBorder);

        button = new JButton("Начать игру");                                // Создаём кнопку
        button.setBounds(70, 180, 200, 50);                    // Устанавливаем положение и размер кнопки

        String[] difficult = {"Лёгки 3х3", "Средний 5х5", "Тяжёлый 10х10"};      // Создаём массив названий для RadioButtonов
        String[] players = {"<html>" + "Игрок против Игрока" + "</html>",
                            "<html>" + "Игрок против Компьютера" + "</html>",
                            "<html>" + "Компьютер против Компьютера" + "</html>"}; // "<html>" + название + "</html>" нужно переноса названий если не помещается в рамку
        ButtonGroup difficultGroup = new ButtonGroup();
        ButtonGroup playersGroup = new ButtonGroup();                              // Создаём группы для RadioButtonов
        List<JRadioButton> difficultyBoxes = new ArrayList<>();
        List<JRadioButton> playerBoxes = new ArrayList<>();                        //Создаём коллекции для хранения RadioButtonов

        for (String item : difficult) {                                            //Проходя по массиву названий уровня сложности
            JRadioButton difficultBox = new JRadioButton(item);                    //создаём RadioButton с именем из массива
            difficultyBoxes.add(difficultBox);                                     //добавляем вновь созданный RadioButton в коллекцию
            difficultGroup.add(difficultBox);                                      //его же добавляем в группу RadioButtonов
            difficultyLevelPanel.add(difficultBox);                                //его же добавляем в панель
        }

        for (String item : players) {                                               //Аналогично предыдущему только для выбора игрока
            JRadioButton playerBox = new JRadioButton(item);
            playerBoxes.add(playerBox);
            playersGroup.add(playerBox);
            playersOptionPanel.add(playerBox);
        }

        difficultyBoxes.getFirst().setSelected(true);                               //Устанавливаем первый RadioButton как отмеченный по умолчанию
        gameModel.setDifficulty(1);                                                 //Присваиваем уровню сложности значение по умолчанию 1
        gameModel.setWidth(300);
        gameModel.setHeight(300);
        playerBoxes.getFirst().setSelected(true);                                   //Устанавливаем первый RadioButton как отмеченный по умолчанию
        gameModel.setCurrentMode("PvsP");                                            //Устанавливаем тип игры по умолчанию как PvsP

        ItemListener listener = e -> {                                      //Создаём слушателя смены состояния RadioButtonов
            if (e.getStateChange() == ItemEvent.SELECTED) {                           //смотрим какой был изменён
                for (JRadioButton item : difficultyBoxes) {                           //если из группы сложность
                    if (e.getSource() == item) {
                        gameModel.setDifficulty(difficultyBoxes.indexOf(item) + 1);
                        if (difficultyBoxes.indexOf(item) + 1 == 2){
                            gameModel.setWidth(500);
                            gameModel.setHeight(500);
                        } else if (difficultyBoxes.indexOf(item) + 1 == 3){
                            gameModel.setWidth(600);
                            gameModel.setHeight(600);
                        }//то меняем сложность на соответствующую
                    }
                }
                for (JRadioButton item : playerBoxes) {                                //если из группы выбор игрока
                    if (e.getSource() == item) {
                        int index = playerBoxes.indexOf(item);
                        if (index == 2)
                            gameModel.setCurrentMode("PvsC");
                        else if (index == 3)
                            gameModel.setCurrentMode("CvsC");                                     //то меняем игру на соответствующую
                    }
                }
            }
        };

        for (JRadioButton diff : difficultyBoxes) {                                     //Добавляем в RadioButton из группы сложность созданного слушателя
            diff.addItemListener(listener);
        }

        for (JRadioButton item : playerBoxes) {                                         //Добавляем в RadioButton из группы выбор игрока созданного слушателя
            item.addItemListener(listener);
        }
        button.addActionListener(e -> {
             frame.dispose(); // Закрываем главное окно после выбора
        });

        frame.add(difficultyLevelPanel);                                                //добавляем в наше главное окно панель сложность
        frame.add(playersOptionPanel);                                                  //добавляем в наше главное окно панель выбор игрока
        frame.add(button);                                                              //добавляем в наше главное окно кнопку
        frame.setVisible(true);                                                         //делаем окно видимым
    }

    public void showWindow() {
        frame.setVisible(true);
    }

    public void hideWindow() {
        frame.setVisible(false);
    }

    public JButton getStartButton() {
        return button;
    }
    public void addOpenWindowListener(ActionListener actionListener){
        button.addActionListener(actionListener);
    }
}