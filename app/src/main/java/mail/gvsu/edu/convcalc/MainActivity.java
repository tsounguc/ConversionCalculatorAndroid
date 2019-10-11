package mail.gvsu.edu.convcalc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
   public static int mode;
   public static final int VICE_SELECTION1 = 1;
   public static final int VICE_SELECTION2 = 2;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText fromTF = (EditText) findViewById(R.id.fromTextField);
        EditText toTF = (EditText) findViewById(R.id.toTextField);
        TextView fromUnits = (TextView) findViewById(R.id.FromUnit);
        TextView toUnits = (TextView) findViewById(R.id.ToUnit);
        TextView title = (TextView) findViewById(R.id.title);

        Button calculateBtn = (Button) findViewById(R.id.calculate_button);
        Button clearBtn = (Button) findViewById(R.id.clear_button);
        Button modeBtn =(Button) findViewById(R.id.mode_button);

        fromTF.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //evalue = "1";
                fromTF.setText("");
                toTF.setText("");
                return false;
            }
        });

        toTF.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //evalue = "2";
                fromTF.setText("");
                toTF.setText("");
                return false;
            }
        });

        calculateBtn.setOnClickListener(v ->{
            hideSoftKeyBoard();
            String fromStr = fromTF.getText().toString();
            String toStr = toTF.getText().toString();
            double fromVal = 0.0;
            double toVal = 0.0;
            if(fromStr.length() == 0 && toStr.length() == 0){
                Snackbar.make(fromTF, "Please enter a value above", Snackbar.LENGTH_INDEFINITE).show();
            }else if(fromStr.length() == 0){
                toVal = Double.parseDouble(toStr);
                if(mode == 0){
                    UnitsConverter.LengthUnits topLabel = UnitsConverter.LengthUnits.valueOf(fromUnits.getText().toString());
                    UnitsConverter.LengthUnits bottomLabel = UnitsConverter.LengthUnits.valueOf(toUnits.getText().toString());
                    fromVal = UnitsConverter.convert(toVal, topLabel, bottomLabel);
                }
                if(mode == 1){
                    UnitsConverter.VolumeUnits topLabel2 = UnitsConverter.VolumeUnits.valueOf(fromUnits.getText().toString());
                    UnitsConverter.VolumeUnits bottomLabel2 = UnitsConverter.VolumeUnits.valueOf(toUnits.getText().toString());
                    fromVal = UnitsConverter.convert(toVal, topLabel2, bottomLabel2);
                }
                fromTF.setText(""+fromVal);
            }else if(toStr.length() == 0){
                fromVal = Double.parseDouble(fromStr);
                if(mode == 0){
                    UnitsConverter.LengthUnits topLabel = UnitsConverter.LengthUnits.valueOf(fromUnits.getText().toString());
                    UnitsConverter.LengthUnits bottomLabel = UnitsConverter.LengthUnits.valueOf(toUnits.getText().toString());
                    toVal = UnitsConverter.convert(fromVal, bottomLabel, topLabel);
                }
                if(mode == 1){
                    UnitsConverter.VolumeUnits topLabel2 = UnitsConverter.VolumeUnits.valueOf(fromUnits.getText().toString());
                    UnitsConverter.VolumeUnits bottomLabel2 = UnitsConverter.VolumeUnits.valueOf(toUnits.getText().toString());
                    toVal = UnitsConverter.convert(fromVal, bottomLabel2, topLabel2);
                }
                toTF.setText(""+toVal);
            }
        });

        clearBtn.setOnClickListener(v -> {
            hideSoftKeyBoard();
            fromTF.setText("");
            toTF.setText("");
        });

        modeBtn.setOnClickListener(v -> {
            hideSoftKeyBoard();
            fromTF.setText("");
            toTF.setText("");
            if(mode == 0 ){
                mode = 1;
                title.setText("Volume Converter");
                fromUnits.setText("Gallons");
                toUnits.setText("Liters");
            }else{
                mode = 0;
                title.setText("Lenghth Converter");
                fromUnits.setText("Yards");
                toUnits.setText("Meters");
            }

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mode == 0) {
            if (resultCode == VICE_SELECTION1) {
                TextView fUnit = (TextView) findViewById(R.id.FromUnit);
                fUnit.setText(data.getStringExtra("length"));
                TextView tUnit = (TextView) findViewById(R.id.ToUnit);
                tUnit.setText(data.getStringExtra("length1"));
            }
            if (resultCode == VICE_SELECTION2) {
                TextView fUnit = (TextView) findViewById(R.id.FromUnit);
                fUnit.setText(data.getStringExtra("volume"));
                TextView tUnit = (TextView) findViewById(R.id.ToUnit);
                tUnit.setText(data.getStringExtra("volume1"));
            }
        }else if (mode == 1){
            if (resultCode == VICE_SELECTION1) {
                TextView fUnit = (TextView) findViewById(R.id.FromUnit);
                fUnit.setText(data.getStringExtra("volume"));
                TextView tUnit = (TextView) findViewById(R.id.ToUnit);
                tUnit.setText(data.getStringExtra("volume1"));
            }
            if (resultCode == VICE_SELECTION2) {
                TextView fUnit = (TextView) findViewById(R.id.FromUnit);
                fUnit.setText(data.getStringExtra("volume"));
                TextView tUnit = (TextView) findViewById(R.id.ToUnit);
                tUnit.setText(data.getStringExtra("volume1"));
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.settings_item)
        {
            SettingsActivity settingsActivity = new SettingsActivity();
            TextView fromUnit = (TextView) findViewById(R.id.FromUnit);
            TextView toUnit = (TextView) findViewById(R.id.ToUnit);
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            intent.putExtra("unit1", fromUnit.getText());
            intent.putExtra("unit2", toUnit.getText());
            startActivityForResult(intent,VICE_SELECTION1);
//            startActivityForResult(intent,VICE_SELECTION2);
        }else {
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
