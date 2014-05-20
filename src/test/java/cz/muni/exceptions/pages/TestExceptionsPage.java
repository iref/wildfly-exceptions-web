package cz.muni.exceptions.pages;

import cz.muni.exceptions.WicketApplication;
import cz.muni.exceptions.pages.ExceptionsPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestExceptionsPage {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
    }

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(ExceptionsPage.class);

        //assert rendered page class
        tester.assertRenderedPage(ExceptionsPage.class);
    }
}
