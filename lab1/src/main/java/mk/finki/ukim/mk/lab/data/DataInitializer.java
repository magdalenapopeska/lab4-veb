package mk.finki.ukim.mk.lab.data;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final LocationRepository locationRepository;

    @PostConstruct
    public void initializeData() {
        for (int i = 0; i < 2; i++) {
            Location location = new Location("name" + i, "address" + i, "capacity" + i, "description" + i);
            locationRepository.save(location);
        }
    }

}
