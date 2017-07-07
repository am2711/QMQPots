package ldq.app.com.qmqpots;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeamSelector extends Fragment {

    Button btn_show_det;
    EditText editText_2, editText_3;

    public void TeamSelector(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.activity_team_selector, container, false);

        btn_show_det = (Button)rootView.findViewById(R.id.btn_show_det);
        editText_2 = (EditText)rootView.findViewById(R.id.editText_2);
        editText_3 = (EditText)rootView.findViewById(R.id.editText_3);

        btn_show_det.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display();
            }
        });

        return rootView;
    }

    public void display() {

        DatabaseHandler db = new DatabaseHandler(getContext());

        Details details1, details2;

        if (editText_2.getText().toString().isEmpty() || editText_3.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please fill in details", Toast.LENGTH_SHORT).show();
        }

        else {

            details1 = db.getDetails("Pot2", Integer.parseInt(editText_2.getText().toString()));
            details2 = db.getDetails("Pot3", Integer.parseInt(editText_3.getText().toString()));

            AlertDialog.Builder adb = new AlertDialog.Builder(getContext());

            String msg = new String("Pot 2: " + details1.getName() + "\n" + "Pot 3: " + details2.getName());

            adb.setMessage(msg).setCancelable(true).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = adb.create();
            alertDialog.setTitle("Team details");
            alertDialog.show();

            Button alertButton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);

            if (alertButton != null) {
                alertButton.setTextColor(getResources().getColor(R.color.white_btn));
            }
        }
    }
}