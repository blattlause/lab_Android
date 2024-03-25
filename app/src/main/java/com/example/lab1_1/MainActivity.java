package com.example.lab1_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    private EditText editTextSurname;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSurname = findViewById(R.id.editTextSurname);
        textViewResult = findViewById(R.id.textViewResult);

        Button buttonGenerate = findViewById(R.id.buttonGenerate);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateResult();
            }
        });
    }

    private void generateResult() {
        String surname = editTextSurname.getText().toString().trim();
        if (surname.isEmpty()) {
            textViewResult.setText("Введите фамилию");
            return;
        }


        Random random = new Random();
        int mark = random.nextInt(7) + 4;

        String result = String.format("%s, ваша оценка по РПМУ: %d", surname, mark);
        textViewResult.setText(result);
    }

}