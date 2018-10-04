package com.example.mateusoliveira.cadastrocliente;

import android.widget.EditText;

import com.example.mateusoliveira.cadastrocliente.utils.ClienteValidationsUtils;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertClienteUnitTest {

    @Test
    public void validationCpfInvalid(){
        boolean actual = ClienteValidationsUtils.sequenceIsInvalid("1111111111");
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void validationCpfSucess(){
        boolean actual = ClienteValidationsUtils.sequenceIsInvalid("32386767841");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void campoEstaVazio(){
        boolean actual = ClienteValidationsUtils.editEmpty("");
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void campoNaoEstaVazio(){
        boolean actual = ClienteValidationsUtils.editEmpty("Mateus");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void cpfValido(){
        boolean actual = ClienteValidationsUtils.validateCPF("43924487898");
        boolean expected = true;
        assertEquals(actual, expected);
    }
    @Test
    public void cpfInvalido(){
        boolean actual = ClienteValidationsUtils.validateCPF("4392448789");
        boolean expected = false;
        assertEquals(actual,expected);
    }


}