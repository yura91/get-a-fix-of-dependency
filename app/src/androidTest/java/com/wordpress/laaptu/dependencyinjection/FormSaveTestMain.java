package com.wordpress.laaptu.dependencyinjection;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.wordpress.laaptu.dependencyinjection.data.DbManager;
import com.wordpress.laaptu.dependencyinjection.model.User;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.wordpress.laaptu.dependencyinjection.model.User.getUser;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Created by laaptu on 9/21/16.
 */
@RunWith(AndroidJUnit4.class) public class FormSaveTestMain {

  @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  @Test public void editAndSaveTest() {
    User user = new User();
    user.name = "Terminator";
    user.phone = "99900000";
    user.address = "From future";
    user.email = "terminator@arnold.com";
    if (mainActivityActivityTestRule.getActivity().dataService.getUser() != null) {
      onView(withId(R.id.menu_edit)).check(matches(isDisplayed()));
      onView(withId(R.id.menu_edit)).perform(click());
    }

    onView(withId(R.id.menu_save)).check(matches(isDisplayed()));

    onView(withId(R.id.txt_name)).perform(clearText(), typeText(user.name));
    onView(withId(R.id.txt_email)).perform(clearText(), typeText(user.email));
    onView(withId(R.id.txt_address)).perform(clearText(), typeText(user.address));
    onView(withId(R.id.txt_phone)).perform(clearText(), typeText(user.phone), closeSoftKeyboard());

    onView(withId(R.id.menu_save)).perform(click());

    User storedUser = mainActivityActivityTestRule.getActivity().dataService.getUser();
    assertEquals(storedUser.toString(), user.toString());
    onView(withId(R.id.menu_edit)).check(matches(isDisplayed()));
    onView(withId(R.id.info_txt)).check(
        matches(allOf(isDisplayed(), containsTextWithValue(user.name))));
  }

  private Matcher<View> containsTextWithValue(final String text) {
    return new TypeSafeMatcher<View>() {
      @Override protected boolean matchesSafely(View item) {
        if (item instanceof TextView) {
          return ((TextView) item).getText().toString().contains(text);
        }
        return false;
      }

      @Override public void describeTo(Description description) {

      }
    };
  }
}
