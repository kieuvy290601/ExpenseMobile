package com.example.demo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_Btn;
    EditText searchTxt;
    Button searchBtn;

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

        searchTxt = findViewById(R.id.searchTxt);
        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tripSearch = searchTxt.getText().toString();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("tripSearch", tripSearch);
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

        searchTxt.setText(getIntent().getStringExtra("tripSearch"));
        if (searchTxt.length() == 0) {
            String tripSearch = searchTxt.getText().toString();
            GetTripData(tripSearch);
        } else if (searchTxt.length() > 0) {
            String searchTrip = searchTxt.getText().toString();
            GetTripData(searchTrip);
        }



        VAdapter = new VAdapter(MainActivity.this,id,name,des,date,risk,description);
        recyclerView.setAdapter(VAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }


    void GetTripData(String tripSearch){
        Cursor cursor = dataHelper.readData(tripSearch);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data was found", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.imenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.delete_all_row){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all data ?");
        builder.setMessage("Are you sure you want to delete all data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);
                myDB.deleteAllRow();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}