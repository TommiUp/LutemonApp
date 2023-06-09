package com.example.lutemonapp;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LutemonMoveActivity extends AppCompatActivity {

    private LutemonStorage storage;

    private RecyclerView recyclerView;
    private RadioGroup rgMove;
    private RadioButton rbHome, rbTraining, rbCombat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lutemon_move);
        storage = LutemonStorage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonMoveAdapter(getApplicationContext(), storage.getLutemons("all")));
        rgMove = findViewById(R.id.rgMove);
        rbHome = findViewById(R.id.rbHome);
        rbTraining = findViewById(R.id.rbTraining);
        rbCombat = findViewById(R.id.rbCombat);
        for (Lutemon lutemon : storage.getLutemons("all")) {
            lutemon.setChecked(false);
        }
    }

    // Method for moving lutemons to different activities such ass battlefield, training area and home
    public void moveLutemons(View view) {
        switch (rgMove.getCheckedRadioButtonId()) {
            case R.id.rbHome:
                System.out.println("Kotiin nappia painettu");
                sendToHome();
                break;
            case R.id.rbTraining:
                System.out.println("Training nappia painettu");
                sendToTraining();
                break;
            case R.id.rbCombat:
                System.out.println("Battle nappia painettu");
                sendToBattle();
                break;
        }
    }

    // Method for sending lutemons to home (arraylist)
    public void sendToHome() {
        List<Lutemon> checkedLutemons = new ArrayList<>();
        for (Lutemon lutemon : storage.getLutemons("all")) {
            if (lutemon.isChecked()) {
                checkedLutemons.add(lutemon);
            }
        }
        for (Lutemon lutemon : checkedLutemons) {
            lutemon.setMaxHealth();
            if (!storage.getLutemons("home").contains(lutemon)) {
                storage.removeLutemon(lutemon, "battle");
                storage.removeLutemon(lutemon, "training");
                storage.addLutemon(lutemon, "home");
                }
            }
        storage.saveLutemons(getApplicationContext());
    }

    // Method for sending lutemons to training area (arraylist)
    public void sendToTraining() {
        List<Lutemon> checkedLutemons = new ArrayList<>();
        for (Lutemon lutemon : storage.getLutemons("all")) {
            if (lutemon.isChecked()) {
                checkedLutemons.add(lutemon);
            }
        }
        for (Lutemon lutemon : checkedLutemons) {
            if (!storage.getLutemons("training").contains(lutemon)) {
                storage.removeLutemon(lutemon, "home");
                storage.removeLutemon(lutemon, "battle");
                storage.addLutemon(lutemon, "training");
            }
        }
        storage.saveLutemons(getApplicationContext());
    }

    // Method for sending lutemons to battlefield (arraylist)
    public void sendToBattle() {
        List<Lutemon> checkedLutemons = new ArrayList<>();
        for (Lutemon lutemon : storage.getLutemons("all")) {
            if (lutemon.isChecked()) {
                checkedLutemons.add(lutemon);
            }
        }
        for (Lutemon lutemon : checkedLutemons) {
            if (!storage.getLutemons("battle").contains(lutemon)) {
                storage.removeLutemon(lutemon, "home");
                storage.removeLutemon(lutemon, "training");
                storage.addLutemon(lutemon, "battle");
            }
        }
        storage.saveLutemons(getApplicationContext());
    }
}