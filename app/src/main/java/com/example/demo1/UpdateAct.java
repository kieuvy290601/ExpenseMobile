package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class UpdateAct extends AppCompatActivity {

    EditText add_Name, add_Des,  add_Desc;
    RadioGroup radioGroup;
    RadioButton radioSelect;
    Button update_Button, delete_Button, see_Expense;
    TextView add_Date;

    String id, name, des, date, risk, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        add_Name = findViewById(R.id.update_Name);
        add_Des = findViewById(R.id.update_Des);
        add_Date = findViewById(R.id.update_Date);
        radioGroup = findViewById(R.id.update_radioG);
        add_Desc = findViewById(R.id.update_Desc);

        update_Button = findViewById(R.id.update_Button);
        delete_Button = findViewById(R.id.delete_Button);
        see_Expense = findViewById(R.id.see_Expense);
        getIntentData();

        update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( UpdateAct.this, MainActivity.class);
                startActivity(intent);

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioSelect = findViewById(selectedId);
                String radioText = radioSelect.getText().toString();

                //And only then we call this
                DatabaseHelper myDB = new DatabaseHelper(UpdateAct.this);
                name = add_Name.getText().toString().trim();
                des = add_Des.getText().toString().trim();
                date = add_Date.getText().toString().trim();
                risk = radioText;
                description = add_Desc.getText().toString().trim();
                boolean result =  myDB.updateData(id, name, des,date,risk, description);
                if(result) {
                    Toast.makeText(UpdateAct.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateAct.this, "Failed Update", Toast.LENGTH_SHORT).show();
                }

            }
        });

        see_Expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( UpdateAct.this, AddExpense.class);
                intent.putExtra("tripID", id);
                startActivity(intent);
            }
        });

        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( UpdateAct.this, MainActivity.class);
                startActivity(intent);
                DatabaseHelper myDB = new DatabaseHelper(UpdateAct.this);
                myDB.deleteARow(id);
                boolean result =  myDB.updateData(id, name, des,date,risk, description);
                if(result) {
                    Toast.makeText(UpdateAct.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateAct.this, "Failed Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showDate(View v){
        DialogFragment newFragment = new UpdateDate();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void upDate(LocalDate date){
        TextView textDate = (TextView)findViewById(R.id.update_Date);
        //EditText editText = (EditText) findViewById(R.id.add_Date);
        textDate.setText(date.toString());
       // editText.getText(update_Date);
    }
    void getIntentData(){

        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("des") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("risk") && getIntent().hasExtra("description")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            des = getIntent().getStringExtra("des");
            date = getIntent().getStringExtra("date");
            risk = getIntent().getStringExtra("risk");
            if(risk.equals("Yes")){
                RadioButton yes = findViewById(R.id.yes);
                yes.setChecked(true);
            }else{
                RadioButton no = findViewById(R.id.no);
                no.setChecked(true);
            }

            /*risk = getIntent().getStringExtra("risk");*/
            description = getIntent().getStringExtra("description");

            //Setting Intent Data
            add_Name.setText(name);
            add_Des.setText(des);
            add_Date.setText(date);
            add_Desc.setText(description);
            Log.d("stev", name+" "+des+" "+date+"  "+description);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}