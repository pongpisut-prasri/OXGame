package com.example.oxgame.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.oxgame.model.Board;
import com.example.oxgame.utils.AIHelper;
import com.example.oxgame.utils.GameUtils;

public class GameViewModel extends AndroidViewModel {
    private final Board board;
    private final MutableLiveData<int[][]> boardLiveData;
    private final MutableLiveData<String> gameResult;
    private final AIHelper aiHelper; // ตัวช่วย AI

    // dependency injection

    public GameViewModel(@NonNull Application application){
        super(application);
        this.aiHelper = new AIHelper(application);
        this.board = new Board();
        this.boardLiveData=new MutableLiveData<>(board.getBoard());
        this.gameResult=new MutableLiveData<String>("");
    }


    public LiveData<int[][]> getBoardLiveData() {
        return boardLiveData;
    }

    public LiveData<String> getGameResult() {
        return gameResult;
    }

    private void checkGameStatus() {
        String winner= GameUtils.getWinner(board.getBoard());
        gameResult.setValue(winner);
    }

    public void resetGame() {
        GameUtils.resetGame(board,boardLiveData,gameResult);
    }

    // ผู้เล่นเดินหมาก
    public void makeMove(int x, int y, int player) {
        if (board.setMove(x, y, player)) {
            boardLiveData.setValue(board.getBoard());
            checkGameStatus();
        }
    }

    /**
     * ฟังก์ชันให้ AI เดินหมาก
     */
    public void makeAiMove() {
        int[][] board = boardLiveData.getValue();
        if (board == null) return;

        int[] move = aiHelper.getNextMove(board); // เรียก AIHelper ให้หา move
        board[move[0]][move[1]] = 2; // 2 แทน AI
        boardLiveData.setValue(board);

        checkGameResult(); // เช็คผลลัพธ์หลัง AI เดิน
    }

    /**
     * เช็คผลลัพธ์ของเกม
     */
    private void checkGameResult() {
        int[][] board = boardLiveData.getValue();
        if (board == null) return;
        String winner = GameUtils.getWinner(board);
        gameResult.setValue(winner);
    }

    private boolean isWinner(int[][] board, int player) {
        // ตรวจสอบแนวตั้ง แนวนอน และแนวทแยง
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }

    private boolean isBoardFull(int[][] board) {
       return GameUtils.isBoardFull(board);
    }
}
