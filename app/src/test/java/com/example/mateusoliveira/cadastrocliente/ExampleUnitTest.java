package com.example.mateusoliveira.cadastrocliente;

import android.widget.EditText;

import com.example.mateusoliveira.cadastrocliente.utils.ClienteValidationsUtils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void validationCpf(){
        boolean actual = ClienteValidationsUtils.sequenceIsInvalid("1111111111");
        boolean expected = false;
        assertEquals(actual, expected);
    }
}