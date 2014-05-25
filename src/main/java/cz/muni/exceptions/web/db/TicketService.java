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

/**
 * Interface provides access to stored tickets.
 *
 * @author Jan Ferko
 */
public interface TicketService {

    /**
     * Retrieves ticket with given id.
     *
     * @param id identifier of ticket
     * @return ticket with given id or {@code null}
     * @throws java.lang.IllegalArgumentException if identifier is {@code null}
     */
    Ticket getTicket(Long id);

    /**
     * Retrieves all tickets from storage.
     *
     * @return list of all stored tickets
     */
    List<Ticket> getTickets();

    /**
     * Retrieves {@code count} tickets starting from {@code first} ticket.
     *
     * @param first index of first ticket, that should be retrieved
     * @param count number of tickets, that should be retrieved
     * @return list of tickets
     */
    List<Ticket> getTickets(int first, int count);

    /**
     * Returns total number of stored tickets.
     *
     * @return total number of tickets
     */
    int count();
            
}
