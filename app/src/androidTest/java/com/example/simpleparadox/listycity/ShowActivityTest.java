package com.example.simpleparadox.listycity;

import android.app.Activity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ShowActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<MainActivity> rule =

            new ActivityTestRule<>(MainActivity.class, true, true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }
    /**
     * Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }
    /**
     * Add a city to the listview and check the city name using assertTrue
     * Clear all the cities from the listview and check again with assertFalse
     */
    @Test
    public void checkSwitch(){
        ListView listView = (ListView) solo.getView(R.id.city_list);
        View view = listView.getChildAt(1);
        solo.clickOnView(view);
        solo.assertCurrentActivity("Switch failed", ShowActivity.class);
    }

    @Test
    public void checkExtra(){
        ListView listView = (ListView) solo.getView(R.id.city_list);
        View view = listView.getChildAt(1);
        solo.clickOnView(view);
        TextView textView = (TextView) solo.getView(R.id.textView);
        String cityname = textView.getText().toString();
        assertEquals("Vancouver", cityname);
    }
    @Test
    public void checkBack(){
        ListView listView = (ListView) solo.getView(R.id.city_list);
        View view = listView.getChildAt(1);
        solo.clickOnView(view);

        Button button = (Button) solo.getView(R.id.backbutton);
        solo.clickOnView(button);
        solo.assertCurrentActivity("Back Button failed",  MainActivity.class);
    }



/**
 * Close activity after each test
 * @throws Exception
*/
     @After
     public void tearDown() throws Exception{
     solo.finishOpenedActivities();
     }
 }