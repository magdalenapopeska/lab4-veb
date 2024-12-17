package mk.finki.ukim.mk.lab.web.controller;


import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    @GetMapping("/events")
    public String getEventsPage(@RequestParam(required = false) String error, @RequestParam(name = "locationId", required = false)Long locationId, Model model)
    {
        if(locationId != null)
        {
            model.addAttribute("events",eventService.findAllByLocationId(locationId));
        }
        else
        {
            model.addAttribute("events",eventService.listAll());
        }
        model.addAttribute("locations", locationService.findAll());
        return "listEvents";
    }

    @GetMapping("/events/add-form")
    public String getAddEventPage(Model model)
    {
        model.addAttribute("locations",locationService.findAll());
        return "add-event";
    }

    @GetMapping("/events/edit-form/{eventId}")
    public String getEditEventForm(@PathVariable (name="eventId") Long eventId, Model model)
    {
        model.addAttribute("locations",locationService.findAll());
        model.addAttribute("event",eventService.findEventById(eventId));
        return "add-event";
    }

    @PostMapping("/events/add-form")
    public String saveEvent(@RequestParam(name = "name") String name,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "popularityScore") double popularityScore,
                            @RequestParam(name = "locationId") long locationId)
    {
        eventService.saveEvent(name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @PostMapping("/events/edit-form/{eventId}")
    public String editEvent(@PathVariable(name = "eventId") Long eventId,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "popularityScore") double popularityScore,
                            @RequestParam(name = "locationId") long locationId)
    {
        eventService.editEvent(eventId,name,description,popularityScore,locationId);
        return "redirect:/events";
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable(name = "id") Long eventId)
    {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

   /* @GetMapping("/events/review/{eventId}")
    public String getReviewPage(@PathVariable (name="eventId") Long eventId, Model model)
    {
        model.addAttribute("event",eventService.findEventById(eventId));
        return "get-review";
    }

    @PostMapping("/events/review/{eventId}")
    public String saveReview(@PathVariable(name = "eventId") Long eventId,
                            @RequestParam (name="id") Integer id,
                            @RequestParam(name = "comment") String comment,
                            @RequestParam(name = "grade") Integer grade)
    {
       eventService.saveReview(eventId, comment, grade);
        return "redirect:/events";
    }*/
}
