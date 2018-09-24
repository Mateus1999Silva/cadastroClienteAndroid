package com.example.mateusoliveira.cadastrocliente;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente.ClienteCadastroView;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InsertClienteInstrumentedTest {

    @Rule
    public ActivityTestRule<ClienteCadastroView> mActivityRule = new ActivityTestRule<>(ClienteCadastroView.class);

    @Test
    public void testInsertClientDadosValidos() {
        onView(withId(R.id.editNomeCompleto)).perform(typeText("Mateus"), closeSoftKeyboard());
        onView(withId(R.id.editCpf)).perform(typeText("43924487898"), closeSoftKeyboard());
        onView(withId(R.id.textDataNascimento)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2018, 5, 23));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.editCep)).perform(typeText("08190420"), closeSoftKeyboard());
        onView(withId(R.id.editNumero)).perform(typeText("899"), closeSoftKeyboard());
        onView(withId(R.id.editComplemento)).perform(typeText("Bloco a"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void testInsertClienteDadosInvalidosEInternetDisconectada() {
        //Validação campo nome vazio
        onView(withId(R.id.button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editNomeCompleto)).check(matches(hasErrorText("Campo vazio, preencha a informação")));
        onView(withId(R.id.editNomeCompleto)).perform(typeText("Mateus Oliveira"), closeSoftKeyboard());

        //Validação campo cpf vazio
        onView(withId(R.id.button)).perform(scrollTo(), click());
        onView(withId(R.id.editCpf)).check(matches(hasErrorText("Campo Vazio, preencha a informação")));
        onView(withId(R.id.editCpf)).perform(typeText("43924487898"), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.viewErroNascimentoInsert)).check(matches(isDisplayed()));
        onView(allOf(withText("Campo vazio, Selecione uma data")));

        onView(withId(R.id.textDataNascimento)).perform(scrollTo(), scrollTo(), scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2018, 5, 23));
        onView(withText("OK")).perform(click());

        //Teste campo cep vazio
        onView(withId(R.id.button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editCep)).check(matches(hasErrorText("Campo vazio, preencha a informação")));
        onView(withId(R.id.editCep)).perform(typeText("0819042"), closeSoftKeyboard());

        //Validação cep com acesso de internet indisponivel
        onView(withId(R.id.button)).perform(scrollTo(), click());
        onView(withId(R.id.editCep)).check(matches(hasErrorText("Campo Inválido, preencha a informação")));
        onView(withId(R.id.editCep)).perform(typeText("0"), closeSoftKeyboard());

        //validacao cep , aparelho sem internet
        onView(withId(R.id.button)).perform(scrollTo(), click());
        onView(withId(R.id.editCep)).check(matches(hasErrorText("Sem acesso a internet, preencha as informações")));

        //Validacao campo Logradouro vazio
        onView(withId(R.id.button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editLogradouro)).check(matches(hasErrorText("Campo vazio, preencha a informação")));
        onView(withId(R.id.editLogradouro)).perform(typeText("Rua abacatuaja"), closeSoftKeyboard());

        //Validacao campo bairro vazio
        onView(withId(R.id.button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editBairro)).check(matches(hasErrorText("Campo vazio, preencha a informação")));
        onView(withId(R.id.editBairro)).perform(typeText("Vila Itaim"), closeSoftKeyboard());

        //Validacao campo numero vazio
        onView(withId(R.id.button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editNumero)).check(matches(hasErrorText("Campo vazio, preencha a informação")));
        onView(withId(R.id.editNumero)).perform(typeText("833"), closeSoftKeyboard());

        //Validacao campo estado vazio
        onView(withId(R.id.button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editEstado)).check(matches(hasErrorText("Campo vazio, preencha a informação")));
        onView(withId(R.id.editEstado)).perform(typeText("Sao Paulo"), closeSoftKeyboard());

    }
}