package com.example.demo1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class AddExpense extends AppCompatActivity {

    EditText add_Amount;
    TextView add_Time;
    Button addEx_Button;

    private final String[] typeOfExpense = {
            "Choose",
            "Food",
            "Drink",
            "Travel",
            "Shopping"
    };
    private Spinner spinner;

    private static final String CHECK_AMOUNT = "[a-zA-Z]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        add_Amount = findViewById(R.id.add_Amount);
        add_Time = findViewById(R.id.add_Time);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> EAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, typeOfExpense);
        EAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter((EAdapter));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("EAdapter", (String) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String tripId = getIntent().getStringExtra("id");

        addEx_Button = findViewById(R.id.addEx_Button);
        addEx_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent i = new Intent(AddExpense.this, MainExpenseAct.class);
                i.putExtra("tripId", tripId);
                startActivity(i);*/

                if (error()){
                    getExpense();
                    DatabaseHelper databaseHelper = new DatabaseHelper(AddExpense.this);
                    boolean result = databaseHelper.addExpense(
                            tripId,
                            spinner.getSelectedItem().toString().trim(),
                            add_Amount.getText().toString().trim(),
                            add_Time.getText().toString().trim()
                            );

                    if(result) {
                        Toast.makeText(AddExpense.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddExpense.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                    Intent i = new Intent(AddExpense.this, MainExpenseAct.class);
                    i.putExtra("tripId", tripId);
                    startActivity(i);

                }
            }
        });
    }

    private void getExpense(){
        spinner = findViewById(R.id.spinner);
        add_Amount = findViewById(R.id.add_Amount);
        add_Time = findViewById(R.id.add_Time);

        String strSpinner = spinner.getSelectedItem().toString();
        String strAmount = add_Amount.getText().toString();
        String strTime = add_Time.getText().toString();

        displayAlert(strSpinner, strAmount, strTime);
    }

    private void displayAlert(String strSpinner, String strAmount, String strTime) {
        new AlertDialog.Builder(this).setTitle("Expense Details entered").setMessage("Details entered: " +
                "\n Type: " + strSpinner +
                "\n Amount: " + strAmount +
                "\n Time of Expense: " + strTime
        ).setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in = new Intent(AddExpense.this, MainExpenseAct.class);
                startActivity(in);
            }
        }).show();
    }

    private  boolean error() {
        if (spinner.getSelectedItem().toString() == "Choose"){
            Toast.makeText(AddExpense.this, "Please select the expense type", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (add_Amount.getText().toString().isEmpty()){
            Toast.makeText(AddExpense.this, "Please insert amount", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (add_Time.getText().toString().length()==0){
            Toast.makeText(AddExpense.this, "Please fill the time of expense", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
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