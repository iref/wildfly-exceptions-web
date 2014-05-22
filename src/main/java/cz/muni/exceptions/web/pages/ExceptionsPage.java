package cz.muni.exceptions.web.pages;

import cz.muni.exceptions.web.db.TicketService;
import cz.muni.exceptions.web.model.Ticket;
import java.util.List;
import javax.inject.Inject;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;

public class ExceptionsPage extends WebPage {
    private static final long serialVersionUID = 1L;
    
    @Inject
    private TicketService ticketService;

    public ExceptionsPage(final PageParameters parameters) {
        super(parameters);
        
        List<Ticket> tickets = ticketService.getTickets();
        ListView<Ticket> ticketsView = new ListView<Ticket>("tickets", tickets) {            
            @Override
            protected void populateItem(ListItem<Ticket> item) {
                item.setModel(new CompoundPropertyModel<>(item.getModelObject()));
                item.add(new Label("detailMessage"));
                item.add(new Label("ticketClass"));
            }
        };
        add(ticketsView);
    }
}
