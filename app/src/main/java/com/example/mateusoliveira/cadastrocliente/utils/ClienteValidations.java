package com.example.mateusoliveira.cadastrocliente.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClienteValidations {

    public static boolean EditEmpty(EditText editValidation) {
        if (editValidation.getText().toString().isEmpty()) {
            editValidation.setError("Campo Vazio, Preencha as informações");
            editValidation.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean dates(TextView date) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataValidation = sdf.parse(date.getText().toString());

            Date dataAtual = new Date();
            if (date.length() == 0 || dataValidation.getTime() > dataAtual.getTime()) {
                date.setError("Data inválida, não pode ser maior que a data de hoje");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean connectionInternet(Context context, List<EditText> camposEditText) {
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {

            return true;
        } else {
            for (EditText campoCep : camposEditText) {
                campoCep.setFocusable(true);
                campoCep.setError("Sem acesso a internet, preencha as informações");
            }
            return false;
        }
    }
}

