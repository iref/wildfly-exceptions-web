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

package cz.muni.exceptions.web.db;

import cz.muni.exceptions.web.AbstractExceptionsTest;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jan Ferko
 * @sa.date 2014-05-20T16:32:42+0100
 */
public class EntityManagerFactoryProducerTest extends AbstractExceptionsTest {
    
    @Inject
    private EntityManagerFactory emf;
    
    @Deployment
    public static Archive<?> getDeployment() {
        return createDeployment().addAsResource("META-INF/persistence.xml");
    }
    
    @Test
    public void testEntityManagerFactoryIsProduced() {
        Assert.assertNotNull(emf);
    }

}
