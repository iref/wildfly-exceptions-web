package cz.muni.exceptions.web;

import cz.muni.exceptions.web.pages.ExceptionDetailPage;
import cz.muni.exceptions.web.pages.ExceptionsPage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 *
 */
public class ExceptionsApplication extends WebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return ExceptionsPage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        // add your configuration here
        
        try {
            InitialContext context = new InitialContext();
        
            BeanManager beanManager = (BeanManager) context.lookup("java:comp/BeanManager");
            new CdiConfiguration(beanManager).configure(this); 
        } catch (NamingException ex) {
            throw new IllegalStateException("BeanManager was not found in JNDI", ex);
        }                        
        
        final BootstrapSettings bootstrapSettings = new BootstrapSettings();        
        bootstrapSettings.setAutoAppendResources(false);
        Bootstrap.install(this, bootstrapSettings);
        
        mountPages();
    }
    
    /**
     * Maps page classes to urls.
     */
    private void mountPages() {
        mountPage("/list", ExceptionsPage.class);
        mountPage("/exceptions/detail", ExceptionDetailPage.class);
    }
}
