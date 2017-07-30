package com.example.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private double C2F (double c) {
        return (c*9/5) + 32;
    }
    private double F2C (double f) {
        return (f - 32)*5/9;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView his = (TextView)findViewById(R.id.his);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                EditText input = (EditText)findViewById(R.id.input);
                TextView output = (TextView)findViewById(R.id.output);
                double in = Double.parseDouble(input.getText().toString());
                double res = 0;
                String out = "";
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.F2C) {
                    res = F2C(in);
                    out = "F to C: "+ String.valueOf(in) +" -> "+  String.valueOf(res).format("%.1f",res);
                }
                else {
                    res = C2F(in);
                    out = "C to F: "+ String.valueOf(in) +" -> "+  String.valueOf(res).format("%.1f",res);
                }
                output.setText(String.valueOf(res).format("%.1f",res));
                String pre = his.getText().toString();
                his.setText(out + "\n" + pre);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TextView his = (TextView)findViewById(R.id.his);
        TextView output = (TextView)findViewById(R.id.output);
        outState.putString("HISTORY", his.getText().toString());
        outState.putString("VALUE", output.getText().toString());

        // Call super last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        TextView output = (TextView)findViewById(R.id.output);
        TextView his = (TextView)findViewById(R.id.his);
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        his.setText(savedInstanceState.getString("HISTORY"));
        output.setText(savedInstanceState.getString("VALUE"));
    }

}
