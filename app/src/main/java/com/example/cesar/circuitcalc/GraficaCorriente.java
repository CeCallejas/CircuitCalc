package com.example.cesar.circuitcalc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class GraficaCorriente extends View {
    private int numeroResistencias = 10;
    private String[] valores;
    int incongnita;
    private int w ;
    private int h ;
    private double voltaje;
    private double respuestas;

    public GraficaCorriente(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        Paint paint3 = new Paint();
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
        float  xRectInicial, xRectFinal;


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


        float espaciado1 = (ancho * 0.88f) / numeroResistencias ;

        float cuarto = ((alto *0.94f) / 2) / 4;
        float resSuperior = margenSuperior +(((alto *0.88f)/2) - cuarto);
        float resInferior = margenSuperior + (((alto *0.88f)/2) + cuarto);

        xRectInicial = margenIzquierdo + espaciado1;

        float tamañoResistencia = ((alto * 0.88f ) / numeroResistencias) - (alto * 0.05f);
        float espaciado = ((alto * 0.88f) - (tamañoResistencia * numeroResistencias)) / (numeroResistencias + 1) ;
        yRectInicial = margenSuperior + espaciado;
        yRectFinal = yRectInicial + tamañoResistencia;
        for (int i = 0; i <numeroResistencias; i++){
            canvas.drawLine(xRectInicial, margenSuperior , xRectInicial, margenInferior, paint);
            canvas.drawRect(xRectInicial - 10,yRectInicial, xRectInicial + 10, yRectFinal, paint3);
            if(incongnita == 2){
                if((i <(numeroResistencias - 1))){
                    canvas.drawText(valores[i], xRectInicial -50, yRectInicial, paint3 );
                } else {
                    paint3.setColor(Color.RED);
                    canvas.drawText(String.valueOf(respuestas), xRectInicial -50, yRectInicial, paint3 );
                    String corr = voltaje +" A";
                    paint3.setColor(Color.BLACK);
                    canvas.drawText(corr, margenIzquierdo + 5, espacioSuperiorFuente -10, paint3 );
                }
            }else {
                canvas.drawText(valores[i], xRectInicial -50, yRectInicial, paint3 );
                String corr = respuestas +" A";
                paint3.setColor(Color.RED);
                canvas.drawText(corr, margenIzquierdo + 5, espacioSuperiorFuente -10, paint3 );
                paint3.setColor(Color.BLACK);
            }
            yRectInicial += espaciado + tamañoResistencia;
            yRectFinal +=espaciado + tamañoResistencia;
            xRectInicial += espaciado1;


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
