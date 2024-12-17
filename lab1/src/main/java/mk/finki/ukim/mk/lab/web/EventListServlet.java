package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventListServlet", urlPatterns = "")
@AllArgsConstructor
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String search = req.getParameter("search");
        String rating = req.getParameter("rating");

        if(search != null && search.equals("")) {
            search = null;
        }

        if(rating != null && rating.equals("")) {
            rating = null;
        }

        if (search != null && rating == null) {
            context.setVariable("events", eventService.searchEventsByText(search));
        } else if (search == null && rating != null) {
            context.setVariable("events", eventService.searchEventsByRating(Double.parseDouble(rating)));
        } else if (search != null && rating != null) {
            context.setVariable("events", eventService.searchEventsByTextAndRating(search, Double.parseDouble(rating)));
        } else {
            context.setVariable("events", eventService.listAll());
        }

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
