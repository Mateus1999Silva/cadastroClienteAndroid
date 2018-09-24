package com.example.mateusoliveira.cadastrocliente;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.mateusoliveira.cadastrocliente.Mvp.ListCliente.ListClienteView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeleteClienteRecyclerViewInstrumentedTest {

    @Rule
    public ActivityTestRule<ListClienteView> activityTestRule = new ActivityTestRule<>(ListClienteView.class);

    @Test
    public void testeDeleteRecyclerView(){

    }

}
