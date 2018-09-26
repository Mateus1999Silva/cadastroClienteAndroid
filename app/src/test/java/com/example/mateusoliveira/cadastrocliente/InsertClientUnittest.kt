package com.example.mateusoliveira.cadastrocliente

import com.example.mateusoliveira.cadastrocliente.utils.ClienteValidationsUtils
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

class InsertClientUnittest {

    @Test
    fun `test validator cpf`() {
        //given
        val correctCpf = "43924487898";
        //when
        val result = ClienteValidationsUtils.validateCPF(correctCpf);
        // then
        assertEquals(true, result);
    }
}