package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;
import com.google.api.services.calendar.model.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.getCalendarService;

@Controller("/kalendaryo")
public class EventController {

    @GetMapping("/update")
    public void updateEvent() throws IOException {
        com.google.api.services.calendar.Calendar service =
                getCalendarService();
        Event event = service.events().get("primary", "7nhdmehd85ogf46u0sdcpmfq7h").execute();
        event.setSummary("Troollolloo!!!");
        Event updatedEvent = service.events().update("primary", event.getId(), event).execute();
    }
}
