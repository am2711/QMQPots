package ldq.app.com.qmqpots;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterFragment extends Fragment {

    Button btn_submit, btn_show, btn_team;
    EditText editText_name;
    Spinner spinner_pots;
    public String pots;
    DatabaseHandler db;

    public RegisterFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        db = new DatabaseHandler(getContext());

        btn_submit = (Button)rootView.findViewById(R.id.btn_submit);
        btn_show = (Button)rootView.findViewById(R.id.btn_show);
        btn_team = (Button)rootView.findViewById(R.id.btn_team);

        editText_name = (EditText)rootView.findViewById(R.id.editText_name);
        spinner_pots = (Spinner)rootView.findViewById(R.id.spinner_pots);

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

        spinner_pots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pots = new String(parent.getItemAtPosition(position).toString());
                Toast.makeText(getContext(),pots, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> categories = new ArrayList<String>();

        categories.add("Pot 1");
        categories.add("Pot 2");
        categories.add("Pot 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pots.setAdapter(dataAdapter);

        return rootView;
    }

    public void submit() {

        String name = new String(editText_name.getText().toString());

        if (name.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in name!", Toast.LENGTH_SHORT).show();
        }
        else {

            Details details = new Details(name, pots);

            //DatabaseHandler db = new DatabaseHandler(this);
            Log.d("Inserting... ", "Inserting...");

            db.addDetails(details,getContext());
        }

        Toast.makeText(getContext(),"Registered",Toast.LENGTH_SHORT).show();
    }

    public void showRegs(){

        Intent intent = new Intent(getContext(), ShowRegs.class);
        startActivity(intent);
    }

    public void teamSelector(){

        Intent intent = new Intent(getContext(), TeamSelector.class);
        startActivity(intent);
    }
}
