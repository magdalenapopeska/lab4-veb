package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="events")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double popularityScore;
   // private Category cat;
    //private Review rewiev;

    @ManyToOne
    private Location location;

   /* public Event(String name, String description, double popularityScore, Category cat) {
        this.name = name;
        this.description=description;
        this.popularityScore=popularityScore;
        //this.cat=cat;
        this.id=(long)(Math.random()*1000);
       // this.rewiev=rewiev;

    }
*/
    public Event(String name, String description, double popularityScore, Location location) {
        this.name = name;
        this.description=description;
        this.popularityScore=popularityScore;
        this.id=(long)(Math.random()*1000);
        this.location=location;
        //this.rewiev=rewiev;
    }
}
