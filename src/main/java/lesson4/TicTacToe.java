package lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY_CELL_SYMBOL = '*';
    private static final char FIRST_PLAYER_SYMBOL = 'X';
    private static final char SECOND_PLAYER_SYMBOL = 'O';

    private static final String FORMAT_STRING = "%4s";
    private static final String FORMAT_STRING_LEFT = "%-2s";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static int fieldSize = 5;
    private static char[][] field;
    private static int winLine = 3;
    private static boolean isSecondPlayerEnabled = false;
    private static boolean isFirstPlayerTurn = false;
    private static boolean isExit = false;
    private static int selectedRow = 0;
    private static int selectedColumn = 0;

    private static final String MSG_INPUT_FIELD_SIZE = "Укажите размер поля (от 3 до 50), либо нажмите 0 для продолжения: ";
    private static final String MSG_INPUT_IS_TWO_PLAYER = "Второй игрок вместо компьютера? Введите 1 для согласия, либо 0 для продолжения: ";
    private static final String MSG_INPUT_ROW = "Введите номер строки: ";
    private static final String MSG_INPUT_COLUMN = "Введите номер столбца: ";
    private static final String MSG_INPUT_NEW_GAME = "Желаете сыграть еще? Введите 1 для согласия, либо 0 для заврешения игры: ";
    private static final String MSG_RULES = "Правила. Побеждает игрок первым собравший ряд из %d своих символов по горизонтали, вертикали или диагонали. Для выхода из игры в любой момент в ответе укажите 0.";
    private static final String MSG_TURN_PLAYER = "Ходит %s%d";
    private static final String MSG_TURN_AI = "Ход компьютера: %d %d";
    private static final String MSG_WIN = "Победа! %s%s победил!";
    private static final String MSG_PLAYER = "Игрок ";
    private static final String MSG_AI = "Компьютер";
    private static final String MSG_THE_END = "Игра окончена";
    private static final String MSG_ERROR_INPUT_FIELD_SIZE = "Ошибка. Неверно указан размер поля.";
    private static final String MSG_ERROR_INPUT_ROW_AND_COLUMN = "Ошибка. Неверно указан номер. Диапазон от 1 до %d.";
    private static final String MSG_ERROR_MOVE_POSITION_BUSY = "Ошибка. Позиция %d %d занята. Выберете другую.";

    public static void main(String[] args) {
        initGame();

        while (true) {
            setTurnPlayer();
            printTurnPlayer();

            turnPlayer();

            if (isExit) {
                printTheEnd();
                break;
            }

            if (isWin()) {
                printWinPlayer();
                takeInputNewGame();
                continue;
            } else {
                if (!isSecondPlayerEnabled) genTurnAI();

                if (isWin()) {
                    printWinAI();
                    takeInputNewGame();
                    continue;
                }
            }

            printField();
        }
    }

    private static void initGame() {
        takeInputFieldSize();
        takeInputOpponent();

        updateWinLine();
        updateField();

        printRules();

        clearField();
        printField();
    }

    private static void takeInputFieldSize() {
        while (true) {
            System.out.print(MSG_INPUT_FIELD_SIZE);

            if (scanner.hasNextByte()) {
                byte input = scanner.nextByte();
                if (input == 0) {
                    break;
                } else if (input > 2 && input < 51) {
                    fieldSize = input;
                    break;
                }
            }

            System.out.println();
            System.out.println(MSG_ERROR_INPUT_FIELD_SIZE);
            scanner.nextLine();
        }
    }

    private static void takeInputOpponent() {
        System.out.print(MSG_INPUT_IS_TWO_PLAYER);

        if (scanner.hasNextByte()) {
            if (scanner.nextByte() == 1) isSecondPlayerEnabled = true;
        }
    }

    private static void takeInputRow() {
        if (isExit) return;

        while (true) {
            System.out.print(MSG_INPUT_ROW);

            scanner.nextLine();
            if (scanner.hasNextByte()) {
                byte input = scanner.nextByte();
                if (input == 0) {
                    isExit = true;
                    break;
                } else if (input > 0 && input <= fieldSize) {
                    selectedRow = input;
                    break;
                }
            }

            printErrorInputRowOrColumn();
        }
    }

    private static void takeInputColumn() {
        if (isExit) return;

        while (true) {
            System.out.print(MSG_INPUT_COLUMN);

            scanner.nextLine();
            if (scanner.hasNextByte()) {
                byte input = scanner.nextByte();
                if (input == 0) {
                    isExit = true;
                    break;
                } else if (input > 0 && input <= fieldSize) {
                    selectedColumn = input;
                    break;
                }
            }

            printErrorInputRowOrColumn();
        }
    }


    private static void takeInputNewGame() {
        System.out.print(MSG_INPUT_NEW_GAME);

        if (scanner.hasNextByte()) {
            if (scanner.nextByte() == 1) {
                clearField();
                isFirstPlayerTurn = false;
                return;
            }
        }

        isExit = true;
    }

    private static void printRules() {
        System.out.println();
        System.out.printf(MSG_RULES, winLine);
        System.out.println();
        System.out.println();
    }

    private static void printField() {
        System.out.printf(FORMAT_STRING_LEFT, "");

        for (int i = 0; i < fieldSize; i++) {
            System.out.printf(FORMAT_STRING, i + 1);
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < fieldSize; i++) {
            System.out.printf(FORMAT_STRING_LEFT, i + 1);

            for (int j = 0; j < fieldSize; j++) {
                System.out.printf(FORMAT_STRING, field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printTurnPlayer() {
        System.out.printf(MSG_TURN_PLAYER, MSG_PLAYER, getTurnPlayer());
        System.out.println();
    }

    private static void printTurnAI() {
        System.out.printf(MSG_TURN_AI, selectedColumn, selectedRow);
        System.out.println();
    }

    private static void printWinAI() {
        System.out.printf(MSG_WIN, MSG_AI, "");
        System.out.println();
    }

    private static void printWinPlayer() {
        System.out.printf(MSG_WIN, MSG_PLAYER, getTurnPlayer());
        System.out.println();
    }

    private static void printTheEnd() {
        System.out.println(MSG_THE_END);
    }

    private static void printErrorInputRowOrColumn() {
        System.out.println();
        System.out.printf(MSG_ERROR_INPUT_ROW_AND_COLUMN, fieldSize);
        System.out.println();
    }

    private static void printErrorInputPositionBusy() {
        System.out.printf(MSG_ERROR_MOVE_POSITION_BUSY, selectedRow, selectedColumn);
        System.out.println();
    }

    private static void updateWinLine() {
        if (fieldSize > 10) winLine = 7;
        else if (fieldSize > 6) winLine = 5;
    }

    private static void updateField() {
        field = new char[fieldSize][fieldSize];
    }

    private static void clearField() {
        for (char[] chars : field) {
            Arrays.fill(chars, EMPTY_CELL_SYMBOL);
        }
    }

    private static void turnPlayer() {
        while (true) {
            takeInputColumn();
            takeInputRow();
            if (isExit) break;

            if (isInputPositionFree()) {
                if (!isFirstPlayerTurn && isSecondPlayerEnabled) setTurnSymbol(SECOND_PLAYER_SYMBOL);
                else setTurnSymbol(FIRST_PLAYER_SYMBOL);
                break;
            } else {
                printErrorInputPositionBusy();
            }
        }
    }

    private static void genTurnAI() {
        while (true) {
            selectedRow = random.nextInt(1, fieldSize);
            selectedColumn = random.nextInt(1, fieldSize);

            if (isInputPositionFree()) {
                setTurnSymbol(SECOND_PLAYER_SYMBOL);
                printTurnAI();
                break;
            }
        }
    }


    private static boolean isInputPositionFree() {
        return field[selectedRow - 1][selectedColumn - 1] == EMPTY_CELL_SYMBOL;
    }

    private static char getTurnSymbol() {
        return field[selectedRow - 1][selectedColumn - 1];
    }

    private static void setTurnSymbol(char sym) {
        field[selectedRow - 1][selectedColumn - 1] = sym;
    }

    private static int getTurnPlayer() {
        if (isSecondPlayerEnabled)
            return isFirstPlayerTurn ? 1 : 2;

        return 1;
    }

    private static void setTurnPlayer() {
        isFirstPlayerTurn = !isFirstPlayerTurn;
    }

    private static boolean isWin() {
        return isWinVertical() || isWinHorizontal() || isWinLeftDiagonal() || isWinRightDiagonal();
    }

    private static boolean isWinVertical() {
        int counter = 0;

        for (int i = 0; i < fieldSize; i++) {
            if (field[i][selectedColumn - 1] == getTurnSymbol()) {
                counter++;
                if (counter == winLine) return true;
            } else counter = 0;
        }
        return false;
    }

    private static boolean isWinHorizontal() {
        int counter = 0;

        for (int i = 0; i < fieldSize; i++) {
            if (field[selectedRow - 1][i] == getTurnSymbol()) {
                counter++;
                if (counter == winLine) return true;
            } else counter = 0;
        }
        return false;
    }

    private static boolean isWinRightDiagonal() {
        int counter = 0;
        int shift = Math.min(selectedRow, selectedColumn);
        int length = fieldSize - Math.abs(selectedRow - selectedColumn);

        if (length >= winLine) {
            System.out.println();
            for (int i = 0; i < length; i++) {
                if (field[selectedRow - shift + i][selectedColumn - shift + i] == getTurnSymbol()) {
                    counter++;
                    if (counter == winLine) return true;
                } else counter = 0;
            }
        }
        return false;
    }

    private static boolean isWinLeftDiagonal() {
        int counter = 0;
        int selectedColumnInversion = fieldSize - selectedColumn + 1;
        int shift = Math.min(selectedRow, selectedColumnInversion);
        int length = fieldSize - Math.abs(selectedRow - selectedColumnInversion);

        if (length >= winLine) {
            for (int i = 0; i < length; i++) {
                if (field[selectedRow - shift + i][fieldSize - (selectedColumnInversion - shift) - 1 - i] == getTurnSymbol()) {
                    counter++;
                    if (counter == winLine) return true;
                } else counter = 0;
            }
        }
        return false;
    }
}