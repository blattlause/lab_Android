package com.example.lab1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.os.Bundle;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Button> buttonList = new ArrayList<>();
        String buttonPrefix = "button";

        for (int i = 1; i <= 8; i++) {
            int buttonId = getResources().getIdentifier(buttonPrefix + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            buttonList.add(button);
        }

        for (Button button : buttonList) {
            button.setOnClickListener(buttonClickListener);
        }

    }

    private void showPopup(Button button) {
        TextView textViewPopup = new TextView(this);
        textViewPopup.setText("Привет, " + button.getText().toString());
        textViewPopup.setTextSize(18);
        textViewPopup.setTextColor(Color.WHITE);
        textViewPopup.setPadding(16, 16, 16, 16);

        PopupWindow popupWindow = new PopupWindow(textViewPopup, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(button, Gravity.CENTER, 0, 0);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof Button) {
                showPopup((Button) v);
            }
        }
    };

}