package io.github.agamgk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//repozytorium
class LangRepository {

    //Optional dzięki temu będzie wiadomo że coś może się nie udać w czasie wywoływania metody
    Optional<Lang> findById(Integer id) {
        //otwiera nowa sesje kominukacji z baza danych
       var session = HibernateUtil.getSessionFactory().openSession();
       //tworzy nowa tranzakcje
        var transaction = session.beginTransaction();
        //wyciagniecie instancje Lang dla konkretnego id
        var result = session.get(Lang.class, id);
        //koniec tranzakcji
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
