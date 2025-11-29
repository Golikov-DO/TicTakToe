package model;

import static constants.GameConstants.*;

public class Model {
    private int side = 3;

    int[][] field;
    public boolean isTurnX;

    public Model() {
        field = new int[side][side];
        isTurnX = true;
    }

    public void newGame (int difficulty){
        this.side = difficulty == 1 ? 3 : difficulty == 2 ? 5 : difficulty == 3 ? 10 : 3;
        field = new int[side][side];
        isTurnX = true;
    }

    public void setValue(int row, int col) {
        if (field[col][row] == FIELD_EMPTY) {
            field[col][row] = isTurnX ? FIELD_X : FIELD_O;
            isTurnX = !isTurnX;
        }
    }

    public int getValue(int row, int col) {
        return field[col][row];
    }

    public int checkWiner() {
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < side; i++) {
            diag += field[i][i];
            diag2 += field[i][(side - 1) - i];
        }
        if (diag == FIELD_O * side || diag == FIELD_X * side) {
            return diag;
        }
        if (diag2 == FIELD_O * side || diag2 == FIELD_X * side) {
            return diag2 * -1;
        }
        int check_i, check_j;
        boolean hasEmpty = false;
        for (int i = 0; i < side; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < side; j++) {
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                check_i += field[i][j];
                if (check_i == FIELD_O * side || check_i == FIELD_X * side) {
                    return check_i + (i + 1);                                       //Возвращаем если все нолики или крестики стали в строку шириной равной ширине поля в строке прибавленной к результату
                }
                check_j += field[j][i];
                if (check_j == FIELD_O * side || check_j == FIELD_X * side) {
                    return check_j - (i + 1) ;                                      //Возвращаем если все нолики или крестики стали в столбец шириной равной ширине поля в столбце отнятому от результата
                }
            }
        }
        if (hasEmpty) return 0;
        else return -1;
    }
}
