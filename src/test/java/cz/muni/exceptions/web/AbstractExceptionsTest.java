/*
 * Copyright 2014 Jan Ferko.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cz.muni.exceptions.web;

import java.io.File;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.NamingException;

import cz.muni.exceptions.web.db.TicketServiceImpl;
import cz.muni.exceptions.web.model.Ticket;
import org.apache.wicket.cdi.AutoConversation;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author Jan Ferko
 */
@RunWith(Arquillian.class)
public abstract class AbstractExceptionsTest {

    protected WicketTester tester;
    
    @Inject
    protected BeanManager beanManager;
    
    public static WebArchive createDeployment() {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
                .resolve("org.apache.wicket:wicket-cdi:6.15.0")
                .withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(AutoConversation.class, AbstractExceptionsTest.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml")
                .addAsLibraries(libs);
    }
    
    @Before
    public void setUp() throws NamingException {
        tester = new WicketTester(getWebApplication());                
        new CdiConfiguration(beanManager).configure(tester.getApplication());        
    }
    
    @After
    public void tearDown() {
        tester.getApplication().internalDestroy();
    }

    protected WebApplication getWebApplication() {
        return new ExceptionsApplication();
    }
}
