package cz.muni.exceptions.web.pages;

import cz.muni.exceptions.web.pages.ExceptionsPage;
import cz.muni.exceptions.web.AbstractExceptionsTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class ExceptionsPageTest extends AbstractExceptionsTest {    
    
    @Deployment
    public static Archive<?> getDeployment() {
        return createDeployment();
    }

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(ExceptionsPage.class);

        //assert rendered page class
        tester.assertRenderedPage(ExceptionsPage.class);
    }
}
