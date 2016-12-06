package com.example.cesar.circuitcalc;

/*Este documento o elemento forma parte de
la aplicacion CircuitCalc, por lo cual, esta
licenciado y protegido bajo las mismas directrices
escritas en el documento COPYING dentro de la
directorio raiz o principal de este proyecto*/

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class TomaDeDatosActivity extends AppCompatActivity {
    int posicionAnterior = 0;
    ListView lv;
    ArrayList<ListadoResistencias> listadoResistencias;
    EditText editRes,editVolt;
    TextView etiquetaResistencia, etiquetaFuente;
    String[] resNum;
    String[] valores;
    int num = 1;
    int botonDondeFueLlamado;
    String unidad;
    String regreso;
    int incognita;
    double respuesta;
    double voltaje;
    Spinner spinner;
    int posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toma_de_datos);
        spinner = (Spinner) findViewById(R.id.spinner);
        lv = (ListView) findViewById(R.id.listado_de_resistencias);
        editRes = (EditText) findViewById(R.id.datosR);
        editVolt = (EditText) findViewById(R.id.voltajeTexto);
        etiquetaResistencia = (TextView) findViewById(R.id.etiqueta);
        etiquetaFuente = (TextView) findViewById(R.id.etiquetaFuente);

        voltaje = 0;

        Intent intent = getIntent();
        regreso = intent.getStringExtra("numeroResistencias");
        incognita = intent.getIntExtra("incognita", 1);
        botonDondeFueLlamado = intent.getIntExtra("qCalculo",1);
        num = Integer.parseInt(regreso) ;
        listadoResistencias = new ArrayList<ListadoResistencias>();

        if (incognita == 1){
            editVolt.setEnabled(false);
            editVolt.setText("?");
        }else {
            num = num -1;
        }

        if (botonDondeFueLlamado == 1){
            etiquetaFuente.setText("Corriente de Fuente");
            etiquetaResistencia.setText("Valor de corriente");
            editRes.setHint("A");
            unidad = "A";

        }else{
            unidad = "V";
        }


        resNum = new String[num];
        valores = new String[num];

        for (int i = 0; i < num; i++){
            resNum[i] = String.valueOf(i + 1);
            valores[i] = "0";
            ListadoResistencias cat = new ListadoResistencias(String.valueOf(i), "Resistencia Nº " + (i+1), valores[i] + unidad);
            listadoResistencias.add(cat);
        }

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resNum));
        actualizacion();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                actualizarListado();
                posicion = position;
                editRes.getText().clear();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        editRes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valores[posicion]= editRes.getText().toString();
                ListadoResistencias cat = new ListadoResistencias(String.valueOf(posicion), "Resistencia Nº " + (posicion+1), valores[posicion]);
                listadoResistencias.remove(posicion);
                listadoResistencias.add(posicion, cat);
                actualizarListado();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void actualizacion(){

    }


    public void actualizarListado(){
        AdapterListadoResistencias adapter = new AdapterListadoResistencias(this, listadoResistencias);
        lv.setAdapter(adapter);
    }

    public void ejecutar(View view){
        actualizacion();

        if(incognita == 1){
            Intent intent = new Intent(this, ConstructorDeGraficasActivity.class);
            intent.putExtra("numeroResistencias", regreso);
            intent.putExtra("valores", valores);
            intent.putExtra("incognita", incognita);
            intent.putExtra("respuesta", respuesta);
            intent.putExtra("botonLLamada", botonDondeFueLlamado);

            startActivity(intent);

        }else{
            if (editVolt.length() > 0 && !(editVolt.getText().toString().equals("0"))){
                voltaje = Integer.valueOf(editVolt.getText().toString());
            }

            if(voltaje == 0){
                Snackbar.make(view, "Ingrese un Voltaje Valido", Snackbar.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(this, ConstructorDeGraficasActivity.class);
                intent.putExtra("numeroResistencias", regreso);
                intent.putExtra("valores", valores);
                intent.putExtra("incognita", incognita);
                intent.putExtra("respuesta", respuesta);
                intent.putExtra("botonLLamada", botonDondeFueLlamado);
                intent.putExtra("fuente", voltaje);

                startActivity(intent);
            }

        }


    }

}