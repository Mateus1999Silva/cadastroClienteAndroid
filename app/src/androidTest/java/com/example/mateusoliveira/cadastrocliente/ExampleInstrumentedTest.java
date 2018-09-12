package com.example.mateusoliveira.cadastrocliente;

import android.net.ConnectivityManager;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente.ClienteCadastroView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<ClienteCadastroView> mActivityRule = new ActivityTestRule<>(ClienteCadastroView.class);

    @Test
    public void testInsertClientDadosValidos() {
        onView(withId(R.id.editNomeCompleto)).perform(replaceText("Mateus"), closeSoftKeyboard());
        onView(withId(R.id.editCpf)).perform(replaceText("43924487898"), closeSoftKeyboard());
        onView(withId(R.id.editCep)).perform(replaceText("08190420"), closeSoftKeyboard());
        onView(withId(R.id.editNumero)).perform(replaceText("833"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

    }
}