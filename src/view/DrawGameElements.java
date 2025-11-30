package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import static constants.GameConstants.*;

public class DrawGameElements extends JPanel {
    private final Model gameModel;
    private final int side;


    public DrawGameElements(Model gameModel) {
        this.gameModel = gameModel;
        int difficulty = gameModel.getDifficulty();
        int width = gameModel.getWidth();
        int height = gameModel.getHeight();
        this.side = difficulty == 1 ? 3 : difficulty == 2 ? 5 : difficulty == 3 ? 10 : 3;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawXO(g);
        drawWinLine(g);
    }
   /* private void handleClick(int mouseX, int mouseY) {
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / side;
        int cellHeight = height / side;

        // Определяем строку и столбец, куда кликнули
        int col = mouseX / cellWidth;
        int row = mouseY / cellHeight;

        // Проверяем, что клик в пределах сетки и ячейка пуста (значение 0)
        //if (row >= 0 && row < 3 && col >= 0 && col < 3 && gameModel.getValue(row, col) == 0) {
        // Обновляем модель
        gameModel.setValue(col, row);
        int winer = gameModel.checkWiner();
        if (winer != 0) {
            if (winer >= FIELD_O * side - side && winer <= FIELD_O * side + side){
                showWiner("O");
            } else if (winer >= FIELD_X * side - side && winer <= FIELD_X * side + side){
                showWiner("X");
            } else showWiner("NON");
        }
        // Запрашиваем перерисовку панели (вызовет paintComponent)
        repaint();
        //}
    }*/


    private void drawXO(Graphics g) {
        for (int i = 0; i < side; i++) { // row
            for (int j = 0; j < side; j++) { // col
                if (gameModel.getValue(i, j) == 10) {
                    drawX(i, j, g);
                } else if (gameModel.getValue(i, j) == 200) {
                    drawO(i, j, g);
                }
            }
        }
    }

    public void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int dw = width / side;
        int dh = height / side;
        g.setColor(Color.BLUE);
        for (int i = 1; i < side; i++) {
            g.drawLine(0, dh * i, width, dh * i);
            g.drawLine(dw * i, 0, dw * i, height);
        }
    }

    public void drawX(int i, int j, Graphics g) {
        g.setColor(Color.BLACK);
        int dw = getWidth() / side;
        int dh = getHeight() / side;
        int x = i * dw;
        int y = j * dh;
        g.drawLine(x, y, x + dw, y + dh);
        g.drawLine(x, y + dh, x + dw, y);
        repaint();
    }

    public void drawO(int i, int j, Graphics g) {
        g.setColor(Color.BLACK);
        int dw = getWidth() / side;
        int dh = getHeight() / side;
        int x = i * dw;
        int y = j * dh;
        g.drawOval(x + 3 * dw / 100, y + 3 * dw / 100, dw * 96 / 100, dh * 96 / 100);
    }

  /*  public void showWiner(String winer) {
        String message = NON_WINER;
        if (winer.equals("X")){
            message = WINER_X;
        } else if(winer.equals("O"))
            message = WINER_O;
        int answer = JOptionPane.showConfirmDialog(parentFrame, QUESTION, message, JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.NO_OPTION) {
            parentFrame.dispose(); // Закрывает текущее окно JFrame
        } else {
            gameModel.newGame(difficulty);
        }
    }*/

    public void drawWinLine(Graphics g){
        int res = gameModel.checkWiner();
        g.setColor(Color.RED);
        int x = (int)((float)getHeight() / side);
        int y = (int)((float)getWidth() / side);
        if (res == FIELD_X * side || res == FIELD_O * side) {
            g.drawLine(0, 0, getWidth(), getHeight());
        }

        if (res == -FIELD_X * side || res == -FIELD_O * side) {
            g.drawLine(getWidth(), 0, 0, getHeight());
        }

        if (res > FIELD_O * side) {                                     //если результат больше максимального значения значит нужно зачеркнуть строку
            int dy = (res - FIELD_O * side) * x - x / 2;                //которая получается путём вычитания максимального результата из нашего
            g.drawLine(0, dy, getWidth(), dy);
        } else if (res > 30) {
            int dy = (res - FIELD_X * side) * x - x / 2;
            g.drawLine(0, dy, getWidth(), dy);
        }

        if (res < FIELD_X * side) {                                     //если результат меньше максимального значения значит нужно зачеркнуть столбец
            int dx = (FIELD_X * side - res) * y - y / 2;                //который получается путём вычитания нашего результат из максимально возможного
            g.drawLine(dx, 0,dx, getHeight());
        } else if (res < FIELD_O * side) {
            int dx = (FIELD_O * side - res) * y - y / 2;
            g.drawLine(dx, 0, dx, getHeight());
        }
    }
}
