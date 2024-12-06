package com.example.oxgame.model;

public class Board {
    private int[][] board; // 3x3 กระดาน (0 = ว่าง, 1 = ผู้เล่น, 2 = AI)

    public Board() {
        board = new int[3][3]; // Initialize กระดาน
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean setMove(int x, int y, int player) {
        if (board[x][y] == 0) {
            board[x][y] = player;
        }
        return true;
    }

    public int checkWinner() {
        //ตรวจสอบแนวนอนและแนวตั้ง
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0]; // ชนะในแนวแถว
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i]; // ชนะในคอลัมน์
            }
        }
        // ตรวจสอบแนวทแยง
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][0];
        }
        // ตรวจสอบเสมอ
        boolean full = true;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    full = false;
                    break;
                }
            }
        }
        return full ? -1 : 0; // -1 = เสมอ, 0 = เกมยังไม่จบ
    }

    public void resetBoard() {
        board = new int[3][3];
    }
}


