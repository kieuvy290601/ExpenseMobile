package com.example.demo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainExpenseAct extends AppCompatActivity {

    RecyclerView recyclerExpense;
    FloatingActionButton add_ExBtn;
    DatabaseHelper dataHelper;
    ArrayList<String> _id, trId, type, amount, time;
    ExAdapter exAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expense);

        recyclerExpense = findViewById(R.id.recyclerExpense);
        add_ExBtn = findViewById(R.id.add_ExBtn);
        add_ExBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainExpenseAct.this, AddExpense.class);
                String tripId = getIntent().getStringExtra("tripId");
                intent.putExtra("id", String.valueOf(tripId));
                startActivity(intent);


            }
        });


        dataHelper = new DatabaseHelper(MainExpenseAct.this);
        _id = new ArrayList<>();
        trId = new ArrayList<>();
        type = new ArrayList<>();
        amount = new ArrayList<>();
        time = new ArrayList<>();

        GetData();

        exAdapter = new ExAdapter(MainExpenseAct.this, _id, trId, type, amount, time) ;
        recyclerExpense.setAdapter(exAdapter);
        recyclerExpense.setLayoutManager(new LinearLayoutManager(this));

    }

    private void GetData() {
        String trID = getIntent().getStringExtra("tripId");
        Cursor cursor = dataHelper.readExpenseData(trID);
        if(cursor.getCount() == 0){
        }else{
            while (cursor.moveToNext()){
                _id.add(cursor.getString(0));
                trId.add(cursor.getString(1));
                type.add(cursor.getString(2));
                amount.add(cursor.getString(3));
                time.add(cursor.getString(4));
            }
        }
    }
}