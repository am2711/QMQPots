package ldq.app.com.qmqpots;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn_submit, btn_show, btn_team;
    EditText editText_name;
    Spinner spinner_pots;
    public String pots;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_show = (Button)findViewById(R.id.btn_show);
        btn_team = (Button)findViewById(R.id.btn_team);

        editText_name = (EditText)findViewById(R.id.editText_name);
        spinner_pots = (Spinner)findViewById(R.id.spinner_pots);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegs();
            }
        });
        btn_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamSelector();
            }
        });

        spinner_pots.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();

        categories.add("Pot 1");
        categories.add("Pot 2");
        categories.add("Pot 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pots.setAdapter(dataAdapter);
    }

    public void submit() {

        String name = new String(editText_name.getText().toString());

        if (name.isEmpty()) {
            Toast.makeText(this, "Please fill in name!", Toast.LENGTH_SHORT).show();
        }
        else {

            Details details = new Details(name, pots);

            //DatabaseHandler db = new DatabaseHandler(this);
            Log.d("Inserting... ", "Inserting...");

            db.addDetails(details,this);
        }

        Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        pots = new String(parent.getItemAtPosition(position).toString());


        Toast.makeText(this,pots,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showRegs(){

        Intent intent = new Intent(this, ShowRegs.class);
        startActivity(intent);
    }

    public void teamSelector(){

        Intent intent = new Intent(this, TeamSelector.class);
        startActivity(intent);
    }
}
