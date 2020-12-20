package io.github.agamgk.hello;

import io.github.agamgk.lang.Lang;
import io.github.agamgk.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

// Sklasa Service ktora będzie odpowiedzialna za przygotowanie powitania (zwróci powiadomienie)
class HelloService {

    static final String FALLBACK_NAME = "world";
    //wartosc domyslna
    static final Lang FALLBACK_LANG = new Lang(1, "Hello","en");
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }
    HelloService(LangRepository repository) {
        this.repository = repository;
    }
    // Bardziej szczegółowa wersja metody prepareGreeting
    String prepareGreeting(String name, Integer langId) {
        //zamian stringa na liczbe lub przypisanie id z FALLBACK_LANG
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome;
    }
}
