package com.example.cesar.circuitcalc;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class LeyOhmActivity extends AppCompatActivity {
    Spinner listaCalculos;
    EditText voltios, amperes, ohmnios;
    TextView respuesta;
    int selec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ley_ohm);

        listaCalculos = (Spinner) findViewById(R.id.selector);
        voltios = (EditText) findViewById(R.id.voltios);
        amperes = (EditText) findViewById(R.id.amperios);
        ohmnios = (EditText) findViewById(R.id.Ohmnios);
        respuesta = (TextView) findViewById(R.id.respue);


        String[] calculos = {"Calcular Corriente", "Calcular Voltaje", "Calcular Resistencia"};
        listaCalculos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, calculos));
        listaCalculos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selec = position;
                switch (position){
                    case 0:
                        voltios.setEnabled(true);
                        ohmnios.setEnabled(true);
                        amperes.setEnabled(false);

                        voltios.setText("");
                        ohmnios.setText("");
                        amperes.setText("");
                        break;
                    case 1:
                        voltios.setEnabled(false);
                        ohmnios.setEnabled(true);
                        amperes.setEnabled(true);

                        voltios.setText("");
                        ohmnios.setText("");
                        amperes.setText("");
                        break;

                    case 2:
                        voltios.setEnabled(true);
                        ohmnios.setEnabled(false);
                        amperes.setEnabled(true);

                        voltios.setText("");
                        ohmnios.setText("");
                        amperes.setText("");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void calcular(View view){
        Double v = Double.valueOf(voltios.getText().toString());
        Double a = Double.valueOf(amperes.getText().toString());
        Double r = Double.valueOf(ohmnios.getText().toString());
        switch (selec){
            case 1:
                if(r > 0){
                    respuesta.setText(String.valueOf(v / r));
                    break;
                }else{
                    Snackbar.make(view, "Resistencia no valida", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
