package com.example.oxgame.utils;

import androidx.lifecycle.MutableLiveData;

import com.example.oxgame.model.Board;

import java.util.Arrays;
import java.util.OptionalInt;

public class GameUtils {
    
    public static String getWinner(int[][] board){
        //ตรวจสอบแนวนอนและแนวตั้ง
        int result =-1;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                result=board[i][0];
                break;
            }else if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                result=board[0][i];
                break;
            }
        }
        // ตรวจสอบแนวทแยง
        if (result==-1){
            if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                result=board[0][0];
            }else if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                result=board[0][0];
            }
        }

        // 99 = stil dont have winner
        if (result==-1){
            boolean full = true;
            for (int[] row : board) {
                OptionalInt isFalse = Arrays.stream(row).filter((cell) -> cell == 0).findFirst();
                if (isFalse.isPresent()){
                    full=false;
                    break;
                }
            }
            result = full? -1:0;
        }

        switch (result){
            case 1:
                return "Player Wins!";
            case 2:
                return "AI Wins!";
            case -1:
                return "It's a Draw!";
            default:
                return "";
        }
    }
    
    public static void resetGame(Board board, MutableLiveData<int[][]> boardLiveData,MutableLiveData<String> gameResult){
        board.resetBoard();
        boardLiveData.setValue(board.getBoard());
        gameResult.setValue("");
    }

    public static boolean isBoardFull(int[][] board){
        for (int[] row : board) {
            OptionalInt object = Arrays.stream(row).filter((cell)->cell==0).findFirst();
            if (object.isPresent()){
                return false;
            }
        }
        return true;
    }
}
