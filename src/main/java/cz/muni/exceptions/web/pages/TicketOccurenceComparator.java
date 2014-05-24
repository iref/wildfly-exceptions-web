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

import cz.muni.exceptions.web.model.TicketOccurrence;
import java.sql.Timestamp;
import java.util.Comparator;

/**
 *
 * @author Jan Ferko
 */
public class TicketOccurenceComparator implements Comparator<TicketOccurrence> {
    public static final TicketOccurenceComparator INSTANCE = new TicketOccurenceComparator();
    
    private TicketOccurenceComparator() {        
    }

    @Override
    public int compare(TicketOccurrence left, TicketOccurrence right) {
        if (left == right) {
            return 0;
        } else if (left == null) {
            return -1;
        } else if (right == null) {
            return 1;
        }
        
        Timestamp leftTimestamp = left.getTimestamp();
        Timestamp rightTimestamp = right.getTimestamp();
                
        return leftTimestamp == null ? -1 : leftTimestamp.compareTo(rightTimestamp);
    }
    
}
