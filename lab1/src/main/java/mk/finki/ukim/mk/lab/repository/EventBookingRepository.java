package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {

    List<EventBooking> eventBookingList = new ArrayList<>();

    public void saveEventBooking(EventBooking eventBooking) {
        eventBookingList.add(eventBooking);
    }
}
