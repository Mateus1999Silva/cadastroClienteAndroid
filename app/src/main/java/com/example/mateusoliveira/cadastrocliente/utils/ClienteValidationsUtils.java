package com.example.mateusoliveira.cadastrocliente.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class ClienteValidationsUtils {

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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = new Date();
        try {
            if (date.equals(null) || date.getText().toString().isEmpty()) {
                date.setError("Preencha o campo de data");
                return false;
            } else if (sdf.parse(date.getText().toString()).getTime() > dataAtual.getTime()) {
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

    public static boolean validateCPF(EditText editCpf) {
        String CPF = editCpf.getText().toString().replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll(" ", "")
                .replaceAll(",", "");

        if (!sequenceIsInvalid(CPF)) {
            editCpf.setError("CPF Inválido");
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                editCpf.setError("Cpf Inválido");
                return (false);
            }
        } catch (InputMismatchException erro) {
            editCpf.setError("Cpf Inválido");
            return (false);
        }

    }

    public static boolean sequenceIsInvalid(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11)) {
            return false;
        }
        return true;
    }

    public static boolean cepIsValid(String cep) {
        String cepReplace = cep.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll(" ", "")
                .replaceAll(",", "");
        if (cepReplace.length() == 8)
            return true;
        else
            return false;
    }
}
