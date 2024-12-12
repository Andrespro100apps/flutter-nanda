package com.nursing.nandapro.nicnoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {

    private EditText uno, dos;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        uno = (EditText) findViewById(R.id.sumagoteo);
        dos = (EditText) findViewById(R.id.restagoteo);
        result = (TextView) findViewById(R.id.resultado);

    }


    public void lanzarcalcular(View view) {

      //  double x,y,r;
        int x=Integer.parseInt(uno.getText().toString());
       int y=Integer.parseInt(dos.getText().toString());
       float abc =((x * 60) / y);
       //int r=x+y;
        result.setText(Float.toString(abc));
}

}