package com.example.mateusoliveira.cadastrocliente;

import android.support.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;

import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class Robot {

    public Robot insertClient(){
        onView(withId(R.id.editNomeCompleto)).perform(typeText("Mateus"), closeSoftKeyboard());
        onView(withId(R.id.editCpf)).perform(typeText("43924487898"), closeSoftKeyboard());
        onView(withId(R.id.textDataNascimento)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2018, 5, 23));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.editCep)).perform(typeText("08190420"), closeSoftKeyboard());
        onView(withId(R.id.editNumero)).perform(typeText("899"), closeSoftKeyboard());
        onView(withId(R.id.editComplemento)).perform(typeText("Bloco a"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        return this;
    }
}
