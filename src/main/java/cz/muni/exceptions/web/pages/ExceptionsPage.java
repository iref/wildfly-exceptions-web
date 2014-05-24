package cz.muni.exceptions.web.pages;

import com.google.common.collect.Ordering;
import cz.muni.exceptions.web.components.TicketOccurrenceLabel;
import cz.muni.exceptions.web.db.TicketService;
import cz.muni.exceptions.web.model.Ticket;
import cz.muni.exceptions.web.model.TicketOccurrence;
import java.util.List;
import javax.inject.Inject;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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
        
        // add ticket list
        List<Ticket> tickets = ticketService.getTickets();
        ListView<Ticket> ticketsView = createTicketsView(tickets);            
        add(ticketsView);    
    }

    private ListView<Ticket> createTicketsView(List<Ticket> tickets) {
        ListView<Ticket> ticketsView = new ListView<Ticket>("tickets", tickets) {
            @Override
            protected void populateItem(ListItem<Ticket> item) {
                item.setModel(new CompoundPropertyModel<>(item.getModelObject()));
                item.add(new Label("id"));
                item.add(new Label("detailMessage"));
                item.add(new Label("ticketClass"));
                item.add(createLastOccurrenceLabel("latestOccurrence", item.getModelObject()));                
            }
        };
        return ticketsView;    
    }
    
    private DateLabel createLastOccurrenceLabel(String id, Ticket ticket) {
        TicketOccurrence latestOccurrence = Ordering.from(TicketOccurenceComparator.INSTANCE)
                .max(ticket.getOccurrences());                                       
        return new TicketOccurrenceLabel(id, latestOccurrence);
    }        
}
