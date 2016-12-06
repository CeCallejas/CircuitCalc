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

/*Este documento o elemento forma parte de
la aplicacion CircuitCalc, por lo cual, esta
licenciado y protegido bajo las mismas directrices
escritas en el documento COPYING dentro de la
directorio raiz o principal de este proyecto*/

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
        Double v ;
        Double a ;
        Double r ;

        if (voltios.length() == 0){
            v = 0.00;
        }else{
            v = Double.valueOf(voltios.getText().toString());
        }

        if(amperes.length() == 0){
            a = 0.00;
        }else{
            a = Double.valueOf(amperes.getText().toString());
        }

        if (ohmnios.length() == 0){
            r = 0.00;
        }else{
            r = Double.valueOf(ohmnios.getText().toString());
        }
        switch (selec){
            case 0:
                if(r > 0){
                    respuesta.setText("Corriente= " + String.valueOf(v / r));
                    break;
                }else{
                    Snackbar.make(view, "Resistencia no valida", Snackbar.LENGTH_LONG).show();
                }
                break;
            case 1:
                respuesta.setText("Voltaje= " + String.valueOf(a * r));
                break;
            case 2:
                if (a > 0){
                    respuesta.setText("Resistencia= " + String.valueOf(v/a));
                }else{
                    Snackbar.make(view, "Corriente no valida", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
