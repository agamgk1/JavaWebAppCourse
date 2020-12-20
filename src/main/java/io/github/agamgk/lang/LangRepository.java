package io.github.agamgk.lang;

import io.github.agamgk.HibernateUtil;

import java.util.List;
import java.util.Optional;

//repozytorium
public class LangRepository {

    List<Lang> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        // pozwala odczytac wszystkie wpisy
        var result = session.createQuery("from Lang", Lang.class).list();
        transaction.commit();
        session.close();
        return result;
    }
    //Optional dzięki temu będzie wiadomo że coś może się nie udać w czasie wywoływania metody
    public Optional<Lang> findById(Integer id) {
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
