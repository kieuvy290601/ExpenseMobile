package com.example.demo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class AddAct extends AppCompatActivity {

    EditText add_Name, add_Des, add_Desc;
    Button button;
    RadioGroup radioGroup;
    RadioButton yes, no;
    RadioButton radioSelect;
    TextView add_Date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add_Name = findViewById(R.id.add_Name);
        add_Des = findViewById(R.id.add_Des);
        add_Date = findViewById(R.id.add_Date);
        add_Desc = findViewById(R.id.add_Desc);
        radioGroup = findViewById(R.id.radioGroup);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (error()){
                    /*Intent intent = new Intent( AddAct.this, MainActivity.class);
                    startActivity(intent);*/
                    getInputs();


                    DatabaseHelper my_database = new DatabaseHelper(AddAct.this);
                    /*ContentValues contentSave = new ContentValues();*/
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioSelect = findViewById(selectedId);
                    String radioText = radioSelect.getText().toString();


                    boolean result = my_database.addTrip(add_Name.getText().toString().trim(),
                            add_Des.getText().toString().trim(),
                            add_Date.getText().toString().trim(),
                            radioText,
                            add_Desc.getText().toString().trim());

                    if(result) {
                        Toast.makeText(AddAct.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddAct.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                };

            }
            private  boolean error() {
                if (add_Name.getText().toString().length()==0){
                    Toast.makeText(AddAct.this, "Please fill name of the trip", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (add_Des.getText().toString().length()==0){
                    Toast.makeText(AddAct.this, "Please fill destination", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else if (add_Date.getText().toString().length()==0){
                    Toast.makeText(AddAct.this, "Please fill the date", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else if (!yes.isChecked() && !no.isChecked()){
                    Toast.makeText(AddAct.this, "Please select the risk", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else if (add_Desc.getText().toString().length()==0){
                    Toast.makeText(AddAct.this, "Please fill description", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return  true;
            }
        });
    }
    private void getInputs(){
        EditText add_Name = findViewById(R.id.add_Name);
        EditText add_Des = findViewById(R.id.add_Des);
        TextView add_Date = findViewById(R.id.add_Date);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioSelect = findViewById(selectedId);
        EditText add_Desc = findViewById(R.id.add_Desc);


        String strName = add_Name.getText().toString();
        String strDes = add_Des.getText().toString();
        String strDate = add_Date.getText().toString();
        String radioText = radioSelect.getText().toString();
        String strDesc = add_Desc.getText().toString();


        displayNextAlert(strName, strDes, strDate, radioText, strDesc);
    };

    private void displayNextAlert(String strName, String strDes, String strDate, String radioText, String strDesc) {
        new AlertDialog.Builder(this).setTitle("Details entered").setMessage("Details entered: " +
                "\n Name: " + strName +
                "\n Destination: " + strDes +
                "\n Date: " + strDate +
                "\n Risk: " + radioText +
                "\n Description: " + strDesc
        ).setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in = new Intent(AddAct.this, MainActivity.class);
                startActivity(in);
            }
        }).show();
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






