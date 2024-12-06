package com.example.oxgame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;

public class Board {
    private int[][] board; // 3x3 กระดาน (0 = ว่าง, 1 = ผู้เล่น, 2 = AI)

    public Board() {
        this.board = new int[3][3]; // Initialize กระดาน
    }

    public int[][] getBoard() {
        return this.board;
    }

    public boolean setMove(int x, int y, int player) {
        if (this.board[x][y] == 0) {
            this.board[x][y] = player;
        }
        return true;
    }

    public int checkWinner() {
        //ตรวจสอบแนวนอนและแนวตั้ง
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] != 0 && this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board[i][2]) {
                return this.board[i][0]; // ชนะในแนวแถว
            }else if (this.board[0][i] != 0 && this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i]) {
                return this.board[0][i]; // ชนะในคอลัมน์
            }
        }
        // ตรวจสอบแนวทแยง
        if (this.board[0][0] != 0 && this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]) {
            return this.board[0][0];
        }else if (this.board[0][2] != 0 && this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0]) {
            return this.board[0][0];
        }
        // ตรวจสอบเสมอ
        boolean full = true;
        for (int[] row : this.board) {
            OptionalInt isFalse = Arrays.stream(row).filter((cell) -> cell == 0).findFirst();
            if (isFalse.isPresent()){
                full=false;
                break;
            }
        }
        return full ? -1 : 0; // -1 = เสมอ, 0 = เกมยังไม่จบ
    }

    public void resetBoard() {
        this.board = new int[3][3];
    }
}


