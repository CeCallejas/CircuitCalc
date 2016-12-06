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

public class PotenciaActivity extends AppCompatActivity {

    Spinner listaCalculos;
    EditText vatios, amperes, voltios;
    TextView respuesta;
    int selec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potencia);

        listaCalculos = (Spinner) findViewById(R.id.selector);
        vatios = (EditText) findViewById(R.id.vatios);
        amperes = (EditText) findViewById(R.id.amperios);
        voltios = (EditText) findViewById(R.id.voltios);
        respuesta = (TextView) findViewById(R.id.respuestasFinal);


        String[] calculos = {"Calcular Corriente", "Calcular Potencia", "Calcular Voltaje"};
        listaCalculos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, calculos));
        listaCalculos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selec = position;
                switch (position){
                    case 0:
                        vatios.setEnabled(true);
                        voltios.setEnabled(true);
                        amperes.setEnabled(false);

                        vatios.setText("");
                        voltios.setText("");
                        amperes.setText("");
                        break;
                    case 1:
                        vatios.setEnabled(false);
                        voltios.setEnabled(true);
                        amperes.setEnabled(true);

                        vatios.setText("");
                        voltios.setText("");
                        amperes.setText("");
                        break;

                    case 2:
                        vatios.setEnabled(true);
                        voltios.setEnabled(false);
                        amperes.setEnabled(true);

                        vatios.setText("");
                        voltios.setText("");
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
        Double p;
        Double a ;
        Double v;

        if (vatios.length() == 0){
            p = 0.00;
        }else{
            p = Double.valueOf(vatios.getText().toString());
        }

        if(amperes.length() == 0){
            a = 0.00;
        }else{
            a = Double.valueOf(amperes.getText().toString());
        }

        if (voltios.length() == 0){
            v = 0.00;
        }else{
            v = Double.valueOf(voltios.getText().toString());
        }

        switch (selec){
            case 0:
                if(v > 0){
                    respuesta.setText("Corriente= " + String.valueOf(p / v));
                    break;
                }else{
                    Snackbar.make(view, "Voltaje no valida", Snackbar.LENGTH_LONG).show();
                }
                break;
            case 1:
                respuesta.setText("Potencia= " + String.valueOf(a * v));
                break;
            case 2:
                if (a > 0){
                    respuesta.setText("Voltaje= " + String.valueOf(p/a));
                }else{
                    Snackbar.make(view, "Corriente no valida", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
