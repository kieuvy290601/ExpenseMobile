package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class AddAct extends AppCompatActivity {

    EditText add_Name, add_Des, add_Date, add_Desc;
    Button button;
    RadioGroup radioGroup;
    RadioButton yes, no;
    RadioButton radioselect;




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
                DatabaseHelper my_database = new DatabaseHelper(AddAct.this);
                /*ContentValues contentSave = new ContentValues();*/
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioselect = findViewById(selectedId);
                String radioText = radioselect.getText().toString();


                boolean result = my_database.addTrip(add_Name.getText().toString().trim(),
                        add_Des.getText().toString().trim(),
                        add_Date.getText().toString().trim(),
                        radioText,
                        add_Desc.getText().toString().trim());

                if(result) {
                    Toast.makeText(AddAct.this, "thhanh cong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddAct.this, "failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
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






