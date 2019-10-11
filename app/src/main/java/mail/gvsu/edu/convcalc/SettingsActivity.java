package mail.gvsu.edu.convcalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class  SettingsActivity extends AppCompatActivity {
    private String fromSelector1 = "";
    private String toSelector1 = "";
    private String fromSelector2 = "";
    private String toSelector2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String unit1 = i.getStringExtra("unit1");
        String unit2 = i.getStringExtra("unit2");

        TextView fromUnits = (TextView) findViewById(R.id.fromLabel2);
        TextView toUnits = (TextView) findViewById(R.id.toLabel2);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(MainActivity.mode == 0) {
                    intent.putExtra("length", fromSelector1);
                    intent.putExtra("length1", toSelector1);
                    setResult(MainActivity.VICE_SELECTION1, intent);
                }else if(MainActivity.mode == 1)
                {
                    intent.putExtra("volume", fromSelector2);
                    intent.putExtra("volume1", toSelector2);
                    setResult(MainActivity.VICE_SELECTION2, intent);
                }

                finish();
                //}

            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.fromChoice);
        Spinner spinner1 = (Spinner) findViewById(R.id.toChoice);
        ArrayAdapter<CharSequence> fromAdapter;
        ArrayAdapter<CharSequence> toAdapter;

        if(MainActivity.mode == 0) {
             fromAdapter = ArrayAdapter.createFromResource(this,
                    R.array.length, android.R.layout.simple_spinner_item);
             toAdapter = ArrayAdapter.createFromResource(this,
                     R.array.length1, android.R.layout.simple_spinner_item);

             fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

             spinner.setAdapter(fromAdapter);
             spinner1.setAdapter(toAdapter);

             int spinnerPosition = fromAdapter.getPosition(unit1);
             int spinner1Position = toAdapter.getPosition(unit2);
             spinner.setSelection(spinnerPosition);
             spinner1.setSelection(spinner1Position);

             spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fromSelector1 = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
             spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    toSelector1 = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        if(MainActivity.mode == 1) {
            fromAdapter = ArrayAdapter.createFromResource(this,
                    R.array.volume, android.R.layout.simple_spinner_item);
            toAdapter = ArrayAdapter.createFromResource(this,
                    R.array.volume1, android.R.layout.simple_spinner_item);

            fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(fromAdapter);
            spinner1.setAdapter(toAdapter);


            int spinnerPosition = fromAdapter.getPosition(unit1);
            int spinner1Position = toAdapter.getPosition(unit2);
            spinner.setSelection(spinnerPosition);
            spinner1.setSelection(spinner1Position);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fromSelector2 = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    toSelector2 = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    public String getFromSelector1() {
        return fromSelector1;
    }

    public String getFromSelector2() {
        return fromSelector2;
    }

    public String getToSelector1() {
        return toSelector1;
    }

    public String getToSelector2() {
        return toSelector2;
    }
}
