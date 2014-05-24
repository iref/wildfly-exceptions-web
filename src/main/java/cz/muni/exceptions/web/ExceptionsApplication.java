package cz.muni.exceptions.web;

import cz.muni.exceptions.web.pages.ExceptionDetailPage;
import cz.muni.exceptions.web.pages.ExceptionsPage;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see cz.muni.exceptions.Start#main(String[])
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
        
        mountPages();
    }
    
    /**
     * Maps page classes to urls.
     */
    private void mountPages() {
        mountPage("/exceptions", ExceptionsPage.class);
        mountPage("/exceptions/detail", ExceptionDetailPage.class);
    }
}
