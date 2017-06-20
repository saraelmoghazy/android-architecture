package com.example.android.architecture.blueprints.todoapp.pocs;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.poc.PocActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by user on 6/18/2017.
 */

@RunWith(AndroidJUnit4.class)
public class PocsScreenTest {

    @Rule
    public ActivityTestRule<PocActivity> pocActivityActivityTestRule = new ActivityTestRule<PocActivity>(PocActivity.class);

    private Matcher<View> withItemText(final String itemText) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View item) {
                return Matchers.allOf(
                        isDescendantOfA(isAssignableFrom(ListView.class)),
                        withText(itemText)).matches(item);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }




//    @Test
//    public void showRemotePocs() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext());
//        onView(withId(R.id.menu_remote)).perform(click());
//        onView(withItemText("delectus aut autem")).check(matches(isDisplayed()));
//
//    }


//    @Test
//    public void showLocalPocs() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext());
//        onView(withId(R.id.menu_local)).perform(click());
//        onView(withItemText("title1")).check(matches(isDisplayed()));
//    }


//    @Test
//    public void showMessageOnPocClick() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext());
//        onView(withId(R.id.menu_remote)).perform(click());
//        onView(withItemText("delectus aut autem")).perform(click());
//        onView(withText("Selected Item : delectus aut autem")).check(matches(isDisplayed()));
//
//    }

}
