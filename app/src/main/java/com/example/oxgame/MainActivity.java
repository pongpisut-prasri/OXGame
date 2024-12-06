package com.example.oxgame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.oxgame.databinding.ActivityMainBinding;
import com.example.oxgame.utils.AIHelper;
import com.example.oxgame.viewmodel.GameViewModel;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding; // ตัวแปร View Binding
    private GameViewModel gameViewModel;
    private AIHelper aiHelper;
    private Button[][] buttons = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // กำหนด Binding
        setContentView(binding.getRoot());

        // Initialize ViewModel
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        //กำหนด Action สำหรับปุ่มในกระดานเกม
        initializeBoard();

        // Observer สำหรับอัปเดต UI ตามข้อมูลใน ViewModel
        observeGameState();
    }

    private void initializeBoard() {
        // กำหนด Action สำหรับปุ่มในกระดาน
        binding.button00.setOnClickListener(v -> onCellClicked(0, 0));
        binding.button01.setOnClickListener(v -> onCellClicked(0, 1));
        binding.button02.setOnClickListener(v -> onCellClicked(0, 2));
        binding.button10.setOnClickListener(v -> onCellClicked(1, 0));
        binding.button11.setOnClickListener(v -> onCellClicked(1, 1));
        binding.button12.setOnClickListener(v -> onCellClicked(1, 2));
        binding.button20.setOnClickListener(v -> onCellClicked(2, 0));
        binding.button21.setOnClickListener(v -> onCellClicked(2, 1));
        binding.button22.setOnClickListener(v -> onCellClicked(2, 2));
    }

    private void onCellClicked(int row, int col) {
        // ผู้เล่นเดินหมาก
        gameViewModel.makeMove(row, col, 1);

        // เช็คสถานะเกม (เช่น AI อาจเดินต่อ)
        makeAiMove();
    }

    private void makeAiMove() {
        gameViewModel.makeAiMove(); // ใช้ ViewModel เรียก AI ให้เดินหมาก
    }

    private void observeGameState() {
        // อัปเดต UI กระดานเมื่อมีการเปลี่ยนแปลง
        gameViewModel.getBoardLiveData().observe(this, board -> {
            binding.button00.setText(getSymbol(board[0][0]));
            binding.button01.setText(getSymbol(board[0][1]));
            binding.button02.setText(getSymbol(board[0][2]));
            binding.button10.setText(getSymbol(board[1][0]));
            binding.button11.setText(getSymbol(board[1][1]));
            binding.button12.setText(getSymbol(board[1][2]));
            binding.button20.setText(getSymbol(board[2][0]));
            binding.button21.setText(getSymbol(board[2][1]));
            binding.button22.setText(getSymbol(board[2][2]));
        });

        // อัปเดตผลเกม
        gameViewModel.getGameResult().observe(this, result -> {
            if (!result.isEmpty()) {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                resetGame(); // รีเซ็ตกระดานเมื่อเกมจบ
            }
        });
    }

    private String getSymbol(int value) {
        if (value == 1) return "X";
        else if (value == 2) return "O";
        else return "";
    }

    private void resetGame() {
        gameViewModel.resetGame(); // รีเซ็ตกระดานใน ViewModel
    }

}