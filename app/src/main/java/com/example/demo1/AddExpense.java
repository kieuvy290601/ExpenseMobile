package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;

public class AddExpense extends AppCompatActivity {

    EditText textAmount;
    TextView textDate;
    Button addEx_Button;

    private final String[] typeOfExpense = {
            "Choose",
            "Food",
            "Drink",
            "Travel",
            "Shopping"
    };
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        textDate = findViewById(R.id.textDate);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> DAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, typeOfExpense);
        DAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((DAdapter));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("DAdapter", (String) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void expenseDatePickerDialog(View v){
        DialogFragment newFragment = new UpdateTime();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void upTime(LocalDate date){
        TextView textDate = (TextView) findViewById(R.id.add_Time);
        textDate.setText(date.toString());
    }
}