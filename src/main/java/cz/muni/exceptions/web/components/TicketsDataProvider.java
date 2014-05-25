/*
 * Copyright 2014 johnny.
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

package cz.muni.exceptions.web.components;

import cz.muni.exceptions.web.db.TicketService;
import cz.muni.exceptions.web.model.Ticket;
import java.util.Iterator;
import javax.inject.Inject;
import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * Provider of tickets for data presenter.
 *
 * @author Jan Ferko
 */
public class TicketsDataProvider implements IDataProvider<Ticket> {
    
    @Inject
    /** Service for fetching tickets. */
    private TicketService ticketService;

    /**
     * Constructor creates new instance of provider and injects TicketService to itself.
     */
    public TicketsDataProvider() {
        CdiContainer.get().getNonContextualManager().inject(this);
    }

    @Override
    public Iterator<? extends Ticket> iterator(long first, long count) {
        return ticketService.getTickets(Long.valueOf(first).intValue(), 
                Long.valueOf(count).intValue())
                .iterator();
    }

    @Override
    public long size() {
        return ticketService.count();
    }

    @Override
    public IModel<Ticket> model(Ticket object) {
        return new CompoundPropertyModel<>(object);
    }

    @Override
    public void detach() {        
    }
    
}
