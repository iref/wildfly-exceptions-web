
package cz.muni.exceptions.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jan Ferko
 * @sa.date 2014-04-15T05:57:20+0100
 */
@Entity
@Table(name = "ticket_occurrences")
public class TicketOccurrence implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Timestamp occurenceTimestamp;
        
    public TicketOccurrence() {        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return occurenceTimestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.occurenceTimestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TicketOccurrence)) {
            return false;
        }
        
        final TicketOccurrence other = (TicketOccurrence) obj;
        return Objects.equals(this.id, other.id);
    }
    
    @Override
    public String toString() {
        return String.format("TicketOccurence{id=%1$s, timestamp=%2$s", id, occurenceTimestamp);
    }
    
}
