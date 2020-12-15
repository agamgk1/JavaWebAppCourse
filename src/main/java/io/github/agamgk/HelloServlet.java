package io.github.agamgk;

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
@WebServlet(name = "Hello",urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    //parametry do wpisu w adresie URL
    public static final String NAME_PARAM = "name";
    public static final String LANG_PARAM = "lang";
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private HelloService service;

    //inicjalizacja pola HelloService w konstruktorze
    HelloServlet(HelloService service) {
        this.service = service;
    }
    //Jeżeli tworzymy własny konstruktor trzeba równiez dodać konstruktor domyślny, bezparamrtowy. Server Cointainer - Jetty tego wymaga
    // W konstruktorze wywoływany jest inny konstruktor
    @SuppressWarnings("unused")
    public HelloServlet() {
        this(new HelloService());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request with parameters " + req.getParameterMap());
        // przypsanie parametru z klasy HelloService (parametr bedzie wpisany w URL)
        var name = req.getParameter(NAME_PARAM);
        var lang = req.getParameter(LANG_PARAM);
        resp.getWriter().write(service.prepareGreeting(name, lang));
    }
}

