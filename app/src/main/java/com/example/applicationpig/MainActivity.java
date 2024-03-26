package com.example.applicationpig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView infoTextView;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTextView = findViewById(R.id.txt);
        imgView = findViewById(R.id.img);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_pig1);
        EditText searchView = (EditText) MenuItemCompat.getActionView(searchItem);
        searchView.setHint("Набирай что-нибудь…");
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                infoTextView.setText(searchView.getText());
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
                infoTextView.setText("Что-то настраивается");
                return true;
        }
        if(id == R.id.action_pig1) {
            infoTextView.setText("");
            imgView.setImageResource(R.drawable.chi1);
            return true;
        }
        if(id == R.id.action_pig2) {
            infoTextView.setText("Хряк");
            imgView.setImageResource(R.drawable.chi2);
            return true;
        }
        if (id == R.id.action_pig3)
        {
                infoTextView.setText("Боров");
                imgView.setImageResource(R.drawable.chi3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}