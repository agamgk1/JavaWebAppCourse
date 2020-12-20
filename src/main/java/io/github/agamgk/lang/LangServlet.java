package io.github.agamgk.lang;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//klasa obsługująca protokół Http
//HttpServlet - dependency z mavena
//Servlet jest inicjowany przez Jetty
//adnotacja do konfiguracji servletu
@WebServlet(name = "Lang",urlPatterns = {"/api/langs"})
public class LangServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(LangServlet.class);
    private LangService service;
    //pole z jackson-databind dependency
    private ObjectMapper mapper;

    //inicjalizacja pola HelloService w konstruktorze
    LangServlet(LangService service, ObjectMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @SuppressWarnings("unused")
    public LangServlet() {
        this(new LangService(), new ObjectMapper());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request with parameters " + req.getParameterMap());
        //header mówiący że przesyłamy w ciele jasona
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(),service.findAll());

    }
}

