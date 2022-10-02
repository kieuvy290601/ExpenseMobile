package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_Btn;

    DatabaseHelper dataHelper;
    ArrayList<String> id, name, des, date, risk, description;
    VAdapter VAdapter;

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

        dataHelper = new DatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        des = new ArrayList<>();
        date = new ArrayList<>();
        risk = new ArrayList<>();
        description = new ArrayList<>();

        SaveDataInArray();

        VAdapter = new VAdapter(MainActivity.this,id,name,des,date,risk,description);
        recyclerView.setAdapter(VAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void SaveDataInArray(){
        Cursor cursor = dataHelper.readData();
        if(cursor.getCount() == 0){
            /*empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);*/
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                des.add(cursor.getString(2));
                date.add(cursor.getString(3));
                risk.add(cursor.getString(4));
                description.add(cursor.getString(5));
            }
            /*empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);*/
        }

    }

}