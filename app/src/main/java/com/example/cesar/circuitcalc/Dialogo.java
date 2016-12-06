package com.example.cesar.circuitcalc;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/*Este documento o elemento forma parte de
la aplicacion CircuitCalc, por lo cual, esta
licenciado y protegido bajo las mismas directrices
escritas en el documento COPYING dentro de la
directorio raiz o principal de este proyecto*/

//TODO: No modifique esta clase.

public class Dialogo extends DialogFragment  {

    private EditText editText;
    private RadioButton fuente, elemento;
    private int selecciones = 1;

    public interface datosguardados{
        void finalizar(String texto, int seleccion);
    }

    public Dialogo(){
        //Constructor necesario
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflamos el dialogo
        final View view = inflater.inflate(R.layout.dialog_personalizado, container);
        editText = (EditText) view.findViewById(R.id.datosNumeros);
        Button aceptar = (Button) view.findViewById(R.id.acept);
        fuente =(RadioButton) view.findViewById(R.id.radioButton);
        elemento =(RadioButton) view.findViewById(R.id.radioButton2);
        elemento.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        fuente.setChecked(false);
                        selecciones = 2;
                    }
                }
        );

        fuente.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        elemento.setChecked(false);
                        selecciones = 1;
                    }
                }
        );
        editText.requestFocus();
        fuente.setChecked(true);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Valores");
        aceptar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (editText.length() > 0 && !(editText.getText().toString().equals("0")) && ((Integer.parseInt(editText.getText().toString())) <= 10)){
                            datosguardados activity = (datosguardados) getActivity();
                            activity.finalizar(editText.getText().toString(), selecciones);
                            dismiss();

                        } else{
                            Snackbar.make(view, "Ingrese un numero valido", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
        );
        return view;
    }

}


