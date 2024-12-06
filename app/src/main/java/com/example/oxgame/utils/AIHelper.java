package com.example.oxgame.utils;

import android.content.Context;
import android.util.Log;

import java.util.Random;

public class AIHelper {
    private Context context;

    public AIHelper(Context context) {
        this.context = context;
    }

    /**
     * ฟังก์ชันสำหรับให้ AI ตัดสินใจว่าจะเดินที่ตำแหน่งไหน
     *
     * @param board กระดานเกมปัจจุบัน (2D array)
     * @return ตำแหน่งที่ AI จะเดิน (int[] ที่มี [row, col])
     */

    public int[] getNextMove(int[][] board) {
        // 1. ตรวจสอบเงื่อนไขชนะ (แนวนอน, แนวตั้ง, แนวทแยง)
        int[] winningMove = findWinningMove(board);
        if (winningMove != null) {
            return winningMove; // คืนค่าช่องที่ทำให้ชนะทันที
        }
        // 2. AI แบบสุ่มตำแหน่งที่ยังว่าง (placeholder)
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != 0); // ตรวจสอบว่าช่องนี้ยังว่าง
        return new int[]{row, col};
    }

    private int[] findWinningMove(int[][] board) {
        // Logic ตรวจสอบแนวนอน, แนวตั้ง, และแนวทแยง

        // ถ้ามี 2 ช่องของ AI และอีกช่องว่าง คืนค่าตำแหน่งนั้น
        return null; // ถ้าไม่มี move ที่ชนะ
    }

    /**
     * ฟังก์ชันสำหรับเทรนโมเดล Q-Learning
     */
    public void trainModel() {
        // อาจจะเรียกใช้ Python Script ผ่าน TensorFlow Lite หรือ REST API
        Log.d("AIHelper", "เริ่มการเทรนโมเดล...");
    }
}
