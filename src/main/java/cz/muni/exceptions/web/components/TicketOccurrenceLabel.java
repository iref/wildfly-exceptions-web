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

import cz.muni.exceptions.web.model.TicketOccurrence;
import java.util.Date;
import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Label, that shows formatted ticket occurrence.
 * 
 * @author Jan Ferko
 */
public class TicketOccurrenceLabel extends DateLabel {
    
    /** Converter for ticket occurrence timestamp. */
    private static final DateConverter CONVERTER = new PatternDateConverter("yyyy-MM-dd HH:mm:ss", true);

    /**
     * Constructor, that creates new instance of label.
     * 
     * @param id label identifier
     * @param ticketOccurrence ticket occurrence, that should be shown in label
     */
    public TicketOccurrenceLabel(String id, TicketOccurrence ticketOccurrence) {
        super(id, createDateModel(ticketOccurrence), CONVERTER);
    }
    
    /**
     * Prepares ticket occurrence timestamp model.
     * 
     * @param ticketOccurrence ticket occurrence, which model is created for.
     * @return model with occurrence timestamp
     */
    private static IModel<Date> createDateModel(TicketOccurrence ticketOccurrence) {
        Date latestOccurrenceDate = new Date(ticketOccurrence.getTimestamp().getTime());
        return Model.of(latestOccurrenceDate);        
    }
    
}
