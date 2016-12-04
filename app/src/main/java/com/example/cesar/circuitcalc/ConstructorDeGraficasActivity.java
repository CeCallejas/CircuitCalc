package com.example.cesar.circuitcalc;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ConstructorDeGraficasActivity extends AppCompatActivity {
    int numeroResistencias;
    String[] valores;
    int llamada;
    Double fuente;
    Double respuestas;
    TextView etiquetaRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltaje_serie);
        etiquetaRespuesta = (TextView)findViewById(R.id.respuestaEtiqueta);

        respuestas = 0.00;
        Intent intent = getIntent();
        numeroResistencias = Integer.valueOf(intent.getStringExtra("numeroResistencias"));
        valores = intent.getStringArrayExtra("valores");
        llamada = intent.getIntExtra("botonLLamada", 1);
        fuente = intent.getDoubleExtra("fuente", 0.00);
        int incognita = intent.getIntExtra("incognita", 1);

        int tamañoArregle = valores.length;
        double suma = 0;
        for(int i = 0; i < tamañoArregle; i++){
            suma += Double.valueOf(valores[i]) ;
        }

        if (incognita == 1) {
            respuestas = suma;
        }else{
            respuestas = fuente - suma;
        }

        etiquetaRespuesta.setText(String.valueOf(respuestas));

        if (llamada == 1) {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.cann);
            GraficaCorriente grafico = new GraficaCorriente(this);
            grafico.setNumeroResistencias(numeroResistencias);
            grafico.setValores(valores);
            grafico.setIncognita(incognita);
            grafico.setVoltaje(fuente);
            grafico.setRespuestas(respuestas);
            frameLayout.addView(grafico);
        } else {

            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.cann);
            GraficaVoltaje grafico = new GraficaVoltaje(this);
            grafico.setNumeroResistencias(numeroResistencias);
            grafico.setValores(valores);
            grafico.setIncognita(incognita);
            grafico.setVoltaje(fuente);
            grafico.setRespuestas(respuestas);
            frameLayout.addView(grafico);

        }

    }



}


