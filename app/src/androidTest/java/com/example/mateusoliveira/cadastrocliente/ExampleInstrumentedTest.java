package com.example.mateusoliveira.cadastrocliente;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import com.example.mateusoliveira.cadastrocliente.Mvp.CadastroCliente.ClienteCadastroView;
import com.google.android.gms.common.api.Api;

import net.vidageek.mirror.dsl.Mirror;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<ClienteCadastroView> mActivityRule = new ActivityTestRule<>(ClienteCadastroView.class);

    @Test
    public void testInsertClientDadosValidos() {
        onView(withId(R.id.editNomeCompleto)).perform(typeText("Mateus"), closeSoftKeyboard());
        onView(withId(R.id.editCpf)).perform(typeText("43924487898"), closeSoftKeyboard());
        onView(withId(R.id.textDataNascimento)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2018, 5, 23));
        onView(withText("OK")).perform(click());
//        onView(withId(R.id.textDataNascimento)).check(matches(withText("23/9/2018")));
        onView(withId(R.id.editCep)).perform(typeText("08190420"), closeSoftKeyboard());
        onView(withId(R.id.editNumero)).perform(pressImeActionButton(), closeSoftKeyboard());
//        onView((withId(R.id.loading ))).check(matches(isDisplayed()));
//        onView(withId(R.id.editLogradouro)).perform(typeText("Rua 235"));
//        onView(withId(R.id.editBairro)).perform(typeText("vila itaim"));
//        onView(withId(R.id.editEstado)).perform(typeText("São Paulo"));
        onView(withId(R.id.editNumero)).perform(typeText("899"), closeSoftKeyboard());
        onView(withId(R.id.editComplemento)).perform(typeText("Bloco a"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }
}