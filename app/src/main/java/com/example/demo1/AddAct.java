package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;

public class AddAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


    }

    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDate(LocalDate date){
        TextView textDate = (TextView) findViewById(R.id.add_Date);
        textDate.setText(date.toString());
    }

}
