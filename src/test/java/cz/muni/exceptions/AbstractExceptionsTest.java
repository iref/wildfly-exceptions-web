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

package cz.muni.exceptions;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.wicket.cdi.AutoConversation;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author Jan Ferko
 * @sa.date 2014-05-20T14:59:41+0100
 */
@RunWith(Arquillian.class)
public abstract class AbstractExceptionsTest {

    protected WicketTester tester;
    
    @Inject
    protected BeanManager beanManager;
    
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(AutoConversation.class, AbstractExceptionsTest.class)                
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
