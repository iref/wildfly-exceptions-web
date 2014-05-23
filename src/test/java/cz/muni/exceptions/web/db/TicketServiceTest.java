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
import cz.muni.exceptions.web.model.Ticket;
import cz.muni.exceptions.web.model.TicketClass;
import cz.muni.exceptions.web.model.TicketOccurrence;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jan Ferko
 * @sa.date 2014-05-20T17:12:18+0100
 */
public class TicketServiceTest extends AbstractExceptionsTest {
    
    @Inject 
    private TicketService ticketService;
    
    @Inject
    private EntityManager entityManager;
    
    @Deployment
    public static Archive<?> getDeployment() {
        return createDeployment();
    }
    
    @Before
    @Override
    public void setUp() throws NamingException {
        super.setUp();
        cleanDatabase();
        insertTickets();
    }
    
    @After
    @Override
    public void tearDown() {
        super.tearDown();
        cleanDatabase();
    }

    @Test//(expected = IllegalArgumentException.class)
    public void testGetTicketForNullId() {
//        Assert.assertNotNull(ticketService);   
//        ticketService.getTicket(null);
    }
    
    @Test
    public void testGetTickets() {
//        List<Ticket> tickets = ticketService.getTickets();
//        Assert.assertNotNull(tickets);
//        Assert.assertEquals(2, tickets.size());
    }

    private void insertTickets() {
        TicketOccurrence to = new TicketOccurrence();
        to.setTimestamp(new Timestamp(new Date().getTime()));
        List<TicketOccurrence> tos = Arrays.asList(to);
        Ticket t = new Ticket("Super detail message", "stacktrace", TicketClass.DATABASE, tos);
        
        entityManager.getTransaction().begin();
        entityManager.persist(t);        
        entityManager.getTransaction().commit();
    }
    
    private void cleanDatabase() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Ticket t").executeUpdate();
        entityManager.getTransaction().commit();
    }
}
