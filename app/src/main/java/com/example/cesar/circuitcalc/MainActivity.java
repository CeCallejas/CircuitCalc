package com.example.cesar.circuitcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Dialogo.datosguardados {
    int select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alerta(View view){
        select = 1;
        Dialogo dialogos = new Dialogo();
        dialogos.show(getSupportFragmentManager(),"Fragmento");
    }


    public void  alerta2(View view){
        select = 2;
        Dialogo dialogos = new Dialogo();
        dialogos.show(getSupportFragmentManager(),"Fragmento");
    }

    @Override
    public void finalizar(String texto, int seleccion) {
        Intent intent = new Intent(this, TomaDeDatosActivity.class);
        intent.putExtra("numeroResistencias", texto);
        intent.putExtra("incognita", seleccion);
        intent.putExtra("qCalculo", select);
        startActivity(intent);
    }

    public void ejecutar(View view){

    }
}
