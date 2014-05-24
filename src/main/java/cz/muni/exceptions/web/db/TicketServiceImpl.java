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

import cz.muni.exceptions.web.model.Ticket;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jan Ferko
 * @sa.date 2014-05-20T17:06:30+0100
 */
@RequestScoped
public class TicketServiceImpl implements TicketService {
    
    private final EntityManager em;
    
    @Inject
    public TicketServiceImpl(EntityManager em) {
        if (em == null) {
            throw new IllegalArgumentException("[EntityManager] is required and should not be null.");
        }
        this.em = em;
    }

    @Override
    public Ticket getTicket(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("[Id] is required and should not be null.");
        }
        
        return em.find(Ticket.class, id);
    }

    @Override
    public List<Ticket> getTickets() {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t", Ticket.class);
        return query.getResultList();
    }
    
    
    @Override
    public List<Ticket> getTickets(int first, int count) {
        final TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t", Ticket.class);
        return query.setFirstResult(first)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public int count() {
        Long count = em.createQuery("SELECT count(*) FROM Ticket t", Long.class)
                .getSingleResult();
        return count.intValue();
    }
}
