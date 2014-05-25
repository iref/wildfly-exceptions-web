package cz.muni.exceptions.web.pages;

import com.google.common.collect.Ordering;
import cz.muni.exceptions.web.components.TicketOccurrenceLabel;
import cz.muni.exceptions.web.components.TicketsDataProvider;
import cz.muni.exceptions.web.db.TicketService;
import cz.muni.exceptions.web.model.Ticket;
import cz.muni.exceptions.web.model.TicketOccurrence;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;
import javax.inject.Inject;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Page shows listing of existing tickets.
 * 
 * @author Jan Ferko
 */
public class ExceptionsPage extends AbstractExceptionsPage {
    private static final long serialVersionUID = 1L;
    
    @Inject
    private TicketService ticketService;

    public ExceptionsPage(final PageParameters parameters) {
        super(parameters);                    
    }
    
    protected void onInitialize() {
        super.onInitialize();
                        
        DataView<Ticket> ticketsView = createTicketsView("tickets");            
        add(ticketsView);
        
        BootstrapAjaxPagingNavigator paginator = new BootstrapAjaxPagingNavigator("paginator", ticketsView);
        add(paginator);
    }

    private DataView<Ticket> createTicketsView(String id) {        
        DataView<Ticket> ticketsView = new DataView<Ticket>(id, new TicketsDataProvider(), 25) {
            @Override
            protected void populateItem(final Item<Ticket> item) {
                item.setModel(new CompoundPropertyModel<>(item.getModelObject()));
                
                Ticket ticket = item.getModelObject();
                item.add(new Label("id"));
                
                Link<Void> detailLink = createDetailMessageLink("detailLink", ticket.getId());
                detailLink.add(new Label("detailMessage"));
                item.add(detailLink);

                item.add(new Label("className"));
                item.add(new Label("ticketClass"));
                item.add(createLastOccurrenceLabel("latestOccurrence", item.getModelObject()));                
            }           
        };
        return ticketsView;    
    }
    
    private Link<Void> createDetailMessageLink(String id, final Long ticketId) {
        return new Link<Void>(id) {
            @Override
            public void onClick() {
                PageParameters params = new PageParameters();
                params.set(AbstractExceptionsPage.RequestParameters.TICKET_ID, ticketId);
                setResponsePage(ExceptionDetailPage.class, params);
            }
        };                
    }
    
    private DateLabel createLastOccurrenceLabel(String id, Ticket ticket) {
        TicketOccurrence latestOccurrence = Ordering.from(TicketOccurenceComparator.INSTANCE)
                .max(ticket.getOccurrences());                                       
        return new TicketOccurrenceLabel(id, latestOccurrence);
    }    
}
