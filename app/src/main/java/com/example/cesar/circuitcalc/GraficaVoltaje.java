package com.example.cesar.circuitcalc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

/*Este documento o elemento forma parte de
la aplicacion CircuitCalc, por lo cual, esta
licenciado y protegido bajo las mismas directrices
escritas en el documento COPYING dentro de la
directorio raiz o principal de este proyecto*/

public class GraficaVoltaje extends View {
    private int numeroResistencias = 1;
    private String[] valores;
    private int w, h;
    private int incongnita;
    private double voltaje;
    private double respuestas;

    public GraficaVoltaje(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        @SuppressLint("DrawAllocation") Paint paint2 = new Paint();
        @SuppressLint("DrawAllocation") Paint paint3 = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        paint2.setColor(Color.YELLOW);
        paint3.setColor(Color.BLACK);
        paint3.setTextSize(25);
        paint3.setTypeface(Typeface.SANS_SERIF);

        int alto = h;
        int ancho = w;

        float margenSuperior = alto * 0.06f;
        float margenInferior = alto * 0.94f;
        float margenIzquierdo = ancho * 0.06f;
        float margenDerecho = ancho * 0.94f;
        float espacioSuperiorFuente = (alto / 2) - 20;
        float espacioInferiorFuente = (alto / 2) + 20;
        float  yRectInicial, yRectFinal;


        //Grafico background
        canvas.drawPaint(paint2);

        //Lineas Verticales
        canvas.drawLine(margenIzquierdo, margenSuperior , margenIzquierdo, espacioSuperiorFuente, paint);
        canvas.drawLine(margenIzquierdo, espacioInferiorFuente , margenIzquierdo, margenInferior, paint);
        canvas.drawLine(margenDerecho, margenSuperior , margenDerecho, margenInferior, paint);

        //Lineas Horizontales
        canvas.drawLine(margenDerecho, margenSuperior , margenIzquierdo, margenSuperior, paint);
        canvas.drawLine(margenDerecho, margenInferior , margenIzquierdo, margenInferior, paint);

        //Fuente
        canvas.drawLine(margenIzquierdo - 20, espacioSuperiorFuente , (margenIzquierdo + 20), espacioSuperiorFuente, paint);
        canvas.drawLine(margenIzquierdo - 15, espacioInferiorFuente , margenIzquierdo + 15, espacioInferiorFuente, paint);

        //Asignacion de espacio de resistencia
        float tamañoResistencia = ((alto * 0.88f ) / numeroResistencias) - (alto * 0.05f);
        float espaciado = ((alto * 0.88f) - (tamañoResistencia * numeroResistencias)) / (numeroResistencias + 1) ;
        yRectInicial = margenSuperior + espaciado;
        yRectFinal = yRectInicial + tamañoResistencia;

        for (int i = 0; i <numeroResistencias; i++){
            canvas.drawRect(margenDerecho - 10,yRectInicial, margenDerecho + 10, yRectFinal, paint3);

            if(incongnita == 2){
                if((i <(numeroResistencias - 1))){
                    canvas.drawText(valores[i], margenDerecho - 70, yRectInicial, paint3 );
                } else {
                    paint3.setColor(Color.RED);
                    canvas.drawText(String.valueOf(respuestas), margenDerecho - 70, yRectInicial, paint3 );
                    String volt = voltaje + " V";
                    paint3.setColor(Color.BLACK);
                    canvas.drawText(volt, margenIzquierdo + 5, espacioSuperiorFuente -10, paint3 );
                }
            }else {

                canvas.drawText(valores[i], margenDerecho - 70, yRectInicial, paint3 );
                String volt = respuestas + " V";
                paint3.setColor(Color.RED);
                canvas.drawText(volt, margenIzquierdo + 5, espacioSuperiorFuente -10, paint3 );
                paint3.setColor(Color.BLACK);
            }


            yRectInicial += espaciado + tamañoResistencia;
            yRectFinal +=espaciado + tamañoResistencia;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.w = w;
        this.h = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setNumeroResistencias(int numeroResistencias){
        this.numeroResistencias = numeroResistencias;
    }

    public void setValores(String[] valores){
        this.valores = valores;
    }

    public void setIncognita(int incognita){
        this.incongnita = incognita;
    }

    public void setVoltaje(double voltaje) {
        this.voltaje = voltaje;
    }

    public void setRespuestas(double respuestas) {
        this.respuestas = respuestas;
    }
}
