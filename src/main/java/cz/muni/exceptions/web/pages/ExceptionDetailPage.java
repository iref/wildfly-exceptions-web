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

package cz.muni.exceptions.web.pages;

import cz.muni.exceptions.web.components.TicketOccurrenceLabel;
import cz.muni.exceptions.web.db.TicketService;
import cz.muni.exceptions.web.model.Ticket;
import cz.muni.exceptions.web.model.TicketOccurrence;
import javax.inject.Inject;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

/**
 *
 * @author Jan Ferko
 */
public class ExceptionDetailPage extends AbstractExceptionsPage {
    
    @Inject
    private TicketService ticketService;

    public ExceptionDetailPage(PageParameters params) {
        super(params);
        IModel<Ticket> pageModel = preparePageModel(params);
        setDefaultModel(pageModel);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        add(new Label("detailMessage"));
        add(new Label("stackTrace"));
        add(new Label("ticketClass"));
        add(new ListView<TicketOccurrence>("occurrences") {

            @Override
            protected void populateItem(ListItem<TicketOccurrence> item) {                
                item.add(new TicketOccurrenceLabel("occurrence", item.getModelObject()));
            }
            
        });
                
        add(new Link<Void>("back") {            
            @Override
            public void onClick() {
                setResponsePage(ExceptionsPage.class);
            }
        });
    }        

    private IModel<Ticket> preparePageModel(PageParameters params) {
        StringValue idValue = params.get("ticketId");
        if (idValue.isEmpty()) {
            error("Ooops!! It's not possible to show ticket detail, because id of ticket wasn't provided");
            throw new RestartResponseException(ExceptionsPage.class);            
        }
        
        Long id = null;
        try {
            id = Long.parseLong(idValue.toString());
            Ticket ticket = ticketService.getTicket(id);
            return new CompoundPropertyModel<Ticket>(ticket);
        } catch (NumberFormatException ex) {
            error("Oopps!! Ticket does not exists.");
            throw new RestartResponseException(ExceptionsPage.class);
        } catch (Exception ex) {
            error("Ooppss!! We have some technical difficulties. Please try later.");
            return Model.of();
        }    
    }
    
}
