package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_Btn = findViewById(R.id.add_Btn);
        add_Btn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAct.class);
                startActivity(intent);
            }
        });
    }


}