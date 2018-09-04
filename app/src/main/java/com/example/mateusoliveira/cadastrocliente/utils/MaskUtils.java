package com.example.mateusoliveira.cadastrocliente.utils;

import android.widget.EditText;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class MaskUtils {

    public static void putMaskCpf(EditText cpf) {
        MaskEditTextChangedListener maskCpf = new MaskEditTextChangedListener("###.###.###-##", cpf);
        cpf.addTextChangedListener(maskCpf);
    }

    public static void putMaskCep(EditText cep){
        MaskEditTextChangedListener maskCep = new MaskEditTextChangedListener("#####-###", cep);
        cep.addTextChangedListener(maskCep);
    }
}
