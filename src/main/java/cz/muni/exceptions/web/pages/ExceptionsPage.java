package cz.muni.exceptions.web.pages;

import cz.muni.exceptions.web.db.TicketService;
import cz.muni.exceptions.web.model.Ticket;
import java.util.List;
import javax.inject.Inject;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class ExceptionsPage extends WebPage {
    private static final long serialVersionUID = 1L;
    
    @Inject
    private TicketService ticketService;

    public ExceptionsPage(final PageParameters parameters) {
        super(parameters);

        add(new Label("clock", "awesome"));
        List<Ticket> tickets = ticketService.getTickets();
        // TODO Add your page's components here
    }
}
